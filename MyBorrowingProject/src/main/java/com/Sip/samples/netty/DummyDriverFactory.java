/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sip.samples.netty;

import com.Sip.netty.server.driver.Driver;
import com.Sip.netty.server.driver.DriverFactory;

/**
 *
 * @author Matthew
 */
public class DummyDriverFactory implements DriverFactory {

  @Override
  public Driver getDriver() {
    return new DummyDriver();
  }
  
}
