package com.whb.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @program: netty
 * @description
 * @author: 吴徽宝
 * @create: 2021-04-15 15:33
 **/
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf)msg;
        try{
            long currentTimeMills = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.print(new Date(currentTimeMills));
            ctx.close();
        }finally {
            m.release();
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
