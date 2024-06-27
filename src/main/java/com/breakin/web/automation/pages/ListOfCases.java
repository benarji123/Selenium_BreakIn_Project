package com.breakin.web.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.breakin.web.automation.base.BasePage;

import java.time.LocalDate;
public class ListOfCases extends BasePage {

	@FindBy(xpath="(//li[@class='menu-item'])[position()=1]")
	private WebElement transectionLink;

	@FindBy(xpath="//li[contains(text() ,'Search Case')]")
	private WebElement listCasesLink;

	@FindBy(id="status")
	private WebElement status;

	@FindBy(xpath="//ul[@class='responsive-table']/li[2]/div[7]")
	private WebElement actualtabalestatus;

	@FindBy(xpath="//input[@id='search']")
	private WebElement search;
	
	public ListOfCases(WebDriver driver,ExtentTest logger) {
		super(driver, logger);

		PageFactory.initElements(driver, this);
	}

	public void navigateToSearchCasesPage() {
		verifyElementisVisible(transectionLink, 10);
		clickElementWithJS(transectionLink);
		verifyElementisVisible(listCasesLink, 10);
		listCasesLink.click();

		//	moveAndClickElement(transectionLink, listCasesLink,"click on listcaselink");
		//		Actions actions = new Actions(driver);
		//        actions.moveByOffset(0, 300).perform();

	}
	public void selectTheStatus(String text) {
		verifyElementisVisible(status, 10);
		veriyElementIsDisplayed(status);

		selectDropDownByPartialText(status,text,"select "+text+" value from the status dropdown");

	}
	public void enterTextInSearch(String text) {
		verifyElementisVisible(search, 10);
	    search.sendKeys(text);

	}
	public void validateStauts(String expected) {
		verifyElementisVisible(actualtabalestatus, 10);
		String actualText=getText(actualtabalestatus);
		Assert.assertEquals(actualText,expected.toUpperCase(),"actual data and expcted data mismatch" );


	}
	public List<String> getAllStatus() {

		List<WebElement> pageNum=driver.findElements(By.xpath("//table[@class='text']//tr[13]//tbody/tr/td"));

		List<String>status=new ArrayList<String>();
		int count=1;
		for(int p=2;p<pageNum.size();p++) {
			while(true)
			{
				List<WebElement>rows=driver.findElements(By.xpath("//ul[@class='responsive-table']/li"));
				for(int i=2;i<rows.size();i++) {
					WebElement el=rows.get(i);
					List<WebElement>clnum=el.findElements(By.tagName("div"));
					int size=clnum.size();
					int j=0;

					for(WebElement el1:clnum) {
						if(j==size-2) {
							status.add(el1.getText());
						}
						j++;

					}
				}
				if(count==pageNum.size()) {
					break;
				}

			}
			pageNum.get(p).click();
			count++;


		}

		return status;	
				

	}
	public void startDate() throws InterruptedException {
		String date="10";
		String month="Aug";
		int year=1992;

		driver.findElement(By.xpath("//span[@class='mat-mdc-button-touch-target']")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//span[@class='mdc-button__label']//span")).click();

		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		boolean flag=false;

		if(year>currentYear) {

			while(true) {
				// select year
				List<WebElement> td = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));

				for(WebElement yr:td) {
					String y=yr.getText();
					if(y.equalsIgnoreCase(Integer.toString(year))) {
						// select year
						yr.click();
						flag=true;
						break;                

					}


				}

				boolean f=true;
				if(flag) {
					f=false;
					break;
				}
				if(f) {
					Thread.sleep(5000);
					driver.findElement(By.xpath("//button[@aria-label='Next 24 years']")).click();
				}

			} 

			Thread.sleep(5000);
			System.out.println("=====================");
			//			


			// select month	 
			List<WebElement> mel = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));
			for(WebElement me:mel) {
				if(month.equalsIgnoreCase(me.getText())) {
					me.click();
					break;
				}
			}
			// select date
			List<WebElement> drow = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td[@role='gridcell']/button/span[@class='mat-calendar-body-cell-content mat-focus-indicator']"));
			for(WebElement td:drow) {
				String uid= td.getText();
				
				if(date.equalsIgnoreCase(uid)) {
					// select year
					td.click();
					break;
				}

			}

		}
		else {
			while(true) {
				// select year
				List<WebElement> td = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));

				for(WebElement yr:td) {
					String y=yr.getText();
					if(y.equalsIgnoreCase(Integer.toString(year))) {
						// select year
						yr.click();
						flag=true;
						break;                

					}


				}
				boolean f=true;
				if(flag) {
					f=false;
					break;
				}
				if(f) {
					driver.findElement(By.xpath("//button[@aria-label='Previous 24 years']")).click();
				}

			} 


			// select month	 
			List<WebElement> mel = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));
			for(WebElement me:mel) {
				if(month.equalsIgnoreCase(me.getText())) {
					me.click();
					break;
				}
			}
			// select date
			List<WebElement> drow = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td[@role='gridcell']/button/span[@class='mat-calendar-body-cell-content mat-focus-indicator']"));
			for(WebElement td:drow) {
				String uid= td.getText();
				
				if(date.equalsIgnoreCase(uid)) {
					// select year
					td.click();
					break;
				}

			}

		}


	}


	public void endDate() throws InterruptedException {
		String date="30";
		String month="Dec";
		int year=1994;

		Thread.sleep(5000);

		driver.findElement(By.xpath("//span[@class='mdc-button__label']//span")).click();
		System.out.println("clcikkmkmolm;mom======");

		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		boolean flag=false;

		if(year>currentYear) {

			while(true) {
				
				// select year
				List<WebElement> td = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));
				for(WebElement yr:td) {
					String y=yr.getText();
					if(y.equalsIgnoreCase(Integer.toString(year))) {
						yr.click();
						flag=true;
						break;                

					}

				}

				boolean f=true;
				if(flag) {
					f=false;
					break;
				}
				if(f) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("//button[@aria-label='Next 24 years']")).click();
				}

			} 

			Thread.sleep(5000);
			
			// select month	 
			List<WebElement> mel = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));
			for(WebElement me:mel) {
				if(month.equalsIgnoreCase(me.getText())) {
					me.click();
					break;
				}
			}
			
			// select date
			List<WebElement> drow = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td[@role='gridcell']/button/span[@class='mat-calendar-body-cell-content mat-focus-indicator']"));
			for(WebElement td:drow) {
				String uid= td.getText();
				
				if(date.equalsIgnoreCase(uid)) {
				    td.click();
					break;
				}

			}



		}
		else {
			while(true) {
				// select year
				List<WebElement> td = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));

				for(WebElement yr:td) {
					String y=yr.getText();
					if(y.equalsIgnoreCase(Integer.toString(year))) {
						// select year
						yr.click();
						flag=true;
						break;                

					}


				}
				boolean f=true;
				if(flag) {
					f=false;
					break;
				}
				if(f) {
					driver.findElement(By.xpath("//button[@aria-label='Previous 24 years']")).click();
				}

			} 


			// select month	 
			List<WebElement> mel = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td/button/span[1]"));
			for(WebElement me:mel) {
				if(month.equalsIgnoreCase(me.getText())) {
					me.click();
					break;
				}
			}
			// select date
			List<WebElement> drow = driver.findElements(By.xpath("//table[@class='mat-calendar-table']//tbody//td[@role='gridcell']/button/span[@class='mat-calendar-body-cell-content mat-focus-indicator']"));
			for(WebElement td:drow) {
				String uid= td.getText();
				
				if(date.equalsIgnoreCase(uid)) {
					// select year
					td.click();
					break;
				}

			}

		}

	}

}




