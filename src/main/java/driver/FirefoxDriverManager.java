package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

import static propertyHelper.PropertyReader.getProperties;

public class FirefoxDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriver driver;
        Properties properties = getProperties();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(properties.getProperty("browser.configs"));
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions);
        webDriver.set(driver);
    }
}
