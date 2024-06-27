package com.breakin.web.automation.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.breakin.web.automation.base.BasePage;
import com.breakin.web.automation.config.PropertiesFileConfig;

public class CaseApprovalForHighTravelCharges extends BasePage{

	
	@FindBy(xpath="//li[@class='menu-item'][position()=1]")
	private WebElement transectionPage;
	
	@FindBy(xpath="//li[text()=' Case Approval for High Travel Charges ']")
	private WebElement CaseApprovalforHighTravelChargesPage;
	
	@FindBy(xpath="//tbody/tr[position()=1]/td[position()=4]")
	private WebElement negotiatedKms;
	
	@FindBy(xpath="//tbody/tr[position()=1]/td[position()=4]/input")
	private WebElement negotiatedField;
	
	@FindBy(xpath="//tbody/tr[position()=1]/td[last()-1]/button")
	private WebElement assignButton;
	
	@FindBy(xpath="//input[@id='search']")
	private WebElement searchField;
	
	
	
	public CaseApprovalForHighTravelCharges(WebDriver driver,ExtentTest logger) {
		super(driver, logger);

		PageFactory.initElements(driver, this);
	}
	
	public void navigateToHighTravel() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisVisible(transectionPage, 10);
		clickElement(transectionPage, "click on tranction link");
		verifyElementisVisible(CaseApprovalforHighTravelChargesPage, 10);
		clickElement(CaseApprovalforHighTravelChargesPage, "clcick on CaseApprovalforHighTravelChargesPage link");
	}
	public void approvalNegotiatedKms(String km) throws IOException, InterruptedException {
		Thread.sleep(2000);
		verifyElementisVisible( searchField, 10);
		enterValues( searchField, PropertiesFileConfig.getTheKeyValue("BreakInId"), "search require break in id");
		Thread.sleep(1000);
		verifyElementisVisible(negotiatedKms, 10);
		clickElement(negotiatedKms, "clck on negotiated Kms");
		Thread.sleep(1000);
		enterValues(negotiatedField, km, "enter negotiated Kms");
		clickElement(assignButton, "click on assign button");
		
		
	}
}
