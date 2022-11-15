package pageObjects.baseObjects;

import driver.UIElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.stream.Collectors;

import static driver.DriverManager.getDriver;
import static propertyHelper.PropertyReader.getProperties;
@Log4j
public abstract class BasePage {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected Actions actions;
    protected Properties properties;

    protected BasePage() {
        driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        actions = new Actions(driver);
        properties = getProperties();
    }

    protected FluentWait<WebDriver> fluentWait(long timeout, long pollingEvery) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void load() {
        log.debug("Open page :: " + properties.getProperty("url"));
        driver.get(properties.getProperty("url"));
    }

    protected void load(String url) {
        log.debug("Open page  :: " + url);
        driver.get(url);
    }

    protected String getPageUrl() {
        log.debug("Get page url");
        return driver.getCurrentUrl();
    }

    protected void enter(WebElement webElement, String enterData) {
        log.debug("I enter :: " + enterData + ", by web element :: " + webElement);
        webElement.clear();
        webElement.sendKeys(enterData);
    }

    protected void enter(By locator, CharSequence... enterData) {
        log.debug("I enter :: " + enterData + ", by locator :: " + locator);
        findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        findElement(locator).sendKeys(enterData);
    }

    protected void click(WebElement webElement) {
        log.debug("I'm click by :: " + webElement);
        new UIElement(driver, wait, webElement).click();
    }

    protected void click(By locator) {
        log.debug("I'm click by :: " + locator);
        verifyElementClickable(locator);
        findElement(locator).click();
    }

    protected void clickWithoutVerifyClickable(By locator) {
        log.debug("Click without verify clickable by :: " + locator);
        findElement(locator).click();
    }

    protected String getText(WebElement webElement) {
        log.debug("I'm get text by  :: " + webElement);
        return webElement.getText();
    }

    protected String getText(By locator) {
        log.debug("I'm get text by  :: " + locator);
        return findElement(locator).getText();
    }

    protected List<String> getTexts(By locator) {
        log.debug("I'm get texts by  :: " + locator);
        return findElements(locator).stream().map(webElement -> webElement.getText()).collect(Collectors.toList());
    }

    protected String getElementAttribute(By by, String attribute) {
        log.debug("Get element => " + by + ", attribute  :: " + attribute);
        return findElement(by).getAttribute(attribute);
    }

    protected List<String> getElementsAttribute(By by, String attribute) {
        log.debug("Get element => " + by + ", attribute  :: " + attribute);
        return findElements(by).stream().map(webElement -> webElement.getAttribute(attribute)).collect(Collectors.toList());
    }

    public Boolean elementNotExist(By by) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        for (int counter = 1; counter < 60; counter++) {
            log.debug("Wait element not exist count = " + counter);
            if (driver.findElements(by).size() == 0) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            }
            waitUntil(1);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return false;
    }

    protected void waitUntil(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitVisibilityElement(By locator) {
        log.debug("Wait visibility of element =>" + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void verifyElementTextToBe(By locator, String text) {
        log.debug("Verify element text to be =>" + locator);
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    protected void verifyElementClickable(By locator) {
        log.debug("Verify element clickable =>" + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void select(By locator, Integer index){
        log.debug("Select by locator =>" + locator + " with index " + index);
        Select select = new Select(findElement(locator));
        select.selectByIndex(index);
    }

    protected void select(By locator, String value){
        log.debug("Select by value =>" + locator + " with value " + value);
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }

    protected void select(WebElement webElement, Integer index){
        log.debug("Select by value =>" + webElement + " with value " + index);
        Select select = new Select(webElement);
        select.selectByIndex(index);
    }

    protected void select(WebElement webElement, String value){
        log.debug("Select by value =>" + webElement + " with value " + value);
        Select select = new Select(webElement);
        select.selectByValue(value);
    }
}
