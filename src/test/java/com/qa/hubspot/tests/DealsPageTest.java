package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.basetest.BaseTest;
import com.qa.hubspot.utilities.Constants;
import com.qa.hubspot.utilities.ExcelUtility;

public class DealsPageTest extends BaseTest {
	
	@BeforeClass
	public void DealsPageSetUp()
	{
		homePage=loginPage.doLogin(prop.getProperty("Username"),prop.getProperty("Password"));
		dealsPage=homePage.goToDealsPage();
		
	}
	
	@Test(priority=2)
	public void verifyDealsPageHeaderTest()
	{
		String header=dealsPage.getDealsPageHeader();
		System.out.println("Deals page header is :"+header);
		Assert.assertTrue(header.contains(Constants.DEALS_PAGE_HEADER));
	}
	
	
	@Test(priority=1)
	public void verifyDealsPageTitleTest()
	{
		String title=dealsPage.getDealsPageTitle();
		System.out.println("Deals page title is :"+title);
		Assert.assertEquals(title, Constants.DEALS_PAGE_TITLE);
	}
	
	
	@DataProvider()
	public Object[][] getDealsData()
	{
		Object data[][]=ExcelUtility.getTestData(Constants.DEALS_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider="getDealsData")
	public void createDealsTest(String dealName, String dealSatge, String amount)
	{
		Assert.assertTrue(dealsPage.createDeals(dealName, dealSatge, amount));
		
	}

}
