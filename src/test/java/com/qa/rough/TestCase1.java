package com.qa.rough;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pageobjects.ZohoHomePage;
import com.qa.pageobjects.ZohoLoginPage;
import com.qa.testbase.TestBase;

public class TestCase1 extends TestBase
{
	
	@DataProvider(name = "getData", parallel = true)
	public Object[][] getData()
	{
		Object[][] obj = new Object[2][3];
		
		obj[0][0] = "trainer@way2automation.com";
		obj[0][1] = "Selenium@1234";
		obj[0][2] = "chrome";
		
		obj[1][0] = "java@way2automation.com";
		obj[1][1] = "Seelnium@1234";
		obj[1][2] = "firefox";
		
		return obj;
	}
	
	
	@Test(dataProvider = "getData")
	public void doLogin(String userName, String password, String browserName)
	{
		openBrowser(browserName);
		
		ZohoHomePage home = new ZohoHomePage();
		ZohoLoginPage loginPgae = home.goToLogin();
		loginPgae.doLoginAsInvalidUser(userName, password);
		
		/*getDriver().findElement(By.xpath("//a[@class='zh-login']")).click();
		getDriver().findElement(By.id("lid")).sendKeys(userName);
		getDriver().findElement(By.id("pwd")).sendKeys(password);
		getDriver().findElement(By.id("signin_submit")).click();*/
		
		tearDown();
		
	}
}
