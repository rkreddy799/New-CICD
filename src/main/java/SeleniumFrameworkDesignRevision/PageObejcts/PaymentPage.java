package SeleniumFrameworkDesignRevision.PageObejcts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Selenium.AbastractComponents.AbstractComponenent;

public class PaymentPage extends AbstractComponenent {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow .btn-primary")
	WebElement checkout;

	By cartProdcuts = By.xpath("//div[@class='cartSection']/h3");

	public boolean productNameCheck(String productName) {
		boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}

	public ConfirmationPage gotoCheckOut() {
		checkout.click();
		return new ConfirmationPage(driver);

	}

}
