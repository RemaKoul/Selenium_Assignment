package com.cyb.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.cyb.pageobjects.HomePage;
import com.cyb.pageobjects.TransactionResults;

public class WebDriverManager {

	public static WebDriver driver;
	public static WebDriver getBrowserDriver(String strBrowser)
	{
		try {
			if (strBrowser.equalsIgnoreCase("FF")) {
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				//driver.get("http://store.demoqa.com/products-page/your-account/");
			}
			else if(strBrowser.equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.ie.driver","D:/IEDriverServer_Win32_2.33.0/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				
			}
			else if(strBrowser.equalsIgnoreCase("CH"))
			{
				System.setProperty("webdriver.chrome.driver","D:/chromedriver_win32_2.21/chromedriver.exe");
				driver=new ChromeDriver();
				driver.manage().window().maximize();
			}
			Thread.sleep(1000);
			return driver;
			//return PageFactory.initElements(driver, HomePage.class);
		} catch (Exception e) {
			Reporter.log("Driver creation failed");
						Assert.fail();
			return null;
		}

		


	}


}
