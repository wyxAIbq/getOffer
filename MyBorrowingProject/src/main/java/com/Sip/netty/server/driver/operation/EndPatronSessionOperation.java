package com.Sip.netty.server.driver.operation;

import com.Sip.messages.EndPatronSession;
import com.Sip.messages.EndSessionResponse;

public interface EndPatronSessionOperation {

	public abstract EndSessionResponse EndPatronSession(EndPatronSession msg);

}