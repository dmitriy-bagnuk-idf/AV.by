package pageObjects.avSelenium;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;

@Log4j
public class LoginPage extends BasePage {
    private final By entryByEmail = By.xpath("//button[contains(text(),'логин')]");
    private final By email = By.id("authLogin");
    private final By password = By.id("loginPassword");
    private final By loginBtn = By.xpath("//button[contains(@class, 'action')]");

    public LoginPage emailEntrySelection() {
        log.debug("Email entry selection");
        click(entryByEmail);
        return this;
    }

    public LoginPage enterEmail() {
        log.debug("Enter email");
        enter(this.email, properties.getProperty("email"));
        return this;
    }

    public LoginPage enterPassword() {
        log.debug("Enter password");
        enter(this.password, properties.getProperty("password"));
        return this;
    }

    public LoginPage clickLoginBtn() {
        log.debug("Click login btn");
        click(loginBtn);
        return this;
    }

    public LoginPage loginWithEmail() {
        log.debug("Login with email");
        emailEntrySelection();
        enterEmail();
        enterPassword();
        clickLoginBtn();
        return this;
    }
}
