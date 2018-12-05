使用说明：

依赖改netty包
指定${netty.port}
new TcpServer()

@Configuration
@ComponentScan("com.fnii.netty")
public class NettyConfig {

    @Bean
    public TcpServer tcpServer() {
        LogLevel logLevel = LogLevel.INFO;
        return new TcpServer(logLevel);
    }
}