import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.av.BookmarksPage;
import pageObjects.av.HomePage;
import pageObjects.av.LoginPage;
import pageObjects.baseObjects.BaseTest;

public class AddingAndRemovingTest extends BaseTest {

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


    @Test(testName = "Adding one car to bookmarks from top cars on homepage",
            priority = 1,
            enabled = true)
    public void addCarTest() {
        get(HomePage.class)
                .addInterestingTodayCarToBookmarks();
        String nameInterestingTodayCar = get(HomePage.class).nameInterestingTodayCar();
        get(HomePage.class)
                .goToBookmarks();
        get(BookmarksPage.class)
                .verifyBookmarksAreOpen()
                .verifyBookmarksAreNotEmpty()
                .verifyCounterBookmarks();
        Assert.assertEquals(get(BookmarksPage.class).nameCarInBookmarks(), nameInterestingTodayCar);
    }

    @Test(testName = "Deleting one car from bookmarks",
            dependsOnMethods = "addCarTest",
            priority = 2,
            enabled = true)
    public void deleteCar() {
        get(HomePage.class)
                .goToBookmarks();
        get(BookmarksPage.class)
                .verifyBookmarksAreOpen()
                .verifyBookmarksAreNotEmpty()
                .deleteAllCarsFromBookmarks()
                .verifyBookmarksAreEmpty();
    }

    @Test(testName = "Adding all cars to bookmarks from top listings",
            dependsOnMethods = "deleteCar",
            priority = 3,
            enabled = true)
    public void addCarsTest() {
        get(HomePage.class)
                .clickTopCarsBtn();
        int countCarsToBookmark = get(HomePage.class).countCarsToBookmark();
        get(HomePage.class)
                .addAllTopCarsToBookmarks()
                .goToBookmarks();
        get(BookmarksPage.class)
                .verifyCarsInBookmarksIsDisplayed()
                .verifyCounterBookmarks();
        Assert.assertEquals(get(BookmarksPage.class).countCarsInBookmarks(), countCarsToBookmark);
    }

    @Test(testName = "Deleting all cars from bookmarks",
            dependsOnMethods = "addCarsTest",
            priority = 4,
            enabled = true)
    public void deleteCarsTest() {
        get(HomePage.class)
                .goToBookmarks();
        get(BookmarksPage.class)
                .verifyBookmarksAreOpen()
                .verifyBookmarksAreNotEmpty()
                .deleteAllCarsFromBookmarks()
                .verifyBookmarksAreEmpty();
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
