package com.qa.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.qa.pageobjects.ZohoHomePage;
import com.qa.pageobjects.ZohoLoginPage;
import com.qa.testbase.TestBase;
import com.qa.utils.Constants;
import com.qa.utils.DataProviders;
import com.qa.utils.DataUtil;
import com.qa.utils.ExcelReader;



public class LoginTest extends TestBase
{
	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "testDataProvider")
	//MethodName should be the same as the TestCase name mentioned in the TestData excel
	public void loginTest(Hashtable<String,String> data)
	{
		String browserName = data.get("browser");
		String userName = data.get("username");
		String password = data.get("password");
		String runMode = data.get("Runmode");
		String tcID = data.get("Test_ID");
		
		ExcelReader excelReader = new ExcelReader(Constants.TESTCASE_XL_PATH);		
		DataUtil.checkExecution("Master", "LoginTest", runMode, excelReader);
		log.info("Inside Login Test");
		logInfo("Inside Login Test No : "+tcID);
		
		openBrowser(browserName);
		logInfo("Launched Browser : "+browserName);
		
		ZohoHomePage home = new ZohoHomePage().open("https://www.zoho.com");
		ZohoLoginPage loginPgae = home.goToLogin();
		loginPgae.doLoginAsInvalidUser(userName, password);
		logInfo("Username entered as : "+userName+" and password entered as : "+password);
	
	}
}
