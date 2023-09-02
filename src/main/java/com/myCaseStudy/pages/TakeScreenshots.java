package com.myCaseStudy.pages;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class TakeScreenshots {

	WebDriver driver;

	public TakeScreenshots(WebDriver driver) {
		this.driver=driver;
	}

	public void takeScreenshot(WebDriver dr) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dateobj = new Date();
		String screenshotName = "screenshot"+ df.format(dateobj);


		File file =((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(file, new File(".\\Screenshots\\"+screenshotName+UUID.randomUUID().toString()+".png"));
		}		
		catch(Exception e){
			e.printStackTrace();
		}

	}
}
