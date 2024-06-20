package qa.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import qa.factory.DriverFactory;
import qa.pages.AccountPage;
import qa.pages.ProductInfoPage;
import qa.pages.RegisterPage;
import qa.pages.SearchPage;
import qa.pages.loginpage;
import qa.utilities.ExcelUtil;

public class BaseTest  {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected loginpage lp;
	protected AccountPage ap;
	protected SearchPage sp;
	protected ProductInfoPage pp;
	protected SoftAssert sa;
	protected RegisterPage rp; 
	
	
	
	@BeforeTest
	public void setup() throws InterruptedException
	{
		df = new DriverFactory();
		prop = df.initprop();
		driver = df.initDriver(prop);
		lp = new loginpage(driver);
		
		sa = new SoftAssert();
		
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
