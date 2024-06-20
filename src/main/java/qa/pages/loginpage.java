package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import qa.utilities.ElementUtil;

public class loginpage {
	
	private WebDriver driver;
	private ElementUtil eu;
	
	//private By Locators
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerlink = By.linkText("Register");
	
	public loginpage(WebDriver driver)
	{
		this.driver= driver;
		eu = new ElementUtil(driver);
	}
	
	@Step(".....getting the login page title....")
	public String getLoginPageTitle()
	{
		String title = eu.waitForTitleIsAndFetch(10, "Account Login");
		System.out.println(title);
		return title;
	}
	
	@Step(".....getting the login page url....")
	public String getLoginPageURL()
	{
		String url = eu.waitForURLContainsAndFetch(10, "route=account/login");
		System.out.println(url);
		return url;
	}

	@Step("....getting forgot pwd link....") 
	public boolean isforgotPwdLinkExist()
	{
		boolean flag = eu.waitForElementVisible(forgotPwdLink, 10).isDisplayed();
		return flag;
	}
	
	@Step("login with username:{0} and password: {1}")
	public AccountPage doLogin(String un, String pwd)
	{
		eu.doSendKeys(emailid, un);
		eu.doSendKeys(password, pwd);
		eu.doClick(loginBtn);
		return new AccountPage(driver) ;
	}
	
	@Step("navigating to register page")
	public RegisterPage doRegister()
	{
		eu.doClick(registerlink);
		return new RegisterPage(driver);
	}
}
