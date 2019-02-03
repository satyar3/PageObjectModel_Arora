package com.qa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoLoginPage extends BasePage
{
	
	/*public ZohoLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}*/
	
	
	@FindBy(id="lid")
	public WebElement userName;
	
	@FindBy(id="pwd")
	public WebElement password;
	
	@FindBy(id="signin_submit")
	public WebElement submitBtn;
	
	/*public void doLogin(String uname, String pwd)
	{
		userName.sendKeys(uname);
		password.sendKeys(pwd);
		submitBtn.click();

	}*/
	
	public ZohoLoginPage doLoginAsInvalidUser(String uname, String pwd)
	{
		/*userName.sendKeys(uname);
		password.sendKeys(pwd);
		submitBtn.click();*/
		
		typing(userName, "User Name", uname);
		typing(password, "Password", pwd);
		click(submitBtn, "Submit Button");
		
		return this;
	}
	
	public ZohoAppPage doLoginAsValidUser(String uname, String pwd)
	{
		/*userName.sendKeys(uname);
		password.sendKeys(pwd);
		submitBtn.click();*/
		
		typing(userName, "User Name", uname);
		typing(password, "Password", pwd);
		click(submitBtn, "Submit Button");
		
		return (ZohoAppPage)openPage(ZohoAppPage.class);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition()
	{
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(userName);
	}
	
}
