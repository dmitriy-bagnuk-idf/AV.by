package seleniumTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.avSelenium.HomePage;
import pageObjects.avSelenium.LoginPage;
import pageObjects.avSelenium.UserAdvertsPage;
import pageObjects.baseObjects.BaseTest;
import steps.CheckOpenPageSteps;

public class UploadFileTest extends BaseTest {
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

    @Test(description = "Upload new photo to the user ad",
            enabled = true)
    public void uploadTest() {
        get(HomePage.class)
                .goToUserAd();
        get(CheckOpenPageSteps.class)
                .verifyUserAdvertsAreOpen();
        get(UserAdvertsPage.class)
                .verifyPhotoNotExistInTheAd()
                .clickEditAdBtn()
                .uploadPhotoToAd()
                .clickSaveAdChangesBtn();
        get(HomePage.class)
                .goToUserAd();
        get(CheckOpenPageSteps.class)
                .verifyUserAdvertsAreOpen();
        get(UserAdvertsPage.class)
                .clickAdBtn()
                .verifyUploadedPhotoInTheAd();
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .goToUserAd();
        get(UserAdvertsPage.class)
                .deletePhotoFromTheAd();
        get(HomePage.class)
                .logout();
    }
}
