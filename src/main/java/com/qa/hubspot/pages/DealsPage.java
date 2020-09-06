package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utilities.Constants;
import com.qa.hubspot.utilities.ElementUtility;

public class DealsPage {

	
	private WebDriver driver;
	ElementUtility elementUtilityObj;
	
	private By header=By.xpath("//h1[contains(@class,'IndexPageRedesignHeader')]");
	private By createDealPrimary=By.xpath("//span[contains(text(), 'Create deal')]");
	private By dealName=By.xpath("//input[@data-field='dealname']");
	private By dealStage=By.xpath("//button[@data-field='dealstage']");
	//private By dealStage=By.xpath("//li[@class='private-typeahead-result private-typeahead-result--selectable']");
	//private By dealStage=By.xpath("//li[@class='private-typeahead-result private-typeahead-result--selectable']//span[@class='private-dropdown__item__label']/span");
	private By amount=By.xpath("//input[@data-field='amount']");
	private By createDealSub=By.xpath("//span[text()='Create']");
	private By dealsBack=By.xpath("(//*[text()='Deals'])[position()=1]");
	
	public DealsPage(WebDriver driver){
		this.driver=driver;
		elementUtilityObj=new ElementUtility(driver);
	}
		
	public String getDealsPageHeader()	{
		elementUtilityObj.waitForElementPresent(header, 10);
		return elementUtilityObj.getElementValue(header);
	}
	
	public String getDealsPageTitle(){
		return elementUtilityObj.waitForTitlePresent(Constants.DEALS_PAGE_TITLE, 10);
		
	}
	
	public Boolean createDeals(String dealName, String dealStage, String amount )
	{
		
		elementUtilityObj.clickWhenReady(createDealPrimary, 10);
		elementUtilityObj.waitForElementToBeVisible(this.dealName, 10);
		elementUtilityObj.doElementSendKeys(this.dealName, dealName);
		elementUtilityObj.doElementSendKeys(this.dealStage, dealStage);
		elementUtilityObj.SelectJqueryDropDown(this.dealStage, dealStage);
		//elementUtilityObj.doElementSendKeys(this.amount, amount);
		elementUtilityObj.clickWhenReady(createDealSub,20);
		By dealNameAndAmount=By.xpath("//span[contains(@class,'DealTicketEditableTitle')]");
		elementUtilityObj.waitForElementToBeVisible(dealNameAndAmount, 20);
		System.out.println(driver.findElement(dealNameAndAmount).getText());
		Boolean flag=elementUtilityObj.verifyElementDisplayed(dealNameAndAmount);
		elementUtilityObj.clickWhenReady(dealsBack,10);
		return flag;
	}
}
