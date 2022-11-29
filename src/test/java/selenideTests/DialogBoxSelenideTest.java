package selenideTests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.avSelenide.HomePageSelenide;
import pageObjects.avSelenide.LoginPageSelenide;
import pageObjects.baseObjects.SelenideBaseTest;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class DialogBoxSelenideTest extends SelenideBaseTest {
    @BeforeTest
    public void openPage() {
        open(baseUrl);
    }

    @Test(description = "Login dialog box display",
            enabled = true)
    public void loginBoxTest() {
        get(HomePageSelenide.class)
                .preconditionBeforeLogin()
                .clickLoginBtn();
        get(LoginPageSelenide.class)
                .verifyLoginBox()
                .verifyLoginBoxText()
                .clickCloseLoginBoxBtn();
    }

    @AfterTest
    public void postconditions() {
        closeWebDriver();
    }
}
