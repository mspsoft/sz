package com.sz.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.sz.base.TestBase;
import com.sz.utilities.businessLibrary;
import com.sz.utilities.businessLibrary.*;
public class loginTest extends TestBase {
	
	@Test
	public void loginTest() throws InterruptedException
	{
		/*
		driver.findElement(By.xpath(OR.getProperty("txtLogin_UserName"))).sendKeys("SZ_DIR_000");
		driver.findElement(By.xpath(OR.getProperty("txtLogin_Passwod"))).sendKeys("Synzeal1234");
		driver.findElement(By.xpath(OR.getProperty("btnLogin_Login"))).click();
		
		driver.manage().window().maximize();
		
		Thread.sleep(5000);
		//Select the menu Masters -> Roles
		//driver.findElement(By.xpath(OR.getProperty("lnkHome_Masters"))).click();
		//Thread.sleep(3000);
		//driver.findElement(By.xpath(OR.getProperty("lnkHome_Roles"))).click();
		businessLibrary.selectMenu("Masters", "Roles");
		*/
		
		
		businessLibrary.launchApplication();
		businessLibrary.loginToSynzeal(config.getProperty("admin_username"), config.getProperty("admin_password"));
		businessLibrary.logout();
		
	}
	

}
