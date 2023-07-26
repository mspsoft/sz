package com.sz.testcases;

import org.testng.annotations.Test;

import com.sz.base.TestBase;
import com.sz.utilities.businessLibrary;

public class EditScheme extends TestBase{
  @Test
  public void f() {
		businessLibrary.launchApplication();
		businessLibrary.loginToSynzeal(config.getProperty("scientist_username"), config.getProperty("scientist_password"));
		businessLibrary.editScheme("SZ-C081D01-A", "In Progress");
		businessLibrary.updateButton();
  }
}
