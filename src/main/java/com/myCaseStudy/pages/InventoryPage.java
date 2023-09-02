package com.myCaseStudy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

	WebDriver driver;

	public InventoryPage(WebDriver driver) {
		this.driver=driver;
	}

	public void clickShoppingcart() {
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
	}

	public String getAppLogoText() {
		return driver.findElement(By.className("app_logo")).getText();

	}

	public String getTitle() {
		return driver.findElement(By.className("title")).getText();

	}

	public void addToCart() {
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
	}
}
