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
	HomePage homePage;
	private WebDriver driver;
	public WelcomePage(WebDriver driver)
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

	@FindBy(css=".go_to_checkout")	
	WebElement btn_GoToCheckOut;

	@FindBy(css=".currentprice")	
	WebElement txt_currentPrice;

	@FindBy(css=".account_icon")
	WebElement btn_MyAccount;
	
	@FindBy(xpath=".//*[@id='post-31']/div/div/div/a[2]")
	WebElement lnk_YourDetails;
	
	@FindBy(id="wpsc_checkout_form_4")
	WebElement txt_Address;
	
	@FindBy(name="submit")
	WebElement btn_SubmitEditProfile;
	
	@FindBy(xpath=".//*[@id='account_logout']/a")
	WebElement btn_LogOut_WelcomePage;
	
	@FindBy(xpath=".//*[@id='header_cart']/a/span[1]")
	WebElement btn_ItemsInCart;
	
	
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
		WebDriverWait wait = new WebDriverWait(driver, 10);	
		wait.until(ExpectedConditions.visibilityOf(btn_LogOut_WelcomePage));
		PerformAction.sendTextToUI(txt_SearchProducts, txtProductName);
		txt_SearchProducts.sendKeys(Keys.ENTER);
		Reporter.log("Product searched is :"+txtProductName);
	}

	public void verifyProductAddedToCart(String txtProductName)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(btn_AddToCart));
		PerformAction.IsElementAvailable(btn_AddToCart);
		Reporter.log("Product   "+txtProductName+"   is successfully added to cart");
	}

	public void addToCart()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(btn_AddToCart));
		PerformAction.clickOnUIElement(btn_AddToCart);
	}
	public void getProductPrice()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(txt_currentPrice));
		strActualPrice = PerformAction.gerTextFromUIElement(txt_currentPrice);
	}
	String newAdress = "Bombay";
	String StrInitialAddress;
	
	public void goToMyAcountDetails()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(btn_MyAccount));
		PerformAction.clickOnUIElement(btn_MyAccount);
		wait.until(ExpectedConditions.visibilityOf(lnk_YourDetails));
		PerformAction.clickOnUIElement(lnk_YourDetails);
	}
	public TransactionResults myAccountEdit()
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(txt_Address));
		StrInitialAddress = PerformAction.gerTextFromUIElement(txt_Address);
		PerformAction.clearUIElement(txt_Address);
		PerformAction.sendTextToUI(txt_Address, newAdress);	
		PerformAction.clickOnUIElement(btn_SubmitEditProfile);
		return PageFactory.initElements(driver, TransactionResults.class);
	}
	
	public void verifyAcountUpdate()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(txt_Address));
		String CurrentAddress = PerformAction.gerTextFromUIElement(txt_Address);
		if (CurrentAddress.equalsIgnoreCase(StrInitialAddress)) {
			Reporter.log("Account details are not updated");
			System.out.println("Account details have not been updated successfully :"+ CurrentAddress +"  NEW ADD "+StrInitialAddress);
			Assert.fail();
		} else if(CurrentAddress.equalsIgnoreCase(newAdress)){
			Reporter.log("Account details have been updated successfully");
			System.out.println("Account details have been updated successfully");
		}
	}
	
	
	public CheckOutPage verifyGoToCart()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(btn_GoToCheckOut));
		PerformAction.IsElementAvailable(btn_GoToCheckOut);
		PerformAction.clickOnUIElement(btn_GoToCheckOut);
		return PageFactory.initElements(driver, CheckOutPage.class);
	}
	
	public CheckOutPage goToCart()
	{
		PerformAction.IsElementAvailable(btn_ItemsInCart);
		PerformAction.clickOnUIElement(btn_ItemsInCart);
		return PageFactory.initElements(driver, CheckOutPage.class);
	}
	
}
