1.一个EventLoopGroup当中会包含一个或多个EventLoop
2.一个EventLoop在它的整个生命周期当中都只会与唯一一个Thread进行绑定
3.所有由EventLoop所处理的各种IO事件都将在它所关联的那个Thread上进行处理
4.一个Channel在它的整个生命周期中只会注册在一个EventLoop上
5.一个EventLoop在运行过程当中会被分配给一个或者多个Channel


结论：
在netty中，Channel的实现一定是线程安全的；基于此，我们可以存储一个Channel的引用，在需要向远程端点发送数据时，通过这个引用来调用Channel相应的方法；
即便是很多线程都在使用它也不会出现多线程问题；而且消息一定是顺序发送出去（channel回吧发送数据以任务形式提交到对应的EventLoop关联的Thread上处理）

在进行业务开发中，不要将长时间的耗时任务放入到EventLoop的执行队列中(会由绑定的那个Thread执行)，因为它会一直阻塞该线程所对应的所以Channel上的其他执行任务，应该使用一个专门的业务线程池
   1.在ChannelHandler回调方法中自定义线程池处理业务
   2.借助netty提供的向ChannelPipeline添加ChannelHandler调用的addLast方法传入EventExecutorGroup
   
   
JDK提供的Future只能通过手工方式检查执行结果，而且这个操作是阻塞的；
netty则使用ChannelFutur对其进行了增强，通过ChannelFutureListener以回调的方式来获取执行结果，去除了手工检查阻塞的操作；
需要注意的是：ChannelFutureListener的operationComplete方法是由IO线程执行，因此这里不要执行耗时操作，或者使用线程池来完成耗时操作


发送消息方式：
1、写到channel中（channel.writeAndFlash）
2、写到与ChannelHandler关联的ChannelHandlerContext中（cxt.writeAndFlash）
区别：channel方式 消息会从ChannelPipleline的末尾开始流动；ChannelHandlerContext方式消息将从ChannelPipleline中的下一个ChannelHandler开始流动。
结论：1、ChannelHandlerContext与ChannelHandler的关联关系永远不会改变，因此对其缓存没有任何问题。
      2、对与Channel的同名方法来说，ChannelHandlerContext的方法将产生更短的事件流，所以在可能的情况下利用这个特性来提升应用性能。
      
      
      
使用Nio进行文件读取步骤
1、从FileInputStream对象获取Channel 
2、创建buffer
3、将数据从Channel中读取到Buffer对象中
 
Buffer：
0 <= mark <= position <= limit <= capacity 

flip()方法  
1、将limit设为当前的position
2、将position设为0

clear()
1、将limit值设为capacity
2、将position设为0

campact()
1、将所以未读数据复制到buffer起始位置
2、将position设为最后一个未读数据后面
3、将limit设为capatacity
4、现在buffer准备好了，但是不会覆盖未读的数据


ByteBuffer    缓冲区类型  ByteBuffer是NIO里用得最多的Buffer，它包含两个实现方式：HeapByteBuffer是基于Java堆的实现，而DirectByteBuffer则使用了unsafe的API进行了堆外的实现
1、heap buffer
2、direct buffer

