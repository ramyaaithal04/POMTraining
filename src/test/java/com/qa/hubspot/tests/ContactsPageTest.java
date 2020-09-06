package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.basetest.BaseTest;
import com.qa.hubspot.utilities.Constants;
import com.qa.hubspot.utilities.ExcelUtility;

public class ContactsPageTest extends BaseTest {


	@BeforeClass
	public void contactsPageSetUp()
	{
		homePage=loginPage.doLogin(prop.getProperty("Username"),prop.getProperty("Password"));
		contactsPage=homePage.goToContactPage();
		
	}
	
	@Test(priority=2)
	public void verifyContactsPageHeaderTest()
	{
		String header=contactsPage.getContactsPageHeader();
		System.out.println("Contact page header is :"+header);
		Assert.assertTrue(header.contains(Constants.CONTACTS_PAGE_HEADER));
	}
	
	
	@Test(priority=1)
	public void verifyContactsPageTitleTest()
	{
		String title=contactsPage.getContactsPageTitle();
		System.out.println("Contact page title is :"+title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	
	@DataProvider()
	public Object[][] getContactsData()
	{
		Object data[][]=ExcelUtility.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider="getContactsData")
	public void createContactsTest(String email, String firstName, String lastName, String jobTitle)
	{
		Assert.assertTrue(contactsPage.createContacts(email,firstName,lastName,jobTitle));
		
	}

}
