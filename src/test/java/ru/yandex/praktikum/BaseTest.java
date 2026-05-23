package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pageobjects.MainPage;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver("chrome");
        driver.get(MainPage.BASE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
