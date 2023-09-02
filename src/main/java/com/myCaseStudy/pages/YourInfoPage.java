package com.myCaseStudy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourInfoPage {

	WebDriver driver;

	public YourInfoPage(WebDriver driver) {
		this.driver=driver;
	}

	
	public void submitYourInfo(String FirstName, String LastName, String ZipCode) {
		driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(FirstName);
        driver.findElement(By.id("last-name")).sendKeys(LastName); 
        driver.findElement(By.id("postal-code")).sendKeys(ZipCode); 
        driver.findElement(By.id("continue")).click();	
	}
}
