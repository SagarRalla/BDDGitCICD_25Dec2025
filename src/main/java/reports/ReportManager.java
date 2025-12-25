package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import utils.DriverManager;
import utils.FilePathUtils;
import utils.JavaUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;
    public static String reportFolder;

    public static void initializeReport(String suiteName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFolder = FilePathUtils.getWorkingDirectoryPath() + "/target/Test execution Report/" + timestamp;
        new File(reportFolder).mkdirs();
        String reportPath = reportFolder + "/" + suiteName + ".html";;
        FileUtils.deleteDirectory(new File(reportFolder + "/screenshots")); // Optional: clean old screenshots
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setDocumentTitle("Automation Test Report");
        extentSparkReporter.config().setReportName("Automation Report");
    }

    public static void createTest(String testcaseName){
        ExtentManager.setExtentTest(extentReports.createTest(testcaseName));
    }

    public static void pass(String log){
        System.out.println(log);
        ExtentManager.getExtentTest().pass(log);
    }

    public static void info(String log){
        System.out.println(log);
        ExtentManager.getExtentTest().info(log);
    }

    public static void skip(String log){
        System.out.println(log);
        ExtentManager.getExtentTest().skip(log);
    }

    public static void checkTestFailure(){
        String testStatus = ExtentManager.getExtentTest().getStatus().toString();
        if (testStatus.equalsIgnoreCase("fail"))
        { Assert.fail();}

    }

    public static void fail(String log) {
        try {
            System.out.println(log);
            ExtentTest test = ExtentManager.getExtentTest();
            String testName = test.getModel().getName();
            String screenshotName = testName + JavaUtils.generateRandomNumber() + ".png";
            String screenshotDir = reportFolder + "/screenshots";
            new File(screenshotDir).mkdirs();
            String fullPath = screenshotDir + "/" + screenshotName;
            File screenshot = ((TakesScreenshot) DriverManager.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(fullPath));
            test.fail(log, MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/" + screenshotName).build());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void exception(String log, Throwable exceptionError){
        try {
            System.out.println(log);
            System.out.println(exceptionError);
            ExtentTest test = ExtentManager.getExtentTest();
            TakesScreenshot ts = (TakesScreenshot) DriverManager.driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            String name = FilePathUtils.getWorkingDirectoryPath() +"/reports/"+ JavaUtils.generateRandomNumber() + ".png";
            FileUtils.copyFile(file, new File(name));
            test.fail(log);
            test.fail(exceptionError, MediaEntityBuilder.createScreenCaptureFromPath(name).build());
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    public static void saveReport(){
        extentReports.flush();
    }
}
