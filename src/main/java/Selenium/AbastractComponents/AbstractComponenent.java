package Selenium.AbastractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesignRevision.PageObejcts.OrderDetails;
import SeleniumFrameworkDesignRevision.PageObejcts.PaymentPage;

public class AbstractComponenent {
	WebDriver driver;

	public AbstractComponenent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement goToCheckout;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement OrderHistroy;

	public void waitelementtoAppear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	public void waitforWebelementtoAppear(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}

	public void waitElementtodisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public PaymentPage goToCartPage() {
		goToCheckout.click();
		return new PaymentPage(driver);

	}
	public OrderDetails goToOrderDetails()
	{
		OrderHistroy.click();
		OrderDetails orderdetails = new OrderDetails(driver);
		return orderdetails;
	}
	

}
