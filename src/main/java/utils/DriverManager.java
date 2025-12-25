package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Properties;

public class DriverManager {

    public static WebDriver driver;
    
    public void launchBrowser(String browserType){
        Properties prop = PropertyClass.getProperty(FilePathUtils.getResourceDirectoryPath()+ "url.properties");
        if(browserType.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions coptions = new ChromeOptions();
            if (Boolean.parseBoolean(prop.getProperty("headless", "true"))){
                coptions.addArguments("--headless");
            }
            coptions.addArguments("--no-sandbox");
            coptions.addArguments("--disable-gpu");
            coptions.addArguments("--no-sandbox");
            coptions.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(coptions);
        } else if (browserType.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        String url = prop.getProperty("url");
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void quitDriver() {
        driver.quit();
    }

    public void click(WebElement element) {
        try {
            element.click();
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }
    public String getText(WebElement ele) {
        String actText="";
        try {
            actText = ele.getText();
        }catch(ElementNotInteractableException | StaleElementReferenceException ex) {
            actText = ele.getText();
        }
        return actText;
    }
    public void hover(WebElement ele) {
        String text = "";
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(ele).perform();
        } catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }

    public void clearAndType(WebElement element, String valueToType) {
        try {
            element.clear();
            Thread.sleep(2000);
            element.sendKeys(valueToType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
