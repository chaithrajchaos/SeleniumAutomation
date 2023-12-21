package pageObjects;

import java.util.List;

import org.apache.hc.core5.http.nio.support.AbstractAsyncServerAuthFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	By productBy =By.cssSelector(".mb-3");
	By prodName =By.cssSelector("b");
	By addToCartBy =By.cssSelector("[class='card-body'] button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement buffering;
	
	
	public List<WebElement> getProductList()
	{
		waitForElementtoappear(productBy);
		
		return products;
	}
	
	public WebElement selectProduct(String productName)
	{
		
		WebElement prod=getProductList().stream().filter(
				product -> product.findElement(prodName).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		selectProduct(productName).findElement(addToCartBy).click();
		
		waitForElementtoappear(toastMessage);
		waitForElementtoDisappear(buffering);
	}
	
	public WebElement selectMultipleProducts(String productName)
	{
		
		WebElement prod=getProductList().stream().filter(
				product -> product.findElement(prodName).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	
	
	
	
	
	
}
