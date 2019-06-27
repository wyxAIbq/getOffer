package com.Sip.netty.server.driver.operation;

import com.Sip.messages.ItemStatusUpdate;
import com.Sip.messages.ItemStatusUpdateResponse;

public interface ItemStatusUpdateOperation {

	public abstract ItemStatusUpdateResponse ItemStatusUpdate(
            ItemStatusUpdate msg);

}