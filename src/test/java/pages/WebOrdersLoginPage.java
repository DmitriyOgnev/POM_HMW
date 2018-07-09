package pages;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WebOrdersLoginPage {

	public WebOrdersLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ctl00_MainContent_username")
	public WebElement userName;
	
	@FindBy(id="ctl00_MainContent_password")
	public WebElement passWord;
	
	@FindBy(id="ctl00_MainContent_login_button")
	public WebElement loginButton;
	



	public void login(String usr, String pass) {
		
		userName.sendKeys(usr);
		passWord.sendKeys(pass);
		loginButton.click();
		
	}
	
}
