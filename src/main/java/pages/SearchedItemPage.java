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

public class SearchedItemPage {

    WebDriver driver;
    WebDriverWait wait;
    DriverManager driverManager = new DriverManager();

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    public WebElement SearchedItem;

    public SearchedItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public SearchedItemPage verifySearchedItem(String userEntereditem) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOf(SearchedItem));
            String title = driverManager.getText(el);
            String item = driverManager.getText(SearchedItem);
            if (title != null && title.toLowerCase().contains(userEntereditem.toLowerCase())) {
                ReportManager.pass("Search results contain: " + userEntereditem);
            } else {
                ReportManager.fail("Search results do not contain: " + userEntereditem + " | First title: " + title);
                throw new AssertionError("Search results mismatch for query: " + userEntereditem);
            }
        }
        catch(Throwable t) {
            ReportManager.exception("Unable to verify results on search results page", t);
            throw t;
        }
        return this;
    }
}
