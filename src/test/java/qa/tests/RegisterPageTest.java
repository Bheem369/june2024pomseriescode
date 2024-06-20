package qa.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.base.BaseTest;
import qa.constants.AppConstants;
import qa.utilities.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	public String randomMail()
	{
		Random rand = new Random();
		String email = "Automation"+rand.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@BeforeClass
	public void regPageSetup()
	{
		rp = lp.doRegister();
	}
	
	@DataProvider
	public Object[][] userRegTestData()
	{
	 Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	 return regData;
	}
	
	@Test(dataProvider ="userRegTestData")
	public void userRegTest(String firstname, String lastname , String telephone, String password, String subscribe)
	{
		Assert.assertTrue(rp.RegisterUser(firstname, lastname, telephone, password, randomMail(), subscribe));
	}

}
