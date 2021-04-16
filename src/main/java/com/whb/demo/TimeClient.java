package com.whb.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: netty
 * @description
 * @author: 吴徽宝
 * @create: 2021-04-15 15:37
 **/
public class TimeClient {
    public static void main(String[]args) throws InterruptedException {
        String host = "localhost";
        int port = 8080;
        Bootstrap b = new Bootstrap();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            b.group(workGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE,true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        }finally {
            workGroup.shutdownGracefully();
        }

    }
}
