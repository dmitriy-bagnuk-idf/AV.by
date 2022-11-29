package pageObjects.avSelenium;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

@Log4j
public class UserAdvertsPage extends BasePage {
    private final By editAdBtn = By.xpath("//a[contains(@href, 'edit')]");
    private final By selectPhotoToAdBtn = By.id("p-48-photo");
    private final By saveAdChangesBtn = By.xpath("//button[@type='submit']");
    private final By uploadedPhoto = By.xpath("//div[@class='uploader__thumb-wrap']");
    private final By photoInTheAd = By.xpath("//div[contains(@class, 'gallery')]/img");
    private final By adBtn = By.xpath("//div[@class='mycard__header']");
    private final By previewPhotoInTheAd = By.xpath("//div[@class='mycard__photo-image']");
    private final By logo = By.xpath("//div[@class='header__logo']");

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
        Assert.assertTrue(findElement(previewPhotoInTheAd).isEnabled());
        return this;
    }

    public UserAdvertsPage verifyPhotoNotExistInTheAd() {
        log.debug("Verify photo doesn't exist in the add");
        Assert.assertTrue(elementNotExist(previewPhotoInTheAd));
        return this;
    }

    public UserAdvertsPage clickLogo() {
        log.debug("Click logo - redirect to home page");
        clickWithoutVerifyClickable(logo);
        return this;
    }
}
