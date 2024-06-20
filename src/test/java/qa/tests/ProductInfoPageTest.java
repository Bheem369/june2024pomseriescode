package qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import qa.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	
	
	@BeforeClass
	public void setUp()
	{
		ap =lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@DataProvider
	    public Object[][] getTestData()
	    {
	    	return new Object[][] {
	    		{"macbook", "MacBook Air", 4 },
	    		{"imac", "iMac", 3},
	    		{"samsung", "Samsung Galaxy Tab 10.1", 7}
	    	};
	    }
	    
	 @Test(dataProvider="getTestData")
	    public void productimgcountTest(String searchKey, String productName, int Expimgcount )
	    {
	    	sp = ap.performSearch(searchKey);
	    	pp = sp.selectProduct(productName);
	       int actualimgcount = pp.Productimagecount();
	       Assert.assertEquals(actualimgcount, Expimgcount );
	    	
	    	
	    }
	 
	 @Test
	 public void ProductInfoTest()
	 {
		 sp = ap.performSearch("macbook");
	     pp = sp.selectProduct("MacBook Air");
	     Map<String, String> prodlist = pp.getProductInfo();
	     System.out.println(prodlist);
	     sa.assertEquals(prodlist.get("Brand"), "Apple");
	     sa.assertEquals(prodlist.get("Product Code"), "Product 18");
	     
	     sa.assertAll();
	 }
	 
	 @Test
	 public void addToCartTest()
	 {
		 sp = ap.performSearch("macbook");
	     pp = sp.selectProduct("MacBook Air");
	     pp.enterQuantity();
	     String actmesg = pp.addProdtoCart();
	     System.out.println(actmesg);
	     sa.assertTrue(actmesg.contains("MacBook"));
	     sa.assertTrue(actmesg.contains("MacBook Air"));

	     
	     sa.assertAll();
	     
	 }
}
