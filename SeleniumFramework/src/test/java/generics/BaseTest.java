package generics;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage page;
 public Properties prop;
	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		//it is a turnarey operator         condition                     true statement            False statement
		String browserName =System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		// String browserName =prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox script
			driver = new FirefoxDriver();
			
		} else if (browserName.equals("edge")) {
			// edge script
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataFile(String filePath) throws IOException
	{
		String jsoncontent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		 ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> userData = (List<HashMap<String, String>>) mapper.readValue(  
				jsoncontent,  new TypeReference<List<HashMap<String, String>>>(){});
		return userData;
	}
	
	public String getScreenshot(String testcasename,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Screenshot"+testcasename+".png"));
		return System.getProperty("user.dir")+"\\Screenshot"+testcasename+".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		 page = new LandingPage(driver);
		page.goTo();
		return page;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser()
	{
		driver.close();
	}
}
