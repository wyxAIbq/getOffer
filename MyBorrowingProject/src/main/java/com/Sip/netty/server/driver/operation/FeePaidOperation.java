package com.Sip.netty.server.driver.operation;

import com.Sip.messages.FeePaid;
import com.Sip.messages.FeePaidResponse;

public interface FeePaidOperation {

	public abstract FeePaidResponse FeePaid(FeePaid msg);

}