package com.breakin.web.automation.test.base;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.Browser;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.breakin.web.automation.base.BasePage;
import com.breakin.web.automation.config.PropertiesFileConfig;
import com.breakin.web.automation.util.Constants;
import com.breakin.web.automation.util.PropertyKey;

import java.util.Map;
import io.github.bonigarcia.wdm.WebDriverManager;




public class TestBase {
	protected static WebDriver driver;
	protected static ExtentSparkReporter htmlrepo;
	protected static  ExtentReports extent;
	protected static ExtentTest logger ;
	protected static Map<String, String> map;
	static int i=0;
    @BeforeMethod
    @Parameters({ "Evironment", "BrowserType" })
     public void setUp(String Environment,String BrowserType,Method method) throws Throwable {
    	 setTestName(method.getName());
    	
    	//Launch the browser
      	if(BrowserType.equalsIgnoreCase(PropertyKey.Chrome.toString())) {
      		System.out.println(BrowserType+"-========");
      		WebDriverManager.chromedriver().setup();
      		driver=new ChromeDriver();
      	}else if(BrowserType.equalsIgnoreCase(PropertyKey.FireFox.toString())) {
      		WebDriverManager.firefoxdriver().setup();
      		driver=new FirefoxDriver();
      	}else if(BrowserType.equalsIgnoreCase(PropertyKey.Edge.toString())) {
      		WebDriverManager.edgedriver().setup();
      		driver=new EdgeDriver();
      	}else if(BrowserType.equalsIgnoreCase(Browser.OPERA.toString())) {
      		System.setProperty("webdrive.opera.drive", System.getProperty("user.dir"+"/Drivers/operadriver.exe"));
      		
      	   driver=new OperaDriver();
      	}
      	
      	   String applicationUrl = null;
      	   if(Environment.equalsIgnoreCase(PropertyKey.UAT.toString())) {
      		 applicationUrl=PropertiesFileConfig.getTheKeyValue("UATURL");
      	   }else if (Environment.equalsIgnoreCase(PropertyKey.Production.toString())) {
      		 applicationUrl=PropertiesFileConfig.getTheKeyValue("PRODUCTIONURL");
		   }else if (Environment.equalsIgnoreCase(PropertyKey.LocalHost.toString())) {
			   applicationUrl=PropertiesFileConfig.getTheKeyValue("LOCALHOSTURL");
		   }
      	   BasePage p=new BasePage(driver,logger);
      	   p.openApplication(applicationUrl);
      	
			 	 
		
	}
    @AfterMethod
	public void tearDown(ITestResult result) throws IOException {
    	
    	
    	if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(Status.FAIL, "TEST CASE FAILED IS "+ result.getName());//to add name in extent report
			logger.log(Status.FAIL, "TEST CASE FAILED IS "+ result.getThrowable());// to add error /exception
			String screenshotpath=BasePage.getScreenshot(driver, result.getName());
			logger.addScreenCaptureFromPath(screenshotpath); //add screenshots			
			
		}else if(result.getStatus()==ITestResult.SKIP) {
			logger.log(Status.SKIP, "TEST CASE SKIPPED IS" + result.getName());
			
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS, "TEST CASE SUCCESSED IS" + result.getName());
		}
    	
    	driver.quit();
    
	}
    @AfterTest
    public void closeBrowser() {
    	System.out.println("close the browser");
    }
    @BeforeSuite
	public  void setExtent() {
    	if(htmlrepo == null) {
		htmlrepo=new ExtentSparkReporter(Constants.extentReportPath+BasePage.getDate()+".html");
		htmlrepo.config().setDocumentTitle("Automation Report");//title of the report
		htmlrepo.config().setReportName("Functional Report");//name of the report
		htmlrepo.config().setTheme(Theme.STANDARD);
		htmlrepo.config().setReportName("Break In UI Test Report");
		htmlrepo.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		extent=new ExtentReports();
		extent.attachReporter(htmlrepo);
	    extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Narada Benarji");
	    extent.setSystemInfo("Hostname","LocalHost");
		extent.setSystemInfo("OS","windows10");
    	}
	}
    public void setTestName(String testName) {
        TestBase.logger = extent.createTest(testName).assignAuthor("Benarji").assignCategory("Fuctionality");
    }
    @AfterSuite
	public void endRoport() {
    	
		extent.flush();
			
	}
    
    
 }

