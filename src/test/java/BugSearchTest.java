import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.av.HomePage;
import pageObjects.av.LoginPage;
import pageObjects.av.UserSettingsPage;
import pageObjects.baseObjects.BaseTest;

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
        get(UserSettingsPage.class)
                .verifyUserSettingsAreOpen()
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
        get(UserSettingsPage.class)
                .verifyUserSettingsAreOpen()
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
