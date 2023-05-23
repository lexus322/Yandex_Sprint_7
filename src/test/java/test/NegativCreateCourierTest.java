package test;

import base.BaseCourierTest;
import constants.CourierFields;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

@Feature("Создание курьера (Негативные сценарии) - POST /api/v1/courier")
public class NegativCreateCourierTest extends BaseCourierTest {
    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier без поля login")
    @Description("Невозможно создать курьера без логина")
    public void createCourierLoginNullValueTest() {
        generateCustomCourierData(CourierFields.PASSWORD, CourierFields.FIRST_NAME);
        Response response = courierAction.postRequestCreateCourier(courierCard);
        response.then().assertThat().body("message",
                        Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE))
                .and().statusCode(SC_BAD_REQUEST);
    }
    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier с пустым полем login")
    @Description("Невозможно создать курьера без логина")
    public void createCourierLoginEmptyValueTest() {
        generateCourierData();
        courierCard.setLogin("");
        Response response = courierAction.postRequestCreateCourier(courierCard);
        response.then().assertThat().body("message",
                        Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE))
                .and().statusCode(SC_BAD_REQUEST);
    }
    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier с пустым полем password")
    @Description("Невозможно создать курьера без пароля")
    public void createCourierPassEmptyValueTest() {
        generateCourierData();
        courierCard.setPassword("");
        Response response = courierAction.postRequestCreateCourier(courierCard);
        response.then().assertThat().body("message",
                        Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE))
                .and().statusCode(SC_BAD_REQUEST);
    }
    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier без поля password")
    @Description("Невозможно создать курьера без пароля")
    public void createCourierPassNullValueTest() {
        generateCustomCourierData(CourierFields.LOGIN, CourierFields.FIRST_NAME);
        Response response = courierAction.postRequestCreateCourier(courierCard);
        response.then().assertThat().body("message",
                        Matchers.equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_CREATE))
                .and().statusCode(SC_BAD_REQUEST);
    }
}
