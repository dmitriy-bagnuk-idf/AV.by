package pageObjects.baseObjects;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Listeners;
import testNgUtils.SelenideListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.*;

@Listeners(SelenideListener.class)
public class SelenideBaseTest {

    protected <T> T get(Class<T> page) {
        return driver().hasWebDriverStarted() ? page(page) : open(Configuration.baseUrl, page);
    }
}
