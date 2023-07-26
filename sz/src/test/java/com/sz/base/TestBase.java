package com.sz.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * setup
 */
public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static FileInputStream fOR;
	ChromeOptions chromeOptions = new ChromeOptions();
	//ExtentReports reports;
	 
	@BeforeSuite
	public void setup()
	{
		try {
			fis= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Configuration.properties");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fOR= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			OR.load(fOR);
		
		if(config.getProperty("browser").equals("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--remote-allow-origins=*");

//			WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().driverVersion("108.0.5359.95").setup();
			//driver = new ChromeDriver(chromeOptions);
			driver = new ChromeDriver(options);
		}
		
		//driver.get(config.getProperty("testsiteurl"));
		//driver.get("http://13.233.9.216:5500/login");
		//driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterSuite
	public void teardown()
	{
		if (driver!=null)
			driver.quit();
	}

}
