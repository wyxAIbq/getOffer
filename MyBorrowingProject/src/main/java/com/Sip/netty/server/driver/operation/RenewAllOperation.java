package com.Sip.netty.server.driver.operation;

import com.Sip.messages.RenewAll;
import com.Sip.messages.RenewAllResponse;

public interface RenewAllOperation {
    public RenewAllResponse RenewAll(RenewAll msg);

}