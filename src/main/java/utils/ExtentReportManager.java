package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {

    private static ExtentReports extentReports;

    private ExtentReportManager() {
        // Prevent object creation
    }

    public static synchronized ExtentReports getExtentReports() {

        if (extentReports == null) {

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String reportDirectory =
                    System.getProperty("user.dir")
                            + File.separator
                            + "reports";

            File directory =
                    new File(reportDirectory);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String reportPath =
                    reportDirectory
                            + File.separator
                            + "AutomationReport_"
                            + timestamp
                            + ".html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config()
                    .setDocumentTitle(
                            "OrangeHRM Automation Report"
                    );

            sparkReporter.config()
                    .setReportName(
                            "OrangeHRM Test Automation"
                    );

            extentReports =
                    new ExtentReports();

            extentReports.attachReporter(
                    sparkReporter
            );

            extentReports.setSystemInfo(
                    "Application",
                    "OrangeHRM"
            );

            extentReports.setSystemInfo(
                    "Environment",
                    "Demo"
            );

            extentReports.setSystemInfo(
                    "Automation Tool",
                    "Selenium WebDriver"
            );

            extentReports.setSystemInfo(
                    "Test Runner",
                    "TestNG"
            );

            extentReports.setSystemInfo(
                    "Build Tool",
                    "Maven"
            );

            extentReports.setSystemInfo(
                    "Browser",
                    "Chrome"
            );
        }

        return extentReports;
    }


    public static void flushReport() {

        if (extentReports != null) {

            extentReports.flush();
        }
    }
}