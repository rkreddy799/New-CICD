package SeleniumFrameworkDesignRevision.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesignRevision.PageObejcts.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	
	public void initializeDriver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFrameworkDesignRevision\\resources\\GlobalData.properties");
		
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,990));
		} 
		else if(browserName.contains("firefox"))
		{
			FirefoxOptions options = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();;
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver = new FirefoxDriver(options);
			driver.manage().window().setSize(new Dimension(1440,990));
		}
		else if(browserName.contains("edge"))
		{
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
				
			}
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,990));
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
	}
	
	public  List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException
	{ // Read JSON file content as a string
        String jsonContent = FileUtils.readFileToString(
                new File(filepath),
                "UTF-8"
        );

        // Create ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Convert JSON string to List<HashMap<String, String>>
        List<HashMap<String, String>> data = mapper.readValue(
                jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {}
        );

        return data;
		
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	  {
		 TakesScreenshot ts = (TakesScreenshot)driver;
		 File src =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports" +testcaseName +".png");
		
		FileUtils.copyFile(src,file );
		 
		return (System.getProperty("user.dir")+"\\reports" +testcaseName +".png");
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
