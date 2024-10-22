package SeleniumFrameworkDesignRevision.PageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbastractComponents.AbstractComponenent;

public class LandingPage extends AbstractComponenent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement useremail;

	@FindBy(id = "userPassword")
	WebElement userpassword;

	@FindBy(id = "login")
	WebElement login;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	@FindBy(css="[class*='toast-error']")
	WebElement errorMessage;
	
	public ProductCatalogue loginaplication(String email, String password) {
		useremail.sendKeys(email);
		userpassword.sendKeys(password);
		login.click();
		return new ProductCatalogue(driver);
		 
	}
	public String getErrorMessage()
	
	{
		waitforWebelementtoAppear(errorMessage);
		return errorMessage.getText();
	}

}
