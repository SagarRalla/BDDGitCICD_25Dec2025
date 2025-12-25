package pages;

import org.openqa.selenium.WebDriver;
import reports.ReportManager;
import utils.DriverManager;
import utils.JavaUtils;

import java.util.Properties;

public class BasePage {

    protected DriverManager driverManager;
    protected Properties prop;
    protected static ReportManager report;
    protected WebDriver driver;

    public BasePage() {
        driverManager = new DriverManager();
        report = new ReportManager();
        try {
            prop = JavaUtils.readPropertiesFile(System.getProperty("user.dir")
                    + "/src/test/resources/Objects.properties");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
