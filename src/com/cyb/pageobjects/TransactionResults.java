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

public class TransactionResults {
	public WebDriver driver;
	WelcomePage welcomePage;
	String StrFinalPrice;
	TransactionResults(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath=".//*[@id='post-30']/div/div[2]/p[1]")
	WebElement txt_SuccessMessage;

	@FindBy(xpath=".//*[@id='post-30']/div/div[2]/table/tbody/tr/td[4]")
	WebElement txt_ItemTotal;
	
	@FindBy(xpath=".//*[@id='account_logout']/a")
	WebElement btn_LogOut;
	
	//Assuring Page has loaded
	public void waitForPageToLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(txt_SuccessMessage));
		if (txt_SuccessMessage.isDisplayed()) {
			Reporter.log("You have successfully Placed the Order");
		} else {
			Reporter.log("Order not placed .Retry!");
			Assert.fail();
		}
	}
	//Write comparison code
	public void getFinalProductPrice(){
		StrFinalPrice = PerformAction.gerTextFromUIElement(txt_ItemTotal);	
	}
	
	public WelcomePage compareProductPrice()
	{
		if (welcomePage.strActualPrice.equalsIgnoreCase(StrFinalPrice)) {
			Reporter.log("Total price displayed on transaction Page :"+StrFinalPrice+"  is same as Product Selection Page :"+welcomePage.strActualPrice);
		} else {
			Reporter.log("Total price displayed on transaction Page :"+StrFinalPrice+"  is NOT same as Product Selection Page :"+welcomePage.strActualPrice);
		}
		return PageFactory.initElements(driver, WelcomePage.class);
	}
	public void verifySuccessfulOrderPlacement()
	{
		PerformAction.IsElementAvailable(txt_SuccessMessage);
		
	}
	public void logOut()
	{
		PerformAction.clickOnUIElement(btn_LogOut);
	}
}
