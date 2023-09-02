package com.myCaseStudy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

	WebDriver driver;

	public CheckoutOverviewPage(WebDriver driver) {
		this.driver=driver;
	}


	public void clickFinish() {
		driver.findElement(By.id("finish")).click();
	}

	public String getCheckoutOverviewTitle() {
		return driver.findElement(By.className("title")).getText();

	}
}
