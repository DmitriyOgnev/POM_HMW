package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrderPage {

	public OrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table/tbody/tr/td/div/h2")
	public WebElement pageSubTitle;

	@FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement productsDropDownMenuWebElem;

	@FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement productOrderQuantity;

	@FindBy(xpath = "//input[@value='Calculate']")
	public WebElement calculateButton;

	public void enterProductInformation(String prdType, int prdQuant) {
		Select productsDropDownMenu = new Select(productsDropDownMenuWebElem);

		productsDropDownMenu.selectByVisibleText(prdType);
		productOrderQuantity.clear();
		productOrderQuantity.sendKeys(Integer.toString(prdQuant));
		calculateButton.click();

	}

	@FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
	public WebElement tbxCustomerName;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
	public WebElement tbxStreet;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
	public WebElement tbxCity;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
	public WebElement tbxState;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
	public WebElement tbxZipCode;

	public void enterCustormerAddress(String[] custAddress) {

		tbxCustomerName.sendKeys(custAddress[0]);
		tbxStreet.sendKeys(custAddress[1]);
		tbxCity.sendKeys(custAddress[2]);
		tbxState.sendKeys(custAddress[3]);
		tbxZipCode.sendKeys(custAddress[4]);

	}

	@FindBy(id = "ctl00_MainContent_fmwOrder_cardList_0")
	public WebElement visaCard;

	@FindBy(id = "ctl00_MainContent_fmwOrder_cardList_1")
	public WebElement masterCard;

	@FindBy(id = "ctl00_MainContent_fmwOrder_cardList_2")
	public WebElement americanCard;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNumber;

	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
	public WebElement cardExparation;

	public void enterPaymentInfo(String cardType, String cardNo, String cardExp) {

		switch (cardType) {
		case "Visa":
			visaCard.click();
			break;
		case "MasterCard":
			masterCard.click();
			break;
		case "American Express":
			americanCard.click();
			break;
		}

		cardNumber.sendKeys(cardNo);
		cardExparation.sendKeys(cardExp);

	}

	@FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement processButton;

	@FindBy(xpath = "//input[@value='Reset']")
	public WebElement resetButton;

	public void processApplication() {
		processButton.click();
	}

	public void resetApplication() {
		resetButton.click();
	}

}
