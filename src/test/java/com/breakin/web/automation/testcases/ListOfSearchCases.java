package com.breakin.web.automation.testcases;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.breakin.web.automation.dataprovider.GenerateData;
import com.breakin.web.automation.pages.ListOfCases;
import com.breakin.web.automation.pages.LoginPage;
import com.breakin.web.automation.test.base.TestBase;

public class ListOfSearchCases extends TestBase{
	@Test(dataProvider = "listofcases")
	public void searchCasesBasesdOnLocation(HashMap<String, String> data) throws InterruptedException {
		LoginPage l=new LoginPage(driver, logger);

		
		l.enterUserName(data.get("username"));

		l.enterPassword(data.get("password"));

		l.clickOnLoginButton();

		ListOfCases l1=new ListOfCases(driver,logger);

		l1.navigateToSearchCasesPage();

		l1.startDate();

		l1.endDate();
		//		
		//		l1.selectTheStatus(data.get("status"));
		//		
		//		Thread.sleep(1000);
		//		
		//	    l1.validateStauts(data.get("Testdata"));
	}
	@DataProvider(name="listofcases")
	public Object[][] getLoginValues() {
		return GenerateData.getTestData( "TestData", "ListOfSearchCases");
	}

}
