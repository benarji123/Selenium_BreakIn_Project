package com.breakin.web.automation.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.breakin.web.automation.base.BasePage;
import com.breakin.web.automation.config.PropertiesFileConfig;

public class ApprovalPage extends BasePage{

	@FindBy(xpath="//li[@class='menu-item'][position()=1]")
	private WebElement transectionPage;
	
	@FindBy(xpath="//li[@class='menu-item menu-active']")
	private WebElement transection;
	
	@FindBy(xpath="//li[text()=' Approval ']")
	private WebElement approvalLink;
	
	@FindBy(xpath="//input[@id='search']")
	private WebElement search;
	
	@FindBy(xpath="//tbody//tr[position()=1]/td[last()-1]")
	private WebElement approvalButton;
	
	@FindBy(xpath="//tbody//tr[position()=1]/td[last()]")
	private WebElement rejectButton;
	
	@FindBy(xpath="//li[contains(text() ,'Search Case')]")
	private WebElement listCasesLink;
	
	@FindBy(xpath="//tbody/tr[position()=1]/td[position()=8]")
	private WebElement statuscl;
	
	
	public ApprovalPage(WebDriver driver,ExtentTest logger) {
		super(driver, logger);

		PageFactory.initElements(driver, this);
	}
	
	public void navigateToApprovalPage() throws InterruptedException {
		Thread.sleep(3000);
		verifyElementisVisible(transectionPage, 10);
		clickElement(transectionPage, "click on tranction link");
		verifyElementisVisible(approvalLink, 10);
		clickElement(approvalLink, "clcick on approval link");
		
	}
	
	public void searchBreakIn() throws InterruptedException, IOException {
		Thread.sleep(2000);
		verifyElementisVisible(search, 10);
		enterValues(search, PropertiesFileConfig.getTheKeyValue("BreakInId"), "search require break in id");
		
	}
	public void approvalCase() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisVisible(approvalButton, 10);
		clickElement(approvalButton, "click on approval button");
	}
	public void navigateSearchCasePage() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisVisible(transection, 10);
		clickElement(transection, "click on tranction link");
		Thread.sleep(2000);
		verifyElementisVisible(listCasesLink, 10);
		clickElement(listCasesLink, "click on listSearchcase link");
	}
	public boolean validateStutasAfterApproval() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisVisible(statuscl, 10);
		String status=getText(statuscl);
		System.out.println(status);
		if(status.equalsIgnoreCase("Recommended")) {
			return true;
		}else {
			return false;
		}
	}
}

