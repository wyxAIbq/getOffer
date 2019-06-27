package com.Sip.netty.server.driver.operation;

import com.Sip.messages.Login;
import com.Sip.messages.LoginResponse;

public interface LoginOperation {

	public abstract LoginResponse Login(Login msg);

}