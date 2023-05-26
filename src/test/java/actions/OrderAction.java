package actions;

import constants.PathApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.OrderCard;

import static io.restassured.RestAssured.given;

public class OrderAction extends BaseApi {
    public OrderAction() {
    }

    @Step("Создание заказа, POST /api/v1/orders")
    public Response postRequestCreateOrder(OrderCard orderCard) {
        return given(RequestSpecification())
                .body(orderCard)
                .when()
                .post(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение полного списка заказов, GET /api/v1/orders")
    public Response getRequestGetOrderList() {
        return given(RequestSpecification())
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение списка заказов по одному параметру, GET /api/v1/orders")
    public Response getRequestGetOrderList(String queryParam, String queryParamValue) {
        return given(RequestSpecification())
                .queryParam(queryParam, queryParamValue)
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение списка заказов по двум параметрам, GET /api/v1/orders")
    public Response getRequestGetOrderList(String firstQueryParam, String firstQueryParamValue,
                                           String secondQueryParam, String secondQueryParamValue) {
        return given(RequestSpecification())
                .queryParam(firstQueryParam, firstQueryParamValue)
                .queryParam(secondQueryParam, secondQueryParamValue)
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Получение списка заказов по трем параметрам, GET /api/v1/orders")
    public Response getRequestGetOrderList(String firstQueryParam, String firstQueryParamValue,
                                           String secondQueryParam, String secondQueryParamValue,
                                           String thirdQueryParam, String thirdQueryParamValue) {
        return given(RequestSpecification())
                .queryParam(firstQueryParam, firstQueryParamValue)
                .queryParam(secondQueryParam, secondQueryParamValue)
                .queryParam(thirdQueryParam, thirdQueryParamValue)
                .get(PathApi.ORDER_BASE_URL);
    }

    @Step("Отмена заказа по трэк номеру, PUT /api/v1/orders/cancel")
    public void putRequestCancelOrderByTrack(int trackNumber) {
        given(RequestSpecification())
                .queryParam("track", trackNumber)
                .put(PathApi.CANCEL_ORDER);
    }

    @Step("Завершение заказа по id, PUT /api/v1/orders/finish/:id")
    public void putRequestFinishOrderById(int orderId) {
        given(RequestSpecification())
                .put(PathApi.FINISH_ORDER + "{:id}", orderId);
    }
}
