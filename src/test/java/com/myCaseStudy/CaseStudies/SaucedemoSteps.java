package com.myCaseStudy.CaseStudies;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import com.myCaseStudy.pages.CheckoutCompletePage;
import com.myCaseStudy.pages.CheckoutOverviewPage;
import com.myCaseStudy.pages.InventoryPage;
import com.myCaseStudy.pages.LoginPage;
import com.myCaseStudy.pages.ShoppingCartPage;
import com.myCaseStudy.pages.TakeScreenshots;
import com.myCaseStudy.pages.YourInfoPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SaucedemoSteps {
	
	static WebDriver driver;
	static TakeScreenshots takeScreenshots;
	static InventoryPage inventoryPage;
	String FirstName= "TestFirstName";
	String LastName= "TestLastName";
	String ZipCode = "06084";
	
	@BeforeAll
	public static void beforeAll() {
		String driverPath = System.getProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		takeScreenshots = new TakeScreenshots(driver);
		inventoryPage = new InventoryPage(driver);
	}

	@Given("I open the Saucedemo website and see the title as {string}")
	public void i_open_the_saucedemo_website_and_see_the_title_as(String title) {
		Assert.assertEquals(driver.getTitle(), title);
	}

	@When("^I provide the username and password I click the login button$")
	public void i_provide_the_username_and_password_i_click_the_login_button(DataTable credentials) {
		List <List <String>> data = credentials.asLists(String.class);
    	String Uname = data.get(1).get(1);
    	String pwd = data.get(2).get(1);
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.login(Uname, pwd);
	}
   
    @Then("I should successfully log into the Saucedemo website")
    public void i_should_successfully_log_into_the_saucedemo_website() throws IOException {
		takeScreenshots.takeScreenshot(driver);
    	Assert.assertEquals(inventoryPage.getAppLogoText(), "Swag Labs");
    }

    @Given("I am logged in")
    public void i_am_logged_in() throws IOException {
    	Assert.assertEquals(inventoryPage.getTitle(), "Products");
    }

    @When("I add multiple items to the cart")
    public void i_add_multiple_items_to_the_cart() throws IOException {
    	inventoryPage.addToCart();
    }

    @Then("those items should appear in my cart")
    public void those_items_should_appear_in_my_cart() throws IOException {
    	InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.clickShoppingcart();		
    }

    @Given("I have items in my cart")
    public void i_have_items_in_my_cart() throws IOException {
    	ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertEquals(shoppingCartPage.verifyTheCart(), 3);
        takeScreenshots.takeScreenshot(driver);
    }

    @When("I proceed to checkout and provide necessary details")
    public void i_proceed_to_checkout_and_provide_necessary_details() throws IOException {
        YourInfoPage yourinfoPage = new YourInfoPage(driver);
        yourinfoPage.submitYourInfo(FirstName, LastName, ZipCode);
        takeScreenshots.takeScreenshot(driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewTitle(), "Checkout: Overview");
    }

    @When("I finish the order")
    public void i_finish_the_order() throws IOException {
    	driver.findElement(By.id("finish")).click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String completeMessage) throws IOException {
    	takeScreenshots.takeScreenshot(driver);
    	CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
    	Assert.assertEquals(checkoutCompletePage.getCheckoutCompleteMsg(), completeMessage);  	
    }

    @AfterAll
    public static void afterAll() {
		if (driver != null) {
			driver.quit();
		}
	}
}
