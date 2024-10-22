package SeleniumFrameworkDesignRevision.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNg {
	
	public static ExtentReports getReportObject()
	{
		//Extent reports,ExtentSparkReporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "\\reports\\index.html");
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("Order Creation results");
		
		ExtentReports extent  = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ravi");
		return extent;
				
		
		
	}
	

}
