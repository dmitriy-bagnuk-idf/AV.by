package seleniumTests;

import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.avSelenium.*;
import pageObjects.baseObjects.BaseTest;
import restAPI.avApi;
import steps.CheckOpenPageSteps;

@Log4j
public class ApiTest extends BaseTest {
    @BeforeTest
    public void openPage() {
        get(HomePage.class)
                .open();
    }

    @BeforeMethod
    public void preconditions() {
        get(HomePage.class)
                .clickLoginBtn();
        get(LoginPage.class)
                .loginWithEmail();
    }

    @Test(description = "Comparison phone number from user setting and API",
            priority = 1,
            enabled = true)
    public void loginApi() {
        get(HomePage.class)
                .goToUserSettings();
        get(CheckOpenPageSteps.class)
                .verifyUserSettingsAreOpen();
        log.debug("Comparison phone number from user setting and API");
        Assert.assertTrue(get(UserSettingsPage.class)
                .getPhoneNumber()
                .contains(get(avApi.class).getPhoneNumberFromApi()));
    }

    @Test(description = "Comparison brand from car ad and API",
            priority = 2,
            enabled = true)
    public void brandCarApi() {
        get(HomePage.class)
                .goToCarPage();
        log.debug("Comparison brand from car ad and API");
        Assert.assertTrue(get(CarPage.class)
                .getCarDescription()
                .contains(get(avApi.class).getBrandCarFromApi()));
    }

    @Test(description = "Comparison total views from car ad and API",
            priority = 3,
            enabled = true)
    public void viewsCarApi() {
        get(HomePage.class)
                .goToCarPage();
        log.debug("Comparison total views from car ad and API");
        Assert.assertTrue(get(CarPage.class)
                .getCarViews()
                .contains(get(avApi.class).getTotalViewsCarFromApi()));
    }

    @Test(description = "Comparison count top cars from home page and API",
            priority = 4,
            enabled = true)
    public void countTopCarsApi() {
        get(HomePage.class)
                .clickTopCarsBtn();
        log.debug("Comparison count top cars from home page and API");
        Assert.assertEquals(get(HomePage.class).countTopCars(), get(avApi.class).getCountPopCarsApi());
    }

    @AfterMethod
    public void postconditions() {
        get(HomePage.class)
                .logout();
    }
}
