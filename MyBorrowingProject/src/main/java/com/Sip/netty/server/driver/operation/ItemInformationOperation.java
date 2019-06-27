package com.Sip.netty.server.driver.operation;

import com.Sip.messages.ItemInformation;
import com.Sip.messages.ItemInformationResponse;

public interface ItemInformationOperation {

	public abstract ItemInformationResponse ItemInformation(ItemInformation msg);

}