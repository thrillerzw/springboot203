package com.example.springboot.common.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

//自定义handler
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //读数据。ChannelHandlerContext：上下文对象，包含channel、pipline等各种属性
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg; //netty
        System.out.println("from client msg="+byteBuf.toString(CharsetUtil.UTF_8));
    }
    //读数据完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush将数据写入缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("i am server",CharsetUtil.UTF_8));
    }
    //异常关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
