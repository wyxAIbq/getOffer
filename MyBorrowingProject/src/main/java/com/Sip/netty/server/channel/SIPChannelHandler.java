package com.Sip.netty.server.channel;

import com.Sip.exceptions.MessageNotUnderstood;
import com.Sip.messages.ACSResend;
import com.Sip.messages.Message;
import com.Sip.messages.SCResend;
import com.Sip.netty.server.driver.Driver;
import com.Sip.netty.server.driver.DriverFactory;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
* Handles a server-side channel.
*/
@Sharable
public class SIPChannelHandler extends SimpleChannelInboundHandler<Message> {
    private static Log logger = LogFactory.getLog(SIPChannelHandler.class);

	private com.Sip.netty.server.driver.DriverFactory driverFactory;
	
	public SIPChannelHandler(DriverFactory driverFactory)
	{
		this.driverFactory = driverFactory;
	}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
    }

    private Message process(Message request) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, MessageNotUnderstood, InstantiationException {
        Driver driver = driverFactory.getDriver();

        Method[] handlerMethods = driver.getClass().getMethods();

        for (Method handlerMethod : handlerMethods) {
            Class<?> types[] = handlerMethod.getParameterTypes();
            if (types.length == 1) {
                if (request.getClass() == types[0]) {
                    return (Message) handlerMethod.invoke(driver, new Object[] { request });
                }
            }
        }
        throw new MessageNotUnderstood();
    }

    
	Message response = null;
    
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message request) throws Exception {
        if (!(request instanceof ACSResend)) {
        	response = process(request);
        } else if (response == null) {
        	response = new SCResend();
        }

        response.setSequenceCharacter(request.getSequenceCharacter());
        
        ChannelFuture future = ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	if (cause instanceof java.io.IOException) {
    		logger.debug(cause.getMessage());
    	} else {
	    	logger.error("Transient communications error", cause);
//	        cause.printStackTrace();
	        try {
	            ctx.write(new SCResend().encode(null));
	            ctx.flush();
	        } catch (Exception e1) {
	        	logger.error("Transient communications error", e1);
	            ctx.close();
	        }
	    }
    }
}