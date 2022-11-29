package pageObjects.avSelenide;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static propertyHelper.PropertyReader.getProperties;

@Log4j
public class LoginPageSelenide {
    private final SelenideElement entryByNumberPhone = $(By.xpath("//button[contains(text(),'логин')]/../button[text()='по телефону']"));
    private final SelenideElement entryByEmail = $(By.xpath("//button[contains(text(),'логин')]"));
    private final SelenideElement email = $(By.id("authLogin"));
    private final SelenideElement passwordEmailEntry = $(By.id("loginPassword"));
    private final SelenideElement passwordPhoneNumberEntry = $(By.id("passwordPhone"));
    private final SelenideElement phoneNumber = $(By.id("authPhone"));
    private final SelenideElement loginBtn = $(By.xpath("//button[contains(@class, 'action')]"));
    private final SelenideElement loginBox = $(By.xpath("//div[text()='Вход']"));
    private final SelenideElement closeLoginBoxBtn = $(By.xpath("//button[@class='drawer__close']"));

    public LoginPageSelenide verifyLoginBox() {
        log.debug("Verify login box is displayed");
        Assert.assertTrue(loginBox.isEnabled());
        return this;
    }

    public LoginPageSelenide verifyLoginBoxText() {
        log.debug("Verify login box text");
        Assert.assertTrue(loginBox.text().contains("Вход"));
        return this;
    }

    public LoginPageSelenide clickCloseLoginBoxBtn() {
        log.debug("Click close login box btn");
        closeLoginBoxBtn.should(enabled).click();
        return this;
    }

    public LoginPageSelenide emailEntrySelection() {
        log.debug("Email entry selection");
        entryByEmail.should(enabled).click();
        return this;
    }

    public LoginPageSelenide phoneNumberEntrySelection() {
        log.debug("Phone number entry selection");
        entryByNumberPhone.should(enabled).click();
        return this;
    }

    public LoginPageSelenide loginWithEmail() {
        log.debug("Login with email");
        emailEntrySelection();
        log.debug("Enter email");
        this.email.setValue(getProperties().getProperty("email"));
        log.debug("Enter password");
        this.passwordEmailEntry.setValue(getProperties().getProperty("password"));
        log.debug("Click login btn");
        loginBtn.should(enabled).click();
        return this;
    }

    public LoginPageSelenide loginWithPhoneNumber() {
        log.debug("Login with phone number");
        phoneNumberEntrySelection();
        log.debug("Enter phone number");
        this.phoneNumber.setValue(getProperties().getProperty("phonenumber"));
        log.debug("Enter password");
        this.passwordPhoneNumberEntry.setValue(getProperties().getProperty("password"));
        log.debug("Click login btn");
        loginBtn.should(enabled).click();
        return this;
    }
}
