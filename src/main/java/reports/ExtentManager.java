package reports;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.ITestListener;

public class ExtentManager implements ITestListener{

    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static ExtentTest getExtentTest(){
        return extentTestThreadLocal.get();
    }

    public static void setExtentTest(ExtentTest test){
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ReportManager.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.get().skip("Test skipped due to dependency failure: " + result.getThrowable());
    }
}
