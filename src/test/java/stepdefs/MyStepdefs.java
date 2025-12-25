package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.SearchedItemTest;
import tests.TestHomePage;
import utils.DriverManager;

public class MyStepdefs {

    TestHomePage testHomePage = new TestHomePage();
    SearchedItemTest searchedItemTest = new SearchedItemTest();

    @Given("user is on Amazon homepage")
    public void userIsOnAmazonHomepage() {
        testHomePage.init();
        testHomePage.openHomePage();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String item) {
        testHomePage.searchForItem(item);
    }

    @Then("search results for {string} should be displayed")
    public void searchResultsForShouldBeDisplayed(String item) {
        testHomePage.searchButton();
        searchedItemTest.init();
        searchedItemTest.verifySearchedItem(item);
    }
}
