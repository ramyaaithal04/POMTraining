package com.qa.hubspot.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.DealsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

public class BaseTest {
	
	WebDriver driver;
	BasePage basePage;	
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;
	public DealsPage dealsPage;
	
//	@Parameters({"browser"})
//	@BeforeTest	
//	public void setUp(String browserName)
//	{
//		basePage=new BasePage();
//		prop=basePage.init_prop();
//		prop.setProperty("Browser", browserName);
//		driver=basePage.init_driver(prop);
//		loginPage=new LoginPage(driver);
//	
//	}
	
	
	@BeforeTest	
	public void setUp()
	{
		basePage=new BasePage();
		prop=basePage.init_prop();
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);
	
	}

	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
}
