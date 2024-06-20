package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import qa.base.BaseTest;

@Epic("EPIC-100: Design Logic for open cart app")
@Story("US-Login: 101: design login page features for open cart")
public class LoginPageTest extends BaseTest {
	
	
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("....getting the title of the page....")
	@Test(priority=1)
	public void loginpagetitleTest()
	{
		String actualtitle = lp.getLoginPageTitle();
		Assert.assertEquals(actualtitle, "Account Logi");
	}
	
	
	
	@Severity(SeverityLevel.NORMAL)
	@Description("....checking url of the page....")
	@Test(priority=2)
	public void loginpageurlTest()
	{
		String actualurl = lp.getLoginPageURL();
		Assert.assertEquals(actualurl, "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("....checking forgot pwd link exist....")
	@Test(priority=3)
	public void ispwdlinkexistTest()
	{
		Assert.assertTrue(lp.isforgotPwdLinkExist());
	}
	
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("....checking username and password are valid or not....")
	@Test(priority=4)
	public void dologinTest()
	{
		lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
}
