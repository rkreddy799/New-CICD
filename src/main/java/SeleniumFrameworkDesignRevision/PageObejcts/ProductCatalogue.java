package SeleniumFrameworkDesignRevision.PageObejcts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbastractComponents.AbstractComponenent;

public class ProductCatalogue extends AbstractComponenent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");

	By toastMessage = By.cssSelector("#toast-container");
	@FindBy(css = ".ng-animating")
	WebElement scroll;

	public List<WebElement> getProductList() {

		waitelementtoAppear(productsBy);
		return products;
	}

	public WebElement getProductBy(String productName) {
		WebElement prod = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addItemToCart(String productName) {
		WebElement prod = getProductBy(productName);
		prod.findElement(addToCart).click();
		waitelementtoAppear(toastMessage);
		waitElementtodisappear(scroll);
	}

}
