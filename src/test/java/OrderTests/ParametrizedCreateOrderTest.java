package OrderTests;

import BaseTests.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
    public class ParametrizedCreateOrderTest extends BaseApiTest {

        private final List<String> colors;

        public ParametrizedCreateOrderTest(List<String> colors) {
            this.colors = colors;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {List.of("BLACK")},
                    {List.of("GREY")},
                    {List.of("BLACK", "GREY")},
                    {null}
            });
        }

        @Test
        @DisplayName("Создание заказа с самокатами разных цветов")
        @Description("Параметризированный тест на создание самокатов с выбором одного из цветов, обоих или ни одного; в теле ответа возвращается 'track' с номером заказа; код ответа: 201")
        public void createOrderTest() {
            OrderModel order = new OrderModel(
                    "Naruto",
                    "Uchiha",
                    "Konoha, 142 apt.",
                    4,
                    "+7 800 355 35 35",
                    5,
                    "2020-06-06",
                    "Saske, come back to Konoha",
                    colors
            );

            OrderSteps.createOrder(order)
                    .then()
                    .statusCode(201)
                    .body("track", notNullValue());
        }
    }

