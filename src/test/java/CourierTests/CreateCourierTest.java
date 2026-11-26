package CourierTests;

import BaseTests.BaseCourierTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Test;

import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.createCourier;

public class CreateCourierTest extends BaseCourierTest {


    @Test
    @DisplayName("Успешное создание курьера")
    @Description("Курьер успешно создан при передаче в ручку всех обязательных полей; успешный запрос возвращает 'ok': 'true', код ответа: 201")
    public void testCreateCourierSuccess() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Ошибка при создании двух одинаковых курьеров")
    @Description("При повторном создании пользователя с уже существующим логином возвращается ошибка 'message': 'Этот логин уже используется. Попробуйте другой.', код ответа: 409")
    public void testCreateDoubleCourier() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));

        createCourier(courier)
                .then()
                .log().all()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Ошибка при создании курьера без логина")
    @Description("При попытке создания курьера без логина система выдает ошибку 'message': 'Недостаточно данных для создания учетной записи', код ответа: 400")
    public void testCreateCourierWithoutLoginError() {
        CourierModel courierWithoutLogin = new CourierModel(null, PASSWORD, FIRSTNAME);
        createCourier(courierWithoutLogin)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


    @Test
    @DisplayName("Ошибка при создании курьера без пароля")
    @Description("При попытке создания курьера без пароля система выдает ошибку 'message': 'Недостаточно данных для создания учетной записи', код ответа: 400")
    public void testCreateCourierWithoutPasswordError() {
        CourierModel courierWithoutPassword = new CourierModel(LOGIN, null, FIRSTNAME);
        createCourier(courierWithoutPassword)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

