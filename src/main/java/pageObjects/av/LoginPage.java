package pageObjects.av;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;

@Log4j
public class LoginPage extends BasePage {
    private final By entryByNumberPhone = By.xpath("(//button[contains(text(),'телефон')])[1]");
    private final By entryByEmail = By.xpath("//button[contains(text(),'логин')]");
    private final By email = By.id("authLogin");
    private final By password = By.id("loginPassword");
    private final By loginBtn = By.xpath("//button[contains(@class, 'action')]");

    public LoginPage emailEntrySelection() {
        click(entryByEmail);
        return this;
    }

    public LoginPage enterEmail() {
        enter(this.email, properties.getProperty("email"));
        return this;
    }

    public LoginPage enterPassword() {
        enter(this.password, properties.getProperty("password"));
        return this;
    }

    public LoginPage clickLoginBtn() {
        click(loginBtn);
        return this;
    }

    public LoginPage loginWithEmail() {
        emailEntrySelection();
        enterEmail();
        enterPassword();
        clickLoginBtn();
        return this;
    }
}
