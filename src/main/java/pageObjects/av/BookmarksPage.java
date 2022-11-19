package pageObjects.av;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

@Log4j
public class BookmarksPage extends BasePage {
    private final By bookmarksIsEmpty = By.xpath("//div[@class='bookmarks-empty']");
    private final By favoritesList = By.xpath("//div[@class='listing__items']");
    private final By carsInBookmarks = By.xpath("//button[@aria-busy]");
    private final By counterBookmarks = By.xpath("//a[@aria-current='page']/small");
    private final By logo = By.xpath("//div[@class='header__logo']");
    private final By getNameCarInBookmarks = By.xpath("(//span[@class='link-text'])");
    private By getCarsInBookmarks(int count) {
        return By.xpath("(//button[@aria-busy])[" + count + "]");
    }

    public BookmarksPage verifyBookmarksAreOpen() {
        log.debug("Bookmarks page is opened");
        Assert.assertTrue(getPageUrl().contains("profile/bookmarks"));
        return this;
    }

    public BookmarksPage verifyBookmarksAreEmpty() {
        log.debug("Bookmarks are empty");
        Assert.assertTrue(findElement(bookmarksIsEmpty).isDisplayed());
        return this;
    }

    public BookmarksPage verifyBookmarksAreNotEmpty() {
        log.debug("Bookmarks are not empty");
        Assert.assertTrue(findElement(favoritesList).isEnabled());
        return this;
    }

    public Integer countCarsInBookmarks() {
        log.debug("Counting the number of cars in bookmarks");
        return findElements(carsInBookmarks).size();
    }

    public BookmarksPage verifyCarsInBookmarksIsDisplayed() {
        log.debug("Cars in bookmarks is displayed");
        Assert.assertTrue(findElement(carsInBookmarks).isDisplayed());
        return this;
    }

    public BookmarksPage verifyCounterBookmarks() {
        log.debug("Verify counter bookmarks");
        Assert.assertEquals(Integer.parseInt(findElement(counterBookmarks).getText()), countCarsInBookmarks());
        return this;
    }

    public BookmarksPage clickLogo() {
        log.debug("Click logo - redirect to home page");
        click(logo);
        return this;
    }

    public BookmarksPage deleteAllCarsFromBookmarks() {
        int countCarsInBookmarks = countCarsInBookmarks();
        while(countCarsInBookmarks >0){
            log.debug("Delete car № " + countCarsInBookmarks);
            click(getCarsInBookmarks(countCarsInBookmarks));
            countCarsInBookmarks--;
        }
        return this;
    }

    public String nameCarInBookmarks() {
        log.debug("Get name car in bookmarks");
        return findElement(getNameCarInBookmarks).getText();
    }
}
