package SeleniumFrameworkDesignRevision.PageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbastractComponents.AbstractComponenent;

public class SuccessPage extends AbstractComponenent {
	WebDriver driver;

	public SuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(tagName = "h1")
	WebElement confirmmsg;

	public String getSuccessmsg() {
		return confirmmsg.getText();
	}

}
