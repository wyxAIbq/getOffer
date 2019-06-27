/*******************************************************************************
 * Copyright (c) 2010 Matthew J. Dovey (www.ceridwen.com).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at 
 * <http://www.gnu.org/licenses/>
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Matthew J. Dovey (www.ceridwen.com) - initial API and implementation
 ******************************************************************************/
package com.Sip.server;

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

@Deprecated
public class MessageHandlerDummyImpl implements MessageHandler {

    @Override
    public ACSStatus Status(SCStatus msg) {
        ACSStatus response = new ACSStatus();
        response.getSupportedMessages().setBlockPatron(true);
        response.getSupportedMessages().setCheckIn(true);
        response.getSupportedMessages().setCheckOut(true);
        response.getSupportedMessages().setEndPatronSession(true);
        response.getSupportedMessages().setFeePaid(true);
        response.getSupportedMessages().setHold(true);
        response.getSupportedMessages().setItemInformation(true);
        response.getSupportedMessages().setItemStatusUpdate(true);
        response.getSupportedMessages().setLogin(true);
        response.getSupportedMessages().setPatronEnable(true);
        response.getSupportedMessages().setPatronInformation(true);
        response.getSupportedMessages().setPatronStatusRequest(true);
        response.getSupportedMessages().setRenew(true);
        response.getSupportedMessages().setRenewAll(true);
        response.getSupportedMessages().setScAcsStatus(true);
        response.getSupportedMessages().setRequestScAcsResend(true);
        return response;
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
