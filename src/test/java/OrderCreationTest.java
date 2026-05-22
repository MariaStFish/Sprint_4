import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    private WebDriver driver;
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

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return TestDataProvider.getOrderDataWithButtonPosition();
    }

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver("firefox");
        driver.get("https://qa-scooter.praktikum-services.ru/");
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

    @After
    public void tearDown() {
        driver.quit();
    }
}
