package pageObjects.av;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

@Log4j
public class HomePage extends BasePage {
    private final Actions actions = new Actions(driver);
    private final By closeMsgBtn = By.xpath("//span[text()='Закрыть']");
    private final By cookieBtn = By.xpath("(//div[contains(@class, 'cookie')])[3]");
    private final By loginBtn = By.xpath("//li[contains(@class, 'login')]");
    private final By bookmarksBtn = By.xpath("//li[contains(@class, 'bookmarks')]");
    private final By topCarsBtn = By.partialLinkText("все объявления");
    private final By carToBookmarkBtn = By.xpath("//button[@class='bookmark']");
    private final By logo = By.xpath("//div[@class='header__logo']");
    private final By userMenu = By.xpath("//li[contains(@class, 'user')]");
    private final By logoutBtn = By.xpath("//a[contains(@class, 'logout')]");
    private final By userSettingsBtn = By.xpath("//a[contains(@href, 'settings')]");
    private final By popup = By.xpath("//div[contains(@class, 'opened')]");
    private final By userMsgBtn = By.xpath("//li[contains(@class, 'messages')]");
    private final By userMsgBox = By.xpath("//div[@class='drawer__title']/h2");
    private final By closeUserMsgBtn = By.xpath("//button[@title='Закрыть']");

    private By getTopCar(int count) {
        return By.xpath("(//button[@class='bookmark'])[" + count + "]");
    }

    private By getInterestingTodayCarToBookmarks(int count) {
        return By.xpath("(//button[contains(@title, 'закладки')])[" + count + "]");
    }

    private By getNameInterestingTodayCar(int count) {
        return By.xpath("(//span[@class='link-text'])[" + count + "]");
    }

    public HomePage open() {
        load();
        if (findElement(closeMsgBtn).isDisplayed()) {
            log.debug("Welcome message is displayed");
            click(closeMsgBtn);
            verifyPageIsOpen();
        } else {
            log.debug("Welcome message is not displayed");
            verifyPageIsOpen();
        }
        click(cookieBtn);
        return this;
    }

    private HomePage verifyPageIsOpen() {
        log.debug("Verify page is open - \"" + properties.getProperty("url") + "\"");
        Assert.assertEquals(getPageUrl(), properties.getProperty("url"));
        return this;
    }

    public HomePage clickLoginBtn() {
        log.debug("Click login btn");
        click(loginBtn);
        return this;
    }

    public HomePage clickBookmarksBtn() {
        log.debug("Click bookmarks btn");
        click(bookmarksBtn);
        return this;
    }

    public HomePage clickTopCarsBtn() {
        log.debug("Click top cars btn");
        click(topCarsBtn);
        return this;
    }

    public HomePage addAllTopCarsToBookmarks() {
        int countTopCars = findElements(carToBookmarkBtn).size();
        while (countTopCars > 0) {
            log.debug("Add to bookmarks car № " + countTopCars);
            click(getTopCar(countTopCars));
            countTopCars--;
        }
        return this;
    }

    public HomePage addInterestingTodayCarToBookmarks() {
        log.debug("Add to bookmarks car № " + properties.getProperty("numberTopCar"));
        click(getInterestingTodayCarToBookmarks(Integer.parseInt(properties.getProperty("numberTopCar"))));
        return this;
    }

    public Integer countCarsToBookmark() {
        log.debug("Counting the number top cars");
        return findElements(carToBookmarkBtn).size();
    }

    public String nameInterestingTodayCar() {
        log.debug("Get name interesting today car № " + properties.getProperty("numberTopCar"));
        return findElement(getNameInterestingTodayCar(Integer.parseInt(properties.getProperty("numberTopCar"))))
                .getText();
    }

    public HomePage clickLogo() {
        log.debug("Click logo - redirect to home page");
        clickWithoutVerifyClickable(logo);
        return this;
    }

    public HomePage logout() {
        log.debug("LogOut");
        actions
                .moveToElement(findElement(userMenu))
                .click(findElement(logoutBtn))
                .build()
                .perform();
        clickLogo();
        verifyElementClickable(loginBtn);
        return this;
    }

    public HomePage goToUserSettings() {
        log.debug("Go to user settings");
        actions
                .moveToElement(findElement(userMenu))
                .click(findElement(userSettingsBtn))
                .build()
                .perform();
        return this;
    }

    public HomePage verifyPopupMsg() {
        log.debug("Verify popup message");
        Assert.assertTrue(findElement(popup).isDisplayed());
        return this;
    }

    public HomePage goToCarPage() {
        log.debug("Go to interesting today car № " + properties.getProperty("numberTopCar") + " page");
        click(getNameInterestingTodayCar(Integer.parseInt(properties.getProperty("numberTopCar"))));
        return this;
    }

    public HomePage clickUserMsgBtn() {
        log.debug("Click user message btn");
        click(userMsgBtn);
        return this;
    }

    public HomePage verifyUserMsgBox() {
        log.debug("Verify user message box is displayed");
        Assert.assertTrue(findElement(userMsgBox).isEnabled());
        return this;
    }

    public HomePage verifyUserMsgBoxText() {
        log.debug("Verify user message box text");
        Assert.assertTrue(findElement(userMsgBox).getText().contains("Диалоги"));
        return this;
    }

    public HomePage clickCloseUserMsgBtn() {
        log.debug("Click close user message box btn");
        click(closeUserMsgBtn);
        return this;
    }
}
