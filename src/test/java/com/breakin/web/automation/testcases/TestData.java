package com.breakin.web.automation.testcases;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.breakin.web.automation.dataprovider.GenerateData;

public class TestData {
   @Test
	public void data() {
	// TODO Auto-generated constructor stub
	  Object[][] d = GenerateData.getSheetName();
	  
	 System.out.println( Arrays.deepToString(d));
   }
		
	

}
