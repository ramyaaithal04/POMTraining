package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utilities.Constants;
import com.qa.hubspot.utilities.ElementUtility;

public class ContactsPage extends BasePage{
	
	//private WebDriver driver;
	ElementUtility elementUtilityObj;
	
	private By header=By.cssSelector("h1[class*='IndexPageRedesignHeader']");
	private By createContactPrimary=By.xpath("//span[contains(text(), 'Create contact')]");
	private By email=By.xpath("//input[@data-field='email']");
	private By firstName=By.xpath("//input[@data-field='firstname']");
	private By lastName=By.xpath("//input[@data-field='lastname']");
	private By jobTitle=By.xpath("//input[@data-field='jobtitle']");
	private By createContactSecondary=By.xpath("(//span[contains(text(), 'Create contact')])[position()=2]");
	private By ContactsBack=By.xpath("(//*[text()='Contacts'])[position()=1]");
	
	
	public ContactsPage(WebDriver driver){
		//this.driver=driver;
		elementUtilityObj=new ElementUtility(driver);
	}
		
	public String getContactsPageHeader()	{
		elementUtilityObj.waitForElementPresent(header, 10);
		return elementUtilityObj.getElementValue(header);
	}
	
	public String getContactsPageTitle(){
		return elementUtilityObj.waitForTitlePresent(Constants.CONTACTS_PAGE_TITLE, 10);
		
	}
	
	public Boolean createContacts(String email,String firstName, String lastName, String jobTitle )
	{
		
		elementUtilityObj.clickWhenReady(createContactPrimary, 10);
		elementUtilityObj.waitForElementToBeVisible(this.email, 10);
		elementUtilityObj.doElementSendKeys(this.email, email);
		elementUtilityObj.doElementSendKeys(this.firstName, firstName);
		elementUtilityObj.doElementSendKeys(this.lastName, lastName);
		elementUtilityObj.waitForElementToBeVisible(this.jobTitle, 10);
		elementUtilityObj.doElementSendKeys(this.jobTitle, jobTitle);
		elementUtilityObj.clickWhenReady(createContactSecondary,20);
		By contactName=By.xpath("//span[contains(@class,'CompanyContactEditableTitle')]");
		elementUtilityObj.waitForElementToBeVisible(contactName, 20);
		Boolean flag=elementUtilityObj.verifyElementDisplayed(contactName);
		elementUtilityObj.clickWhenReady(ContactsBack,10);
		return flag;
	}

}
