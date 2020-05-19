package com.example.springboot.common.nio.netty;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

//自定义handler 。
// 也可以继承SimpleChannelInboundHandler，更简单。abstract class SimpleChannelInboundHandler<I> extends ChannelInboundHandlerAdapter
//继承SimpleChannelInboundHandler ,客户端发发数据：channelActive方法 读书节：channelRead0方法  服务端读：channelRead0方法，读后顺带回复。
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //读数据。ChannelHandlerContext：上下文对象，包含channel、pipline等各种属性
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg; //netty
        System.out.println("from client msg="+byteBuf.toString(CharsetUtil.UTF_8));
    }
    //读数据完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        Channel channel = channelHandlerContext.channel();
        ChannelPipeline pipeline = channel.pipeline();
        ChannelHandler handler = channelHandlerContext.handler();
        //writeAndFlush将数据写入缓存并刷新
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("i am server",CharsetUtil.UTF_8));

        //自定义普通任务，加入taskQueue队列
        channel.eventLoop().execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5*1000);
                    channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("i am server normal task",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //自定义定时任务，加入scheduledTaskQueue队列
        channel.eventLoop().scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("i am server schedule task",CharsetUtil.UTF_8));
            }
        },0,10, TimeUnit.SECONDS);//每隔10秒执行，客户端会不断收到消息

    }
    //异常关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) throws Exception {
        channelHandlerContext.close();
    }
    //心跳重写
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
