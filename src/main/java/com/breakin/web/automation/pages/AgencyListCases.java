package com.breakin.web.automation.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




import com.aventstack.extentreports.ExtentTest;
import com.breakin.web.automation.base.BasePage;
import com.breakin.web.automation.config.PropertiesFileConfig;




public class AgencyListCases extends BasePage
{

	@FindBy(xpath="//tbody//tr[position()=1]/td[last()]/button")
	private WebElement viewEditButton;
	
	
	@FindBy(xpath="//span[@class='expand']")
	private WebElement expandButton;
	
	
	@FindBy(xpath="//button[text()=' Edit ']")
	private WebElement editButton;
	
	@FindBy(xpath="//input[@id='inspectionDoneBy']")
	private WebElement inspectionDoneBytxt;
	
	@FindBy(xpath="//select[@id='inspectionStatus']")
	private WebElement inspectionStatusDropDown;
	
	@FindBy(xpath="//mat-radio-button[@id='mat-radio-2']")
	private WebElement highTravelCaseRadioButton;
	
	@FindBy(xpath="//input[@id='make']")
	private WebElement makeTextField;
	
	@FindBy(xpath="//input[@id='model']")
	private WebElement modelTextField;
	
	@FindBy(xpath="//input[@id='manufacturedYear']")
	private WebElement manufactureYear;
	
	@FindBy(xpath="//input[@id='mileagePerKms']")
	private WebElement mileagePerKmsTextField;
	
	
	@FindBy(xpath="//input[@id='regNo']")
	private WebElement regNoTextField;
	
	@FindBy(xpath="//input[@id='engineNo']")
	private WebElement engineNoTextField;
	
	@FindBy(xpath="//input[@id='chassisNo']")
	private WebElement chassisNoTextField;
	
	@FindBy(xpath="//input[@id='physicalInspectionDate']")
	private WebElement physicalInspectionDate;
	
	@FindBy(xpath="//input[@id='inspectionAddress']")
	private WebElement inspectionAddress;
	
	@FindBy(xpath="//input[@id='assignedSurveyor']")
	private WebElement assignedSurveyor;
	
	@FindBy(xpath="//input[@id='reasonForRejection']")
	private WebElement reasonForRejection;
	
	@FindBy(xpath="//input[@id='agencyRemarks']")
	private WebElement agencyRemarks;
	
	
	
	@FindBy(xpath="//button[text()=' Save ']")
	private WebElement saveButton;
	
	@FindBy(xpath="//input[@id='search']")
	private WebElement search;
	
	@FindBy(xpath="//tbody/tr[position()=1]/td[position()=6]")
	private WebElement statuscl;
	
	@FindBy(xpath="//tbody/tr[position()=1]/td[position()=7]")
	private WebElement uploadphptoButton;
	
	@FindBy(xpath="//tbody/tr[position()=1]/td[position()=8]")
	private WebElement uploadVirButton;
	
	@FindBy(xpath="//i[@class='fa fa-cloud-upload fa-3x']")
	private WebElement uploadIcon;
	
	@FindBy(xpath="//button[text()='Upload']")
	private WebElement uploadButton;
	
	@FindBy(xpath="//input[@id='origin']")
	private WebElement originField;
	
	@FindBy(xpath="//input[@id='destination']")
	private WebElement destinationField;
	
