/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sip.samples.netty;

import com.Sip.messages.ACSStatus;
import com.Sip.messages.CheckInResponse;
import com.Sip.messages.CheckOutResponse;
import com.Sip.messages.EndSessionResponse;
import com.Sip.messages.FeePaidResponse;
import com.Sip.messages.HoldResponse;
import com.Sip.messages.ItemInformationResponse;
import com.Sip.messages.ItemStatusUpdateResponse;
import com.Sip.messages.LoginResponse;
import com.Sip.messages.PatronEnableResponse;
import com.Sip.messages.PatronInformationResponse;
import com.Sip.messages.PatronStatusRequest;
import com.Sip.messages.PatronStatusResponse;
import com.Sip.messages.RenewAllResponse;
import com.Sip.messages.RenewResponse;
import com.Sip.messages.SCStatus;
import com.Sip.netty.server.driver.AbstractDriver;
import com.Sip.netty.server.driver.operation.BlockPatronOperation;
import com.Sip.netty.server.driver.operation.CheckInOperation;
import com.Sip.netty.server.driver.operation.CheckOutOperation;
import com.Sip.netty.server.driver.operation.EndPatronSessionOperation;
import com.Sip.netty.server.driver.operation.FeePaidOperation;
import com.Sip.netty.server.driver.operation.HoldOperation;
import com.Sip.netty.server.driver.operation.ItemInformationOperation;
import com.Sip.netty.server.driver.operation.ItemStatusUpdateOperation;
import com.Sip.netty.server.driver.operation.LoginOperation;
import com.Sip.netty.server.driver.operation.PatronEnableOperation;
import com.Sip.netty.server.driver.operation.PatronInformationOperation;
import com.Sip.netty.server.driver.operation.PatronStatusOperation;
import com.Sip.netty.server.driver.operation.RenewAllOperation;
import com.Sip.netty.server.driver.operation.RenewOperation;

/**
 *
 * @author Matthew
 */
public class DummyDriver extends AbstractDriver
  implements  BlockPatronOperation,
              CheckInOperation,
              CheckOutOperation,
              EndPatronSessionOperation,
              FeePaidOperation,
              HoldOperation,
              ItemInformationOperation,
              ItemStatusUpdateOperation,
              LoginOperation,
              PatronEnableOperation,
              PatronInformationOperation,
              PatronStatusOperation,
              RenewAllOperation,
              RenewOperation
{

  @Override
  public ACSStatus Status(ACSStatus status, SCStatus msg) {
    status.setACSRenewalPolicy(false);
    status.setCheckInOk(true);
    status.setCheckOutOk(true);
    status.setOfflineOk(false);
    status.setStatusUpdateOk(true);
    return status;
  }

    @Override
    public PatronStatusResponse BlockPatron(
            com.Sip.messages.BlockPatron msg) {
        return new PatronStatusResponse();
    }

    @Override
    public CheckInResponse CheckIn(
            com.Sip.messages.CheckIn msg) {
        return new CheckInResponse();
    }

    @Override
    public CheckOutResponse CheckOut(
            com.Sip.messages.CheckOut msg) {
        return new CheckOutResponse();
    }

    @Override
    public EndSessionResponse EndPatronSession(
            com.Sip.messages.EndPatronSession msg) {
        return new EndSessionResponse();
    }

    @Override
    public FeePaidResponse FeePaid(
            com.Sip.messages.FeePaid msg) {
        return new FeePaidResponse();
    }

    @Override
    public HoldResponse Hold(com.Sip.messages.Hold msg) {
        return new HoldResponse();
    }

    @Override
    public ItemInformationResponse ItemInformation(
            com.Sip.messages.ItemInformation msg) {
        return new ItemInformationResponse();
    }

    @Override
    public ItemStatusUpdateResponse ItemStatusUpdate(
            com.Sip.messages.ItemStatusUpdate msg) {
        return new ItemStatusUpdateResponse();
    }

    @Override
    public LoginResponse Login(com.Sip.messages.Login msg) {
        return new LoginResponse();
    }

    @Override
    public PatronEnableResponse PatronEnable(
            com.Sip.messages.PatronEnable msg) {
        return new PatronEnableResponse();
    }

    @Override
    public PatronInformationResponse PatronInformation(
            com.Sip.messages.PatronInformation msg) {
        return new PatronInformationResponse();
    }

    @Override
    public PatronStatusResponse PatronStatus(PatronStatusRequest msg) {
        return new PatronStatusResponse();
    }

    @Override
    public RenewResponse Renew(com.Sip.messages.Renew msg) {
        return new RenewResponse();
    }

    @Override
    public RenewAllResponse RenewAll(
            com.Sip.messages.RenewAll msg) {
        return new RenewAllResponse();
    }
}
