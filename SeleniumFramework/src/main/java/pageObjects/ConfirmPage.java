package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import abstractcomponents.AbstractComponents;

public class ConfirmPage extends AbstractComponents {

	WebDriver driver;
	
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	By messageText=By.cssSelector(".hero-primary");

	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String confirmMessage()
	{
		waitForElementtoappear(messageText);
		String confirmmsg= message.getText();
		System.out.println(confirmmsg);
		return confirmmsg;
	}

}
