package actions;

import constants.PathApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.CourierCard;
import resources.CourierId;

import static io.restassured.RestAssured.given;

public class CourierAction extends BaseApi {
    public CourierAction() {
    }

    public Response postRequestCreateCourier(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .when()
                .post(PathApi.COURIER_BASE_URL);
    }

    public Response postRequestLogIn(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .when()
                .post(PathApi.COURIER_LOGIN);
    }

    @Step("Удаление курьера")
    public Response deleteRequestRemoveCourier(CourierCard courierCard) {
        return deleteRequestRemoveCourierById(getCourierId(courierCard));
    }

    @Step("Узнать ID курьера по его логину")
    public String getCourierId(CourierCard courierCard) {
        Response response = postRequestLogIn(courierCard);
        CourierId courierId = response.as(CourierId.class);
        return courierId.getId();
    }

    @Step("Удалить курьера, DELETE запрос - /api/v1/courier/{:id}")
    public Response deleteRequestRemoveCourierById(String courierID) {
        int courierId = Integer.parseInt(courierID);
        return given(RequestSpecification())
                .delete(PathApi.COURIER_BASE_URL + "/{:id}", courierId);
    }

}
