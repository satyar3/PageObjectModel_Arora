package com.qa.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.qa.pageobjects.ZohoAppPage;
import com.qa.pageobjects.ZohoHomePage;
import com.qa.pageobjects.ZohoLoginPage;
import com.qa.testbase.TestBase;
import com.qa.utils.Constants;
import com.qa.utils.DataProviders;
import com.qa.utils.DataUtil;
import com.qa.utils.ExcelReader;

public class ValidateCRMTest extends TestBase
{
	@Test(dataProviderClass = DataProviders.class, dataProvider = "testDataProvider")
	//MethodName should be the same as the TestCase name mentioned in the TestData excel
	public void validateCRMTest(Hashtable<String,String> data)
	{		
		String runMode = data.get("Runmode");
		String browserName = data.get("browser");
		String userName = getDefaultUserName();
		String password = getDefaultPassword();		
		String tcID = data.get("Test_ID");
		
		ExcelReader excelReader = new ExcelReader(Constants.TESTCASE_XL_PATH);		
		DataUtil.checkExecution("Master", "ValidateCRMTest", runMode, excelReader);
		
		log.info("Inside CRM Test");
		logInfo("Inside CRM Test No : "+tcID);
		
		openBrowser(browserName);
		logInfo("Launched Browser : "+browserName);
		
		ZohoHomePage home = new ZohoHomePage().open("https://www.zoho.com");
		ZohoLoginPage loginPgae = home.goToLogin();
		ZohoAppPage zohoAppPage = loginPgae.doLoginAsValidUser(userName, password);
		logInfo("Username entered as : "+userName+" and password entered as : "+password);
		
		zohoAppPage.goToCRM();
	
	}
}
