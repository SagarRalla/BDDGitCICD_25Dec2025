package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverManager;

public class TestHomePage extends BaseTest {

    HomePage homePage;

    public void init() {
        WebDriver driver = DriverManager.driver;
        homePage = new HomePage(driver);
    }

    public void openHomePage() {
        homePage.clickSearchBox();
    }

    public void searchForItem(String item) {
        homePage.searchForItem(item);
    }

    public void searchButton() {
        homePage.clickSearchButton();
    }
}
