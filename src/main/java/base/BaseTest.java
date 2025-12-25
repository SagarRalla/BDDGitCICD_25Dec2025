package base;

import org.testng.annotations.*;
import reports.ReportManager;
import utils.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

public class BaseTest
{
    protected DriverManager interactions = new DriverManager();

    @BeforeSuite
    public void reportSetup() throws IOException {
        String projectDir = System.getProperty("user.dir");
        String propertiesPath = projectDir + "/src/test/resources/EnvironmentDetails.properties";
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            props.load(fis);
        }
        String environmentType = props.getProperty("environmentType", "default");
        String suiteName = "Regression_Report_" + environmentType;
        ReportManager.initializeReport(suiteName);
    }

    @Parameters("testCaseName")
    @BeforeClass
    public void setup(String testCaseName) throws MalformedURLException {
        ReportManager.createTest(testCaseName);
        interactions.launchBrowser("chrome");			// added this line
    }

    @AfterClass
    public void quitBrowser() {
        interactions.quitDriver();
    }

    @AfterSuite
    public void endReport() {
        ReportManager.saveReport();
    }
}
