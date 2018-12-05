package com.fnii.netty.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/30 13:57
 * @desc:
 */
public class TestSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] bytes = new byte[2048];
        inputStream.read(bytes);
        System.out.println(new String(bytes));

        Thread.sleep(100000);
    }
}
