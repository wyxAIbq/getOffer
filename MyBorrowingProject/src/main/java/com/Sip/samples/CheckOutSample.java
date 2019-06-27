/**
 * *****************************************************************************
 * Copyright (c) 2010 Matthew J. Dovey (www.ceridwen.com).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * <http://www.gnu.org/licenses/>
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors: Matthew J. Dovey (www.ceridwen.com) - initial API and
 * implementation
 * ****************************************************************************
 */
package com.Sip.samples;

import com.Sip.exceptions.*;
import com.Sip.messages.*;
import com.Sip.netty.server.SIPDaemon;
import com.Sip.samples.netty.DummyDriverFactory;
import com.Sip.transport.SSLSocketConnection;
import com.Sip.transport.SocketConnection;
import com.Sip.types.enumerations.Language;
import com.Sip.types.enumerations.ProtocolVersion;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CheckOutSample {

    private static final boolean SSL = false;
    private static SelfSignedCertificate ssc;

    public static void main(String[] args) {
        try {
            //接收字符转码
            System.setProperty("com.Sip.charset", "UTF-8");

            SIPDaemon server;

            // Run netty server
            if (SSL) {
                ssc = new SelfSignedCertificate();
                server = new SIPDaemon("CheckOutSample", "localhost", 12345, ssc.certificate(), ssc.privateKey(), new DummyDriverFactory(), true);
            } else {
                server = new SIPDaemon("CheckOutSample", "localhost", 12345, new DummyDriverFactory(), true);
            }
            server.start();

            // Do sample checkout
            CheckOutSample.checkOut("201722180225","201722180225","30803876");

            // Shut down netty server
            server.stop();
        } catch (Exception ex) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void checkOut(String patronIdentifier, String patronPassword, String itemIdentifier ) {
        /**
         * Now try basic client commands
         */
        SocketConnection connection;

        if (SSL) {
            connection = new SSLSocketConnection();
            ((SSLSocketConnection) connection).setServerCertificateCA(ssc.certificate());
        } else {
            connection = new SocketConnection();
        }
        connection.setHost("222.197.165.110");
        connection.setPort(5550);
        connection.setConnectionTimeout(30000);
        connection.setIdleTimeout(30000);
        connection.setRetryAttempts(2);
        connection.setRetryWait(500);

        try {
            connection.connect();
        } catch (Exception ex) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        /**
         * It is necessary to send a SC Status with protocol version 2.0 else server
         * will assume 1.0)
         */
        SCStatus scStatusRequest = new SCStatus();
        scStatusRequest.setProtocolVersion(ProtocolVersion.VERSION_2_00);

        Message scStatusResponse;

        try {
            scStatusResponse = connection.send(scStatusRequest);
        } catch (RetriesExceeded | MessageNotUnderstood | ChecksumError | SequenceError | MandatoryFieldOmitted | InvalidFieldLength ex) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        if (!(scStatusResponse instanceof ACSStatus)) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, "Error - Status Request did not return valid response from server.");
            return;
        }

        /**
         * For debugging XML handling code (but could be useful in Cocoon)
         */
        scStatusResponse.xmlEncode(System.out);

        /**
         * Check if the server can support checkout
         */
        if (!((ACSStatus) scStatusResponse).getSupportedMessages().isCheckOut()) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, "Check out not supported");
            return;
        }

        //用户信息查询
        PatronInformation patronInformation = new PatronInformation();
        patronInformation.setPatronIdentifier(patronIdentifier);
        patronInformation.setPatronPassword(patronPassword);
        patronInformation.setLanguage(Language.CHINESE);
        patronInformation.setTransactionDate(new Date());
        patronInformation.setInstitutionId("uestc");
        //还书
        CheckIn checkIn = new CheckIn();
        checkIn.setNoBlock(Boolean.TRUE);
        checkIn.setTransactionDate(new Date());
        checkIn.setReturnDate(new Date());
        checkIn.setCurrentLocation("100607AP");
        checkIn.setInstitutionId("uestc");
        checkIn.setItemIdentifier(itemIdentifier);


        /**
         * Now try a checkout request
         */
        CheckOut checkOutRequest = new CheckOut();
        checkOutRequest.setPatronIdentifier(patronIdentifier);//6130027 ajing819
        checkOutRequest.setPatronPassword(patronPassword);
        checkOutRequest.setItemIdentifier(itemIdentifier);
        checkOutRequest.setSCRenewalPolicy(Boolean.TRUE);
        checkOutRequest.setTransactionDate(new Date());

        ItemInformation itemInformation = new ItemInformation();
        itemInformation.setTransactionDate(new Date());
        itemInformation.setItemIdentifier(itemIdentifier);
        itemInformation.setInstitutionId("uestc");

        Message checkOutResponse;
        Message checkInResponse;
        Message itemInformationResponse;

        //还书操作
        /*try{
            checkInResponse = connection.send(checkIn);
        } catch (RetriesExceeded | MessageNotUnderstood | ChecksumError | SequenceError | MandatoryFieldOmitted | InvalidFieldLength ex) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if (!(checkInResponse instanceof CheckInResponse)) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, "Error - CheckIn Request did not return valid response from server");
            return;
        }
        checkInResponse.xmlEncode(System.out);*/

        //借书操作
/*        try {
            checkOutResponse = connection.send(checkOutRequest);
        } catch (RetriesExceeded | MessageNotUnderstood | ChecksumError | SequenceError | MandatoryFieldOmitted | InvalidFieldLength ex) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if (!(checkOutResponse instanceof CheckOutResponse)) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, "Error - CheckOut Request did not return valid response from server");
            return;
        }
        checkOutResponse.xmlEncode(System.out);*/

        //查询馆藏信息
/*        try {
            itemInformationResponse = connection.send(itemInformation);
        } catch (RetriesExceeded | MessageNotUnderstood | ChecksumError | SequenceError | MandatoryFieldOmitted | InvalidFieldLength ex) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if (!(itemInformationResponse instanceof ItemInformationResponse)) {
            Logger.getLogger(CheckOutSample.class.getName()).log(Level.SEVERE, "Error - itemInformation Request did not return valid response from server");
            return;
        }
        itemInformationResponse.xmlEncode(System.out);*/

        connection.disconnect();
    }

}

