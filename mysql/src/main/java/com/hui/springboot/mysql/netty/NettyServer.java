package com.hui.springboot.mysql.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-21 19:30
 */
public class NettyServer {


    // 端口号
    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();//① 是一个启动NIO服务的辅助启动类
        NioEventLoopGroup worker = new NioEventLoopGroup();//②NioEventLoopGroup是用来处理IO操作的多线程事件循环器
        serverBootstrap
                .group(worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            //读取客户端发送的消息
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println("【客户端发送的消息】" + msg);
                            }
                        });
                    }
                })
                .bind(port);
    }

    public static void main(String[] args) throws Exception {
        try {
            new NettyServer(8888).start();
            System.out.println("启动成功 8888");
        } catch (Exception e) {
            System.out.println("启动失败");
        }
    }
}
