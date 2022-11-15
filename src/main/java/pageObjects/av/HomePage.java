package pageObjects.av;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;
@Log4j
public class HomePage extends BasePage {

    private final By closeMsgBtn = By.xpath("//span[text()='Закрыть']");

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
        return this;
    }

    private HomePage verifyPageIsOpen() {
        Assert.assertEquals(getPageUrl(), properties.getProperty("url"));
        return this;
    }
}
