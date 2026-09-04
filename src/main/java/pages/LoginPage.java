package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {

    // ==============================
    // LOCATORS
    // ==============================

    private final By usernameField =
            By.name("username");

    private final By passwordField =
            By.name("password");

    private final By loginButton =
            By.cssSelector("button[type='submit']");

    private final By loginErrorMessage =
            By.cssSelector(".oxd-alert-content-text");


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public LoginPage(WebDriver driver) {

        super(driver);
    }


    // ==============================
    // PAGE ACTIONS
    // ==============================

    public void enterUsername(String username) {

        enterText(
                usernameField,
                username
        );
    }


    public void enterPassword(String password) {

        enterText(
                passwordField,
                password
        );
    }


    public DashboardPage clickLogin() {

        click(loginButton);

        return new DashboardPage(driver);
    }


    // ==============================
    // COMPLETE LOGIN ACTION
    // ==============================

    public DashboardPage login(
            String username,
            String password) {

        enterUsername(username);

        enterPassword(password);

        return clickLogin();
    }


    // ==============================
    // NEGATIVE LOGIN SUPPORT
    // ==============================

    public boolean isLoginErrorDisplayed() {

        return isDisplayed(loginErrorMessage);
    }
}