package com.byshu.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @author byshu
 * @desc
 */
public class ClientUserHandler extends ChannelDuplexHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.err.println("userEventTriggered.." + evt);
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
        for (int i = 1; i <= 5; i++) {
            UserProto.User.Builder personBuilder = UserProto.User.newBuilder();
            personBuilder.setId(i);
            personBuilder.setAge(20);
            personBuilder.setGender(1);
            personBuilder.setName("jane");
            UserProto.User user = personBuilder.build();
            ctx.write(user);
        }
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("channelInactive..");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("channelRead..");
        UserProto.User user = (UserProto.User) msg;
        System.out.println("receive from server : " + user);
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
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.err.println("write..");
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.err.println("flush..");
        super.flush(ctx);
    }
}
