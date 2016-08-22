package com.cyb.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.cyb.pageobjects.CheckOutPage;

public class PerformAction {
	//creating common functions
	private static WebDriver driver;
	PerformAction(WebDriver driver)
	{
		this.driver=driver;
	}
	public static void clickOnUIElement(WebElement element)
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		element.click();
	}
	public static void sendTextToUI(WebElement element,String strText)
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		element.sendKeys(strText);
	}
	public static void closeCurrentBrowser(WebDriver driver)
	{
		driver.close();
	}

	public static void closeAllOpenBrowsers(WebDriver driver)
	{
		driver.quit();
	}
	public static void IsElementAvailable(WebElement element)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (element.isDisplayed()) {
			Reporter.log("Object found");
		}
		else
		{
			Reporter.log("Object   " +element+"   not found");
			Assert.fail();
		}

	}
	
	public static String gerTextFromUIElement(WebElement element)
	{
		String strText = element.getText();
		return strText;
		
	}
	
	public static void selectValueFromDropDown(WebElement element,String strValue)
	{
		Select select = new Select(element);
		select.selectByVisibleText(strValue);
	}
	
	public static void selectCheckBox(WebElement element)
	{
		element.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
