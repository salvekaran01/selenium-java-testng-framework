package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import testdata.LoginDataProvider;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
	@Test(
		    description = "Verify valid user can login successfully",
		    groups = {"smoke", "regression"}
		)
		public void verifyValidLogin() {
  
		    System.out.println(
		            "Starting valid login test"
		    );

		    System.out.println(
		            "Current URL: " + driver.getCurrentUrl()
		    );

		    LoginPage loginPage =
		            new LoginPage(driver);

        DashboardPage dashboardPage =
                loginPage.login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                );

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard was not displayed after successful login"
        );

        System.out.println(
                "Valid login test passed successfully"
        );
    }


    @Test(
    	    dataProvider = "invalidLoginData",
    	    dataProviderClass = LoginDataProvider.class,
    	    description = "Verify error message for different invalid login scenarios",
    	    groups = {"regression"}
    	)
    public void verifyInvalidLogin(
            String scenario,
            String username,
            String password) {

        System.out.println(
                "Starting scenario: " + scenario
        );

        System.out.println(
                "Username: " + username
        );

        System.out.println(
                "Password: ******** "
        );

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(username);

        loginPage.enterPassword(password);

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Login error message was not displayed for scenario: "
                        + scenario
        );

        System.out.println(
                "Scenario passed: " + scenario
        );
    }
}