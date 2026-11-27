package ru.praktikum_services.qa_scooter.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;import ru.praktikum_services.qa_scooter.model.CourierModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static ru.praktikum_services.qa_scooter.data.CourierData.COURIER_PATH_CREATE;
import static ru.praktikum_services.qa_scooter.data.CourierData.COURIER_PATH_LOGIN;

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
                .extract()
                .response();
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
                .extract()
                .response();
    }

    @Step("Удаление курьера по id")
    public static void deleteCourier(int courierId) {
        given()
                .log().all()
                .when()
                .delete(COURIER_PATH_CREATE + "/" + courierId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("ok", is(true));
    }
}
