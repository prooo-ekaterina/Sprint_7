package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierModel;

import static data.CourierData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CourierSteps {


    @Step("Создание курьера")
    public static Response createCourier(CourierModel courier) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_PATH_CREATE)
                .then()
                .extract().response();
    }

    @Step("Авторизация курьера в системе")
    public static Response loginCourier(CourierModel courier) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_PATH_LOGIN)
                .then()
                .extract().response();
    }

    @Step("Удаление курьера по id")
    public static void deleteCourier(int courierId) {
        given()
                .log().all()
                .when()
                .delete(COURIER_PATH_CREATE + "/" + courierId)
                .then()
                .statusCode(200)
                .body("ok", is(true));
    }
}
