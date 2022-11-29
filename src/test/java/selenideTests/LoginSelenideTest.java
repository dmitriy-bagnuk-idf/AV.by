package selenideTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.avSelenide.HomePageSelenide;
import pageObjects.avSelenide.LoginPageSelenide;
import pageObjects.baseObjects.SelenideBaseTest;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class LoginSelenideTest extends SelenideBaseTest {
    @BeforeMethod
    public void preconditions() {
        open(baseUrl);
    }

    @Test(description = "Login with email",
            priority = 1,
            enabled = true)
    public void loginWithEmail() {
        get(HomePageSelenide.class)
                .preconditionBeforeLogin()
                .clickLoginBtn();
        get(LoginPageSelenide.class)
                .loginWithEmail();
        get(HomePageSelenide.class)
                .verifySuccessfulLogin();
    }

    @Test(description = "Login with phone number",
            priority = 2,
            enabled = true)
    public void loginWithPhoneNumber() {
        get(HomePageSelenide.class)
                .preconditionBeforeLogin()
                .clickLoginBtn();
        get(LoginPageSelenide.class)
                .loginWithPhoneNumber();
        get(HomePageSelenide.class)
                .verifySuccessfulLogin();
    }

    @AfterMethod
    public void postconditions() {
        closeWebDriver();
    }
}
