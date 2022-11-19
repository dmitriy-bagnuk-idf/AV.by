package pageObjects.av;

import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;

public class CarsPage extends BasePage {
    private final By carToBookmarksBtn = By.xpath("//div/p/button[contains(@title, 'в закладки')]");

    public CarsPage clickCarToBookmarksBtn() {
        click(carToBookmarksBtn);
        return this;
    }
}
