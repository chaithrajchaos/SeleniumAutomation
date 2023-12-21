package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[.='Checkout']")
	WebElement checkoutButton;
	
	public List<WebElement> cartProductList()
	{
		return cartProducts;
	}
	
	public Boolean verifyProductInCart(String productName)
	{
		Boolean match = cartProductList().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage clickOnCheckout()
	{
		checkoutButton.click();
		
		return new PaymentPage(driver);
	}

}
