package com.sz.utilities;

import java.time.Duration;
import org.openqa.selenium.support.ui.Select;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sz.base.TestBase;

public class businessLibrary extends TestBase{

	public static void launchApplication()
	{
		Reporter.log("-----------------------------------------------------------------------");
		Reporter.log("-------------------=---launchApplication--------------------------");
		driver.get(config.getProperty("testsiteurl"));
		Reporter.log("Application launched successfully");
		driver.manage().window().maximize();
		Reporter.log("-----------------------------------------------------------------------");
	}
	public static void loginToSynzeal(String userName, String password)
	{
	try {
			Reporter.log("-----------------------------------------------------------------------");
			Reporter.log("----------------------loginToSynzeal function--------------------------");	
			driver.findElement(By.xpath(OR.getProperty("txtLogin_UserName"))).sendKeys(userName);
			driver.findElement(By.xpath(OR.getProperty("txtLogin_Passwod"))).sendKeys(password);
			driver.findElement(By.xpath(OR.getProperty("btnLogin_Login"))).click();
			Reporter.log("login To Synzeal is successful");	
			//driver.manage().window().maximize();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void logout() throws InterruptedException
	{
		Reporter.log("-----------------------------------------------------------------------");
		Reporter.log("----------------------logoutSynzeal function--------------------------");
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("btnHome_PageHeaderLoginLogo"))).click();
		driver.findElement(By.xpath(OR.getProperty("lnkSignOut"))).click();
		Reporter.log("-----------------------------------------------------------------------");
		
	}
	public static void addRole(String roleName, String roleDescription)
	{
		Reporter.log("-----------------------------------------------------------------------");
		Reporter.log("----------------------AddRole function--------------------------");
		driver.findElement(By.cssSelector(OR.getProperty("txtMasters_RoleName"))).sendKeys(roleName);
		driver.findElement(By.cssSelector(OR.getProperty("txtMasters_RoleDisplayName"))).sendKeys(roleDescription);
		driver.findElement(By.xpath(OR.getProperty("btnMasters_AddRole"))).click();
		Reporter.log("----------------------AddRole button is clicked successfully--------------------------");
		Reporter.log("-----------------------------------------------------------------------");
	}
	
