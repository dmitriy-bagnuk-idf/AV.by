package seleniumTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.avSelenium.HomePage;
import pageObjects.avSelenium.LoginPage;
import pageObjects.avSelenium.UserSettingsPage;
import pageObjects.baseObjects.BaseTest;
import steps.CheckOpenPageSteps;

public class BugSearchTest extends BaseTest {
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

    @Test(description = "Old psw hide switch in the user settings (bug search)",
            priority = 1,
            enabled = true)
    public void oldPswHideSwitch() {
        get(HomePage.class)
                .goToUserSettings();
        get(CheckOpenPageSteps.class)
                .verifyUserSettingsAreOpen();
        get(UserSettingsPage.class)
                .clickChangePswBtn()
                .enterOldPsw()
                .enterNewPsw(properties.getProperty("password"))
                .clickHideSwitchOldPswBtn()
                .verifyOldPswIsNotHidden()
                .verifyNewPswIsHidden();

    }

    @Test(description = "New psw hide switch in the user settings (bug search)",
            priority = 2,
            enabled = true)
    public void newPswHideSwitch() {
        get(HomePage.class)
                .goToUserSettings();
        get(CheckOpenPageSteps.class)
                .verifyUserSettingsAreOpen();
        get(UserSettingsPage.class)
                .clickChangePswBtn()
                .enterOldPsw()
                .enterNewPsw(properties.getProperty("password"))
                .clickHideSwitchNewPswBtn()
                .verifyNewPswIsNotHidden()
                .verifyOldPswIsHidden();
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
