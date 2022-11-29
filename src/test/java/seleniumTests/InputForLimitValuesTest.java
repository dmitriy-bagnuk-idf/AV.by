package seleniumTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.avSelenium.HomePage;
import pageObjects.avSelenium.LoginPage;
import pageObjects.avSelenium.UserSettingsPage;
import dataProvider.dataForAVby;
import pageObjects.baseObjects.BaseTest;
import steps.CheckOpenPageSteps;

public class InputForLimitValuesTest extends BaseTest {
    @BeforeTest
    public void openPage() {
        get(HomePage.class)
                .open();
    }


    @BeforeMethod
    public void preconditions() {
        get(HomePage.class)
                .clickLoginBtn();
        get(LoginPage.class)
                .loginWithEmail();
    }

    @Test(description = "Checking the new password field for boundary values",
            dataProviderClass = dataForAVby.class,
            dataProvider = "password data",
            enabled = true)
    public void limitValuesTest(String password) {
        get(HomePage.class)
                .goToUserSettings();
        get(CheckOpenPageSteps.class)
                .verifyUserSettingsAreOpen();
        get(UserSettingsPage.class)
                .enterDataInNewPswField(password);
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
