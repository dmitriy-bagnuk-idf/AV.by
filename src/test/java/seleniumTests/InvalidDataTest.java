package seleniumTests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.avSelenium.HomePage;
import pageObjects.avSelenium.LoginPage;
import pageObjects.avSelenium.UserSettingsPage;
import dataProvider.dataForAVby;
import pageObjects.baseObjects.BaseTest;
import steps.CheckOpenPageSteps;

public class InvalidDataTest extends BaseTest {
    @BeforeTest
    public void openPage() {
        get(HomePage.class)
                .open()
                .clickLoginBtn();
        get(LoginPage.class)
                .loginWithEmail();
        get(HomePage.class)
                .goToUserSettings();
        get(CheckOpenPageSteps.class)
                .verifyUserSettingsAreOpen();
        get(UserSettingsPage.class)
                .clickChangePhoneNumberBtn();
    }

    @Test(description = "Change user phone number with invalid and exceeding allowed data",
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
