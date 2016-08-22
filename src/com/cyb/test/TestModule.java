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
	public HomePage HomePage;
	public WelcomePage WelcomePage;
	public CheckOutPage CheckOutPage;
	public TransactionResults TransactionResults;
	public BillingPage BillingPage;
	//rrf
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
		PageFactory.initElements(this.driver, HomePage.class);
		HomePage.navigateToURL(strURL);
		HomePage.enterUserName(strUser);
		HomePage.enterPassword(strPassword);
		WelcomePage = HomePage.submitUserContext();
	}

	@Test
	//Submit an order for an Apple iPhone4s 16GB SIM-Free – Black 
	public void submitAnOrder()
	{
		WelcomePage.waitForPageToLoad();
		WelcomePage.searchForProduct(strProduct);
		WelcomePage.verifyProductAddedToCart(strProduct);
		WelcomePage.addToCart();
		WelcomePage.getProductPrice();
		WelcomePage.verifyGoToCart();	
		CheckOutPage.waitForPageToLoad();
		CheckOutPage.ProceedToCheckout();
		CheckOutPage.verifyGoToCart();
		BillingPage.waitForPageToLoad();
		BillingPage.enterShippingAddressDetails();
		BillingPage.proceedToPurchase();
		TransactionResults.waitForPageToLoad();
		TransactionResults.verifySuccessfulOrderPlacement();
	}

	@Test
	//verify the Total Price
	public void verifyTotalPrice()
	{
		TransactionResults.getFinalProductPrice();
		TransactionResults.compareProductPrice();
	}

	@Parameters({"strBrowser","strUser","strPassword"})
	@Test
	/*Verify updating your account details is saved and retrieved after 
	logging out and back in using the My Account link.*/
	public void verifyAccountDetails(String strBrowser,String strUser,String strPassword)
	{
		WelcomePage.waitForPageToLoad();
		WelcomePage.goToMyAcountDetails();
		WelcomePage.myAccountEdit();
		TransactionResults.logOut();
		preRequisite(strBrowser, strUser, strPassword);
		WelcomePage.waitForPageToLoad();
		WelcomePage.goToMyAcountDetails();
		WelcomePage.verifyAcountUpdate();	
	}

	@Test
	/*Verify removing all items from your cart produces an empty cart message.*/
	public void verifyRemovingItemEmptyCartMessage()
	{
		CheckOutPage.RemoveItemFromCart();
	}

	@AfterTest
	public void tearDown()
	{
		PerformAction.closeAllOpenBrowsers(driver);
	}
}
