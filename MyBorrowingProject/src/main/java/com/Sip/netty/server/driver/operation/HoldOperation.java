package com.Sip.netty.server.driver.operation;

import com.Sip.messages.Hold;
import com.Sip.messages.HoldResponse;

public interface HoldOperation {

	public abstract HoldResponse Hold(Hold msg);

}