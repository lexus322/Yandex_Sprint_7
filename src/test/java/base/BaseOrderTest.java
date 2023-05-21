package base;

import actions.GenerateOrderCardData;
import actions.GenerateOrderValue;
import actions.OrderAction;
import io.qameta.allure.Step;
import resources.OrderCard;
import resources.TrackCard;

public class BaseOrderTest {
    public OrderAction orderAction = new OrderAction();
    public GenerateOrderCardData generateOrderCardData = new GenerateOrderCardData();
    public GenerateOrderValue generateOrderValue = new GenerateOrderValue();
    public OrderCard orderCard;

    @Step("Заполнение карточки заказа случайными данными")
    public void generateOrderData() {
        generateOrderCardData.generateRandomDataField();
        orderCard = new OrderCard(
                generateOrderCardData.getFirstName(), generateOrderCardData.getLastName(),
                generateOrderCardData.getAddress(), generateOrderCardData.getMetroStation(),
                generateOrderCardData.getPhone(), generateOrderCardData.getRentTime(),
                generateOrderCardData.getDeliveryDate(), generateOrderCardData.getComment());
    }

    @Step("Заполнение карточки заказа случайными данными + цвет")
    public void generateOrderData(String[] color) {
        generateOrderCardData.generateRandomDataField();
        orderCard = new OrderCard(
                generateOrderCardData.getFirstName(), generateOrderCardData.getLastName(),
                generateOrderCardData.getAddress(), generateOrderCardData.getMetroStation(),
                generateOrderCardData.getPhone(), generateOrderCardData.getRentTime(),
                generateOrderCardData.getDeliveryDate(), generateOrderCardData.getComment(), color);
    }


    @Step("Отмена тестового заказа")
    public void cancelTestOrder(TrackCard trackCard) {
        orderAction.putRequestCancelOrderByTrack(trackCard.getTrack());
    }

}
