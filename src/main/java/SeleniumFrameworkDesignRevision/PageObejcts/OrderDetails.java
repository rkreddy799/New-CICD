package SeleniumFrameworkDesignRevision.PageObejcts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbastractComponents.AbstractComponenent;

public class OrderDetails extends AbstractComponenent{
	WebDriver driver;

	public OrderDetails(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> orderNames;
	
	public Boolean getItemName(String productName)
	{
		Boolean match=orderNames.stream().anyMatch(s->s.getText().equals(productName));
		return match;
	}
	


}
