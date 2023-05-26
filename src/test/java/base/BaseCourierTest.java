package base;

import actions.CourierAction;
import actions.GenerateCourierData;
import constants.CourierFields;
import io.qameta.allure.Step;
import resources.CourierCard;

public class BaseCourierTest {
    private final GenerateCourierData generateCourierData = new GenerateCourierData();
    public CourierAction courierAction = new CourierAction();
    public CourierCard courierCard;

    @Step("Создание курьера с рандомными данными")
    public void createNewTestCourier() {
        generateLogPassCourierData();
        courierAction.postRequestCreateCourier(courierCard);
    }


    @Step("Создание и заполение карточки курьера (login + password + firstname)")
    public void generateCourierData() {
        generateCourierData.generateLoginPassName();
        courierCard = new CourierCard(
                generateCourierData.getCourierLogin(),
                generateCourierData.getCourierPassword(),
                generateCourierData.getCourierFirstName());
    }

    @Step("Создание и заполение карточки курьера (login + password)")
    private void generateLogPassCourierData() {
        generateCourierData.generateLoginPassName();
        courierCard = new CourierCard(
                generateCourierData.getCourierLogin(),
                generateCourierData.getCourierPassword());
    }

    @Step("Создание и заполнение карточки курьера случайными данными (при помощи полей)")
    public void generateCustomCourierData(String firstField, String secondField) {
        generateCourierData.generateLoginPassName();
        courierCard = new CourierCard();
        fillField(firstField);
        fillField(secondField);
    }

    private void fillField(String field) {
        switch (field) {
            case CourierFields.LOGIN:
                courierCard.setLogin(generateCourierData.getCourierLogin());
                break;
            case CourierFields.PASSWORD:
                courierCard.setPassword(generateCourierData.getCourierPassword());
                break;
            case CourierFields.FIRST_NAME:
                courierCard.setFirstName(generateCourierData.getCourierFirstName());
                break;
        }
    }

    @Step("Удаление созданных тестовых данных")
    public void deleteTestCourier() {
        courierAction.deleteRequestRemoveCourier(courierCard);
    }
}
