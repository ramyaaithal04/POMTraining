package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utilities.OptionManagerUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	public static String flashElement;
	public static ThreadLocal<WebDriver> tldriver=new ThreadLocal<WebDriver>();
	//public static ElementUtility elementUtilityObj;
	//OptionManagerUtility optionManager;
/**
 * This methos is used to initilize the webdriver and launch the url
 * @param browserOptionManagerUtility
 * @return WebDriver
 */
	
	public WebDriver init_driver(Properties prop)
	{
		flashElement=prop.getProperty("Highlight");
		String browser=prop.getProperty("Browser");
		System.out.println("The browser name is :"+browser);
		OptionManagerUtility optionManager=new OptionManagerUtility(prop);
		
			
		if (browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
		 	//driver=new ChromeDriver(optionManager.getChromeOtions());
			tldriver.set(new ChromeDriver(optionManager.getChromeOtions()));
		}
		else if (browser.equalsIgnoreCase("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
	 		//driver=new FirefoxDriver(optionManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
		}
		else if (browser.equalsIgnoreCase("safari"))
			//driver=new SafariDriver();
			tldriver.set(new SafariDriver());
		else
			System.out.println("Please pass the correct browser name.");
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();	
		getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	//    elementUtilityObj=new ElementUtility(driver);
		getDriver().get(prop.getProperty("URL"));
		
		return getDriver();
				
	}
	/**
	 * get driver using threadlocal
	 */
	
	public static synchronized WebDriver getDriver() {
				
		return tldriver.get();
	}
	/**
	 * this method is used to get the properties from the config.properties file
	 * @return properties
	 */
	public Properties init_prop()
	{
		
		String path=null;
		String env=null;
		Properties prop=new Properties();
		 
		try {
			
			env=System.getProperty("env");
					
			if(env==null)
			{
				path="./src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
				System.out.println("Running on production environment.");
			}
				
			else
			{
				switch (env){
				case "qa":
					path="./src\\main\\java\\com\\qa\\hubspot\\config\\config.qa.properties";
					System.out.println("Running on environment "+env);
					break;
				default:
					System.out.println("Please pass the correct environment.");
					break;
				}
				
			}
		
			FileInputStream ip=new FileInputStream(path);
			  prop.load(ip);
		    } catch (FileNotFoundException e) {
					e.printStackTrace();
		      } catch (IOException e) {
					e.printStackTrace();
			}
		
		 return prop;
		 
	}
	
	
	/**
	 *method to get the screenshot
	 * 
	 */
	public String getScreenshot()
	{
		File scr=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/Screenshots/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(scr, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
	}
	
	
	
}
