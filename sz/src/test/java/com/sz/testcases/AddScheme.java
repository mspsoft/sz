package com.sz.testcases;

import org.testng.annotations.Test;

import com.sz.base.TestBase;
import com.sz.utilities.businessLibrary;

public class AddScheme extends TestBase {
	
@Test
	public void AddScheme() throws InterruptedException
	{
		businessLibrary.launchApplication();
		businessLibrary.loginToSynzeal(config.getProperty("scientist_username"), config.getProperty("scientist_password"));
		//businessLibrary.selectMenu("Schemes", "List");
		//businessLibrary.SearchMaster("SZ-C081D01-A");
		//businessLibrary.EditScheme();
		//businessLibrary.addShape("pyrrole", 50, 50);
		businessLibrary.selectMenu("New", "Scheme");
		businessLibrary.addNewScheme("SZ-M039870");
		businessLibrary.selectMenu("Schemes", "List");
		//businessLibrary.SearchMaster("SZ-M039870");
		//businessLibrary.EditFirstListedScheme();
		businessLibrary.editScheme("SZ-M039870", "In Progress");
		
		businessLibrary.logout();
	}
}
