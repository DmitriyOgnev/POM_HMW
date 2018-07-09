package POMDesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTests {

	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrderPage;
	ProductsPage productsPage;
	
	
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

	@Test(description="Test login page")
	public void labelsVerification() {

		
//// Using individual elements of Page model to assert and navigate to the webelements
		
		Assert.assertEquals(driver.getTitle(), "Web Orders Login", "Login Page is not displayed, the app is down");
//		loginPage.userName.sendKeys("Tester");
//		loginPage.passWord.sendKeys("test");
//		loginPage.loginButton.click();
		
		// Using a single method to do whole login process
		loginPage.login("Tester", "test");
		
		allOrderPage = new AllOrdersPage(driver);
		Assert.assertTrue(allOrderPage.webOrders.isDisplayed(), "Web Orders is not displayed");
		Assert.assertEquals(allOrderPage.menu1stItem.getText(), "View all orders");
		Assert.assertEquals(allOrderPage.menu2ndItem.getText(), "View all products");
		Assert.assertEquals(allOrderPage.menu3rdItem.getText(), "Order");
	} 
	
	@Test(description="Verify default list of availbale products")
	public void avnailableProductsTest() {
		
		Assert.assertEquals(driver.getTitle(), "Web Orders Login", "Login Page is not displayed, the app is down");
		loginPage.login("Tester", "test");

		allOrderPage = new AllOrdersPage(driver);
		allOrderPage.menu2ndItem.click();

		productsPage = new ProductsPage(driver);
		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList<String>();
		
		for (WebElement webElem : productsPage.productNames) {
			actProducts.add(webElem.getText());
		}
		
		Assert.assertEquals(actProducts, expProducts);
		
		System.out.println(actProducts);
		System.out.println(expProducts);
		
	}
	
	
	
	@AfterMethod
	public void teardownMethod() {
		allOrderPage.logOut();		
	}
	
	@AfterClass
	public void teardownClass() {		
		driver.close();
	}
	
}
