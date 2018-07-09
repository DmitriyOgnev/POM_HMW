package POMDesign;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.OrderPage;
import pages.WebOrdersLoginPage;

public class PlaceOrderTest {
	
	
	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrderPage;
	OrderPage orderPage;
	
	
	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	@BeforeMethod
		public void setUpMethod() {

		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);

	}

	
	@Test(description="Testing order placement")
	public void orderPlacementTest() {
		
		loginPage.login("Tester", "test");
		
		allOrderPage = new AllOrdersPage(driver);
		
		// Asserting that logged in and landed on the right page		
		Assert.assertTrue(allOrderPage.webOrders.isDisplayed(), "Web Orders is not displayed");
		Assert.assertEquals(allOrderPage.menu1stItem.getText(), "View all orders");
		Assert.assertEquals(allOrderPage.menu2ndItem.getText(), "View all products");
		Assert.assertEquals(allOrderPage.menu3rdItem.getText(), "Order");
		
		// Clicking the 3rd item in the menu
		allOrderPage.menu3rdItem.click();
		
		
		orderPage = new OrderPage(driver);
		
		// Product information
		String prdType = "FamilyAlbum";
		int prdQuant = 2;
		
		orderPage.enterProductInformation(prdType, prdQuant);
		
		// Defines an array of strings for whole address 
		String[] custAddress = {
				"Peter Jones",
				"123 Main st.",
				"Anytown",
				"AS",
				"12345"	};
		
		// Enters the address
		orderPage.enterCustormerAddress(custAddress);

		// Credit card information string array
		String[] crdCard = {"Visa", "1234567812345678", "11/22"};
		
		// Enter Payment information
		orderPage.enterPaymentInfo(crdCard[0], crdCard[1], crdCard[2]);
		
		// Processes the application
		orderPage.processApplication();
		
		// Clicking the 1st item in the menu
		allOrderPage.menu1stItem.click();
		
		// Gets a map of first records row split into cells 
		Map<String, String> map = new HashMap<String, String>(allOrderPage.getNthRow(1));
		
		// Asserts address information
		Assert.assertEquals(map.get("Name"), custAddress[0]);
		Assert.assertEquals(map.get("Street"), custAddress[1]);
		Assert.assertEquals(map.get("City"), custAddress[2]);
		Assert.assertEquals(map.get("State"), custAddress[3]);
		Assert.assertEquals(map.get("Zip"), custAddress[4]);
		
		// Asserts product information
		Assert.assertEquals(map.get("Product"), prdType);
		Assert.assertEquals(map.get("#"), Integer.toString(prdQuant));
		
		// Asserts payment information
		Assert.assertEquals(map.get("Card"), crdCard[0]);
		Assert.assertEquals(map.get("Card Number"), crdCard[1]);
		Assert.assertEquals(map.get("Exp"), crdCard[2]);
					
		// Asserts date
		String strCurrTime = "07/09/2018";
		Assert.assertEquals(map.get("Date"), strCurrTime);
	
	}
	
	@AfterClass
	public void teardownClass() {
		driver.close();
	}

}
