package com.breakin.web.automation.testcases;

import java.io.IOException;
import java.util.HashMap;
import com.breakin.web.automation.dataprovider.GenerateData;
import com.breakin.web.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.breakin.web.automation.test.base.TestBase;




public class DataDrivenLoginTest extends TestBase{
	@Test(dataProvider ="logindata")
	public void loginToBreakIn(HashMap<String, String> data) throws IOException, InterruptedException{

		LoginPage loginPage=new LoginPage(driver,logger);

		
		loginPage.enterUserName(data.get("username"));

		loginPage.enterPassword(data.get("password"));

		loginPage.clickOnLoginButton();

		Assert.assertEquals(true,loginPage.validateLogoutButton());

	}
	@DataProvider(name="logindata")
	public Object[][] getLoginValues() {
		return GenerateData.getTestData( "TestData", "Login");
	}

}



