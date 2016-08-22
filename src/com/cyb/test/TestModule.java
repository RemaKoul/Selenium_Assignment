package com.cyb.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyb.pageobjects.BillingPage;
import com.cyb.pageobjects.CheckOutPage;
import com.cyb.pageobjects.HomePage;
import com.cyb.pageobjects.TransactionResults;
import com.cyb.pageobjects.WelcomePage;
import com.cyb.utility.PerformAction;
import com.cyb.utility.WebDriverManager;

public class TestModule {
	public HomePage homePage;
	public WelcomePage welcomePage;
	public CheckOutPage checkOutPage;
	public TransactionResults transactionResults;
	public BillingPage billingPage;
	private WebDriver driver;
	//String strUser="demoOrder";
	//String strPassword ="demoOrder";
	String strURL ="http://store.demoqa.com/products-page/your-account/";
	String strProduct="Apple iPhone 4S 16GB SIM-Free - Black";
	//	String strBrowser ="IE";

	@Parameters({"strBrowser","strUser","strPassword"})
	@BeforeTest
	public void preRequisite(String strBrowser,String strUser,String strPassword)
	{
		this.driver=WebDriverManager.getBrowserDriver(strBrowser);
		homePage = PageFactory.initElements(this.driver, HomePage.class);
		homePage.navigateToURL(strURL);
		homePage.enterUserName(strUser);
		homePage.enterPassword(strPassword);
		welcomePage = homePage.submitUserContext();
	}

	@Test
	//Submit an order for an Apple iPhone4s 16GB SIM-Free – Black 
	public void submitAnOrder()
	{
		welcomePage.waitForPageToLoad();
		welcomePage.searchForProduct(strProduct);
		welcomePage.verifyProductAddedToCart(strProduct);
		welcomePage.addToCart();
		welcomePage.getProductPrice();
		welcomePage.verifyGoToCart();	
		checkOutPage.waitForPageToLoad();
		checkOutPage.ProceedToCheckout();
		checkOutPage.verifyGoToCart();
		billingPage.waitForPageToLoad();
		billingPage.enterShippingAddressDetails();
		billingPage.proceedToPurchase();
		transactionResults.waitForPageToLoad();
		transactionResults.verifySuccessfulOrderPlacement();
	}

	@Test
	//verify the Total Price
	public void verifyTotalPrice()
	{
		transactionResults.getFinalProductPrice();
		transactionResults.compareProductPrice();
	}

	@Parameters({"strBrowser","strUser","strPassword"})
	@Test
	/*Verify updating your account details is saved and retrieved after 
	logging out and back in using the My Account link.*/
	public void verifyAccountDetails(String strBrowser,String strUser,String strPassword)
	{
		welcomePage.waitForPageToLoad();
		welcomePage.goToMyAcountDetails();
		welcomePage.myAccountEdit();
		transactionResults.logOut();
		preRequisite(strBrowser, strUser, strPassword);
		welcomePage.waitForPageToLoad();
		welcomePage.goToMyAcountDetails();
		welcomePage.verifyAcountUpdate();	
	}

	@Test
	/*Verify removing all items from your cart produces an empty cart message.*/
	public void verifyRemovingItemEmptyCartMessage()
	{
		checkOutPage.RemoveItemFromCart();
	}

	@AfterTest
	public void tearDown()
	{
		PerformAction.closeAllOpenBrowsers(driver);
	}
}
