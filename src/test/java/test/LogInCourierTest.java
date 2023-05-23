package test;

import base.BaseCourierTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
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
    }

    @After
    public void afterDelete() {
        deleteTestCourier();
    }


}
