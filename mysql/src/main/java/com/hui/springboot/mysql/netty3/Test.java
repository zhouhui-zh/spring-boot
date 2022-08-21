package com.hui.springboot.mysql.netty3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-22 15:29
 */
public class Test {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(10086);


        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("accept");

//                byte[] resp = ("a@$@bbbbb@$@cccccccccc@$@ddddddddddddddddddddddddddddddddddddddd@$@33333333333@$@4              v@$@").getBytes();
//                InputStream inputStream = new FileInputStream(new File("D:/proj/netty/tcpradio.dat"));
            InputStream inputStream = new FileInputStream(new File("tcpradio.dat"));
            OutputStream outputStream = socket.getOutputStream();
            byte[] readBuf = new byte[10240];
            int readLen;
            readLen = inputStream.read(readBuf, 0, 10240);
            System.out.println(readLen);
            outputStream.write(readBuf, 0, readLen);
            inputStream.close();
            outputStream.close();
        }
    }
}
