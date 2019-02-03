package com.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.extentlisteners.ExtentListeners;
import com.qa.utils.DriverManager;

/**
 * 
 * @author Satyaranjan.Muduli
 *
 * @param <T>
 * 
 * 1 - Initialize page factory
 * 2 - Load condition
 */


public abstract class BasePage<T>
{
	protected WebDriver driver;
	
	public BasePage()
	{
		this.driver = DriverManager.getDriver();
	}
	
	//Initializing the page factory and which class we are navigating to
	public T openPage(Class<T> className)
	{
		T page = null;
		driver = DriverManager.getDriver();
		AjaxElementLocatorFactory ajaxElemLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
		page = PageFactory.initElements(driver, className);
		PageFactory.initElements(ajaxElemLocatorFactory, page);
		ExpectedCondition pageloadCondition = ((BasePage) page).getPageLoadCondition();
		waitForPageToLoad(pageloadCondition);
		
		return page;
	}
	
	protected abstract ExpectedCondition getPageLoadCondition();
	
	private void waitForPageToLoad(ExpectedCondition pageloadCondition)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(pageloadCondition);
	}
	
	// To generate logs in extent report Method-2
	public void click(WebElement element, String elementName)
	{
		element.click();
		ExtentListeners.testReport.get().info("Clicking on the element : " + elementName);
	}

	public void typing(WebElement element, String elementName, String value)
	{
		element.sendKeys(value);
		ExtentListeners.testReport.get().info("Typing in the element : " + elementName + " and entered value is : " + value);
	}
}
