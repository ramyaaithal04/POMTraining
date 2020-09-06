package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utilities.Constants;
import com.qa.hubspot.utilities.ElementUtility;

import io.qameta.allure.Step;

public class LoginPage{
	
	private WebDriver driver;
	ElementUtility elementUtilityObj;
	
	private By emailId=By.id("username");
	private By password=By.id("password");
	private By loginButton=By.id("loginBtn");
	private By signUpLink=By.linkText("Sign up");
	
	public LoginPage(WebDriver driver)
	{
		elementUtilityObj=new ElementUtility(driver);
		this.driver=driver;
	}
	
	@Step("getting page login page title")
	public String getLoginPageTitle()
	
	{
		
		return elementUtilityObj.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("getting signup link exists on login page")
	public Boolean isSignUpLinkExist()
	{
		return elementUtilityObj.verifyElementDisplayed(signUpLink);
	}
	
	@Step("Userlogin with username :{0} and password :{1}")
	public HomePage doLogin(String usr, String pswd)
	{
		elementUtilityObj.doElementSendKeys(emailId, usr);
		elementUtilityObj.doElementSendKeys(password, pswd);
		elementUtilityObj.doElementClick(loginButton);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HomePage(driver);
	}
}
