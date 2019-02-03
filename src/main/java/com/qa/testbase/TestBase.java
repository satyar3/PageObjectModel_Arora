package com.qa.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.customlistners.WebEventListener;
import com.qa.extentlisteners.ExtentListeners;
import com.qa.utils.DriverManager;
import com.qa.utils.Driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase
{
	private WebDriver driver;
	
	FileInputStream fs;
	Properties prop = new Properties();
	
	private String defaultUserName;
	private String defaultPassword;
	
	public String getDefaultUserName()
	{
		return defaultUserName;
	}

	public void setDefaultUserName(String defaultUserName)
	{
		this.defaultUserName = defaultUserName;
	}

	public String getDefaultPassword()
	{
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword)
	{
		this.defaultPassword = defaultPassword;
	}
	
	protected static EventFiringWebDriver e_driver;
	protected static WebEventListener eventlistener; 
	
	public Logger log = Logger.getLogger(TestBase.class);
	
	@BeforeSuite
	public void setUpFrameWork()
	{
		
		configLogging();
		Driverfactory.setGridPath("http://localhost:4444/wd/hub");
		Driverfactory.setConfigPropertyFile("src\\test\\resources\\properties\\Config.properties");
		
		try
		{
			fs = new FileInputStream(Driverfactory.getConfigPropertyFile());
			prop.load(fs);
			log.info("Config file loaded !!!");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		setDefaultUserName(prop.getProperty("defaultusername"));
		setDefaultPassword(prop.getProperty("defaultpassword"));
	}
	
	public void configLogging()
	{
		String log4jConfigFile = System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}
	
	public void openBrowser(String browserName)
	{
		boolean remote = Boolean.valueOf(prop.getProperty("remote"));
		
		Driverfactory.setRemote(remote);
		
		if(Driverfactory.isRemote() == false)
		{
			if (browserName.equals("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);
				log.info("Chrome Browser launched");
			}
			else if (browserName.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox Browser launched");
			}
		}
		
		else
		{
			DesiredCapabilities cap = null;

			if (browserName.equals("firefox"))
			{
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);
			}
			else if (browserName.equals("chrome"))
			{
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			}
			else if (browserName.equals("ie"))
			{
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplorer");
				cap.setPlatform(Platform.WINDOWS);
			}

			try
			{
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				log.info("Starting the session on Selenium Grid");
			}
			catch (MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
		
		DriverManager.setDriver(driver);
		
		// To generate logs in extent report Method-1
		/*e_driver = new EventFiringWebDriver(DriverManager.getDriver());
		eventlistener = new WebEventListener();
		e_driver.register(eventlistener);
		DriverManager.setDriver(e_driver);*/
		
		DriverManager.getDriver().manage().window().maximize();		
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		DriverManager.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//DriverManager.getDriver().get("https://www.zoho.com");
		
	}
	
	public void logInfo(String message)
	{
		ExtentListeners.testReport.get().info(message);
	}
	
	@AfterMethod
	public void tearDown()
	{
		logInfo("Test Completed !!");
		DriverManager.getDriver().quit();
	}
}
