package com.breakin.web.automation.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.breakin.web.automation.base.BasePage;
import com.breakin.web.automation.config.PropertiesFileConfig;

public class ManualAllocationPage extends BasePage {

	@FindBy(xpath="//table[@class='table table-hover table-borderless table-sm']//tbody")
	private WebElement datatable;


	@FindBy(xpath="//li[@class='menu-item'][position()=1]")
	private WebElement transectionLink;

	@FindBy(xpath="//li[text()=' Manual Allocation ']")
	private WebElement manualallocationlink;

	@FindBy(id="mat-select-value-3")
	private WebElement agencyTypeDropDown;
 
	@FindBy(xpath="//mat-option[@ng-reflect-value='In-house']")
	private WebElement inHouse;
	
	
	@FindBy(id="mat-select-value-7")
	private WebElement agencynameDropDown;
	
	@FindBy(xpath="//mat-option[@ng-reflect-value='A10001']")
	private WebElement agencyName;

	@FindBy(xpath="//button[text()='Assign Agency']")
	private WebElement assignButton;



	public ManualAllocationPage(WebDriver driver,ExtentTest logger) {
		super(driver, logger);

		PageFactory.initElements(driver, this);
	}

	public void navigateToManualAllocationPage() throws InterruptedException{
		//	   verifyElementisVisible(transectionLink, 10);
		//	   moveAndClickElement(transectionLink, manualallocationlink,"click on manual allocation link");
		//	   Actions actions = new Actions(driver);
		//       actions.moveByOffset(0, 300).perform();
		Thread.sleep(3000);
		verifyElementisVisible(transectionLink, 10);
		clickElement(transectionLink, "click on transectionLink");
		verifyElementisVisible(manualallocationlink, 10);
		clickElement(manualallocationlink, "click on manualallocationlink");

	}

	public void selectBidCase() throws IOException, InterruptedException  {


		Thread.sleep(3000);


		//scrollDown();
		boolean b= driver.findElement(By.xpath("//nav[@role='navigation']")).isDisplayed();
		if(b) 
		{
			scrollDown();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//pagination-template//li[last()-1]//span[last()]")).click();
			Thread.sleep(3000);
			WebElement requiredrow= driver.findElement(By.xpath("//table[@class='table table-hover table-borderless table-sm']/tbody/tr[last()]"));
			List<WebElement> col = requiredrow.findElements(By.tagName("td"));
			int size=col.size();
			int j=1;
			WebElement lastcl=null;
			String breakInId=null;
			
			
			for(WebElement el:col) {
				if(j==2) {
					breakInId= el.getText();
				}
				else if(j==1) {
					lastcl=el;
					

				}
				j++;
			}

			PropertiesFileConfig.setData("BreakInId", breakInId);
			Thread.sleep(2000);
			WebElement sel= lastcl.findElement(By.tagName("input"));
			//verifyElementisVisible(sel, 10);
			clickElement( sel,"select BID Case");

		}else {
			scrollDown();
			WebElement requiredrow= driver.findElement(By.xpath("//table[@class='table table-hover table-borderless table-sm']/tbody/tr[last()]"));
			List<WebElement> col = requiredrow.findElements(By.tagName("td"));
			int size=col.size();
			int j=1;
			WebElement lastcl=null;
			String breakInId=null;
			for(WebElement el:col) {
				if(j==2) {
					breakInId= el.getText();
				}
				if(j==1) {
					lastcl=el;

				}
				j++;
			}

			PropertiesFileConfig.setData("BreakInId", breakInId);
			WebElement sel= lastcl.findElement(By.tagName("input"));
			verifyElementisVisible(sel, 10);
			clickElement( sel,"select BID Case");
		}
	}

	public void selectAgencyType(String agencytype) {
		verifyElementisVisible(agencyTypeDropDown, 10);
		agencyTypeDropDown.click();
		verifyElementisVisible(inHouse, 10);
		inHouse.click();
		

		//selectDropDownByPartialText(agencyTypeDropDown, agencytype,"select "+agencytype+" value from agencytype dropdown");
	}
	public void selectAgencyName(String agencyname) throws InterruptedException {
		Thread.sleep(5000);
		verifyElementisVisible(agencynameDropDown, 10);
		//selectDropDownByPartialText(agencynameDropDown,agencyname,"select "+agencyname+" value from agencyName dropdown" );
		agencynameDropDown.click();
		verifyElementisVisible(agencyName, 10);
		agencyName.click();
		
	}
	public void clickOnAssignAgencyButton() {
		scrollDown();
		verifyElementisVisible(assignButton, 10);
		clickElement(assignButton,"click on assign button");
	}


}

