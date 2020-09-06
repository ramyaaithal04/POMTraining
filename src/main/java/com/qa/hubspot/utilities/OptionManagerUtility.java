package com.qa.hubspot.utilities;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManagerUtility {
	
	Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;
	
	public OptionManagerUtility(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOtions()
	{
		co=new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("Headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("Incognito"))) co.addArguments("--incognito");
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions()
	{
		fo=new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("Headless"))) fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("Incognito"))) fo.addArguments("--incognito");
		return fo;
	}
}
