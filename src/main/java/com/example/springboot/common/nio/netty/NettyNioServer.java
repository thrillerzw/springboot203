package com.example.springboot.common.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/*<dependency>
<groupId>io.netty</groupId>
<artifactId>netty-all</artifactId>
<version>4.1.45.Final</version>
</dependency>*/
public class NettyNioServer {
    public static void main(String[] args) {
        /**
         * 创建bossGroup、workerGroup角色。bossGroup:只处理连接请求。workerGroup:处理读写请求
         * 默认线程数为cpu*2 ,可以构造函数指定个数
         * EventExecutor[] children;   EventExecutor extends  java.util.concurrent.ScheduledExecutorService
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        try{
            //服务端启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //配置参数
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {//设置handler

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("netty server ok");
//            绑定端口，同步生成ChannelFuture
            ChannelFuture channelFuture = serverBootstrap.bind(9500).sync();
//            对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
        }
    }
}
