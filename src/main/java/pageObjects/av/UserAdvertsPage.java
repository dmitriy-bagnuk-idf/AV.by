package pageObjects.av;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.ArrayList;

@Log4j
public class UserAdvertsPage extends BasePage {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    HomePage homePage = new HomePage();
    private final By editAdBtn = By.xpath("//a[contains(@href, 'edit')]");
    private final By selectPhotoToAdBtn = By.id("p-48-photo");
    private final By saveAdChangesBtn = By.xpath("//button[@type='submit']");
    private final By uploadedPhoto = By.xpath("//div[@class='uploader__thumb-wrap']");
    private final By photoInTheAd = By.xpath("//div[@class='mycard__photo-image']");
    private final By logo = By.xpath("//div[@class='header__logo']");

    public UserAdvertsPage verifyUserSettingsAreOpen() {
        if (getPageUrl().contains("offers")) {
            log.debug("User adverts page is opened");
            Assert.assertTrue(getPageUrl().contains("offers"));
        } else {
            log.debug("Extra tab open");
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
            log.debug("Extra tab closed");
            homePage.goToUserSettings();
            log.debug("User adverts page is opened");
            Assert.assertTrue(getPageUrl().contains("offers"));
        }
        return this;
    }

    public UserAdvertsPage clickEditAdBtn() {
        log.debug("Click edit ad btn");
        click(editAdBtn);
        return this;
    }

    public UserAdvertsPage uploadPhotoToAd() {
        log.debug("Select and upload photo to ad");
        findElement(selectPhotoToAdBtn).sendKeys(System.getProperty("user.dir") + "/files/tire.jpg");
        waitUntil(1);
        waitVisibilityElement(uploadedPhoto);
        return this;
    }

    public UserAdvertsPage clickSaveAdChangesBtn() {
        log.debug("Click save ad changes btn");
        click(saveAdChangesBtn);
        return this;
    }

    public UserAdvertsPage verifyUploadedPhoto() {
        log.debug("Verify uploaded photo in the add");
        Assert.assertTrue(findElement(photoInTheAd).isEnabled());
        return this;
    }

    public UserAdvertsPage verifyPhotoNotExistInTheAd() {
        log.debug("Verify photo doesn't exist in the add");
        Assert.assertTrue(elementNotExist(photoInTheAd));
        return this;
    }

    public UserAdvertsPage clickLogo() {
        log.debug("Click logo - redirect to home page");
        clickWithoutVerifyClickable(logo);
        return this;
    }
}
