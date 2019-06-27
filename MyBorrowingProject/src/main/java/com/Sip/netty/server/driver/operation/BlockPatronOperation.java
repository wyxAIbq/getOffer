package com.Sip.netty.server.driver.operation;

import com.Sip.messages.BlockPatron;
import com.Sip.messages.PatronStatusResponse;

public interface BlockPatronOperation {

	public abstract PatronStatusResponse BlockPatron(BlockPatron msg);

}