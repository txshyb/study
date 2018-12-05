package com.fnii.netty.client;

import com.fnii.netty.protocol.SmartCarProtocol;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/30 13:45
 * @desc: 模拟client发送接收
 */
public class TestSocket {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8088);
        //    client.setKeepAlive(true);
        String str = "{\"act\":0}";
        SmartCarProtocol smartCarProtocol = new SmartCarProtocol(str.length(), str.getBytes());

        OutputStream out = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        while (true) {
            objectOutputStream.writeInt(smartCarProtocol.getHeadData());
            objectOutputStream.writeInt(smartCarProtocol.getContentLength());
            objectOutputStream.write(smartCarProtocol.getContent());
            objectOutputStream.flush();
        }

//        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[2048];
//        while (inputStream.read(bytes) > -1) {
//            System.out.println(new String(bytes));
//        }
    }
}
