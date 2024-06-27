package com.breakin.web.automation.dataprovider;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.breakin.web.automation.cutom.listerns.CustomTestListener;
import com.breakin.web.automation.util.*;

import java.util.Collections;
import java.util.HashMap;
import java.io.*;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class GenerateData {
	static String path1=System.getProperty("user.dir") + "/src/test/resources/testdata/79205E40.xlsx";
	Workbook wb;
	
	/************** To the Data for TestCase ******************/
	public static Object[][] getTestData( String sheetName, String testName) {
		Object[][] dataSet = null;
	
		try {
			ExcelUtils readdata = new ExcelUtils(path1);
		

			// Find Start Row of TestCase
			int startRowNum = 0;
			while (!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
				startRowNum++;
			}

			int startTestColumn = startRowNum + 1;
			int startTestRow = startRowNum + 2;

			// Find Number of Rows of TestCase
			int rows = 0;
			while (!readdata.getCellData(sheetName, 0, startTestRow + rows).equals("")) {
				rows++;
			}

			// Find Number of Columns in Test
			int colmns = 0;
			while (!readdata.getCellData(sheetName, colmns, startTestColumn).equals("")) {
				colmns++;
			}

			// Define Two Object Array
			dataSet = new Object[rows][1];
			HashMap<String, String> dataTable = null;
			int dataRowNumber = 0;
			for (int rowNumber = startTestRow; rowNumber <= startTestColumn + rows; rowNumber++) {
				dataTable = new HashMap<>();
				for (int colNumber = 0; colNumber < colmns; colNumber++) {
					String key = readdata.getCellData(sheetName, colNumber, startTestColumn);
					String value = readdata.getCellData(sheetName, colNumber, rowNumber);
					dataTable.put(key, value);
				}
				dataSet[dataRowNumber][0] = dataTable;
				dataRowNumber++;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return dataSet;
	}
	public static void generateTestNGXMLFromExcel() {
		// String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/testdata/excel.xlsx";
		    try (FileInputStream fis = new FileInputStream(path1);
		         Workbook workbook = new XSSFWorkbook(fis)) {
		        Sheet sheet = workbook.getSheet("Table Data"); // Assuming data is on the first sheet

		        // Create a new TestNG object
		        TestNG testNG = new TestNG();

		        // Create a new XML suite
		        XmlSuite suite = new XmlSuite();
		        suite.setName("Dynamic Test Suite");

		        // Loop through the rows in the Excel sheet
		        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
		            Row row = sheet.getRow(i);

		            // Assuming column index 1 contains the class name and column index 2 contains the execution status
		            Cell classNameCell = row.getCell(1);
		            Cell executionStatusCell = row.getCell(2);

		            String className = classNameCell.getStringCellValue();
		            String executionStatus = executionStatusCell.getStringCellValue();

		            // If the execution status is "yes", add the test class to the XML test
		            if (executionStatus.equalsIgnoreCase("Y")) {
		                XmlTest test = new XmlTest(suite);
		                test.setName(className);

		                // Add parameters from the Excel sheet
		                for (int j = 3; j <= row.getLastCellNum(); j++) {
		                    Cell parameterCell = row.getCell(j);

		                    // Skip empty cells
		                    if (parameterCell == null || parameterCell.getCellType() == CellType.BLANK) {
		                        continue;
		                    }
		                    String parameterValue;

		                    if (parameterCell.getCellType() == CellType.NUMERIC) {
		                       int Value = (int)parameterCell.getNumericCellValue();
		                       parameterValue=String.valueOf(Value);
		                    } else {
		                        parameterValue = parameterCell.getStringCellValue();
		                    }
		                    

		                    // Assuming the parameter names are specified in the row above the data row
		                    Cell parameterNameCell = sheet.getRow(0).getCell(j);
		                    String parameterName = parameterNameCell.getStringCellValue();

		                    test.addParameter(parameterName, parameterValue);
		                }

		                // Add the test class to the XML test
		                XmlClass xmlClass = new XmlClass(className);
		                test.getXmlClasses().add(xmlClass);
		            }
		        }

		        // Add the XML suite to the TestNG object
		        testNG.setXmlSuites(Collections.singletonList(suite));

		        // Set the output directory for the generated XML file
		        String outputDirectory = System.getProperty("user.dir") + "/Dynamic";
		        testNG.setOutputDirectory(outputDirectory);

		        // Create the output directory if it doesn't exist
		        File directory = new File(outputDirectory);
		        if (!directory.exists()) {
		            directory.mkdirs();
		        }

		        // Generate the TestNG XML file
		        String xmlFilePath = outputDirectory + "/testng.xml";
		        String xmlString = suite.toXml();
		        try (FileWriter writer = new FileWriter(xmlFilePath)) {
		            writer.write(xmlString);
		        }

		        System.out.println("TestNG XML generated successfully.");
		        
		        // Add the listeners to the TestNG object
		        testNG.addListener(new CustomTestListener());

		        // Run the TestNG tests
		        testNG.run();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }

	}
	public static Object[][] getSheetName() {
		Object[][] d = null;
		String path=System.getProperty("user.dir") + "/src/test/resources/testdata/79205E40.xlsx";
		Workbook workbook = null;
		
		System.out.println("==========================");
		try {
		File f=new File(path);
       FileInputStream fis = new FileInputStream(f);
        //  workbook = new XSSFWorkbook(fis);
         // sheet = workbook.getSheetAt(0);
          //fis.close();
          workbook=WorkbookFactory.create(fis);
      } catch (Exception e) {
          e.printStackTrace();
      }
		Sheet sheet=workbook.getSheetAt(0);
		int cnum=sheet.getRow(0).getLastCellNum();
		int rnum=sheet.getLastRowNum();
		for (int i=1;i<=rnum;i++) {
			String status=sheet.getRow(i).getCell(3).toString();
			 if(status.equalsIgnoreCase("y")) {
				 String platform= sheet.getRow(i).getCell(1).toString();
				 String deviceName= sheet.getRow(i).getCell(2).toString();
				 System.out.println(platform);
				 System.out.println(deviceName);
				 
				 d = getTestData(platform, deviceName);
			 }
		
				
	}
		return d;
	}
}
