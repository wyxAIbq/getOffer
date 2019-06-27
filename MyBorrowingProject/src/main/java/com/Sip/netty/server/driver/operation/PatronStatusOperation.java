package com.Sip.netty.server.driver.operation;

import com.Sip.messages.PatronStatusRequest;
import com.Sip.messages.PatronStatusResponse;

public interface PatronStatusOperation {

	public abstract PatronStatusResponse PatronStatus(PatronStatusRequest msg);

}