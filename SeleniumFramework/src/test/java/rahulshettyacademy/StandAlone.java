package rahulshettyacademy;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import abstractcomponents.AbstractComponents;
import generics.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.ConfirmPage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalogue;

public class StandAlone extends BaseTest {
	
	@Test(dataProvider="getData1", groups= {"Purchase"},retryAnalyzer =generics.Retry.class)

	public void submitOrder(HashMap<String,String> h) throws IOException{

		//String productName = "IPHONE 13 PRO";		
		ProductCatalogue productcat = page.logInApplication(h.get("email"), h.get("password"));

		productcat.addProductToCart(h.get("productname"));
		CartPage cartpage = productcat.goToCart();

		Boolean match = cartpage.verifyProductInCart(h.get("productname"));
		Assert.assertTrue(match);
		PaymentPage paymentpage = cartpage.clickOnCheckout();

		paymentpage.enterShippingAddress("India");
		ConfirmPage confirmpage = paymentpage.clickOnPalceOrder();

		String confirmmsg = confirmpage.confirmMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"chaithrakala1998@gmail.com","1998@20Chai","IPHONE 13 PRO"},{"chaithrakala1998@gmail.com","1998@20Chai","IPHONE 13 PRO"}};
	}
	
	
	@DataProvider
	public Object[][] getData1() throws IOException
	{
		
		List<HashMap<String, String>> data=getJsonDataFile(System.getProperty("user.dir")+"\\src\\test\\java\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(0)}};
		
	}
	
	//HashMap<String,String> h=new HashMap<String,String>();
//	h.put("email", "chaithrakala1998@gmail.com");
//	h.put("password", "1998@20Chai");
//	h.put("productname", "IPHONE 13 PRO");
//	
//	HashMap<String,String> h1=new HashMap<String,String>();
//	h1.put("email", "chaithrakala1998@gmail.com");
//	h1.put("password", "1998@20Chai");
//	h1.put("productname", "IPHONE 13 PRO");
	
	

}
