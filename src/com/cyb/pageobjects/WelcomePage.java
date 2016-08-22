package com.cyb.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.cyb.utility.PerformAction;

public class WelcomePage {
	HomePage HomePage;
	private WebDriver driver;
	WelcomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	//Declaring Global Variable for Price comparison
	String strActualPrice;

	// Identifying Elements 
	@FindBy(id="logo")
	WebElement img_logo;

	@FindBy(name="s")
	WebElement txt_SearchProducts;

	@FindBy(name="Buy")
	WebElement btn_AddToCart;

	@FindBy(className="go_to_checkout")	
	WebElement btn_GoToCheckOut;

	@FindBy(className="currentprice")	
	WebElement txt_currentPrice;

	@FindBy(className="account_icon")
	WebElement btn_MyAccount;
	
	@FindBy(xpath=".//*[@id='post-31']/div/div/div/a[2]")
	WebElement lnk_YourDetails;
	
	@FindBy(id="wpsc_checkout_form_4")
	WebElement txt_Address;
	
	@FindBy(name="submit")
	WebElement btn_SubmitEditProfile;
	
	//Assuring Page has loased
	public void waitForPageToLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo")));
		if (img_logo.isDisplayed()) {
			Reporter.log("Welcome to Demo QA.");
		} else {
			Reporter.log("Demo QA page is not displayed");
			Assert.fail();
		}
	}

	//search product
	public void searchForProduct(String txtProductName)
	{
		PerformAction.sendTextToUI(txt_SearchProducts, txtProductName);
		txt_SearchProducts.sendKeys(Keys.ENTER);
	}

	public void verifyProductAddedToCart(String txtProductName)
	{
		PerformAction.IsElementAvailable(btn_AddToCart);
		Reporter.log("Product   "+txtProductName+"   is successfully added to cart");
	}

	public void addToCart()
	{
		PerformAction.clickOnUIElement(btn_AddToCart);
	}
	public void getProductPrice()
	{
		strActualPrice = PerformAction.gerTextFromUIElement(txt_currentPrice);
	}
	String newAdress = "Bombay";
	String StrInitialAddress;
	
	public void goToMyAcountDetails()
	{
		PerformAction.clickOnUIElement(btn_MyAccount);
		PerformAction.clickOnUIElement(lnk_YourDetails);
	}
	public void myAccountEdit()
	{
		StrInitialAddress = PerformAction.gerTextFromUIElement(txt_Address);
		PerformAction.sendTextToUI(txt_Address, newAdress);	
		PerformAction.clickOnUIElement(btn_SubmitEditProfile);
	}
	
	public void verifyAcountUpdate()
	{
		String CurrentAddress = PerformAction.gerTextFromUIElement(txt_Address);
		if (CurrentAddress.equalsIgnoreCase(StrInitialAddress)) {
			Reporter.log("Account details are not updated");
			Assert.fail();
		} else if(CurrentAddress.equalsIgnoreCase(newAdress)){
			Reporter.log("Account details have been updated successfully");
		}
	}
	
	
	public CheckOutPage verifyGoToCart()
	{
		PerformAction.IsElementAvailable(btn_GoToCheckOut);
		PerformAction.clickOnUIElement(btn_GoToCheckOut);
		return PageFactory.initElements(driver, CheckOutPage.class);
	}
	
	
}
