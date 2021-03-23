package com.byshu.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author byshu
 * @desc
 */
public class LastHandler extends ChannelDuplexHandler {

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        System.err.println("lasthandler read..");
        super.read(ctx);
    }

}
