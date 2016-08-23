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
	/*String strURL ="http://store.demoqa.com/products-page/your-account/";
	String strProduct="Apple iPhone 4S 16GB SIM-Free - Black";
*/
	@Parameters({"strBrowser","strUser","strPassword","strURL"})
	@BeforeTest
	public void preRequisite(String strBrowser,String strUser,String strPassword,String strURL)
	{
		driver=WebDriverManager.getBrowserDriver(strBrowser);
		homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.navigateToURL(strURL);
		homePage.enterUserName(strUser);
		homePage.enterPassword(strPassword);
		welcomePage = homePage.submitUserContext();
	}
	
	@Parameters({"strProduct","strCountry","txtEmailAddress","txtFirstName","txtLastName","txtShipingAddress","txtCity","txtState","txtPhoneNumber"})
	@Test(priority=0)
	//Submit an order for an Apple iPhone4s 16GB SIM-Free – Black 
	public void submitAnOrder(String strProduct,String strCountry,String txtEmailAddress,String txtFirstName,String txtLastName,String txtShipingAddress,String txtCity,String txtState,String txtPhoneNumber)
	{
		welcomePage.waitForPageToLoad();
		
		welcomePage.searchForProduct(strProduct);
		welcomePage.verifyProductAddedToCart(strProduct);
		welcomePage.getProductPrice();
		welcomePage.addToCart();
		checkOutPage = welcomePage.verifyGoToCart();
		checkOutPage.waitForPageToLoad();
		//checkOutPage.ProceedToCheckout();
		billingPage = checkOutPage.ProceedToCheckout();
		billingPage.waitForPageToLoad();
		billingPage.enterShippingAddressDetails(strCountry,txtEmailAddress,txtFirstName,txtLastName,txtShipingAddress,txtCity,txtState,txtPhoneNumber);
		transactionResults = billingPage.proceedToPurchase();
		transactionResults.waitForPageToLoad();
		transactionResults.getFinalProductPrice();
		transactionResults.verifySuccessfulOrderPlacement();
		welcomePage = transactionResults.compareProductPrice();
	}

	//@Test(priority=1)
/*	//verify the Total Price
	public void verifyTotalPrice()
	{
		transactionResults.compareProductPrice();
	}*/

	
	@Parameters({"strBrowser","strUser","strPassword","strURL"})
	@Test(priority=1)
	/*Verify updating your account details is saved and retrieved after 
	logging out and back in using the My Account link.*/
	public void verifyAccountDetails(String strBrowser,String strUser,String strPassword,String strURL) throws InterruptedException
	{
		//welcomePage = transactionResults.compareProductPrice();
		welcomePage.waitForPageToLoad();
		welcomePage.goToMyAcountDetails();
		transactionResults = welcomePage.myAccountEdit();
		transactionResults.logOut();
		preRequisite(strBrowser, strUser, strPassword, strURL);
		welcomePage.waitForPageToLoad();
		welcomePage.goToMyAcountDetails();
		welcomePage.verifyAcountUpdate();	
	}
	
	@Parameters({"strProduct"})
	@Test(priority=2)
	/*Verify removing all items from your cart produces an empty cart message.*/
	public void verifyRemovingItemEmptyCartMessage(String strProduct)
	{
		welcomePage.searchForProduct(strProduct);
		welcomePage.verifyProductAddedToCart(strProduct);
		welcomePage.addToCart();
		checkOutPage = welcomePage.verifyGoToCart();	
		//checkOutPage = welcomePage.goToCart();	
		checkOutPage.waitForPageToLoad();
		checkOutPage.RemoveItemFromCart();
		PerformAction.closeAllOpenBrowsers(driver);
	}

	@AfterTest
	public void tearDown()
	{
		PerformAction.closeAllOpenBrowsers(driver);
	}
}
