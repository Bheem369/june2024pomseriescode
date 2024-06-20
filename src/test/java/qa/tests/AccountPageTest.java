package qa.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.base.BaseTest;
import qa.constants.AppConstants;

public class AccountPageTest extends BaseTest {
	
	
	
	@BeforeClass
	public void setUp()
	{
		ap =lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority=1)
	public void accPageTitleTest()
	{
		String actTitle =ap.accPageTittle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	@Test(priority=2)
	public void accPageURLTest()
	{
		String acturl = ap.accPageURL();
		Assert.assertTrue(acturl.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	
    @Test(priority=3)
	public void isLinkExistTest()
	{
    	Assert.assertTrue(ap.islogoutlinkExist());
	}
    
    @Test(priority=4)
    public void accheaderscountTest()
    {
    	List<String> actheaderlist = ap.getaccHeaderList();
    	Assert.assertEquals(actheaderlist, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
    	
    }
    
    @DataProvider
    public Object[][] getTestData()
    {
    	return new Object[][] {
    		{"macbook", "MacBook Air"},
    		{"imac", "iMac"},
    		{"samsung", "Samsung Galaxy Tab 10.1"}
    	};
    }
    
    @Test(priority=5,dataProvider="getTestData")
    public void SearchTest(String searchKey, String productName )
    {
    	sp = ap.performSearch(searchKey);
    	pp = sp.selectProduct(productName);
    	String actproductheader = pp.ProductInfoText();
    	System.out.println(actproductheader);
    	Assert.assertEquals(actproductheader,productName );
    	
    }
   
    
}
