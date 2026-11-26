package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;

import static data.OrderData.*;
import static io.restassured.RestAssured.given;

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
                .extract().response();
    }

@Step ("Получение списка заказов")
    public static Response getOrderList() {
        return given()
                .log().all()
                .when()
                .get(ORDER_PATH)
                .then()
                .extract().response();
}


}

