package com.breakin.web.automation.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BasePage {

	
	/** The driver. */
	protected WebDriver driver;

	/** The logger. */
	protected ExtentTest logger;

	/** The wait. */
	protected WebDriverWait wait;

	/** The js. */
	protected JavascriptExecutor js;
	/**
	 * Instantiates a new page base.
	 *
	 * @param driver the driver
	 * @param logger the logger
	 */
	public BasePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		js = (JavascriptExecutor) driver;
	}

	
	/**
	 * **************** Get Page Title **********************.
	 *
	 * @param expectedTitle the expected title
	 * @return the title
	 */
	public void assertTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/**
	 * **************** Open URL **********************.
	  *
	 * @param 
	  
	 */
	public void openApplication(String websiteURLKey) {
		
		driver.get(websiteURLKey);
		driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
      
		reportPass(websiteURLKey + "Openend the WebSite sucessfully");
		//waitForPageLoad();
	}
	
	/**
	 * Clicks on the specified element.
	 *
	 * @param element The web element to click. (e.g., WebElement)
	 * @param logMsg The log message to report on successful click. (e.g., String)
	 */
    public void clickElement(WebElement element,String logMsg) {
    	try {
    		element.click();
    		reportPass(logMsg);
    	}catch (Exception e) {
		reportFail(e.getMessage());
		}
    }
    
    
    /**
     * Enters a value in the specified field.
     *
     * @param element The web element representing the field. (e.g., WebElement)
     * @param enterText The text to enter in the field. (e.g., String)
     * @param logMsg The log message to report on successful value entry. (e.g., String)
     */
    public void enterValues(WebElement element,String enterText, String logMsg) {
    	try {
    		element.clear();
    		element.sendKeys(enterText);
    		reportPass(logMsg);
    	} catch (Exception e) {
			reportFail(e.getMessage());
		}
    }
    
    /**
     * Selects a value from a dropdown.
     *
     * @param webElement The dropdown element. (e.g., WebElement)
     * @param value The value to select from the dropdown. (e.g., String)
     * @param logMsg The log message to report on successful selection. (e.g., String)
     */
	public void selectDropDownValue(WebElement webElement, String value,String logMsg) {
		try {
			Select select = new Select(webElement);
			select.selectByValue(value);
			reportPass(logMsg);
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * Selects a dropdown value by index.
	 *
	 * @param webElement The dropdown element. (e.g., WebElement)
	 * @param index The index of the value to select. (e.g., int)
	 * @param logMsg The log message to report on successful selection. (e.g., String)
	 */
	public void selectDropDownValueByIndex(WebElement webElement, int index,String logMsg) {
		try {
			Select select = new Select(webElement);
			select.selectByIndex(index);
			reportPass(logMsg);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * Selects a dropdown value by partial text.
	 *
	 * @param webElement The dropdown element. (e.g., WebElement)
	 * @param value The partial text to match for selection. (e.g., String)
	 * @param logMsg The log message to report on successful selection. (e.g., String)
	 */
	public void selectDropDownByPartialText(WebElement webElement, String value,String logMsg) {
		try {
			Select select = new Select(webElement);
			List<WebElement> options = select.getOptions();
			for (WebElement option : options) {
				String currentOption = option.getText();
				if (currentOption.contains(value)||currentOption.equalsIgnoreCase(value)) {
					option.click();
					reportPass(logMsg);
				}
			}
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * Verifies if an element is displayed.
	 *
	 * @param webElement The web element to verify. (e.g., WebElement)
	 */
	public void veriyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				reportPass("Element is Displayed");
			} else {
				reportFail("Element is not appeared");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * Retrieves all the elements of a dropdown.
	 *
	 * @param webElement The dropdown element. (e.g., WebElement)
	 * @return The list of all elements in the dropdown.
	 */
	public List<WebElement> getAllElementsOfDropDown(WebElement webElement) {
		List<WebElement> allElements = null;
		try {
			Select select = new Select(webElement);
			allElements = select.getOptions();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return allElements;
	}

	/**
	 * Reports a test failure.
	 *
	 * @param reportString The string to be reported. (e.g., String)
	 */
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		Assert.fail(reportString);
	}

	/**
	 * Reports a test success.
	 *
	 * @param reportString The string to be reported. (e.g., String)
	 */
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	
	/**
	 * Waits for an element to be clickable.
	 *
	 * @param webElement The web element to wait for. (e.g., WebElement)
	 * @param time The maximum time to wait for the element to be clickable. (e.g., Duration)
	 */
	public void verifyElementisClickable(WebElement webElement, Duration time) {
		try {
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));

			reportPass("Element is Clickable");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	/**
	 * Waits for an element to be visible.
	 *
	 * @param webElement The web element to wait for. (e.g., WebElement)
	 * @param i The maximum time to wait in seconds. (e.g., int)
	 */

	public void verifyElementisVisible(WebElement webElement, int i ) {
		try {
			wait = new WebDriverWait(driver,Duration.ofSeconds(i));
			wait.until(ExpectedConditions.visibilityOf(webElement));
           	reportPass("Element is visible");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/**
	 * Retrieves the text of a web element.
	 *
	 * @param el The web element to retrieve the text from. (e.g., WebElement)
	 * @return The text of the web element, or null if an exception occurs.
	 */
	public String getText(WebElement el) {
		String text=null;
	
		try {
			text=el.getText();
			
		}catch (Exception e) {
			reportFail(e.getMessage());
		
		}
		return text;
		
	}

	/**
	 * Mouse click
	 * 
	 */
	public void mouseClickonElement(WebElement webElement) {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(webElement).click().perform();
			reportPass( "Clicked ");
		}catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	/**
	 * Performs a right or left navigation action on a web element.
	 *
	 * @param driver The WebDriver instance. (e.g., WebDriver)
	 * @param element The web element to perform the navigation on. (e.g., WebElement)
	 * @param direction The direction of navigation ("right" or "left"). (e.g., String)
	 */
	public void navigateRightAndLeft(WebDriver driver, WebElement element, String direction) {
		try {
			 Actions actions = new Actions(driver);
		        
		        int xOffset = 0;
		        int yOffset = 0;
		        
		        if (direction.equalsIgnoreCase("right")) {
		            xOffset = 100; // Adjust the offset as needed
		        } else if (direction.equalsIgnoreCase("left")) {
		            xOffset = -100; // Adjust the offset as needed
		        } else {
		            throw new IllegalArgumentException("Invalid direction provided: " + direction);
		        }
		        
		        actions.clickAndHold(element)
		               .moveByOffset(xOffset, yOffset)
		               .release()
		               .build()
		               .perform();
		        reportPass(direction);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/**
	 * Moves the mouse to the source element, then moves to the target element and performs a click action.
	 *
	 * @param sourceElement The source element to move the mouse to. (e.g., WebElement)
	 * @param targetElement The target element to move the mouse to and perform a click action. (e.g., WebElement)
	 * @param logMsg The log message to report on successful move and click. (e.g., String)
	 */
	 public  void moveAndClickElement( WebElement sourceElement, WebElement targetElement,String logMsg) {
		 try {
	        Actions actions = new Actions(driver);
	        actions.moveToElement(sourceElement)
	               .moveToElement(targetElement)
	               .click()
	               .perform();
              reportPass(logMsg);
		 }catch (Exception e) {
			 reportFail(e.getMessage());
		}
	    }

	/**
	 * *************** Wait Functions in Framework ****************.
	 */
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		while (true) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}
		waitLoad(2);
		while (true) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (Boolean.TRUE.equals(jsState)) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	/**
	 * Waits for a specified amount of time in seconds.
	 *
	 * @param i The duration to wait in seconds. (e.g., int)
	 */
	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			reportFail(e.getMessage());	
		}
	}
	
	/**
	 * Scrolls down the webpage by a fixed amount.
	 */
	public void scrollDown() {
		js.executeScript("window.scrollBy(0,800);");
	}
	 
    public  void clickElementWithJS(WebElement element) {
         js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
	
	
	
	
	/**
	 * GetDate As String
	 */
	public static String getDate() {
			String date=new SimpleDateFormat("YYYY-MM-dd hh-mm-ss").format(new Date());
			return date;
	}
	
	/**
	 * Takes a screenshot of the current screen and saves it to a file.
	 *
	 * @param driver The WebDriver instance. (e.g., WebDriver)
	 * @param screenshotName The name for the screenshot file. (e.g., String)
	 * @return The path to the saved screenshot file.
	 * @throws IOException If an I/O error occurs during the screenshot capture or file saving.
	 */
	public static String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException {
		String dateName=getDate();
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"/ScreenShots/"+ScreenshotName + dateName + ".png";
		File finaldestination = new File(destination);
		FileUtils.copyFile(source, finaldestination);
		return destination;
		
  }
	 public  void scrollAndEnterText(WebElement el, String text) {
	      
	        Actions actions = new Actions(driver);

	        // Scroll to the element
	        actions.moveToElement(el).perform();

	        verifyElementisVisible(el, 10);

	        // Enter text into the element
	        el.sendKeys(text);
	    }
	 public  void scrollAndSelectvalue(WebElement el, String text,String logmsg) {
	      try {
	    	  Actions actions = new Actions(driver);

		        // Scroll to the element
		        actions.moveToElement(el).perform();

		        verifyElementisVisible(el, 10);

		        // Enter text into the element
		        el.sendKeys(text);
		        
		        reportPass(logmsg);
		} catch (Exception e) {
		    reportFail(logmsg);
		}
	        
	    }
}
	
	