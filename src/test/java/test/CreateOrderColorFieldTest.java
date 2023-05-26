package test;

import base.BaseOrderTest;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import resources.OrderColor;
import resources.TrackCard;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Заполнение цвета в заказе, POST /api/v1/orders")
@DisplayName("Создание заказа с параметаризиронными данными для цвета")
@RunWith(Parameterized.class)
public class CreateOrderColorFieldTest extends BaseOrderTest {

    private final String[] color;

    public CreateOrderColorFieldTest(String[] color) {

        this.color = color;
    }

    @Parameterized.Parameters(name = "Значение поля color: {0}")
    public static Object[][] getColorValue() {
        Object[][] objects = {
                {OrderColor.NO_COLOR.getValue()},
                {OrderColor.GRAY.getValue()},
                {OrderColor.TWO_COLOR.getValue()},
        };
        return objects;
    }

    @Before
    public void setUp() {
        generateOrderData(color);
    }

    @Test
    public void createOrderDiffColorValue() {
        Response response = orderAction.postRequestCreateOrder(orderCard);
        response.then().assertThat().body("track", notNullValue())
                .and().statusCode(SC_CREATED);
        cancelTestOrder(response.as(TrackCard.class));
    }

}
