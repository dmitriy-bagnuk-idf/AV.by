package pageObjects.avSelenium;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

@Log4j
public class UserSettingsPage extends BasePage {
    private final By changePswBtn = By.xpath("//a[contains(@href, 'password')]");
    private final By oldPsw = By.id("old-password");
    private final By newPsw = By.id("new-password");
    private final By acceptPswBtn = By.xpath("//button[contains(@class, 'action')]");
    private final By errorMsg = By.xpath("//div[@class='error-message']");
    private final By popup = By.xpath("//div[contains(@class, 'opened')]");
    private final By changePhoneNumberBtn = By.xpath("//a[contains(@href, 'phone')]");
    private final By acceptPhoneNumberBtn = By.xpath("//button[contains(@class, 'primary')] ");
    private final By newPhoneNumber = By.id("phoneNumber");
    private final By hideSwitchOldPswBtn = By.xpath("//label[@for='old-password']/../button");
    private final By hideSwitchNewPswBtn = By.xpath("//label[@for='new-password']/../button");
    private final By phoneNumber = By.xpath("//div/b[contains(text(), '375')]");

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

    public UserSettingsPage clickAcceptPswBtn() {
        log.debug("Click accept btn");
        click(acceptPswBtn);
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

    public UserSettingsPage verifyAcceptPswBtnIsNotClickable() {
        log.debug("Verify accept btn is not clickable");
        Assert.assertFalse(findElement(acceptPswBtn).isEnabled());
        return this;
    }

    public UserSettingsPage changePsw(String password) {
        log.debug("Changing password");
        clickChangePswBtn();
        enterOldPsw();
        enterNewPsw(password);
        clickAcceptPswBtn();
        return this;
    }

    public UserSettingsPage enterDataInNewPswField(String password) {
        log.debug("Change password with different data from data-provider");
        if (password.length() == 0) {
            clickChangePswBtn();
            enterOldPsw();
            enterNewPsw(password);
            verifyAcceptPswBtnIsNotClickable();
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

    public UserSettingsPage clickChangePhoneNumberBtn() {
        log.debug("Click change phone number btn");
        click(changePhoneNumberBtn);
        return this;
    }

    public UserSettingsPage enterNewPhoneNumber(String number) {
        log.debug("Enter new phone number");
        enter(this.newPhoneNumber, number);
        return this;
    }

    public UserSettingsPage clickAcceptPhoneNumberBtn() {
        log.debug("Click accept phone number btn");
        click(acceptPhoneNumberBtn);
        return this;
    }

    public UserSettingsPage verifyInvalidDataErrorMsg() {
        log.debug("Verify phone number data");
        Assert.assertTrue(findElement(errorMsg).getText().contains("Неверно указан номер"));
        return this;
    }

    public UserSettingsPage clickHideSwitchOldPswBtn() {
        log.debug("Click hide switch old psw btn");
        click(hideSwitchOldPswBtn);
        return this;
    }

    public UserSettingsPage clickHideSwitchNewPswBtn() {
        log.debug("Click hide switch new psw btn");
        click(hideSwitchNewPswBtn);
        return this;
    }

    public UserSettingsPage verifyOldPswIsNotHidden() {
        log.debug("Verify old psw is not hidden");
        Assert.assertTrue(findElement(oldPsw).getAttribute("type").contains("text"));
        return this;
    }

    public UserSettingsPage verifyOldPswIsHidden() {
        log.debug("Verify old psw is hidden");
        Assert.assertTrue(findElement(oldPsw).getAttribute("type").contains("password"));
        return this;
    }

    public UserSettingsPage verifyNewPswIsNotHidden() {
        log.debug("Verify new psw is not hidden");
        Assert.assertTrue(findElement(newPsw).getAttribute("type").contains("text"));
        return this;
    }

    public UserSettingsPage verifyNewPswIsHidden() {
        log.debug("Verify new psw is hidden");
        Assert.assertTrue(findElement(newPsw).getAttribute("type").contains("password"));
        return this;
    }

    public String getPhoneNumber() {
        log.debug("Get phone number from user settings page");
        return findElement(phoneNumber).getText();
    }
}
