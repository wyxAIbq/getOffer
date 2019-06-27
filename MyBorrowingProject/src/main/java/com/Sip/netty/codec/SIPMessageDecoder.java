package com.Sip.netty.codec;

import com.Sip.messages.Message;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

@Sharable
public class SIPMessageDecoder extends MessageToMessageDecoder<String> {
	private boolean strictChecksumChecking = false; 

	public SIPMessageDecoder() {}

	public SIPMessageDecoder(boolean strictChecksumChecking) 
	{
		this.strictChecksumChecking = strictChecksumChecking;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
		out.add(Message.decode(msg, null, strictChecksumChecking));
	}
}
