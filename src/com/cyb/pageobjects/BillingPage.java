package com.cyb.pageobjects;

import java.util.concurrent.TimeUnit;

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

public class BillingPage {
	public WebDriver driver;
	BillingPage(WebDriver driver)
	{
		this.driver=driver;
	}
	// Identifying Elements 
	@FindBy(id="current_country")
	WebElement drp_CurrentCountry;

	@FindBy(name="wpsc_submit_zipcode")
	WebElement btn_CalculateState;

	@FindBy(id="wpsc_checkout_form_9")
	WebElement txt_emailAddress;

	@FindBy(id="wpsc_checkout_form_2")
	WebElement txt_FirstName;

	@FindBy(id="wpsc_checkout_form_3")
	WebElement txt_LastName;

	@FindBy(id="wpsc_checkout_form_4")
	WebElement txt_ShipingAddress;

	@FindBy(id="wpsc_checkout_form_5")
	WebElement txt_City;

	@FindBy(id="wpsc_checkout_form_6")
	WebElement txt_State;

	@FindBy(id="shippingSameBilling")
	WebElement chb_sameAsShipingAddress;

	@FindBy(id="wpsc_checkout_form_7")
	WebElement drp_selectCountryAddress;

	@FindBy(id="wpsc_checkout_form_18")
	WebElement txt_PhoneNumber;

	@FindBy(css=".make_purchase wpsc_buy_button")
	WebElement btn_Purchase;

	//Assuring Page has loaded
	public void waitForPageToLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpsc_checkout_form_9")));
		if (txt_emailAddress.isDisplayed()) {
			Reporter.log("Welcome to Calculate Shipping Price Page");
		} else {
			Reporter.log(" Calculate Shipping Price page is not displayed");
			Assert.fail();
		}
	}

	public void enterShippingAddressDetails()
	{
		String strCountry="India";
		PerformAction.selectValueFromDropDown(drp_CurrentCountry, strCountry);
		PerformAction.clickOnUIElement(btn_CalculateState);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpsc_checkout_form_9")));
		PerformAction.sendTextToUI(txt_emailAddress, "abc@testdomain.com");
		PerformAction.sendTextToUI(txt_FirstName, "Rema");
		PerformAction.sendTextToUI(txt_LastName, "Koul");
		PerformAction.sendTextToUI(txt_ShipingAddress, "Pune");
		PerformAction.sendTextToUI(txt_City, "Pune");
		PerformAction.sendTextToUI(txt_State, "Mah");
		PerformAction.selectValueFromDropDown(drp_selectCountryAddress, strCountry);
		PerformAction.sendTextToUI(txt_PhoneNumber, "9049986606");
		PerformAction.selectCheckBox(chb_sameAsShipingAddress);
	}


	public TransactionResults proceedToPurchase()
	{
		PerformAction.clickOnUIElement(btn_Purchase);
		return PageFactory.initElements(driver, TransactionResults.class);
	}
}
