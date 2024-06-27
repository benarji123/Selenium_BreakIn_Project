package com.breakin.web.automation.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.breakin.web.automation.base.BasePage;
import com.breakin.web.automation.config.PropertiesFileConfig;
import com.google.common.base.Verify;

public class RejectedByAgenciesPage extends BasePage{

	@FindBy(xpath="//li[@class='menu-item'][position()=1]")
	private WebElement transectionLink;

	@FindBy(xpath="//li[text()=' Reject By Agencies ']")
	private WebElement rejectedByAgenciesLink;

	@FindBy(xpath="//select[@ng-reflect-name='agencyType']")
	private WebElement agencyTypeDropDown;
		
	@FindBy(xpath="//select[@ng-reflect-name='agencyName']")
	private WebElement agencynameDropDown;

	@FindBy(xpath="//button[text()='Assign Agency']")
	private WebElement assignButton;
	
	@FindBy(xpath="/input[@id='search']")
	private WebElement searchtxt;
	
	@FindBy(xpath="//tr[position()=1]/td[last()]/input")
	private WebElement bidcheckbox;
	public RejectedByAgenciesPage (WebDriver driver,ExtentTest logger) {
		super(driver, logger);

		PageFactory.initElements(driver, this);
	}

	public void searchBreakInId() throws IOException {
		verifyElementisVisible(searchtxt, 10);
	    enterValues(searchtxt, PropertiesFileConfig.getTheKeyValue("BreakInId"), "search break in id");
	    verifyElementisVisible(agencyTypeDropDown, 0);
	}
	public void selectBid() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisVisible(bidcheckbox, 10);
		clickElement(bidcheckbox, "select re assign  bid");
		
	}
	public void selectAgencyType(String agencyType) {
		verifyElementisVisible(agencyTypeDropDown, 10);
		selectDropDownByPartialText(agencyTypeDropDown, agencyType, "select "+agencyType+ "from agencyType dropdown");
	}
	public void selectAgencyName(String agencyName) {
		verifyElementisVisible(agencynameDropDown, 10);
		selectDropDownByPartialText(agencynameDropDown,  agencyName, "select "+agencyName+ "from agencyType dropdown");
	}
	public void clickAssignButton() {
		verifyElementisVisible(assignButton, 10);
		clickElement(assignButton, "click on assign buttons");
		
	}
	public void navigateToRejectedByAgenciesPage() throws InterruptedException {
		Thread.sleep(3000);
		verifyElementisVisible(transectionLink, 10);
		clickElement(transectionLink, "click on transectionLink");
		verifyElementisVisible(rejectedByAgenciesLink, 10);
		clickElement(rejectedByAgenciesLink, "click on rejectedByAgenciesLink");
	}
}
