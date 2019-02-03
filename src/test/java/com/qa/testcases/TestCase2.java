package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.pageobjects.ZohoHomePage;
import com.qa.pageobjects.ZohoLoginPage;
import com.qa.testbase.TestBase;
import com.qa.utils.DataProviders;


public class TestCase2 extends TestBase
{
	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "testDataProvider")
	public void doLogin(String userName, String password, String browserName)
	{
		openBrowser(browserName);
		
		ZohoHomePage home = new ZohoHomePage();
		ZohoLoginPage loginPgae = home.goToLogin();
		loginPgae.doLoginAsInvalidUser(userName, password);
		
	}
}
