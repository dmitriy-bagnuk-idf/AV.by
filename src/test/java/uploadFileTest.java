import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.av.UserAdvertsPage;
import pageObjects.av.HomePage;
import pageObjects.av.LoginPage;
import pageObjects.baseObjects.BaseTest;

public class uploadFileTest extends BaseTest {
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

    @Test(testName = "Upload new photo to the user ad",
            enabled = true)
    public void uploadTest() {
        get(HomePage.class)
                .goToUserAd();
        get(UserAdvertsPage.class)
                .verifyUserSettingsAreOpen()
                .verifyPhotoNotExistInTheAd()
                .clickEditAdBtn()
                .uploadPhotoToAd()
                .clickSaveAdChangesBtn()
                .clickLogo();
        get(HomePage.class)
                .goToUserAd();
        get(UserAdvertsPage.class)
                .verifyUserSettingsAreOpen()
                .verifyUploadedPhoto();
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
