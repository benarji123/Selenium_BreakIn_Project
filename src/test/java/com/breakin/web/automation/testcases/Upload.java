package com.breakin.web.automation.testcases;

import java.util.HashMap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.breakin.web.automation.dataprovider.GenerateData;
import com.breakin.web.automation.pages.ListOfCases;
import com.breakin.web.automation.pages.LoginPage;
import com.breakin.web.automation.test.base.TestBase;

public class Upload extends TestBase{
	@Test(dataProvider = "list")
	public void sample(HashMap<String, String> data) {
		LoginPage l=new LoginPage(driver, logger);

		l.enterUserName(data.get("username"));

		l.enterPassword(data.get("password"));

		l.clickOnLoginButton();

		ListOfCases l1=new ListOfCases(driver,logger);

		l1.navigateToSearchCasesPage();
	}
	@DataProvider(name="list")
	public Object[][] getLoginValues() {
		return GenerateData.getTestData( "TestData", "Upload");
	}
}
