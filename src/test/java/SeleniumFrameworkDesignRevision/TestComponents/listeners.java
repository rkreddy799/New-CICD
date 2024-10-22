package SeleniumFrameworkDesignRevision.TestComponents;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumFrameworkDesignRevision.resources.ExtentReportsNg;

public class listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReportsNg.getReportObject();
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();//Thread safe
	
	
	
	@Override
    public void onTestStart(ITestResult result) {
        // Code to execute when a test starts
		 test=extent.createTest(result.getMethod().getMethodName());
		 extenttest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
    	//System.out.println("I successfully executed listener pass code");
    	extenttest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute when a test fails
    	extenttest.get().fail(result.getThrowable());
    	
    	try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	//Screenshot
    	String filepath= null;
    	try {
			filepath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	extenttest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
    	
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Code to execute when a test is skipped
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
