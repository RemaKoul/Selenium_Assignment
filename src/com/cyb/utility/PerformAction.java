package com.cyb.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

public class PerformAction {
	//creating common functions
	public static void clickOnUIElement(WebElement element)
	{
		element.click();
	}
	public static void sendTextToUI(WebElement element,String strText)
	{
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
		if (element.isDisplayed()) {
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
	}
}
