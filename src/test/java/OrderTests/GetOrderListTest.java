package OrderTests;

import BaseTests.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.OrderSteps;


import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest extends BaseApiTest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("В тело ответа возвращается список заказов; код ответа 200")
    public void getOrderListTest() {
        Response response = OrderSteps.getOrderList();

        response.then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
