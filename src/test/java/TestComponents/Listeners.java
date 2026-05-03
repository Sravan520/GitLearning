package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportsTestNg;





public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent= ExtentReportsTestNg.extentReports();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();
	
	 @Override
	    public void onTestStart(ITestResult result) {
	       
		 ExtentTest  test=extent.createTest(result.getMethod().getMethodName());
	        extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	extentTest.get().log(Status.PASS, "Execution is Successfull");
	        
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	String path = null;
	        System.out.println("Test Failed: " + result.getName());
	        System.out.println("Reason: " + result.getThrowable());
	        extentTest.get().fail(result.getThrowable());
	        try {
				driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        try {
	        	path=getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	        extentTest.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName() );
	        
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Skipped: " + result.getName());
	    }
	    
	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Suite Finished: " + context.getName());
	      extent.flush();
	      System.out.println("Extent report flushed successfully.");
	    }
	        
	    

	     

}
