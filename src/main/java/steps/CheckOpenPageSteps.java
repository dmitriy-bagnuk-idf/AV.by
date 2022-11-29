package steps;

import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import pageObjects.avSelenium.HomePage;
import pageObjects.baseObjects.BasePage;

import java.util.ArrayList;

@Log4j
public class CheckOpenPageSteps extends BasePage {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    HomePage homePage = new HomePage();

    public CheckOpenPageSteps verifyUserSettingsAreOpen() {
        if (getPageUrl().contains("settings")) {
            log.debug("User settings page is opened");
            Assert.assertTrue(getPageUrl().contains("settings"));
        } else {
            log.debug("Extra tab open");
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
            log.debug("Extra tab closed");
            homePage.goToUserSettings();
            log.debug("User settings page is opened");
            Assert.assertTrue(getPageUrl().contains("settings"));
        }
        return this;
    }

    public CheckOpenPageSteps verifyBookmarksAreOpen() {
        if (getPageUrl().contains("bookmarks")) {
            log.debug("Bookmarks page is opened");
            Assert.assertTrue(getPageUrl().contains("bookmarks"));
        } else {
            log.debug("Extra tab open");
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
            log.debug("Extra tab closed");
            homePage.goToBookmarks();
            log.debug("Bookmarks page is opened");
            Assert.assertTrue(getPageUrl().contains("bookmarks"));
        }
        return this;
    }

    public CheckOpenPageSteps verifyUserAdvertsAreOpen() {
        if (getPageUrl().contains("offers")) {
            log.debug("User adverts page is opened");
            Assert.assertTrue(getPageUrl().contains("offers"));
        } else {
            log.debug("Extra tab open");
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
            log.debug("Extra tab closed");
            homePage.goToUserAd();
            log.debug("User adverts page is opened");
            Assert.assertTrue(getPageUrl().contains("offers"));
        }
        return this;
    }
}
