import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.av.HomePage;
import pageObjects.av.LoginPage;
import pageObjects.av.UserSettingsPage;
import pageObjects.av.dataProvider.dataForAVby;
import pageObjects.baseObjects.BaseTest;

public class invalidDataTest extends BaseTest {
    @BeforeTest
    public void openPage() {
        get(HomePage.class)
                .open()
                .clickLoginBtn();
        get(LoginPage.class)
                .loginWithEmail();
        get(HomePage.class)
                .goToUserSettings();
        get(UserSettingsPage.class)
                .verifyUserSettingsAreOpen()
                .clickChangePhoneNumberBtn();
    }

    @Test(testName = "Change user phone number with invalid and exceeding allowed data",
            dataProviderClass = dataForAVby.class,
            dataProvider = "phone number data",
            priority = 1,
            enabled = true)
    public void inputInvalidData(String phoneNumber) {
        get(UserSettingsPage.class)
                .enterNewPhoneNumber(phoneNumber)
                .clickAcceptPhoneNumberBtn()
                .verifyInvalidDataErrorMsg();
    }

    @AfterTest
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
