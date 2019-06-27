package com.Sip.netty.server.driver;

import com.Sip.messages.ACSStatus;
import com.Sip.messages.SCStatus;
import com.Sip.netty.server.driver.operation.*;
import com.Sip.types.enumerations.ProtocolVersion;

import java.util.Date;

public abstract class AbstractDriver implements Driver {

	@Override
	public final ACSStatus Status(SCStatus msg) {
      ACSStatus response = new ACSStatus(); 
      if (this instanceof BlockPatronOperation) {
      	response.getSupportedMessages().setBlockPatron(true);
      }
      if (this instanceof CheckInOperation) {
	      response.getSupportedMessages().setCheckIn(true);
      }
      if (this instanceof CheckOutOperation) {
	      response.getSupportedMessages().setCheckOut(true);
		  }
      if (this instanceof EndPatronSessionOperation) {
	        response.getSupportedMessages().setEndPatronSession(true);
		  }
      if (this instanceof FeePaidOperation) {
	        response.getSupportedMessages().setFeePaid(true);
	    }
      if (this instanceof HoldOperation) {
	        response.getSupportedMessages().setHold(true);
	    }
		  if (this instanceof ItemInformationOperation) {
	        response.getSupportedMessages().setItemInformation(true);
    	}
		  if (this instanceof ItemStatusUpdateOperation) {
	        response.getSupportedMessages().setItemStatusUpdate(true);
	    }
	    if (this instanceof LoginOperation) {
	        response.getSupportedMessages().setLogin(true);
	    }
	    if (this instanceof PatronEnableOperation) {
	        response.getSupportedMessages().setPatronEnable(true);
	    }
	    if (this instanceof PatronInformationOperation) {
	        response.getSupportedMessages().setPatronInformation(true);
	    }
	    if (this instanceof PatronStatusOperation) {
	        response.getSupportedMessages().setPatronStatusRequest(true);
	    }
	    if (this instanceof RenewOperation) {
	        response.getSupportedMessages().setRenew(true);
	    }
	    if (this instanceof RenewAllOperation) {
	        response.getSupportedMessages().setRenewAll(true);
	    }
	    if (this instanceof RequestResendOperation) {
	        response.getSupportedMessages().setRequestScAcsResend(true);
	    }
	    if (this instanceof StatusOperation) {
	        response.getSupportedMessages().setScAcsStatus(true);
    	}
	    
	    response.setDateTimeSync(new Date());
	    response.setProtocolVersion(ProtocolVersion.VERSION_2_00);
	    response.setRetriesAllowed(999);
	    response.setTimeoutPeriod(999);
      response.setOnlineStatus(true);
		  return this.Status(response, msg);
	}
	
	abstract public ACSStatus Status(ACSStatus status, SCStatus msg);
}
