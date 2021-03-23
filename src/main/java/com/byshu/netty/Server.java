package com.byshu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @desc
 * @author byshu
 */
public class Server {

    public static void main(String[] args) {

        EventLoopGroup parentGroup = new NioEventLoopGroup(1);
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            new ServerBootstrap()
                .group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline()
                            .addLast(new IdleStateHandler(60, 0, 0)) // 读空闲 写空闲 禁用all 秒
                            .addLast(new ProtobufVarint32FrameDecoder()) // 根据头部长度字段解决粘包 拆包
                            .addLast(new ProtobufDecoder(UserProto.User.getDefaultInstance()))
                            .addLast(new ProtobufVarint32LengthFieldPrepender()) // 头部增加长度字段
                            .addLast(new ProtobufEncoder())
                            .addLast(new ServerUserHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1000) // sync/accept queue
                .childOption(ChannelOption.TCP_NODELAY, true) // 关闭Nagle算法 一个TCP连接上可以有多个还未被确认的分组
                .bind(9090)
                .sync()
                .channel()
                .closeFuture()
                .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
