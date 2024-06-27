package com.breakin.web.automation.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.breakin.web.automation.config.PropertiesFileConfig;
import com.breakin.web.automation.dataprovider.GenerateData;
import com.breakin.web.automation.pages.AgencyListCases;
import com.breakin.web.automation.pages.ApprovalPage;
import com.breakin.web.automation.pages.CaseApprovalForHighTravelCharges;
import com.breakin.web.automation.pages.ListOfCases;
import com.breakin.web.automation.pages.LoginPage;
import com.breakin.web.automation.pages.ManualAllocationPage;
import com.breakin.web.automation.test.base.TestBase;

public class E_2_E_Fuctionality_When_Canveyance_Applicable extends TestBase{


	@Test(dataProvider = "assignagency")
	public void assignCaseToSurveyor(HashMap<String, String> data) throws IOException, InterruptedException {

		LoginPage l=new LoginPage(driver, logger);


		l.enterUserName(data.get("username"));

		l.enterPassword(data.get("password"));

		l.clickOnLoginButton();

		ManualAllocationPage m=new ManualAllocationPage(driver, logger);

		m.navigateToManualAllocationPage();

		m.selectBidCase();

		m.selectAgencyType(data.get("agencytype"));

		m.selectAgencyName("NEW INSURANCE");

		m.clickOnAssignAgencyButton();


	}
	@Test(dependsOnMethods = {"assignCaseToSurveyor"})
	public void applyConveyance() throws IOException, InterruptedException {
		LoginPage l=new LoginPage(driver, logger);
		l.enterUserName("A10001");
		l.enterPassword("abc");
		l.clickOnLoginButton();
		ListOfCases sc=new ListOfCases(driver, logger);
		sc.enterTextInSearch(PropertiesFileConfig.getTheKeyValue("BreakInId"));

		AgencyListCases ac=new AgencyListCases(driver,logger);
		ac.clickOnViewAndEditButton();
		ac.clickOnExpandButton();
		ac.editButton();
		ac.enterValueInspectionDoneByField("Narada");
		ac.clickOnHighTravelCaseButton();
		ac.enterValueOriginField("Hyd");
		ac.enterValueDestinationField("Mumbai");
		ac.enterValueTotalKmField("500");
		ac.enterValueMakeField("Narada");
		ac.enterValueModelField("Narada");
		ac.enterValueManufacturedYearField("2020");
		ac.enterValueMileageINKMsField("50");
		ac.enterValueRegistrationNoField("Ap-001");
		ac.enterValueEngineNoField("1111");
		ac.enterValueChassisNoField("1234");
		// ac.enterValuePhysicalInspectionDateField("29-05-2025");
		ac.enterValueInspectionAddressField("HYd");
		ac.enterValueAssignedSurveyorField("Benarji");
		ac.enterValueReasonForRejectionField("Wrong");
		ac.enterValueAgencyRemarksField("Nothing");
		ac.clickOnSaveButton();




	}
	@Test(dependsOnMethods = {"applyConveyance"})
	public void approvalConveyance() throws InterruptedException, IOException {
		LoginPage l=new LoginPage(driver, logger);
		l.enterUserName("operations");
		l.enterPassword("1234");
		l.clickOnLoginButton();
		CaseApprovalForHighTravelCharges c=new CaseApprovalForHighTravelCharges(driver, logger);
		c.navigateToHighTravel();
		c.approvalNegotiatedKms("400");

	}
	@Test(dependsOnMethods = {"approvalConveyance"})
	public void agencyInspection() throws IOException, InterruptedException, AWTException {
		LoginPage l=new LoginPage(driver, logger);
		l.enterUserName("A10001");
		l.enterPassword("abc");
		l.clickOnLoginButton();
		AgencyListCases ac=new AgencyListCases(driver,logger);
		ac.searchBreakInId();
		ac.clickOnViewAndEditButton();
		ac.clickOnExpandButton();
		ac.editButton();
		ac.selectInspectionStatus("Appointment Confirmed As Scheduled");
		ac.enterValuePhysicalInspectionDateField("29-05-2025");
		ac.clickOnSaveButton();

		ac.searchBreakInId();

		ac.clickUploadPhotoButton();
		ac.clickUploadIcon();
		ac.uploadImages("photos");
		ac.clickOnUploadButton();
		Thread.sleep(3000);
		ac.clickOnUploadVirButton();
		ac.clickUploadIcon();
		ac.uploadImages("vir");
		ac.clickOnUploadButton();
		ac.clickOnViewAndEditButton();
		ac.clickOnExpandButton();
		ac.editButton();
		ac.selectInspectionStatus("Submit For Approval");
		ac.clickOnSaveButton();


	}
	@Test(dependsOnMethods = { "agencyInspection" })
	public void approvalCase() throws InterruptedException, IOException {
		LoginPage l=new LoginPage(driver, logger);
		l.enterUserName("operations");
		l.enterPassword("1234");
		l.clickOnLoginButton();
		ApprovalPage a=new ApprovalPage(driver, logger);
		a.navigateToApprovalPage();
		a.searchBreakIn();
		a.approvalCase();
		a.navigateSearchCasePage();
		a.searchBreakIn();
		System.out.println(a.validateStutasAfterApproval()); 
	} 

	@DataProvider(name="assignagency")
	public Object[][] getLoginValues() {
		return GenerateData.getTestData( "TestData", "AssignOpenCaseToSurveyor");
	}
}
