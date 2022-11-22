package pageObjects.av;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import pageObjects.baseObjects.BasePage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@Log4j
public class ApiPage extends BasePage {
    CarPage carPage = new CarPage();
    String phoneNumberApi;
    String brandCarApi;
    String totalViewsCarApi;
    int countTopCarsApi;

    public String getPhoneNumberFromApi() {
        log.debug("Get phone number from api when logging in");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(getJsonData("av"))
                .post("https://api.av.by/auth/login/sign-in");
        response
                .then()
                .assertThat()
                .statusCode(200);
        return phoneNumberApi = response
                .body()
                .jsonPath()
                .getString("phone.number");
    }

    public String getBrandCarFromApi() {
        log.debug("Get brand car from api");
        Response response = given()
                .get("https://api.av.by/offer-types/cars/price-statistics/offers/" + carPage.getCarNumberAd());
        response
                .then()
                .assertThat()
                .statusCode(200);
        return brandCarApi = response
                .body()
                .jsonPath()
                .getString("title.brand");
    }

    public String  getTotalViewsCarFromApi() {
        log.debug("Get total views from api");
        Response response = given()
                .get("https://api.av.by/offers/" + carPage.getCarNumberAd() + "/counter");
        response
                .then()
                .assertThat()
                .statusCode(200);
        return totalViewsCarApi = response
                .body()
                .jsonPath()
                .getString("totalViews");
    }

    public Integer getCountPopCarsApi() {
        log.debug("Get count top car from api");
        Response response = given()
                .get("https://api.av.by/offer-types/cars/offers/top");
        response
                .then()
                .assertThat()
                .statusCode(200);
        return countTopCarsApi = response
                .jsonPath()
                .getList("")
                .size();
    }

    public String getJsonData(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get("files/" + filename + ".json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
