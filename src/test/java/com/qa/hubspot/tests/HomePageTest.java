package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.basetest.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utilities.Constants;

public class HomePageTest extends BaseTest{
	
   HomePage homePage;
    
    @BeforeClass
    public void homePageSetUp()
    {
    		homePage=loginPage.doLogin(prop.getProperty("Username"), prop.getProperty("Password"));
    }
	
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		String title=homePage.getHomePageTitle();
		System.out.println("Home page title is :"+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHeaderValue()
	{
		String header=homePage.getHeaderValue();
		System.out.println("Home page header is :"+header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyAccountName()
	{
		String accountName=homePage.getAccountName();
		Assert.assertEquals(accountName, prop.getProperty("Accountname").trim());
	}
	
	@Test(priority=4)
	public void verifySettingsIconExist()
	{
		Assert.assertTrue(homePage.isSettingsIconExist());
	}
	
}
