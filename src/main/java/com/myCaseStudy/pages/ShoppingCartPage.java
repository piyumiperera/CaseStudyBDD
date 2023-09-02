package com.myCaseStudy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage {

	WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		this.driver=driver;
	}

	public int verifyTheCart() {
		List<WebElement> dynamicElement = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		return dynamicElement.size();

	}
}
