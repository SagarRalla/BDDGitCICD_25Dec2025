package stepdefs;

import io.cucumber.java.*;
import reports.ReportManager;
import utils.DriverManager;

public class Hooks
{
    @BeforeAll
    public static void beforeAll() throws Exception {
        // Initialize the report once for the whole run
        ReportManager.initializeReport("Regression_Report_amazon");
    }

    @AfterAll
    public static void afterAll() {
        ReportManager.saveReport();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        // Create an Extent test node per scenario
        ReportManager.createTest(scenario.getName());
        new DriverManager().launchBrowser("chrome");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ReportManager.fail("Scenario failed: " + scenario.getName());
        } else {
            ReportManager.pass("Scenario passed: " + scenario.getName());
        }
        new DriverManager().quitDriver();
    }


   // @Before
   // public void setUp() {
   //     new DriverManager().launchBrowser("chrome");
   // }

    //@After
    //public void tearDown() {
     //   new DriverManager().quitDriver();
    //}
}
