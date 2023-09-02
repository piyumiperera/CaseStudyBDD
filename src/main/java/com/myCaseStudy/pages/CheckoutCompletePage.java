package com.myCaseStudy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {

	WebDriver driver;

	public CheckoutCompletePage(WebDriver driver) {
		this.driver=driver;
	}


	public String getCheckoutCompleteMsg() {
		return driver.findElement(By.className("complete-header")).getText();

	}
}
