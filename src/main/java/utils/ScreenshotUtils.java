package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
        // Prevent object creation
    }

    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        String screenshotDirectory =
                System.getProperty("user.dir")
                        + File.separator
                        + "screenshots";

        File directory =
                new File(screenshotDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath =
                screenshotDirectory
                        + File.separator
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        try {

            TakesScreenshot screenshot =
                    (TakesScreenshot) driver;

            File sourceFile =
                    screenshot.getScreenshotAs(
                            OutputType.FILE
                    );

            File destinationFile =
                    new File(filePath);

            FileUtils.copyFile(
                    sourceFile,
                    destinationFile
            );

            return destinationFile.getAbsolutePath();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to capture screenshot",
                    e
            );
        }
    }
}