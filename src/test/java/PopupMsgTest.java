import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.av.BookmarksPage;
import pageObjects.av.HomePage;
import pageObjects.av.LoginPage;
import pageObjects.av.UserSettingsPage;
import pageObjects.baseObjects.BaseTest;

public class PopupMsgTest extends BaseTest {

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

    @Test(testName = "Popup message when adding car to bookmarks",
            priority = 1,
            enabled = true)
    public void popupMsgHomeTest() {
        get(HomePage.class)
                .addInterestingTodayCarToBookmarks()
                .verifyPopupMsg();
    }

    @Test(testName = "Popup message when removed car from bookmarks",
            priority = 2,
            dependsOnMethods = "popupMsgHomeTest",
            enabled = true)
    public void popupMsgBookmarksTest() {
        get(HomePage.class)
                .goToBookmarks();
        get(BookmarksPage.class)
                .verifyBookmarksAreOpen()
                .verifyBookmarksAreNotEmpty()
                .deleteAllCarsFromBookmarks()
                .verifyPopupMsg();
    }

    @Test(testName = "Popup message when when changing password",
            priority = 3,
            enabled = true)
    public void popupMsgUserSettingsTest() {
        get(HomePage.class)
                .goToUserSettings();
        get(UserSettingsPage.class)
                .verifyUserSettingsAreOpen()
                .changePsw(properties.getProperty("password"))
                .verifyPopupMsg();
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
