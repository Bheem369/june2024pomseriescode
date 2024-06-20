package qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.utilities.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eu;
	
	
	
	
	private By logoutlink = By.linkText("Logout");
	private By search = By.name("search");
	private By accHeaders = By.xpath("//div[@id='content']//h2");
    private By seacrhbtn = By.cssSelector(".btn.btn-default");
	
	
	public AccountPage(WebDriver driver)
	{
		this.driver = driver;
		eu = new ElementUtil(driver); 
	}
	
	public String accPageTittle()
	{
		String title = eu.waitForTitleIsAndFetch(10, "My Account");
		System.out.println(title);
		return title;
		
	}
	
	public String accPageURL()	
	{
		String url = eu.waitForURLContainsAndFetch(10, "route=account/account");
		System.out.println(url);
		return url;
		
	}
	
	public boolean islogoutlinkExist()
	{
		boolean flag = eu.waitForElementVisible(logoutlink, 10).isDisplayed();
		return flag;
	}

	public boolean isSearchExist()
	{
		boolean flag = eu.waitForElementVisible(search, 10).isDisplayed();
		return flag;
	}
	
	public List<String> getaccHeaderList()
	{
		System.out.println(eu.getElementsTextList(accHeaders));
		return eu.getElementsTextList(accHeaders);
	}
	
	 public SearchPage performSearch(String searchKey)
	    {
	    if(isSearchExist())	
	    {
	    	eu.doSendKeys(search, searchKey);
	    	eu.doClick(seacrhbtn);
	    	return new SearchPage(driver);
	    }
		 
	    else
	    {
	    	System.out.println("search field is not present on the page");
	    	return null;
	    }
	    }
}
