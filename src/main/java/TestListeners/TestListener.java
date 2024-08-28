package TestListeners;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import DriverConfig.BaseClass;


public class TestListener extends TestListenerAdapter {
	ExtentReports extent;
	ExtentTest test;
	ExtentSparkReporter spark;

	@Override
	public void onTestStart(ITestResult result) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.mm.ss").format(new Date()); 
		String repName = "Test-Report_" + timeStamp + ".html";
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("ExtentReports/"+repName);  
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		
		
		spark.config().setDocumentTitle(" JOB Applications Framework ");
		spark.config().setReportName("Hirist_Naukri_Insthyre");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		test=extent.createTest(tr.getName()).addScreenCaptureFromBase64String(BaseClass.captureScreen());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		test=extent.createTest(tr.getName()).addScreenCaptureFromBase64String(BaseClass.captureScreen());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
	}

	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		test=extent.createTest(tr.getName()).addScreenCaptureFromBase64String(BaseClass.captureScreen());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}



}
