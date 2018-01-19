package com.xiongqi.netty.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {

    private int port;

    public MyServer(int port) {
        super();
        this.port = port;
    }

    private void bind() throws InterruptedException {
        /**
         *用于服务器端接受客户端的连接
         */
        EventLoopGroup bossGruop = new NioEventLoopGroup();

        /**
         *用于网络事件的处理
         */
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //创建ServerBootstrap实例
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGruop, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel arg0) throws Exception {
                    arg0.pipeline().addLast(new MyServerHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 1024);//指定此套接口排队的最大连接个数
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGruop.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new MyServer(8089).bind();
    }
}