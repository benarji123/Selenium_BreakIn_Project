package com.breakin.web.automation.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.breakin.web.automation.config.PropertiesFileConfig;
import com.breakin.web.automation.pages.AgencyListCases;
import com.breakin.web.automation.pages.ApprovalPage;
import com.breakin.web.automation.pages.ListOfCases;
import com.breakin.web.automation.pages.LoginPage;
import com.breakin.web.automation.pages.ManualAllocationPage;
import com.breakin.web.automation.pages.RejectedByAgenciesPage;
import com.breakin.web.automation.test.base.TestBase;

public class E_2_E_Functionality_When_Request_For_ReAssignment_Applicable extends TestBase{


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
	public void applyForRequestForReAssignment() throws IOException, InterruptedException {
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
		ac.selectInspectionStatus("Request For");
		ac.clickOnSaveButton();
	}
	@Test(dependsOnMethods = {"applyForRequestForReAssignment"})
	public void reassignCaseToOtherAgency() throws InterruptedException, IOException {
		LoginPage l=new LoginPage(driver, logger);
		l.enterUserName("operations");
		l.enterPassword("1234");
		l.clickOnLoginButton();
		RejectedByAgenciesPage r=new RejectedByAgenciesPage(driver,logger);
		r.navigateToRejectedByAgenciesPage();
		r.searchBreakInId();
		r.selectBid();
		r.selectAgencyType("In-House");
		r.selectAgencyName("");
		r.clickAssignButton();


	}
	@Test(dependsOnMethods = {"reassignCaseToOtherAgency"})
	public void doAgencyInspection() throws InterruptedException, IOException, AWTException {
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
		ac.selectInspectionStatus("Appointment Confirmed As Scheduled");
		ac.enterValuePhysicalInspectionDateField("29-05-2025");
		ac.enterValueMakeField("Narada");
		ac.enterValueModelField("Narada");
		ac.enterValueManufacturedYearField("2020");
		ac.enterValueMileageINKMsField("50");
		ac.enterValueRegistrationNoField("Ap-001");
		ac.enterValueEngineNoField("1111");
		ac.enterValueChassisNoField("1234");

		ac.enterValueInspectionAddressField("HYd");
		ac.enterValueAssignedSurveyorField("Benarji");
		ac.enterValueReasonForRejectionField("Wrong");
		ac.enterValueAgencyRemarksField("Nothing");
		ac.clickOnSaveButton();
		ac.searchBreakInId();
		Assert.assertEquals(ac.validateStatus("Appointment Confirmed As Scheduled"), true);
		ac.clickUploadPhotoButton();
		ac.clickUploadIcon();
		ac.uploadImages("photos");
		ac.clickOnUploadButton();
		Thread.sleep(3000);
		ac.clickOnUploadVirButton();
		ac.clickUploadIcon();
		ac.uploadImages("vir");
		ac.clickOnUploadButton();
		Thread.sleep(3000);
		ac.searchBreakInId();

		ac.clickOnViewAndEditButton();
		ac.clickOnExpandButton();
		ac.editButton();
		ac.selectInspectionStatus("Submit For Approval");
		ac.clickOnSaveButton();
	}
	@Test(dependsOnMethods = {"doAgencyInspection"})
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
}
