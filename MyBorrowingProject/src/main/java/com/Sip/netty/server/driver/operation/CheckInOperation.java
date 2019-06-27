package com.Sip.netty.server.driver.operation;

import com.Sip.messages.CheckIn;
import com.Sip.messages.CheckInResponse;

public interface CheckInOperation {

	public abstract CheckInResponse CheckIn(CheckIn msg);

}