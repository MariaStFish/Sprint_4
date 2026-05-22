package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    //Первая форма
    private By nameField = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    // Вторая форма
    private By dateField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div/div/input");
    private By rentalPeriodField = By.className("Dropdown-control");
    private By blackCheckbox = By.xpath("//label[text()='чёрный жемчуг']/input");
    private By greyCheckbox = By.xpath("//label[text()='серая безысходность']/input");

    // Кнопки заказа
    private By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By confirmButton = By.xpath("//button[text()='Да']");

    // Сообщение об успехе
    private final By successMessage = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstForm(String name, String surname, String address, String metro, String phone) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        WebElement element = driver.findElement(metroField);
        element.click();
        element.sendKeys(metro);
        By metroOption = By.xpath("//div[contains(@class, 'Order_Text__2broi') and text()='" + metro + "']");
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(metroOption)).click();
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.urlContains("/order"));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(dateField));
    }

    public void setDate(String date) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(dateField));
        WebElement dateInput = driver.findElement(dateField);
        dateInput.click();
        dateInput.sendKeys(date);
        String day = date.split("\\.")[0];
        By datePicker = By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + day + "']");
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(datePicker)).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(By.className("react-datepicker")));
    }

    public void selectRentalPeriod(String period) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(rentalPeriodField)).click();
        By periodOption = By.xpath("//div[@class='Dropdown-option' and text()='" + period + "']");
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(periodOption)).click();
    }

    public void selectColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            driver.findElement(blackCheckbox).click();
        } else if (color.equalsIgnoreCase("grey")) {
            driver.findElement(greyCheckbox).click();
        }
    }

    public void fillSecondForm(String date, String rentalPeriod, String color) {
        setDate(date);
        selectRentalPeriod(rentalPeriod);
        selectColor(color);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isOrderSuccessMessageDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).isDisplayed();
    }

    public void completeOrderFlow(String name, String surname, String address, String metro, String phone,
                                  String date, String rentalPeriod, String color) {
        fillFirstForm(name, surname, address, metro, phone);
        clickNextButton();
        fillSecondForm(date, rentalPeriod, color);
        clickOrderButton();
        confirmOrder();
    }
}
