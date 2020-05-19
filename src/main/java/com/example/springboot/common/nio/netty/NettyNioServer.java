package com.example.springboot.common.nio.netty;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

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
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {//设置workerGroup handler

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                            //消息推送，可以使用集合把SocketChannel管理起来，可以用普通任务异步处理。

                            //------心跳检测---------。readerIdleTime 多久没有读,  writerIdleTime多久没有写, long allIdleTime多久没有读写
//当IdleStateEvent触发后，就会传递给管道的下一个handler的userEventTriggered(ChannelHandlerContext ctx, Object evt) 方法处理
                            ch.pipeline().addLast(new IdleStateHandler(5,10,15, TimeUnit.SECONDS));
//自定义处理心跳的handler，重写userEventTriggered方法，evt判断类型后强转为IdleStateEvent类型，通过参数IdleState state判断事件类型
//                            ch.pipeline().addLast(xx);
                        }
                    });
                   /* .childHandler(new ChannelInitializer<SocketChannel>() {//设置workerGroup handler
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //websocket长连接
                            //基于http协议，使用http的编码解码
                            pipeline.addLast(new HttpServerCodec());
                            //以块的方式写
                            pipeline.addLast(new ChunkedWriteHandler());
                            //http数据传输过程分段，当浏览器发送大量数据时，会发多次请求。HttpObjectAggregator聚合多个分段。
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //WebSocketServerProtocolHandler将http协议升级为ws协议，保持长连接
                            //js 请求uri ws://localhost:9500/chat
                            pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));
                            //  pipeline.addLast(xx); //自定义handler处理业务逻辑
                        }
                    });*/
            System.out.println("netty server ok");
//            绑定端口
//           ChannelFuture channelFuture = serverBootstrap.bind(9500).sync();
            ChannelFuture channelFuture = serverBootstrap.bind(9500).addListener(new GenericFutureListener(){

                @Override
                public void operationComplete(Future future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("bind success");
                    }else{
                        System.out.println("bind fail");
                    }
                }
            });
//            对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
