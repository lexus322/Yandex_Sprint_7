package test;

import base.BaseCourierTest;
import constants.CourierFields;
import constants.ErrorMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Логин курьера - POST /api/v1/courier/login")
public class LogInCourierTest extends BaseCourierTest {

    @Test
    @DisplayName("Отправка корректного запроса POST /api/v1/courier/login")
    @Description("Успешный вход курьера /api/v1/courier/login")
    public void logInCourierHappyPathTest() {
        createNewTestCourier();
        Response response = courierAction.postRequestLogIn(courierCard);
        response.then().assertThat().body("id", notNullValue())
                .and().statusCode(SC_OK);
        deleteTestCourier();
    }

    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier/login с измененным паролем")
    @Description("Невозможно войти с измененным паролем")
    public void logInCourierChangedPasswordTest() {
        createNewTestCourier();
        String initialValue = courierCard.getPassword();
        courierCard.setPassword(CourierFields.RANDOM_PASSWORD);
        Response response = courierAction.postRequestLogIn(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_FOUND_DATA_FOR_LOG_IN))
                .and().statusCode(SC_NOT_FOUND);
        removeTestDataWithChangedPass(initialValue);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier/login с некорректными/не созданными данными курьера")
    @Description("Невозможно войти с некорректными/не созданными данными курьеров")
    public void logInCourierIncorrectDataTest() {
        generateCourierData();
        Response response = courierAction.postRequestLogIn(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_FOUND_DATA_FOR_LOG_IN))
                .and().statusCode(SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier/login с полем password = null")
    @Description("Невозможно войти без пароля")
    public void logInCourierPassNullValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getPassword();
        courierCard.setPassword(CourierFields.NULL_VALUE);
        Response response = courierAction.postRequestLogIn(courierCard);
        response.then().assertThat().body(equalTo(ErrorMessage.SERVICE_UNAVAILABLE))
                .and().statusCode(SC_GATEWAY_TIMEOUT);
        removeTestDataWithChangedPass(initialValue);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier/login с пустым полем password")
    @Description("Невозможно войти без пароля")
    public void logInCourierPassEmptyValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getPassword();
        courierCard.setPassword(CourierFields.EMPTY_VALUE);
        Response response = courierAction.postRequestLogIn(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_BAD_REQUEST);
        removeTestDataWithChangedPass(initialValue);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier/login с полем login = null")
    @Description("Невозможно войти без логина")
    public void logInCourierLoginNullValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getLogin();
        courierCard.setLogin(CourierFields.NULL_VALUE);
        Response response = courierAction.postRequestLogIn(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_BAD_REQUEST);
        removeTestDataWithChangedLogin(initialValue);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/v1/courier/login с пустым полем login")
    @Description("Невозможно войти без логина")
    public void logInCourierLoginEmptyValueTest() {
        createNewTestCourier();
        String initialValue = courierCard.getLogin();
        courierCard.setLogin(CourierFields.EMPTY_VALUE);
        Response response = courierAction.postRequestLogIn(courierCard);
        response.then().assertThat().body("message", equalTo(ErrorMessage.NOT_ENOUGH_DATA_FOR_LOG_IN))
                .and().statusCode(SC_BAD_REQUEST);
        removeTestDataWithChangedLogin(initialValue);
    }

    @Step("Удаление созданных тестовых данных")
    public void removeTestDataWithChangedPass(String initialValue) {
        courierCard.setPassword(initialValue);
        deleteTestCourier();
    }

    @Step("Удаление созданных тестовых данных")
    public void removeTestDataWithChangedLogin(String initialValue) {
        courierCard.setLogin(initialValue);
        deleteTestCourier();
    }

}
