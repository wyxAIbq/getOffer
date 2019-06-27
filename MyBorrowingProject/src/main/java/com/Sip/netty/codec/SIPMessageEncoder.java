package com.Sip.netty.codec;

import com.Sip.messages.Message;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

@Sharable
public class SIPMessageEncoder extends MessageToMessageEncoder<Message> {
	
	public SIPMessageEncoder() {}

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
		out.add(msg.encode() + "\r");
	}
}

