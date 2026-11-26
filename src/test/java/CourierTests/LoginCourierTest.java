package CourierTests;

import BaseTests.BaseCourierTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.CourierModel;
import org.junit.Before;
import org.junit.Test;

import static data.CourierData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;

public class LoginCourierTest extends BaseCourierTest {

    @Before
    public void setUpCourier() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        createCourier(courier)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Успешная авторизация курьера в системе")
    @Description("Курьер может авторизоваться в системе; успешный запрос возвращает id в теле ответа; код статуса: 200")
    public void testCourierLoginSuccess() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD);
        createCourier(courier);
        Response loginResponse = loginCourier(courier);
        courierId = loginResponse.path("id");
        loginResponse
                .then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue());

    }

    @Test
    @DisplayName("Ошибка при попытке авторизации курьера в системе без логина")
    @Description("При попытке авторизации в системе без логина происходит ошбика авторизации 'message': 'Недостаточно данных для входа'; код статуса: 400")
    public void testCourierLoginWithoutLoginError() {
        CourierModel courierWithoutLogin = new CourierModel("", PASSWORD);
        loginCourier(courierWithoutLogin)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Ошибка при попытке авторизации курьера в системе без пароля")
    @Description("При попытке авторизации в системе без пароля происходит ошбика авторизации 'message': 'Недостаточно данных для входа'; код статуса: 400")
    public void testCourierLoginWithoutPasswordError() {
        CourierModel courierWithoutPassword = new CourierModel(LOGIN, "");
        loginCourier(courierWithoutPassword)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Ошибка при попытке авторизации курьера с неверным логином")
    @Description("При попытке авторизации курьера с неверным логином происходит ошибка авторизации 'message': 'Учетная запись не найдена'; код статуса: 404")
    public void testCourierLoginWithIncorrectLoginError() {
        String nonExistingLogin = "error_" + System.currentTimeMillis();
        CourierModel courierWithIncorrectLogin = new CourierModel(nonExistingLogin, PASSWORD);
        loginCourier(courierWithIncorrectLogin)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Ошибка при попытке авторизации курьера с неверным логином")
    @Description("При попытке авторизации курьера с неверным логином происходит ошибка авторизации 'message': 'Учетная запись не найдена'; код статуса: 404")
    public void testCourierLoginWithIncorrectPasswordError() {
        String nonExistingPassword = "error_" + System.currentTimeMillis();
        CourierModel courierWithIncorrectPassword = new CourierModel(LOGIN, nonExistingPassword);
        loginCourier(courierWithIncorrectPassword)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

}
