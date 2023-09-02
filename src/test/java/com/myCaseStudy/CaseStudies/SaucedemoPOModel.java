package com.myCaseStudy.CaseStudies;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.myCaseStudy.pages.CheckoutCompletePage;
import com.myCaseStudy.pages.CheckoutOverviewPage;
import com.myCaseStudy.pages.InventoryPage;
import com.myCaseStudy.pages.LoginPage;
import com.myCaseStudy.pages.ShoppingCartPage;
import com.myCaseStudy.pages.TakeScreenshots;
import com.myCaseStudy.pages.YourInfoPage;

public class SaucedemoPOModel {

	WebDriver driver;
	String FirstName= "TestFirstName";
	String LastName= "TestLastName";
	String ZipCode = "06084";

	@BeforeSuite
	public void setUp() {
		String driverPath = System.getProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@DataProvider(name="data")
	public static Object[][] dataprovider()
	{
		return new Object[][] {{"standard_user","secret_sauce"}};
	}
	
	@Test(dataProvider = "data")
	public void E2EScenario(String userName, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName, password); //Login to SauceDemo website
		InventoryPage inventoryPage = new InventoryPage(driver);
		Assert.assertEquals(inventoryPage.getAppLogoText(), "Swag Labs"); // Verify the Logo text
		Assert.assertEquals(inventoryPage.getTitle(), "Products"); // Verify the title of the page

		//Take a screenshot of the products page
		TakeScreenshots takeScreenshots = new TakeScreenshots(driver);
		takeScreenshots.takeScreenshot(driver);

		inventoryPage.addToCart(); // Adding items to the cart
		inventoryPage.clickShoppingcart(); // Open shopping cart
		ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
		Assert.assertEquals(shoppingCart.verifyTheCart(), 3); //Verify if the cart has items
		takeScreenshots.takeScreenshot(driver); //Take a screenshot of the cart
		YourInfoPage yourInfoPage = new YourInfoPage(driver);
		yourInfoPage.submitYourInfo(FirstName, LastName, ZipCode);
		CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
		Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewTitle(), "Checkout: Overview"); // Verify the checkout overview page is loaded
		takeScreenshots.takeScreenshot(driver);//Take a screenshot of the checkout page
		checkoutOverviewPage.clickFinish();
		CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
		Assert.assertEquals(checkoutCompletePage.getCheckoutCompleteMsg(), "Thank you for your order!"); //Verify the checkout completed message 
		takeScreenshots.takeScreenshot(driver); //Take a screenshot of the completion page
	}	

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
