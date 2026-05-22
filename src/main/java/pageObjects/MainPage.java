package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    //Локатор для кнокпи "Заказать" в шапке
    private By topOrderButton = By.className("Button_Button__ra12g");
    //Локатор для второй кнопки "Заказать"
    private By bottomOrderButton = By.className("Button_Middle__1CSJM");
    //Локатор для всех заголовков выпадающего списки
    private By faqQuestions = By.className("accordion__button");
    //Локатор для ответов выпадающего списки
    private By faqAnswers = By.className("accordion__panel");
    //Локатор для блока вопросов
    private By faqBlock = By.className("Home_FAQ__3uVm4");

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToFaqBlock() {
        WebElement element = driver.findElement(faqBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(faqQuestions));
    }
    public void clickFaqQuestions(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(questions.get(index)));
        questions.get(index).click();
    }

    public String getFaqAnswerText(int index) {
        List<WebElement> answers = driver.findElements(faqAnswers);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(answers.get(index)));
        return answers.get(index).getText();
    }

    public String openQuestionAndGetAnswer(int index) {
        scrollToFaqBlock();
        clickFaqQuestions(index);
        return getFaqAnswerText(index);
    }

    public void clickTopOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(topOrderButton));
        driver.findElement(topOrderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.urlContains("/order"));
    }

    public void clickBottomOrderButton() {
        WebElement element = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.urlContains("/order"));
    }
}