	@FindBy(xpath="//input[@id='totalKmsToTravel']")
	private WebElement totalkmField;
	public AgencyListCases(WebDriver driver,ExtentTest logger) 
	{
		
		super(driver, logger);

		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnViewAndEditButton() throws InterruptedException
	{ Thread.sleep(3000);
		
		verifyElementisVisible(viewEditButton, 10);
		clickElement(viewEditButton, "click on edit button");
		
	}
	
	
	public void clickOnExpandButton() throws InterruptedException
	{
		Thread.sleep(3000);
		verifyElementisVisible(expandButton, 10);
		clickElement(expandButton, "Click on expand button");
	}
	
	public void editButton() throws InterruptedException
	{ Thread.sleep(1000);
		verifyElementisVisible(editButton, 10);
		clickElement(editButton, "Click on edit button");
	}
	
	
	public void selectInspectionStatus(String inpectionStatus) throws InterruptedException
	{  Thread.sleep(1000);
		verifyElementisVisible(inspectionStatusDropDown, 10);
		selectDropDownByPartialText(inspectionStatusDropDown, inpectionStatus, "Select "+inpectionStatus+" from inspectionStatusDropDown");
	}
	 
	public void enterValueInspectionDoneByField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(inspectionDoneBytxt, 10);
		enterValues(inspectionDoneBytxt, text, "enter "+text+" in Inspection done by Fields");
		
	}
	public void enterValueMakeField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(makeTextField, 10);
		enterValues(makeTextField, text, "enter "+text+" in makeTextField");
		
	}
	public void enterValueModelField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(modelTextField, 10);
		enterValues(modelTextField, text, "enter "+text+" in modelTextField");
		
	}
	public void enterValueManufacturedYearField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(manufactureYear, 10);
		enterValues(manufactureYear, text, "enter "+text+" in manufactureYearField");
		
	}
	public void enterValueRegistrationNoField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(regNoTextField, 10);
		enterValues(regNoTextField, text, "enter "+text+" in regNoTextFieldTextField");
		
	}
	public void enterValueMileageINKMsField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(mileagePerKmsTextField, 10);
		enterValues(mileagePerKmsTextField, text, "enter "+text+" in mileagePerKmsTextField");
		
	}
	public void enterValueEngineNoField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(engineNoTextField, 10);
		enterValues(engineNoTextField, text, "enter "+text+" in engineNoTextField");
		
	}
	public void enterValueChassisNoField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(chassisNoTextField, 10);
		enterValues(chassisNoTextField, text, "enter "+text+" in chassisNoTextField");
		scrollDown();
		
	}
	public void enterValuePhysicalInspectionDateField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(physicalInspectionDate, 10);
		enterValues(physicalInspectionDate, text, "enter "+text+" in physicalInspectionDateTextField");
		
	}
	public void enterValueInspectionAddressField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(inspectionAddress, 10);
		enterValues(inspectionAddress, text, "enter "+text+" in inspectionAddressTextField");
		
	}
	
	public void enterValueAssignedSurveyorField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(inspectionDoneBytxt, 10);
		enterValues(assignedSurveyor, text, "enter "+text+" in AssignedSurveyorTextField");
		
	}
	public void enterValueReasonForRejectionField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(reasonForRejection, 10);
		enterValues(reasonForRejection, text, "enter "+text+" in reasonForRejectionField");
		
	}
	public void enterValueAgencyRemarksField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(agencyRemarks, 10);
		enterValues(agencyRemarks, text, "enter "+text+" in agencyRemarksField");
		
	}
	public void clickOnSaveButton() throws InterruptedException {
		Thread.sleep(3000);
		verifyElementisVisible(saveButton, 10);
		clickElement(saveButton, "click on save button");
	}
	public void searchBreakInId() throws IOException, InterruptedException {
		//driver.navigate().refresh();
		Thread.sleep(3000);
		driver.navigate().refresh();
		verifyElementisVisible(search, 10);
		enterValues(search, PropertiesFileConfig.getTheKeyValue("BreakInId"), "search break in id in searchbox");
	}
	public boolean validateStatus(String expctedStatus) {
		verifyElementisVisible(statuscl, 10);
		String actualtext=getText(statuscl);
		if(actualtext.equals(expctedStatus)) {
			return true;
		}else {
			return false;
		}
	}
	public void clickUploadPhotoButton() throws InterruptedException {
		Thread.sleep(3000);
		verifyElementisVisible(uploadphptoButton, 10);
		clickElement(uploadphptoButton, "click on uploadphptoButton ");
	}
	
	public void clickUploadIcon() throws InterruptedException {
		//Thread.sleep(3000);
		verifyElementisVisible(uploadIcon, 10);
		clickElement(uploadIcon, "click on uploadIcon ");
	}
	public void uploadImages(String uploadType) throws InterruptedException, AWTException {
		Thread.sleep(3000);
		Robot r=new Robot();
		if(uploadType.equalsIgnoreCase("vir")||uploadType.equalsIgnoreCase("virreport")) {
			StringSelection s=new StringSelection("D:\\sikuli\\Payslip_Jul_2023");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
		    // Simulate pressing the Tab key 11 times
//	        for (int i = 0; i < 2; i++) {
//	            r.keyPress(KeyEvent.VK_TAB);
//	            r.keyRelease(KeyEvent.VK_TAB);
//	            Thread.sleep(1000); // Add a small delay between key presses if needed
//	        }
	        Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		}else if(uploadType.equalsIgnoreCase("photos")||uploadType.equalsIgnoreCase("images")) {
			StringSelection s=new StringSelection("C:\\Users\\NaradaBenarji\\eclipse-workspace\\BreakInWebsite\\src\\test\\resources\\images");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
		    // Simulate pressing the Tab key 11 times
	        for (int i = 0; i < 2; i++) {
	            r.keyPress(KeyEvent.VK_TAB);
	            r.keyRelease(KeyEvent.VK_TAB);
	            Thread.sleep(1000); // Add a small delay between key presses if needed
	        }
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
			  // Simulate pressing the Tab key 11 times
	        for (int i = 0; i < 11; i++) {
	            r.keyPress(KeyEvent.VK_TAB);
	            r.keyRelease(KeyEvent.VK_TAB);
	            Thread.sleep(1000); // Add a small delay between key presses if needed
	        }
	        
	        r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_A);
	        r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_A);
			Thread.sleep(3000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		}
		
	
	}
	public void clickOnUploadButton() throws InterruptedException
	{
		Thread.sleep(2000);
		verifyElementisVisible(uploadButton, 10);
		clickElement(uploadButton, "click on upload button");
	}
	public void clickOnUploadVirButton() throws InterruptedException
	{
		Thread.sleep(2000);
		verifyElementisVisible(uploadVirButton, 10);
		clickElement(uploadVirButton, "click on upload button");
	}
	
	public void clickOnHighTravelCaseButton() throws InterruptedException
	{
		Thread.sleep(2000);
		verifyElementisVisible(highTravelCaseRadioButton, 10);
		clickElement(highTravelCaseRadioButton, "click on highTravelCaseRadioButton");
	}
	
	public void enterValueOriginField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(originField, 10);
		enterValues(originField, text, "enter "+text+" in originFieldField");
		
	}
	public void enterValueDestinationField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(destinationField, 10);
		enterValues(destinationField, text, "enter "+text+" in destinationFieldField");
		
	}
	public void enterValueTotalKmField(String text) throws InterruptedException {
		Thread.sleep(1000);
		verifyElementisVisible(totalkmField, 10);
		enterValues(totalkmField, text, "enter "+text+" in totalkmFieldField");
		
	}
}
