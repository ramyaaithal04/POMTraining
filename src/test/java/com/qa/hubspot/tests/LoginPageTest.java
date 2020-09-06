package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.basetest.BaseTest;
import com.qa.hubspot.listeners.AllureReportListener;
import com.qa.hubspot.utilities.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(AllureReportListener.class)
@Epic("Login page module for hubspot")
@Story("3 test cases for login module")

public class LoginPageTest extends BaseTest{
	
	
	@Description("method to verify the login page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void VerifyLoginPageTitleTest()
	{
		String title=loginPage.getLoginPageTitle();
		System.out.println("The login page title is - "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("method to check if sign up link exists")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1)
	public void VerifySignUpLinkTest()
	{
		Assert.assertTrue(loginPage.isSignUpLinkExist());
	}
	
	@Description("method to verify the login feature")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void LoginTest()
	{
		loginPage.doLogin(prop.getProperty("Username").trim(), prop.getProperty("Password").trim());
		
	}
	
	
	
}
