package com.myCaseStudy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public void login(String userName, String password) {
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password); 
		driver.findElement(By.id("login-button")).click();
	}

}
