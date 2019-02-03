package com.qa.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders
{
	@DataProvider(name = "TC_01",parallel = true)
	public Object[][] getData01()
	{
		Object[][] obj = new Object[2][3];
		
		obj[0][0] = "trainer@way2automation.com";
		obj[0][1] = "Selenium@1234";
		obj[0][2] = "Chrome";
		
		obj[1][0] = "java@way2automation.com";
		obj[1][1] = "Selenium@1234";
		obj[1][2] = "Firefox";
		
				
		return obj;
	}
	
	@DataProvider(name = "TC_02", parallel = true)
	public Object[][] getData02()
	{
		Object[][] obj = new Object[1][3];
		
		obj[0][0] = "corporate@way2automation.com";
		obj[0][1] = "Selenium@1234";
		obj[0][2] = "Chrome";
		
		return obj;
	}	

	
	//
	@DataProvider(name = "testDataProvider", parallel = true)
	public static Object[][] getTestData(Method m)
	{

		System.out.println(m.getName());

		ExcelReader excel = new ExcelReader(Constants.TESTCASE_XL_PATH);
		String testcase = m.getName();
		return DataUtil.getData(testcase, excel);

	}
}
