package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.ReportManager;
import utils.DriverManager;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HomePage clickSearchBox() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
            ReportManager.info("Clicked on search box on home page");
        } catch (Throwable t) {
            ReportManager.exception("Unable to click search box on home page", t);
            throw t;
        }
        return this;
    }

    public HomePage searchForItem(String item) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
            searchBox.sendKeys(item);
            ReportManager.info("searched item on home page");
        } catch (Throwable t) {
            ReportManager.exception("Unable to search item on home page", t);
            throw t;
        }
        return this;
    }

    public HomePage clickSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
            ReportManager.info("Clicked on search button on home page");
        } catch (Throwable t) {
            ReportManager.exception("Unable to click search button on home page", t);
            throw t;
        }
        return this;
    }
}
