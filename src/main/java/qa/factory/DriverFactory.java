package qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import qa.exception.FrameworkException;


public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager op;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) throws InterruptedException
	{
		op = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight").trim();
		String browserName= prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser name is:" + browserName);
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		//	driver = new ChromeDriver(op.getChromeOptions());
			tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
		//	driver = new FirefoxDriver(op.getfirefoxOptions());
			tlDriver.set(new FirefoxDriver(op.getfirefoxOptions()));
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
		//	driver = new EdgeDriver(op.getEdgeOptions());
			tlDriver.set(new EdgeDriver(op.getEdgeOptions()));
		}
		
		else
		{
			System.out.println("plz pass the right browser" + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		Thread.sleep(1000);
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	public Properties initprop()
	{
		prop = new Properties();
		FileInputStream ip = null;
	    String envname = System.getProperty("env");
	    System.out.println("Running test cases on env:" + envname);
	    
	    try
	    {
	    if(envname == null)
	    {
	    	ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
	    }
	    
	    else
	    {
	    	switch(envname.toLowerCase().trim())
	    	{
	    	case "qa":
	    		ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
	    		break;
	    	case "prelive":
	    		ip = new FileInputStream("./src/test/resources/config/prelive.config.properties");
	    		break;
	    	case "prod":
	    		ip = new FileInputStream("./src/test/resources/config/config.properties");
	    		break;
	    		
	    	default:
	    		System.out.print("wrong browser passed....no need to run test cases");
	    		throw new FrameworkException("WRONG ENV IS PASSED");
	    		//break;
	    	}
	    
	    }
	    }
	    
	    
	   catch(FileNotFoundException e)
	   {
		   e.printStackTrace();
	   }
	    
	    try
	    {
	    	prop.load(ip);
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
	      return prop;
	}

	public static String getScreenshot() {
		
	     File srcfile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	     String path = System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
	     File destination = new File(path);
	     try {
			FileUtil.copyFile(srcfile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	     return path;
	    		 
	}
	
	

}