	public static void SearchMaster(String searhString)
	{
		try {
			Reporter.log("-----------------------------------------------------------------------");
			Reporter.log("----------------------SearchMaster function--------------------------");
			Reporter.log("Searching for string ->"+searhString);
			driver.findElement(By.cssSelector(OR.getProperty("txtMasters_SearchTxt"))).sendKeys(searhString);
	//		driver.findElement(By.cssSelector(OR.getProperty("txtMasters_SearchTxt"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void EditFirstListedScheme()
	{
		driver.findElement(By.xpath(OR.getProperty("btnEditScheme"))).click();
	}
	
	public static void addShape(String shapeName, int x, int y)
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement canvasWebElement = driver.findElement(By.xpath(OR.getProperty("elementScheme_Canvas")));
		
		switch(shapeName)
		{
			case"pyrrole":
				driver.findElement(By.xpath(OR.getProperty("btnSchemes_PyreRole"))).click();
				break;
			case "cyclopentene":
				driver.findElement(By.xpath(OR.getProperty("btnSchemes_CycloPentene"))).click();
				break;
		}
		try {
			 Actions builder = new Actions(driver);
			 builder.moveToElement(canvasWebElement, x, y);
			 builder.click();
			 builder.perform();
			    } catch (Exception e) 
			    {
			     
			    }
	}
	
	public static void verifyTable(String searhString)
	{
		//if (driver.findElement(By.xpath(OR.getProperty("tblTable_Row_1_2"))).getText().compareToIgnoreCase(searhString)==0)			
		Assert.assertEquals(searhString, driver.findElement(By.xpath(OR.getProperty("tblTable_Row_1_2"))).getText());
		
	}	
	
	public static void selectList(String listName, String listValue)
	{
		switch(listName)
		{
			case"schemestatus":
				Select lstSchemeStatus = new Select( driver.findElement(By.xpath(OR.getProperty("lstSchemes_status"))));
				lstSchemeStatus.selectByVisibleText(listValue);
				break;
		}
		
	}
	public static void selectMenu(String menu1, String menu2)
	{
		try {
			driver.findElement(By.xpath("//a[text()='"+menu1 +"']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[text()='"+menu2 +"']")).click();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addNewScheme(String CatalogID)
	{
		try {
			Actions actions = new Actions(driver);
			
			
			
			driver.findElement(By.xpath(OR.getProperty("btnSchemes_SearchCatalog"))).click();
			driver.findElement(By.xpath(OR.getProperty("txtSchemes_productSearch"))).sendKeys(CatalogID);
			
			Thread.sleep(3000);
			//select first result item
			WebElement elementLocator = driver.findElement(By.xpath(OR.getProperty("tblTable_Row_1_2")));
			actions.doubleClick(elementLocator).perform();
			Thread.sleep(6000);
			//WebElement we = driver.findElement(By.xpath(OR.getProperty("btnSchemes_addScheme")));
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(Duration.ofSeconds(30L))
				       .pollingEvery(Duration.ofSeconds(5L))
				       .ignoring(NoSuchElementException.class);

				   WebElement we = wait.until(new Function<WebDriver, WebElement>() {
				     public WebElement apply(WebDriver driver) {
				       return driver.findElement(By.xpath("//form[@id=\"scheme-form\"]//button[@type=\"submit\"]"));
				     }
				   });
				   
		//	WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(we));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", we);
			actions.moveToElement(we).build().perform();

/*			
			actions.moveToElement(we, 50, 50);
			actions.click(we).build().perform();
			//we.click();
			
			
		

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(Duration.ofSeconds(30L))
				       .pollingEvery(Duration.ofSeconds(5L))
				       .ignoring(NoSuchElementException.class);

				   WebElement we = wait.until(new Function<WebDriver, WebElement>() {
				     public WebElement apply(WebDriver driver) {
				       return driver.findElement(By.xpath("//form[@id=\"scheme-form\"]//button[@type=\"submit\"]"));
				     }
				   });
				   
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", we);
				   we.click();
				   */
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateButton()
	{
		Actions actions = new Actions(driver);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(Duration.ofSeconds(30L))
			       .pollingEvery(Duration.ofSeconds(5L))
			       .ignoring(NoSuchElementException.class);

			   WebElement we = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       return driver.findElement(By.xpath("//form[@id=\"scheme-form\"]//button[@type=\"submit\"]"));
			     }
			   });
			   
	//	WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(we));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", we);
		actions.moveToElement(we).build().perform();
		we.click();
	}

	
	public static void addExperimentButtonClick()
	{
		try {
			Thread.sleep(10000);
		Actions actions = new Actions(driver);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(Duration.ofSeconds(30L))
			       .pollingEvery(Duration.ofSeconds(5L))
			       .ignoring(NoSuchElementException.class);

			   WebElement we = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       return driver.findElement(By.xpath(OR.getProperty("btnScheme_addExperiment")));
			     }
			   });
			   Thread.sleep(5000);
	//	WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(we));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", we);
		actions.moveToElement(we).build().perform();
		
			Thread.sleep(8000);
		
		we.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public static void editScheme(String schemeName, String schemeStatus)
	{
		Actions actions = new Actions(driver);
		businessLibrary.selectMenu("Schemes", "List");
		businessLibrary.SearchMaster(schemeName);
		businessLibrary.EditFirstListedScheme();
		businessLibrary.selectList("schemestatus", schemeStatus);
	//	WebElement we = driver.findElement(By.xpath(OR.getProperty("btnSchemes_addScheme")));
		
	//	actions.moveToElement(we, 50, 50);
	//	actions.click(we).build().perform();		
	}	
	
	public static void addExperiment(String stepNumber, String journalCode, String experimentName, String status, String ReactionType, String ReactionName)
	{
		try {
			Thread.sleep(5000);
		
		 driver.findElement(By.xpath(OR.getProperty("txtStepNo"))).sendKeys(stepNumber);
		 Thread.sleep(5000);
		 Select lstJournalCodeobj = new Select( driver.findElement(By.xpath(OR.getProperty("lstJournalCode"))));
		 lstJournalCodeobj.selectByVisibleText(journalCode);
		 Thread.sleep(5000);
		 Select lstReactionTypeobj = new Select( driver.findElement(By.xpath(OR.getProperty("lstReactionType"))));
		 lstReactionTypeobj.selectByVisibleText(ReactionType);
		 Thread.sleep(5000);
		 Select lstReactionNameobj = new Select( driver.findElement(By.xpath(OR.getProperty("lstReactionName"))));
		 lstReactionNameobj.selectByVisibleText(ReactionName);
		 Thread.sleep(5000);
		driver.findElement(By.xpath(OR.getProperty("txtExperimentName"))).sendKeys(experimentName);		
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(Duration.ofSeconds(30L))
			       .pollingEvery(Duration.ofSeconds(5L))
			       .ignoring(NoSuchElementException.class);

			   WebElement we = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       return driver.findElement(By.xpath(OR.getProperty("btnAddSaveExperiment")));
			     }
			   });
		Thread.sleep(5000);
	//	WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(we));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", we);
		actions.moveToElement(we).build().perform();
		Thread.sleep(5000);
		we.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	public void clickJavaScriptButton(String buttonName)
	{
 
		switch(buttonName.toUpperCase())
		{
			case "ADDUPDATEEXPERIMENT":
				WebElement btnWebElement = driver.findElement(By.xpath(OR.getProperty("btnAddSaveExperiment")));
				break;
			case "cyclopentene":
				driver.findElement(By.xpath(OR.getProperty("btnSchemes_CycloPentene"))).click();
				break;
		}
	
	}*/
}
