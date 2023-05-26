package test;

import base.BaseOrderTest;
import io.restassured.response.Response;
import org.junit.Test;
import resources.TrackCard;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderNullColorFieldTest extends BaseOrderTest {
    @Test
    public void createOrderNullColorValue() {
        generateOrderData();
        Response response = orderAction.postRequestCreateOrder(orderCard);
        response.then().assertThat().body("track", notNullValue())
                .and().statusCode(SC_CREATED);
        cancelTestOrder(response.as(TrackCard.class));
    }
}
