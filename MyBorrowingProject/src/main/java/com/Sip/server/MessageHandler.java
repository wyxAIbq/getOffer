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
import com.Sip.messages.BlockPatron;
import com.Sip.messages.CheckIn;
import com.Sip.messages.CheckInResponse;
import com.Sip.messages.CheckOut;
import com.Sip.messages.CheckOutResponse;
import com.Sip.messages.EndPatronSession;
import com.Sip.messages.EndSessionResponse;
import com.Sip.messages.FeePaid;
import com.Sip.messages.FeePaidResponse;
import com.Sip.messages.Hold;
import com.Sip.messages.HoldResponse;
import com.Sip.messages.ItemInformation;
import com.Sip.messages.ItemInformationResponse;
import com.Sip.messages.ItemStatusUpdate;
import com.Sip.messages.ItemStatusUpdateResponse;
import com.Sip.messages.Login;
import com.Sip.messages.LoginResponse;
import com.Sip.messages.PatronEnable;
import com.Sip.messages.PatronEnableResponse;
import com.Sip.messages.PatronInformation;
import com.Sip.messages.PatronInformationResponse;
import com.Sip.messages.PatronStatusRequest;
import com.Sip.messages.PatronStatusResponse;
import com.Sip.messages.Renew;
import com.Sip.messages.RenewAll;
import com.Sip.messages.RenewAllResponse;
import com.Sip.messages.RenewResponse;
import com.Sip.messages.SCStatus;

@Deprecated
public interface MessageHandler {
    public ACSStatus Status(SCStatus msg);

    public PatronStatusResponse BlockPatron(BlockPatron msg);

    public CheckInResponse CheckIn(CheckIn msg);

    public CheckOutResponse CheckOut(CheckOut msg);

    public EndSessionResponse EndPatronSession(EndPatronSession msg);

    public FeePaidResponse FeePaid(FeePaid msg);

    public HoldResponse Hold(Hold msg);

    public ItemInformationResponse ItemInformation(ItemInformation msg);

    public ItemStatusUpdateResponse ItemStatusUpdate(ItemStatusUpdate msg);

    public LoginResponse Login(Login msg);

    public PatronEnableResponse PatronEnable(PatronEnable msg);

    public PatronInformationResponse PatronInformation(PatronInformation msg);

    public PatronStatusResponse PatronStatus(PatronStatusRequest msg);

    public RenewResponse Renew(Renew msg);

    public RenewAllResponse RenewAll(RenewAll msg);
}
