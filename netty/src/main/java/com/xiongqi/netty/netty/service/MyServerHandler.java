package com.xiongqi.netty.netty.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class MyServerHandler extends ChannelHandlerAdapter {

@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("客户端连上了...");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf buf=(ByteBuf) msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        System.out.println("服务器端接收的消息："+new String(req));
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        ctx.flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        ctx.close();
    }
}
