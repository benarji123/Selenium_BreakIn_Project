package com.breakin.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.breakin.web.automation.base.BasePage;



public class LoginPage extends BasePage{
  
  
  @FindBy(id="domain")
  private WebElement domainDropDown;
	
  @FindBy(id = "username")
  private WebElement usernameInput;
  
  @FindBy(id="password")
  private WebElement passwordInput;
  
  @FindBy(xpath="//button[text()=' SIGN IN ']")
  private WebElement signInButton;
  
  @FindBy(xpath="//button[@class='btn user-btn']")
  private WebElement profile;
  
  @FindBy(xpath="//b[text()=' Logout']")
  private WebElement logout;
  
  public LoginPage(WebDriver driver,ExtentTest logger) {
	
	  super(driver, logger);
	 
      PageFactory.initElements(driver, this);
     
	  
  }
  
  public void enterUserName(String username) {
	  verifyElementisVisible(usernameInput, 10);
	  enterValues(usernameInput, username,"Enter "+username+" in Username text field ");
	 
  }
  
  public void enterPassword(String password) {
	  verifyElementisVisible(passwordInput, 10);
	  enterValues(passwordInput, password,"Enter "+password+" in Password text field ");
	 
  }
  public void selectDropDown(String enterVisableText) {
	  verifyElementisVisible(domainDropDown, 10);
	  selectDropDownByPartialText(domainDropDown, enterVisableText,"Selecte "+enterVisableText+ " values fro the domain dropdown");
	 
  }
  public void clickOnLoginButton() {
	  verifyElementisVisible(signInButton, 10);
	  clickElement(signInButton,"click on sing in butoon");
	  
  }
 
  public boolean validateLogoutButton() throws InterruptedException {
	       Thread.sleep(10000);
	    	boolean flag=false;
	   	   
			 if(driver.getPageSource().contains("invalid credentials")) {
					flag=false;
			 }
		  else {
			
			verifyElementisVisible(logout, 10);
			clickElement(logout,"click on logout botton");
			flag=true;
		
		}
		return flag;
	
		       
}
}


