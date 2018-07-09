package POMDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.WebOrdersLoginPage;

public class WebOrderLoginTests {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
			
	}

	@Ignore
	@Test
	public void positiveLoginTest() {
		
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		
		String expectedTitle= "Web Orders";
		String actualTitle= driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expectedTitle));
	
	} 
	

	@Test
	public void positiveLoginUsingPOM() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		// Create a WebOrdersLoginPage object
		WebOrdersLoginPage loginPage = new WebOrdersLoginPage(driver);
		loginPage.userName.sendKeys("Tester");
		loginPage.passWord.sendKeys("test");
		loginPage.loginButton.click();
	
		String expectedTitle= "Web Orders";
		String actualTitle= driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expectedTitle));
		
	}
	
	
	@AfterMethod
	public void teardownMehtod() {
		driver.quit();
	}

}
