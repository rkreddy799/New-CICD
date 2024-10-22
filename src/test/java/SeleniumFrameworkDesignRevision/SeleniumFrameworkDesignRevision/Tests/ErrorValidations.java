package SeleniumFrameworkDesignRevision.SeleniumFrameworkDesignRevision.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumFrameworkDesignRevision.PageObejcts.ConfirmationPage;
import SeleniumFrameworkDesignRevision.PageObejcts.LandingPage;
import SeleniumFrameworkDesignRevision.PageObejcts.PaymentPage;
import SeleniumFrameworkDesignRevision.PageObejcts.ProductCatalogue;
import SeleniumFrameworkDesignRevision.PageObejcts.SuccessPage;
import SeleniumFrameworkDesignRevision.TestComponents.BaseTest;
import SeleniumFrameworkDesignRevision.TestComponents.retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=retry.class)
	public void loginErrorValidation() throws IOException {
		// TODO Auto-generated method stub

		// Product Page
		landingpage.loginaplication("rkreddy0333@gmail.com", "Ravirama12");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test(dataProvider ="getData")
	public void productErrorValidation(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		//String productName = "ZARA COAT 3";
		// Product Page
		ProductCatalogue productcatalogue = landingpage.loginaplication(input.get("email"),input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addItemToCart(input.get("productName"));

		// cart page
		PaymentPage paymentpage = productcatalogue.goToCartPage();
		Boolean match = paymentpage.productNameCheck(input.get("productName"));
		Assert.assertTrue(match);

	}

}
