package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {

	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By placeDropdown=By.cssSelector(".ta-results");
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement placeName;
	
	@FindBy(css=".action__submit")
	WebElement placeorder;
	
	public void enterShippingAddress(String countryName) {
		Actions act=new Actions(driver);
		act.sendKeys(country, countryName).build().perform();
		waitForElementtoappear(placeDropdown);
		
		placeName.click();
		
	}
	
	public ConfirmPage clickOnPalceOrder()
	{
		placeorder.click();
		
		return new ConfirmPage(driver);
	}
}
