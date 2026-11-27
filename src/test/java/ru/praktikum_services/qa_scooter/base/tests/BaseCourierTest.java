package ru.praktikum_services.qa_scooter.base.tests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import ru.praktikum_services.qa_scooter.model.CourierModel;
import org.junit.After;

import static ru.praktikum_services.qa_scooter.data.CourierData.LOGIN;
import static ru.praktikum_services.qa_scooter.data.CourierData.PASSWORD;
import static ru.praktikum_services.qa_scooter.steps.CourierSteps.deleteCourier;
import static ru.praktikum_services.qa_scooter.steps.CourierSteps.loginCourier;

public class BaseCourierTest extends BaseApiTest {

    protected int courierId;


    @After
    public void cleanUp() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD);
        Response response = loginCourier(courier);
        if (response.statusCode() == HttpStatus.SC_OK) {
            int id = response.path("id");
            deleteCourier(id);
        }
    }
}
