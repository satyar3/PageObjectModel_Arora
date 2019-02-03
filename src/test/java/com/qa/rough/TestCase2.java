package com.qa.rough;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.testbase.TestBase;
import com.qa.utils.DriverManager;

public class TestCase2 extends TestBase
{
	
	@DataProvider(name = "getData", parallel = true)
	public Object[][] getData()
	{
		Object[][] obj = new Object[1][3];
		
		obj[0][0] = "corporate@way2automation.com";
		obj[0][1] = "Seelnium@1234";
		obj[0][2] = "chrome";
		
		
		return obj;
	}
	
	@Test(dataProvider = "getData")
	public void doLogin(String userName, String password, String browserName)
	{
		openBrowser(browserName);
		
		DriverManager.getDriver().get("https://www.zoho.com");
		
		DriverManager.getDriver().findElement(By.xpath("//a[@class='zh-login']")).click();
		DriverManager.getDriver().findElement(By.id("lid")).sendKeys(userName);
		DriverManager.getDriver().findElement(By.id("pwd")).sendKeys(password);
		DriverManager.getDriver().findElement(By.id("signin_submit")).click();
		
		tearDown();
	}
}
