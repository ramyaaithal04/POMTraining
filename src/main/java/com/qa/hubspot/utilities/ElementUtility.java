package com.qa.hubspot.utilities;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtility {
	
		public WebDriver driver;
		static final String SelectAll = "ALL";
		JScriptExecutorUtility jsUtil;
		
		public ElementUtility(WebDriver driver)
		{
			this.driver=driver;
			jsUtil=new JScriptExecutorUtility(driver);
		}
		
		
	/**
	 * method to find the web element
	 * @param locator
	 * @return WebElement
	 */
		public WebElement doFindElement(By locator)
		{
			WebElement element;
			element=driver.findElement(locator);
			if (Boolean.parseBoolean(BasePage.flashElement))
				jsUtil.flash(element);
			
			return element;
		}
		
		
		
		/**
		 * method to find list of web elements
		 * @param locator
		 * @return List<WebElement>
		 */
		
		 public List<WebElement> doFindElements(By locator) 
		 {
			 List<WebElement> elementList= driver.findElements(locator); 
			 return elementList; 
		 }
		 
			
		/**
		 * method to enter the values
		 * @param locator
		 * @param value
		 */	
		public void doElementSendKeys(By locator, String value)
		{
			WebElement element;
			element=doFindElement(locator);
			element.sendKeys(value);
			
		}
		
		/**
		 * method to perform click operation
		 * @param locator
		 */	
		public void doElementClick(By locator)
		{
			WebElement element;
			element=doFindElement(locator);
			element.click();
					
		}
		
		/**
		 * method to perform specific Link click operation
		 * @param locator
		 */	
		public void doLinkClick(By locator, String value)
		{
			List<WebElement> linksList=doFindElements(locator);
			String linkText;
			for(int i=0;i<linksList.size();i++)
			{
				linkText=linksList.get(i).getText();
				if (linkText.equalsIgnoreCase(value))
				 {
					linksList.get(i).click();
					break;
				}
			}
			
		}
		

		/**
		 * method to get the text from the web element
		 * @param locator
		 * @return
		 */
		public String getElementValue(By locator)
		{
			WebElement element;
			element=doFindElement(locator);
			String value=element.getText();
			return value;
					
		}
		
		/**
		 * method to verify if the web element is displayed
		 * @param Locator
		 * @return
		 */	
		public Boolean verifyElementDisplayed(By locator)
		{
			WebElement element;
			element=doFindElement(locator);
			Boolean status;
			status=element.isDisplayed();
			return status;
		}
		
		/********************************DROP DOWN Utility***************************************/
		/**
		 * method to select the value from the dropdown by visible text
		 * @param locator
		 * @param value
		 */
			
		public void doSelectDropDownByVisibleText(By locator, String value)
		{
			Select select=new Select(doFindElement(locator));	
			select.selectByVisibleText(value);
		 
		}
		
		/**
		 * method to select the value from the dropdown by value attribute
		 * @param locator
		 * @param value
		 */
				
		public void doSelectDropDownByValue(By locator, String value)
		{

			Select select=new Select(doFindElement(locator));	
			select.selectByValue(value);
		 
		}
		
		/**
		 * method to select the value by index from the dropdown
		 * @param locator
		 * @param value
		 */
				
		public void doSelectDropDownByIndex(By locator, int index)
		{

			Select select=new Select(doFindElement(locator));		
			select.selectByIndex(index);
		 
		}
		
	/**
	 * method to get the count of options available in the dropdown
	 * @param locator
	 * @return int
	 */
		public int getDropDownOptionCount(By locator)
		{

			Select select=new Select(doFindElement(locator));		
			List<WebElement> optionList=select.getOptions();
			return optionList.size();
		 
		}
		
		/**
		 * method to select the value from the dropdown with out using the select class
		 * @param locator
		 * @param value
		 */
		
		public void doSelectDropDownWithoutSelectClass(By locator, String value)
		{
			List<WebElement> optionList=doFindElements(locator);	
			for(WebElement e:optionList)
			{
				if (e.getText().equals(value))
				{
					e.click();
					break;
				}
			}

		 
		}
		/**
		 * method to select single or multi or all values from a jquerydropdown
		 * @param locator
		 * @param value
		 */
		
		public void SelectJqueryDropDown(By locator, String... value) {
			List<WebElement> optionList = driver.findElements(locator);
			if (value[0].contentEquals(ElementUtility.SelectAll)) {
				try {
					for (WebElement e : optionList)
						e.click();

				} catch (Exception e) {

				}
			}

			else {
				for (int i = 0; i < value.length; i++) {
					for (WebElement e : optionList) {
						if (e.getText().equals(value[i])) {
							e.click();
							break;
						}
					}
				}
			}
		}
		
		/*******************************User Actions Using Actions Class******************************/
		/**
		 * method to do send keys using Actions class
		 * @param locator
		 * @param value
		 */
		public void doSendKeysUsingActions(By locator, String value)
		{
			Actions action=new Actions(driver);		
			action.sendKeys(doFindElement(locator), value).perform();
		}
		
		/**
		 * method to do click using Actions class
		 * @param locator
		 */
		public void doClickUsingActions(By locator){
			
			Actions action=new Actions(driver);		
			action.click(doFindElement(locator)).perform();
			
			
		}

		/******************************************explicit Wait****************************************/
		
		/**
		 * methods to wait for the web element to present in DOM for the specified timeOut
		 * @param locator
		 * @param timeOut
		 * @return Web Element
		 */
		
		public WebElement waitForElementPresent(By locator, int timeOut)
		{
			WebDriverWait wait=new WebDriverWait(driver,timeOut);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		/**
		 * methods to wait for the title to display for the specified timeout
		 * @param title
		 * @param timeOut
		 * @return String
		 */

		public String waitForTitlePresent(String title, int timeOut)
		{
			WebDriverWait wait=new WebDriverWait(driver,timeOut);
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
					
		}
	
		// from Naveen
		
		public List<WebElement> visibilityofAllElements(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		public void getPageLinksText(By locator, int timeOut) {
			visibilityofAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
		}
		
		public WebElement waitForElementToBeVisible(By locator, int timeOut) {
			WebElement element = doFindElement(locator);
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.visibilityOf(element));

		}

		public boolean waitForUrl(String url, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.urlContains(url));
		}

		public Alert waitForAlertToBePresent(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.alertIsPresent());
		}

		public WebElement waitForElementToBeClickable(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public void clickWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
		}

		public WebElement waitForElementWithFluentWait(By locator, int timeOut, int interval) {

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(interval))
					.ignoring(NoSuchElementException.class);

			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));}
		

}
