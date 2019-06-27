/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sip.transport;

import com.Sip.exceptions.*;
import com.Sip.messages.*;
import com.Sip.netty.server.SIPDaemon;
import com.Sip.samples.netty.DummyDriverFactory;
import com.Sip.types.enumerations.ProtocolVersion;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.fail;

/**
 *
 * @author Matthew.Dovey
 */
public class TestSSLSocketTransport {
  SIPDaemon server;
  SelfSignedCertificate ssc;
	
	@Before
	public void setUp() throws Exception {
      // Run netty server
      ssc = new SelfSignedCertificate();
      server = new SIPDaemon("Sample", "localhost", 12345, ssc.certificate(), ssc.privateKey(), new DummyDriverFactory(), true);

      server.start();
	}

	@After
	public void tearDown() throws Exception {
    server.stop();
	}

	@Test
	public void test() {
        /**
         * Now try basic client commands
         */
        Connection connection;
        Message request, response;

        connection = new SSLSocketConnection();
        ((SSLSocketConnection) connection).setServerCertificateCA(ssc.certificate());
        ((SocketConnection) connection).setHost("localhost");
        ((SocketConnection) connection).setPort(12345);
        ((SocketConnection) connection).setConnectionTimeout(30000);
        ((SocketConnection) connection).setIdleTimeout(30000);
        ((SocketConnection) connection).setRetryAttempts(2);
        ((SocketConnection) connection).setRetryWait(500);

        try {
            connection.connect();
        } catch (Exception e1) {
        	fail("Connection failed: " + e1.getMessage());
        	return;
        }

        /**
         * It is necessary to send a SC Status with protocol version 2.0 else
         * server will assume 1.0)
         */

        request = new SCStatus();
        ((SCStatus) request).setProtocolVersion(ProtocolVersion.VERSION_2_00);

        try {
            response = connection.send(request);
        } catch (RetriesExceeded e) {
        	fail("Retries exceeded: " + e.getMessage());
        	return;
        } catch (MessageNotUnderstood e) {
        	fail("Message not understood: " + e.getMessage());
        	return;
        } catch (ChecksumError e) {
        	fail("Checksum error: " + e.getMessage());
        	return;
        } catch (SequenceError e) {
        	fail("Sequence error: " + e.getMessage());
        	return;
        } catch (MandatoryFieldOmitted e) {
        	fail("Mandatory Field Omitted: " + e.getMessage());
        	return;
        } catch (InvalidFieldLength e) {
        	fail("Invalid field length: " + e.getMessage());
        	return;
        }
        
        if (!(response instanceof ACSStatus)) {
            fail("Status Request did not return valid response from server.");
        	return;
        }


        /**
         * Check if the server can support checkout
         */
        if (!((ACSStatus) response).getSupportedMessages().isCheckOut()) {
        	fail("Check out not supported");
        	return;
        }

        request = new CheckOut();

        /**
         * The code below would be the normal way of creating the request
         */

        ((CheckOut) request).setPatronIdentifier("2000000");
        ((CheckOut) request).setItemIdentifier("300000000");
        ((CheckOut) request).setSCRenewalPolicy(Boolean.TRUE);
        ((CheckOut) request).setTransactionDate(new Date());

        try {
            response = connection.send(request);
        } catch (RetriesExceeded e) {
        	fail("Retries exceeded: " + e.getMessage());
        	return;
        } catch (MessageNotUnderstood e) {
        	fail("Message not understood: " + e.getMessage());
        	return;
        } catch (ChecksumError e) {
        	fail("Checksum error: " + e.getMessage());
        	return;
        } catch (SequenceError e) {
        	fail("Sequence error: " + e.getMessage());
        	return;
        } catch (MandatoryFieldOmitted e) {
        	fail("Mandatory Field Omitted: " + e.getMessage());
        	return;
        } catch (InvalidFieldLength e) {
        	fail("Invalid field length: " + e.getMessage());
        	return;
        }

        if (!(response instanceof CheckOutResponse)) {
            fail("Error - CheckOut Request did not return valid response from server");
        	return;
        }
        
        try {
        	String testCase = response.encode('1');
        	assert(testCase.startsWith("120NUN") && testCase.contains("AA|AB|AH|AJ|AO|AY1AZ")); // strip out components which may change (transaction date and checksum)
	    } catch (MessageNotUnderstood e) {
	    	fail("Message not understood: " + e.getMessage());
      } catch (MandatoryFieldOmitted e) {
        fail("Mandatory Field Omitted: " + e.getMessage());
	    } catch (InvalidFieldLength e) {
	    	fail("Invalid field length: " + e.getMessage());
	    }
	}
}
  
  
  
  
  
  
