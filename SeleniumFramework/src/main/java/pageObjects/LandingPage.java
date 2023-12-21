package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage {

	
    public WebDriver driver;
	
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="userEmail")
	WebElement emailid;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement login;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue logInApplication(String email,String password) {
		
		emailid.sendKeys(email);
		passwordEle.sendKeys(password);
		login.click();
		
		return new ProductCatalogue(driver);
	}
	
	
}
