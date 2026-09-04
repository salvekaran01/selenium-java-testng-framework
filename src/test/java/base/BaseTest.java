package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        String browser =
                ConfigReader.get("browser");

        System.out.println(
                "Starting browser: " + browser
        );

        initializeBrowser(browser);

        driver.manage()
                .window()
                .maximize();

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(
                                ConfigReader.getInt(
                                        "implicitWait"
                                )
                        )
                );

        driver.manage()
                .timeouts()
                .pageLoadTimeout(
                        Duration.ofSeconds(60)
                );

        String url =
                ConfigReader.get("url");

        System.out.println(
                "Opening application: " + url
        );

        driver.get(url);
    }


    private void initializeBrowser(
            String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions options =
                        new ChromeOptions();

                options.addArguments(
                        "--disable-notifications"
                );

                options.addArguments(
                        "--disable-popup-blocking"
                );

                options.addArguments(
                        "--start-maximized"
                );

                driver =
                        new ChromeDriver(options);

                break;


            case "firefox":

                driver =
                        new FirefoxDriver();

                break;


            case "edge":

                driver =
                        new EdgeDriver();

                break;


            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: "
                                + browser
                );
        }
    }


    public WebDriver getDriver() {

        return driver;
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {

            System.out.println(
                    "Closing browser"
            );

            driver.quit();

            driver = null;
        }
    }
}