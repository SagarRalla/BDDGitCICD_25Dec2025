package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Listener implements ITestListener {

    private static final String RERUN_FILE = "rerun.txt";

    @Override
    public void onTestFailure(ITestResult result) {
        String failedTest = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RERUN_FILE, true))) {
            writer.write(failedTest);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext context) {
        // Clear the rerun file at the start of the test suite
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RERUN_FILE))) {
            writer.write(""); // Clear the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}
    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onFinish(ITestContext context) {}
}
