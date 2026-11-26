package BaseTests;

import io.restassured.response.Response;
import model.CourierModel;
import org.junit.After;

import static data.CourierData.*;
import static steps.CourierSteps.*;

public class BaseCourierTest extends BaseApiTest {

    protected int courierId;


    @After
    public void cleanUp() {
        CourierModel courier = new CourierModel(LOGIN, PASSWORD);
        Response response = loginCourier(courier);
        if (response.statusCode() == 200) {
            int id = response.path("id");
            deleteCourier(id);
        }
    }
}
