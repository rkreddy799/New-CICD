package SeleniumFrameworkDesignRevision.SeleniumFrameworkDesignRevision.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesignRevision.PageObejcts.ConfirmationPage;
import SeleniumFrameworkDesignRevision.PageObejcts.LandingPage;
import SeleniumFrameworkDesignRevision.PageObejcts.OrderDetails;
import SeleniumFrameworkDesignRevision.PageObejcts.PaymentPage;
import SeleniumFrameworkDesignRevision.PageObejcts.ProductCatalogue;
import SeleniumFrameworkDesignRevision.PageObejcts.SuccessPage;
import SeleniumFrameworkDesignRevision.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone2 extends BaseTest {
	//String productName = "ZARA COAT 3";
	//String countryName = "India";

	@Test(dataProvider ="getData",groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		

		// Product Page
		ProductCatalogue productcatalogue = landingpage.loginaplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addItemToCart(input.get("productName"));

		// cart page
		PaymentPage paymentpage = productcatalogue.goToCartPage();
		Boolean match = paymentpage.productNameCheck(input.get("productName"));
		Assert.assertTrue(match);

		ConfirmationPage confirmationpage = paymentpage.gotoCheckOut();
		SuccessPage successpage = confirmationpage.countryName(input.get("countryName"));
		String thankyouMsg = successpage.getSuccessmsg();
		Assert.assertEquals(thankyouMsg, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dataProvider="getData",dependsOnMethods="submitOrder")
	public void OrderHistroyPage(HashMap<String,String> input)
	{
		
		ProductCatalogue productcatalogue = landingpage.loginaplication(input.get("email"), input.get("password"));
		OrderDetails orderdetails=productcatalogue.goToOrderDetails();
		Boolean match=orderdetails.getItemName(input.get("productName"));
		Assert.assertTrue(match);	
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "rkreddy0333@gmail.com");
		map.put("password", "Ravirama12@");
		map.put("productName", "ADIDAS ORIGINAL");
		map.put("countryName", "India");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "ravitest1@gmail.com");
		map1.put("password", "Ravirama12@");
		map1.put("productName", "IPHONE 13 PRO");
		map1.put("countryName", "India");
		*/
		List<HashMap<String ,String>> data=getJsonDatatoMap(System.getProperty("user.dir") + "/src/test/java/SeleniumFrameworkDesignRevision/data/PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
	/*
	 2nd way of data sending
	 @DataProvider
	 public Object [] [] getData()
	 {
	 return new Object[] []= {{"rkreddy0333@gmail.com","Ravirama12@","ADIADA ORIGINAL","India"},{"ravitest1@gmail.com","Ravirama12@","IPHONE 13 PRO","India"}}
	 }
	 
	 

	 */

}
