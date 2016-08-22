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

public class CheckOutPage {
	private WebDriver driver;
	CheckOutPage(WebDriver driver)
	{
		this.driver=driver;
	}

	// Identifying Elements 
	@FindBy(name="submit")
	WebElement btn_Remove;
	
	@FindBy(className="step2")
	WebElement btn_continueCheckOut;
	
	@FindBy(className="entry-content")
	WebElement txt_EmptyCartMessage;
	WebDriverWait wait = new WebDriverWait(driver, 10);
	//Assuring Page has loased
	public void waitForPageToLoad()
	{
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
		if (btn_Remove.isDisplayed()) {
			Reporter.log("Welcome to CheckOut Page");
		} else {
			Reporter.log(" Check out page is not displayed");
			Assert.fail();
		}
	}
	
	public void ProceedToCheckout()
	{
		PerformAction.clickOnUIElement(btn_continueCheckOut);
	
	}
	public void RemoveItemFromCart()
	{
		String strSuccessEmptyCartMsg = "Oops, there is nothing in your cart.";
		PerformAction.clickOnUIElement(btn_Remove);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("entry-content")));
		if (PerformAction.gerTextFromUIElement(txt_EmptyCartMessage).contains(strSuccessEmptyCartMsg)) {
			Reporter.log("Cart has been emptied successfully");
		} else {
			Reporter.log("Cart has not been emptied successfully");
		}
	}
	public BillingPage verifyGoToCart()
	{
		PerformAction.clickOnUIElement(btn_continueCheckOut);
		return PageFactory.initElements(driver, BillingPage.class);
	}

}
