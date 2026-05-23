package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pageobjects.MainPage;
import ru.yandex.praktikum.pageobjects.OrderPage;

import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderCreationTest extends BaseTest {
    private MainPage homePage;
    private OrderPage orderPage;

    // Поля для параметризации
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String date;
    private String rentalPeriod;
    private String color;
    private String buttonPosition;

    public OrderCreationTest(String name, String surname, String address, String metro,
                             String phone, String date, String rentalPeriod, String color, String buttonPosition) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.buttonPosition = buttonPosition;
    }

    @Parameterized.Parameters(name = "{index}: {0} {1}, кнопка: {8}")
    public static Collection<Object[]> testData() {
        return TestDataProvider.getOrderDataWithButtonPosition();
    }

    @Before
    public void setUpPages() {
        homePage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testCreateOrder() {
        if (buttonPosition.equals("top")) {
            homePage.clickTopOrderButton();
        } else {
            homePage.clickBottomOrderButton();
        }
        orderPage.completeOrderFlow(name, surname, address, metro, phone, date, rentalPeriod, color);

        Assert.assertTrue("Сообщение об успешном заказе не появилось",
                orderPage.isOrderSuccessMessageDisplayed());
    }

}
