package pageObjects.avSelenide;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.WebDriverRunner.url;
import static propertyHelper.PropertyReader.getProperties;

@Log4j
public class HomePageSelenide {
    private final SelenideElement closeMsgBtn = $(By.xpath("//span[text()='Закрыть']"));
    private final SelenideElement cookieBtn = $(By.xpath("//div[contains(@class,'cookie')]/button"));
    private final SelenideElement loginBtn = $(By.xpath("//a[@href='/login']"));
    private final SelenideElement userMenu = $(By.xpath("//li[contains(@class, 'user')]"));
    private final SelenideElement userName = $(By.xpath("//h4[@class='nav__dropdown-name']"));

    public HomePageSelenide preconditionBeforeLogin() {
        if (closeMsgBtn.is(visible)) {
            log.debug("Welcome message is displayed");
            closeMsgBtn.should(enabled).click();
            verifyPageIsOpen();
        } else {
            log.debug("Welcome message is not displayed");
            verifyPageIsOpen();
        }
        cookieBtn.should(enabled).click();
        return this;
    }

    private void verifyPageIsOpen() {
        log.debug("Verify page is open - \"" + getProperties().getProperty("url") + "\"");
        Assert.assertEquals(url(), getProperties().getProperty("url"));
    }

    public HomePageSelenide clickLoginBtn() {
        log.debug("Click login btn");
        loginBtn.should(enabled).click();
        return this;
    }

    @SneakyThrows
    public HomePageSelenide verifySuccessfulLogin() {
        log.debug("Verify successful login");
        userMenu.should(enabled);
        actions()
                .moveToElement(userMenu)
                .build()
                .perform();
        Thread.sleep(1500);
        Assert.assertTrue(userName.should(enabled).text().contains("Тест"));
        return this;
    }
}
