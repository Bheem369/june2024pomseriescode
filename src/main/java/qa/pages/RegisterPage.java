package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.constants.AppConstants;
import qa.utilities.ElementUtil;

public class RegisterPage {
	
	
	private WebDriver driver;
	private ElementUtil eu;
	
	

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confpass = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");
	private By agreecheckbox =By.xpath("//input[@name='agree']");
	private By continuebtn = By.xpath("//input[@value='Continue']");
	private By registersuccessmsg =By.cssSelector("div#content h1");
	private By logoutlink = By.linkText("Logout");
	private By registerlink = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		eu = new ElementUtil(driver);
	}
	
	public boolean RegisterUser(String firstname, String lastname , String telephone, String password, String email, String subscribe )
	{
		eu.waitForElementVisible(this.firstname, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstname);
		eu.doSendKeys(this.lastname, lastname);
		eu.doSendKeys(this.email, email);
		eu.doSendKeys(this.telephone,telephone );
		eu.doSendKeys(this.password, password);
		eu.doSendKeys(this.confpass, password);
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eu.doClick(subscribeYes);
		}
		else
		{
			eu.doClick(subscribeNo);
		}
		eu.doClick(agreecheckbox);
		eu.doClick(continuebtn);
		
		String succmesg = eu.waitForElementVisible(registersuccessmsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		if(succmesg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG))
		{
			eu.doClick(logoutlink);
			eu.doClick(registerlink);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
}