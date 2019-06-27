package com.Sip.netty.server.driver.operation;

import com.Sip.messages.PatronInformation;
import com.Sip.messages.PatronInformationResponse;

public interface PatronInformationOperation {

	public abstract PatronInformationResponse PatronInformation(
            PatronInformation msg);

}