![ByteBuffer 索引图解](https://note.youdao.com/yws/api/personal/file/373A7260DA514F7B965F2CDEA423281B?method=getImage&version=3630&cstk=_orpzSS_)

Netty  ByteBuf所提供的3种缓冲区类型
![ByteBuf 索引图解](https://note.youdao.com/yws/api/personal/file/42AA5851B10F4190855421E3E9E60A1F?method=getImage&version=3642&cstk=_orpzSS_)
1、heap buffer
2、direct buffer
3、composite buffer

heap buffer (堆缓冲区)
最常用的类型，ByteBuf将数据存储到JVM的堆空间中，并且将实际的数据存放到byte array中来实现。
优点：由于数据是存在JVM的堆中，因此可以快速的创建与快速的释放，并且它提供了直接访问内部字节数组的方法。
缺点：每次读写数据时，都需将数据复制到直接缓冲区中再进行网络传输（socket发送数据到网络上都是通过直接缓冲区来发送的）

DirectByteBuf （直接缓冲区）

在JVM堆之外直接分配内存空间，直接缓冲区占用并不会堆的空间，因为它是由操作系统在本地内存进行的数据分配

优点：在使用Socket进行数据传递时，性能非常好，因为数据直接位于操作系统的本地内存中，所以不需要才能从JVM将数据复制到直接缓冲区中，性能很好
缺点：因为DirectByteBuf是直接在操作系统内存中，所以内存空间的分配与释放要比堆空间更复杂，而且速度要慢些

Netty通过提供内存池来解决DirectByteBuf这个问题，直接缓冲区并不支持通过字节数组的方式来访问数据
对于后端的业务消息的编解码来说，推荐使用HeapByteBuf；对于IO通信线程在读写缓冲区时，推荐使用DirectByteBuf

Composite Buffer (复合缓冲区)
类似一个缓冲区容器，可以加入HeapByteBuf和DirectByteBuf


NIO的ByteBuffer与Netty的ByteBuf 差异对比
1、Netty的ByteBuf采用了读写索引分离的策略（readerIndex和writeIndex），一个初始化的ByteBuf的readerIndex和writerIndex为0
2、ByteBuf的读索引不可以大于写索引
3、对于ByteBuf的任何读写操作都会分别单独维护读索引与写索引，maxCapacity最大容量是Integer.MAX_VALUE。

NIO的ByteBuffer缺点
1、final byte[] hb ; //这是ByteBuffer对象用于存储数据的对象声明;因为final修饰，也就是长度固定不变，一旦分配好后不能动态扩容和收缩；而且在待存储的数据字节很大
时就有极可能出现IndexOutOfBoundsException。如果要预防这个异常，那就需要在存储之前完全确定存储字节的大小，如果ByteBuffer的空间不足，我们只有一个解决方案：创建
一个全新的ByteBuffer对象，然后再将之前的ByteBuffer中的数据复制过去，这一切操作需要由开发者自己来手动完成。
2、ByteBuffer只使用一个position指针来标识位置信息，在进行读写切换时就需要调用flip()方法或者是rewind()方法，使用不方便

Netty的ByteBuf的优点
1、存储字节的数组是动态的，其最大默认值是Integer.MAX_VALUE。这里的动态性体现在write()方法中，write()在执行时会判断buffer容量，如果不足则自动扩容。
2、ByteBuf的读写索引完全是分开的，使用起来很方便。



AtomicIntegerFieldUpdater总结   也可以用AtomicInteger
1、更新器更新的必须是int类型变量，不能是其包装类型
2、更新器更新的必须是volatile类型变量，确保线程之间共享变量时的立即可见性
3、变量不能是static的，必须是实例变量，因为Unsafe。objectFieldOffset()方法不支持静态变量（CAS操作本质上是通过对象实例的偏移量来直接进行赋值）
4、更新器只能修改它可见范围内的变量，因为更新器是通过反射来得到这个变量，如果变量不可见就会报错

如果要更新的变量是包装类型，那么可以使用AtomicReferenceFieldUpdater来进行更新


ByteBuf的引用计数
被引用计数包含的对象，能够显示的被垃圾回收。当初始化的时候，计数为1。retain（）方法能够增加计数，release() 方法能够减少计数，如果计数被减少到0则对象会被显示回收，
再次访问被回收的这些对象将会抛出异常。如果一个对象实现了ReferenceCounted，并且包含有其他对象也实现来ReferenceCounted，当这个对象计数为0被回收的时候，所包含的对象同样会通过release()释放掉

常用的引用计数类 AbstractReferenceCountedByteBuf     
retain() 增加引用计数 、release()减少引用计数   都是通过自循CAS操作实现的 
                                         
