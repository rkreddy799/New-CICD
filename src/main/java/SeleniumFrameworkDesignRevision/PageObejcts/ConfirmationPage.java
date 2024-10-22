package SeleniumFrameworkDesignRevision.PageObejcts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbastractComponents.AbstractComponenent;

public class ConfirmationPage extends AbstractComponenent {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	By suggestion = By.cssSelector(".ta-results");

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement Submit;

	public SuccessPage countryName(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitelementtoAppear(suggestion);
		selectCountry.click();
		Submit.click();
		return new SuccessPage(driver);

	}

}
