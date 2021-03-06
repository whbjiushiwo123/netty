package com.whb.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: netty
 * @description
 * @author: 吴徽宝
 * @create: 2021-04-15 11:18
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L-2208988800L));
        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                assert  future == f;
                ctx.close();
            }
        });

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
