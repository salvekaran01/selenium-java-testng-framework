package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class TestListener
        implements ITestListener {

    private static final Logger logger =
            LogManager.getLogger(
                    TestListener.class
            );

    private static final ExtentReports extent =
            ExtentReportManager.getExtentReports();

    private static final ThreadLocal<ExtentTest>
            extentTest = new ThreadLocal<>();


    @Override
    public void onStart(
            ITestContext context) {

        logger.info(
                "TEST SUITE STARTED: {}",
                context.getName()
        );
    }


    @Override
    public void onTestStart(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        ExtentTest test =
                extent.createTest(
                        testName
                );

        extentTest.set(test);

        logger.info(
                "TEST STARTED: {}",
                testName
        );

        test.log(
                Status.INFO,
                "Test Started: " + testName
        );
    }


    @Override
    public void onTestSuccess(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        logger.info(
                "TEST PASSED: {}",
                testName
        );

        ExtentTest test =
                extentTest.get();

        if (test != null) {

            test.log(
                    Status.PASS,
                    "Test Passed: " + testName
            );
        }
    }


    @Override
    public void onTestFailure(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        logger.error(
                "TEST FAILED: {}",
                testName
        );
        
        if (result.getThrowable() != null) {

            result.getThrowable().printStackTrace();
        }

        ExtentTest test =
                extentTest.get();

        if (test != null) {

            test.log(
                    Status.FAIL,
                    "Test Failed: " + testName
            );

            if (result.getThrowable() != null) {

                test.log(
                        Status.FAIL,
                        result.getThrowable()
                );
            }
        }


        Object testInstance =
                result.getInstance();

        if (testInstance instanceof BaseTest) {

            WebDriver driver =
                    ((BaseTest) testInstance)
                            .getDriver();

            if (driver != null) {

                try {

                    String screenshotPath =
                            ScreenshotUtils
                                    .captureScreenshot(
                                            driver,
                                            testName
                                    );

                    logger.error(
                            "Screenshot captured: {}",
                            screenshotPath
                    );

                    if (test != null) {

                        test.addScreenCaptureFromPath(
                                screenshotPath
                        );
                    }

                } catch (Exception e) {

                    logger.error(
                            "Screenshot capture failed",
                            e
                    );

                    if (test != null) {

                        test.log(
                                Status.WARNING,
                                "Screenshot capture failed: "
                                        + e.getMessage()
                        );
                    }
                }
            }
        }
    }


    @Override
    public void onTestSkipped(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        logger.warn(
                "TEST SKIPPED: {}",
                testName
        );

        ExtentTest test =
                extentTest.get();

        if (test != null) {

            test.log(
                    Status.SKIP,
                    "Test Skipped: " + testName
            );
        }
    }


    @Override
    public void onFinish(
            ITestContext context) {

        logger.info(
                "TEST SUITE FINISHED: {}",
                context.getName()
        );

        extent.flush();

        extentTest.remove();
    }
}