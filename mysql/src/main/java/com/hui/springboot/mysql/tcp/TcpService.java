package com.hui.springboot.mysql.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-21 18:54
 */
public class TcpService {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务器启动了 8888");
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接成功");

            TCPClient tcpClient = new TCPClient("127.0.0.1", 9999) {
                @Override
                protected void onDataReceive(byte[] bytes, int size) {
                    String content = null;

                    content = "TCPServer say :" + new String(bytes, 0, bytes.length);

                    System.out.println(content);
                }
            };
            tcpClient.connect();//连接TCPServer
            System.out.println("客户端连接成功 3306");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            OutputStream outputStream1 = null;
            while ((len = inputStream.read()) != -1) {
                String content = new String(bytes, 0, len, "utf-8");
                System.out.println(len + "\t" + content);

                if (tcpClient.isConnected()) {
                    //发送数据
                    tcpClient.send(bytes);
                }
            }
            outputStream.close();
            //关闭连接

            if (outputStream1 != null) {
                outputStream1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000 * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
