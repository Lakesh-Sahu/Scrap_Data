package scraper.listener;

import scraper.utils.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class Listener extends Base implements ITestListener {
    private static final Logger log = LogManager.getLogger(Listener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("{} : Started", result.getName());
        log.trace("{} : Description : {}", result.getName(), result.getMethod().getDescription());
        startBrowser();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Quiting the browser window or closing all the windows
        wp.quit();
        log.info("{} Passed and Ended", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("{} : Failed and Ended", result.getName());
        log.trace("Throwable for {} : {}", result.getName(), Arrays.toString(result.getThrowable().getStackTrace()));
        wp.quit();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("{} : Skipped", result.getName());
        log.trace("Throwable for {} : {}", result.getName(), Arrays.toString(result.getThrowable().getStackTrace()));
        wp.quit();
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test Cases Started");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test Cases Ended");
    }
}
