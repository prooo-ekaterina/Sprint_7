package ru.praktikum_services.qa_scooter.order.tests;

import org.apache.http.HttpStatus;import ru.praktikum_services.qa_scooter.base.tests.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.steps.OrderSteps;


import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest extends BaseApiTest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("В тело ответа возвращается список заказов; код ответа 200")
    public void getOrderListTest() {
        Response response = OrderSteps.getOrderList();

        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("orders", notNullValue());
    }
}
