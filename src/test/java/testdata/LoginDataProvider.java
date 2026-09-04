package testdata;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {

        return new Object[][] {

            {
                "Invalid Username + Invalid Password",
                "InvalidUser",
                "WrongPassword"
            },

            {
                "Valid Username + Invalid Password",
                "Admin",
                "WrongPassword"
            },

            {
                "Invalid Username + Valid Password",
                "InvalidUser",
                "admin123"
            },

            {
                "Completely Invalid Credentials",
                "TestUser",
                "Test123"
            }

        };
    }
}