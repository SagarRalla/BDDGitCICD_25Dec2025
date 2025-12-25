package tests;

import org.openqa.selenium.WebDriver;
import pages.SearchedItemPage;
import utils.DriverManager;

public class SearchedItemTest {

    SearchedItemPage searchedItemPage ;

    public void init() {
        WebDriver driver = DriverManager.driver;
        searchedItemPage = new SearchedItemPage(driver);
    }

    public void verifySearchedItem(String item) {
        searchedItemPage.verifySearchedItem(item);
    }
}
