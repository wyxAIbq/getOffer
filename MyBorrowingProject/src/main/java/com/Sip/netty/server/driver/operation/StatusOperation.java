package com.Sip.netty.server.driver.operation;

import com.Sip.messages.ACSStatus;
import com.Sip.messages.SCStatus;

public interface StatusOperation {

	public abstract ACSStatus Status(SCStatus msg);

}