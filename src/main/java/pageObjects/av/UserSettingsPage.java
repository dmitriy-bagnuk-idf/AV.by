package pageObjects.av;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.ArrayList;

@Log4j
public class UserSettingsPage extends BasePage {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    HomePage homePage = new HomePage();
    private final By changePswBtn = By.xpath("//a[contains(@href, 'password')]");
    private final By oldPsw = By.id("old-password");
    private final By newPsw = By.id("new-password");
    private final By acceptBtn = By.xpath("//button[contains(@class, 'action')]");
    private final By errorMsg = By.xpath("//div[@class='error-message']");
    private final By popup = By.xpath("//div[contains(@class, 'opened')]");


    public UserSettingsPage verifyUserSettingsAreOpen() {
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

    public UserSettingsPage clickChangePswBtn() {
        log.debug("Click change password btn");
        click(changePswBtn);
        return this;
    }

    public UserSettingsPage enterOldPsw() {
        log.debug("Enter old password");
        enter(this.oldPsw, properties.getProperty("password"));
        return this;
    }

    public UserSettingsPage enterNewPsw(String password) {
        log.debug("Enter new password");
        enter(this.newPsw, password);
        return this;
    }

    public UserSettingsPage clickAcceptBtn() {
        log.debug("Click accept btn");
        click(acceptBtn);
        return this;
    }

    public UserSettingsPage verifyLengthErrorMsg() {
        log.debug("Verify psw length");
        Assert.assertTrue(findElement(errorMsg).getText().contains("Минимум 8 символов"));
        return this;
    }

    public UserSettingsPage verifySyntaxErrorMsg() {
        log.debug("Verify psw syntax");
        Assert.assertTrue(findElement(errorMsg).getText().contains("цифры и латинские буквы"));
        return this;
    }

    public UserSettingsPage verifyPopupMsg() {
        log.debug("Verify popup message");
        Assert.assertTrue(findElement(popup).isDisplayed());
        return this;
    }

    public UserSettingsPage verifyAcceptBtnIsNotClickable() {
        log.debug("Verify accept btn is not clickable");
        Assert.assertFalse(findElement(acceptBtn).isEnabled());
        return this;
    }

    public UserSettingsPage changePsw(String password) {
        log.debug("Changing password");
        clickChangePswBtn();
        enterOldPsw();
        enterNewPsw(password);
        clickAcceptBtn();
        return this;
    }

    public UserSettingsPage enterDataInNewPsw(String password) {
        log.debug("Change password with different data from data-provider");
        if (password.length() == 0) {
            clickChangePswBtn();
            enterOldPsw();
            enterNewPsw(password);
            verifyAcceptBtnIsNotClickable();
        } else if (password.length() == 1) {
            changePsw(password);
            verifySyntaxErrorMsg();
        } else if (password.length() < 8) {
            changePsw(password);
            verifyLengthErrorMsg();
        } else {
            changePsw(password);
            verifyPopupMsg();
        }
        return this;
    }
}
