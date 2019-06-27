package com.Sip.netty.server.driver.operation;

import com.Sip.messages.PatronEnable;
import com.Sip.messages.PatronEnableResponse;

public interface PatronEnableOperation {

	public abstract PatronEnableResponse PatronEnable(PatronEnable msg);

}