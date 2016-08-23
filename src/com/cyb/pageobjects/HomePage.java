package com.cyb.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.cyb.utility.PerformAction;

public class HomePage {
	private WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	@FindBy(id="log")
	WebElement txt_UserName;

	@FindBy(id="pwd")
	WebElement txt_Password;

	@FindBy(id="login")
	WebElement btn_LogIn;

	//Assure that current page has loaded successfully
	public void waitForPageToLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
		if (btn_LogIn.isDisplayed()) {
			Reporter.log("Welcome to Login page");
		} else {
			Reporter.log("Login page is not displayed");
			Assert.fail();
		}
	}

	public void navigateToURL(String strURL)
	{
		driver.get(strURL);
		Reporter.log("Naviagted to URL "+strURL);
	}
	public void enterUserName(String txtUserName)
	{
		PerformAction.sendTextToUI(txt_UserName, txtUserName);
		Reporter.log("User Name entered as :"+txtUserName);
	}

	public void enterPassword(String txtPassword)
	{
		PerformAction.sendTextToUI(txt_Password, txtPassword);
		Reporter.log("Password entered as : ******** ");
	}

	public WelcomePage submitUserContext()
	{
		PerformAction.clickOnUIElement(btn_LogIn);
		Reporter.log("Clicked on Login Button");
		return PageFactory.initElements(driver, WelcomePage.class);
	}
}
