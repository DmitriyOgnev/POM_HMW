package pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllOrdersPage {

	public AllOrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td/h1")
	public WebElement webOrders;

	
	@FindBy(css=".login_info")
	public WebElement welcomeMsg;
	

	@FindBy(xpath="//ul[@id='ctl00_menu']/li[1]/a")
	public WebElement menu1stItem;


	@FindBy(xpath="//ul[@id='ctl00_menu']/li[2]/a")
	public WebElement menu2ndItem;

	
	@FindBy(xpath="//ul[@id='ctl00_menu']/li[3]/a")
	public WebElement menu3rdItem;
	
	
	@FindBy(id="ctl00_logout")
	public WebElement logOut;
	
	public void logOut() {
		logOut.click();
	}
	
	@FindBy(id="ctl00_MainContent_orderGrid")
	public WebElement recordsTable;
	
	public Map<String, String> getNthRow(int n){
		
		Map<String, String> map = new HashMap<String, String>();
		
		for (int i=2; i<=12 ; i++) {

			String key = recordsTable.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[1]/th[" + i + "]")).getText();
			String value = recordsTable.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[" + (n+1) +"]/td[" + i + "]")).getText();
			
			map.put(key, value);
		}
		
		return map;
	}

}
