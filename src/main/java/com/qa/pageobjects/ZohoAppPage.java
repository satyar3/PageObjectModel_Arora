package com.qa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoAppPage extends BasePage
{	
	@FindBy(xpath="//*[contains(text(),'Cliq')]")
	public WebElement cliq;
	
	@FindBy(xpath="//*[contains(text(),'Creator')]")
	public WebElement creator;
	
	@FindBy(xpath="//*[contains(text(),'CRM')]")
	public WebElement crm;
	
	@FindBy(xpath="//*[contains(text(),'SalesIQ')]")
	public WebElement salesIQ;
	
	@FindBy(xpath="//*[contains(text(),'Subscription')]")
	public WebElement subscription;
	
	@Override
	protected ExpectedCondition getPageLoadCondition()
	{
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(crm);
	}
	
	
	public ZohoCRMPage goToCRM()
	{
		click(crm, "CRM Link");
		return (ZohoCRMPage) openPage(ZohoCRMPage.class);
	}
	
	public ZohoCliqPage goToCliq()
	{
		click(cliq, "Cliq Link");
		return (ZohoCliqPage) openPage(ZohoCliqPage.class);
	}
	
	public ZohoSubScriptionPage goToSubscriptions()
	{
		click(subscription, "Subscription Link");
		return (ZohoSubScriptionPage) openPage(ZohoSubScriptionPage.class);
	}
	
	public ZohoCreatorPage goToCreator()
	{
		click(creator, "Creator Link");
		return (ZohoCreatorPage) openPage(ZohoCreatorPage.class);
	}
	
	public ZohoSalesIQPage goToSalesIQ()
	{
		click(salesIQ, "SalesIQ Link");
		return (ZohoSalesIQPage) openPage(ZohoSalesIQPage.class);
	}
	
}
