package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.constants.AppConstants;
import qa.utilities.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eu;
	private By searchProductResult = By.cssSelector(".product-layout");

	public SearchPage(WebDriver driver) {
		this.driver=driver;
		eu = new ElementUtil(driver);
	}
	
	public int getSearchProductCount()
	{
		return eu.waitForElementsVisible(searchProductResult, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	}

	public ProductInfoPage selectProduct(String productName)
	{
		By productLocator = By.linkText(productName);
		eu.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}
}
