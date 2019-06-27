package com.Sip.netty.server.driver.operation;

import com.Sip.messages.CheckOut;
import com.Sip.messages.CheckOutResponse;

public interface CheckOutOperation {

	public abstract CheckOutResponse CheckOut(CheckOut msg);

}