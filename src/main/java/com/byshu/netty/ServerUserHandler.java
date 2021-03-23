package com.byshu.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author byshu
 * @desc
 */
public class ServerUserHandler extends ChannelDuplexHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.err.println("userEventTriggered.." + evt);
        if (evt instanceof IdleStateEvent) {
            ctx.close();
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channelRegistered..");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channelUnregistered..");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channelActive..");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channelInactive..");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("channelRead..");
        UserProto.User user = (UserProto.User) msg;
        System.out.println("receiver from client : " + user);
        ChannelFuture future = ctx.writeAndFlush(user);
        future.addListener(future1 -> {
            System.out.println("operationComplete!!!!!!!!!!");
        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channelReadComplete..");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channelWritabilityChanged..");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("exceptionCaught..");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        System.err.println("read..");
        ctx.channel().unsafe().beginRead();
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        // write从当前handler的prev开始write 所以当前write方法不执行
        System.err.println("write..");
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.err.println("flush..");
        super.flush(ctx);
    }
}
