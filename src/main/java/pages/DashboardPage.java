package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class DashboardPage extends BasePage {

    // ==============================
    // LOCATORS
    // ==============================

    private final By dashboardHeader =
            By.xpath("//h6[text()='Dashboard']");

    // ==============================
    // CONSTRUCTOR
    // ==============================

    public DashboardPage(WebDriver driver) {

        super(driver);
    }

    // ==============================
    // PAGE VALIDATION
    // ==============================

    public boolean isDashboardDisplayed() {

        return isDisplayed(dashboardHeader);
    }
}