package com.cyb.pageobjects;

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
	public CheckOutPage(WebDriver driver)
	{
		this.driver=driver;
	}

	// Identifying Elements 
	//@FindBy(name="submit")
	@FindBy(xpath=".//*[@id='checkout_page_container']/div[1]/table/tbody/tr[2]/td[6]/form/input[4]")
	WebElement btn_Remove;

	@FindBy(css=".step2")
	WebElement btn_continueCheckOut;

	@FindBy(css=".entry-content")
	WebElement txt_EmptyCartMessage;

	//Assuring Page has loaded
	public void waitForPageToLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(btn_Remove));

		if (btn_Remove.isDisplayed()) {
			Reporter.log("Welcome to CheckOut Page");
		} else {
			Reporter.log(" Check out page is not displayed");
			Assert.fail();
		}
	}

	public void RemoveItemFromCart()
	{
		String strSuccessEmptyCartMsg = "Oops, there is nothing in your cart.";
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(btn_Remove));
		PerformAction.clickOnUIElement(btn_Remove);
		Reporter.log("Clicked on Remove Button to empty the cart");
	//	PerformAction.clickOnUIElement(btn_Remove);
		wait.until(ExpectedConditions.visibilityOf(txt_EmptyCartMessage));
		if (PerformAction.gerTextFromUIElement(txt_EmptyCartMessage).contains(strSuccessEmptyCartMsg)) {
			Reporter.log("Cart has been emptied successfully.Cart message says : "+strSuccessEmptyCartMsg);
		} else {
			Reporter.log("Cart has not been emptied successfully");
		}
	}
	public BillingPage ProceedToCheckout()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(btn_continueCheckOut));
		PerformAction.clickOnUIElement(btn_continueCheckOut);
		Reporter.log("Clicked on Continue to Check Out");
		return PageFactory.initElements(driver, BillingPage.class);
	}

}
