package ru.praktikum_services.qa_scooter.base.tests;

import io.restassured.RestAssured;
import org.junit.Before;

public class BaseApiTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
}
