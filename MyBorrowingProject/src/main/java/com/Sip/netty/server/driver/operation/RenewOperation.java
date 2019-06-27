package com.Sip.netty.server.driver.operation;

import com.Sip.messages.Renew;
import com.Sip.messages.RenewResponse;

public interface RenewOperation {

	public abstract RenewResponse Renew(Renew msg);

}