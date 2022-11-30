package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Properties;

import static propertyHelper.PropertyReader.getProperties;

public class EdgeDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriver driver;
        Properties properties = getProperties();
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments(properties.getProperty("browser.configs"));
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(edgeOptions);
        webDriver.set(driver);
    }
}
