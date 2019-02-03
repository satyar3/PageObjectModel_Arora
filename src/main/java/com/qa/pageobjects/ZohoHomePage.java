package com.qa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.utils.DriverManager;

public class ZohoHomePage extends BasePage
{
	/*WebDriver driver;
	
	public ZohoHomePage(WebDriver driver)
	{
		this.driver  = driver;
		PageFactory.initElements(driver, this);
	}*/
	

	
	//Always use Open method in the 1st page which is going to hit the application first. 
	//Here in this example Home page is the 1st page which will be open, so defined open() there
	public ZohoHomePage open(String url)
	{
		DriverManager.getDriver().get(url);
		return (ZohoHomePage)openPage(ZohoHomePage.class);
	}
	
	@FindBy(xpath="//a[@class='zh-login']")
	public WebElement logInBtn;
	
	
	public ZohoLoginPage goToLogin()
	{
		//logInBtn.click();		
		click(logInBtn, "Login Button");
		//return new ZohoLoginPage(driver);
		return (ZohoLoginPage) openPage(ZohoLoginPage.class);
	}


	@Override
	protected ExpectedCondition getPageLoadCondition()
	{
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(logInBtn);
	}
	
}
