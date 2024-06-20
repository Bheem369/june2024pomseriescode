package qa.pages;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.utilities.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eu;
	private Map<String, String> productInfoMap;
	
	private By productHeader = By.tagName("h1"); 
	private By imagecount = By.cssSelector(".thumbnail img");
	private By prodMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By prodpriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
    private By qunatity = By.id("input-quantity");
    private By addtoCart = By.id("button-cart");
    private By succmesg = By.cssSelector("div.alert.alert-success");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtil(driver);
		}
	
	public String ProductInfoText()
	{
		String Productheadertext = eu.doElementGetText(productHeader);
		System.out.println(Productheadertext);
		return Productheadertext;
	}
	
	public int Productimagecount()
	{
	    return eu.getTotalElementsCount(imagecount);	
	}
	
	public void enterQuantity()
	{
		eu.doSendKeys(qunatity, String.valueOf("2"));
	}
	
	public String addProdtoCart()
	{
		eu.doClick(addtoCart);
		String mesg = eu.waitForElementVisible((succmesg), 5).getText();
		String substrmesg = mesg.trim().substring(0, mesg.length()-1);
		return substrmesg;
	}
	
	
	public Map<String, String> getProductInfo()
	{
		productInfoMap  = new HashMap<String, String>();
		productInfoMap.put("Productname", ProductInfoText());
		getProductMetaData();
		getPriceData();
		return productInfoMap;
		
		
	}
	
	
	private void getProductMetaData()
	{
		List<WebElement>  metaList = eu.getElements(prodMetaData);
		 for(WebElement e : metaList)
		 {
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		
	}
	

}
	
     private void getPriceData()
	{
		List<WebElement> pricelist = eu.getElements(prodpriceData);
		String price = pricelist.get(0).getText();
		String exTax =pricelist.get(1).getText();
		String extaxval = exTax.split(":")[1].trim();
		productInfoMap.put("Productprice", price);
		productInfoMap.put("exTax", extaxval);
	}
	
	
}


