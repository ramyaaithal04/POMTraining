package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utilities.Constants;
import com.qa.hubspot.utilities.ElementUtility;

public class HomePage {

	private WebDriver driver;
	ElementUtility elementUtilityObj;
	
	private By header=By.tagName("h1");
	private By accountName=By.xpath("//span[@class='account-name ']");
	private By settingsIcon=By.id("navSetting");
	private By contactsPageMainMenu=By.id("nav-primary-contacts-branch");
	private By contactsPageSubMenu=By.id("nav-secondary-contacts");
	private By SalesPageMainMenu=By.id("nav-primary-sales-branch");
	private By DealsMenu=By.id("nav-secondary-deals");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		elementUtilityObj=new ElementUtility(driver);
	}
	
	
	
	public String getHomePageTitle()
	{
		return elementUtilityObj.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 10);
	}
	
	
	public String getHeaderValue()
	{
		return elementUtilityObj.getElementValue(header);
		
	}
	
	public String getAccountName()
	{
		return elementUtilityObj.getElementValue(accountName);
	}
	
	
	public Boolean isSettingsIconExist() 
	{
		return elementUtilityObj.verifyElementDisplayed(settingsIcon);
	}
	
	public ContactsPage goToContactPage()
	{
		clickOnContactsMenu();		
		return new ContactsPage(driver);
	}
	
	
	public DealsPage goToDealsPage()
	{
		clickOnSalesMenu();		
		return new DealsPage(driver);
		
	}
	private void clickOnSalesMenu()
	{
		elementUtilityObj.waitForElementPresent(SalesPageMainMenu, 10);
		elementUtilityObj.doElementClick(SalesPageMainMenu);
		elementUtilityObj.waitForElementPresent(DealsMenu, 10);
		elementUtilityObj.doElementClick(DealsMenu);
	}

	private void clickOnContactsMenu()
	{
		elementUtilityObj.waitForElementPresent(contactsPageMainMenu, 10);
		elementUtilityObj.doElementClick(contactsPageMainMenu);
		elementUtilityObj.waitForElementPresent(contactsPageSubMenu, 10);
		elementUtilityObj.doElementClick(contactsPageSubMenu);
	}

}
