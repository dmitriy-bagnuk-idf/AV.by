import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.av.HomePage;
import pageObjects.av.LoginPage;
import pageObjects.av.UserSettingsPage;
import pageObjects.av.dataProvider.dataForAVby;
import pageObjects.baseObjects.BaseTest;

public class inputForLimitValuesTest extends BaseTest {
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

    @Test(testName = "Checking the new password field for boundary values",
            dataProviderClass = dataForAVby.class,
            dataProvider = "password data")
    public void limitValuesTest(String password) {
        get(HomePage.class)
                .goToUserSettings();
        get(UserSettingsPage.class)
                .verifyUserSettingsAreOpen()
                .enterDataInNewPsw(password);
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
