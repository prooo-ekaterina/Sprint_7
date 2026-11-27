package ru.praktikum_services.qa_scooter.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.model.OrderModel;

import static io.restassured.RestAssured.given;
import static ru.praktikum_services.qa_scooter.data.OrderData.ORDER_PATH;

public class OrderSteps {

    @Step("Создание заказа")
    public static Response createOrder(OrderModel order) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then()
                .extract()
                .response();
    }

@Step ("Получение списка заказов")
    public static Response getOrderList() {
        return given()
                .log().all()
                .when()
                .get(ORDER_PATH)
                .then()
                .extract()
                .response();
}


}

