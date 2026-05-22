import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;

import java.util.Collection;

@RunWith(Parameterized.class)
public class FaqDropdownTest {
    private WebDriver driver;
    private MainPage homePage;

    private int questionIndex;
    private String expectedAnswer;

    public FaqDropdownTest(int questionIndex, String expectedAnswer) {
            this.questionIndex = questionIndex;
            this.expectedAnswer = expectedAnswer;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testData() {
            return TestDataProvider.getFaqData();
        }

        @Before
        public void setUp() {
            driver = DriverFactory.getDriver("firefox");
            driver.get("https://qa-scooter.praktikum-services.ru/");
            homePage = new MainPage(driver);
        }

        @Test
        public void testFaqAccordion() {
            String actualAnswer = homePage.openQuestionAndGetAnswer(questionIndex);
            Assert.assertEquals("Текст ответа для вопроса " + questionIndex + " не совпадает",
                    expectedAnswer, actualAnswer);
        }

        @After
        public void tearDown() {
            driver.quit();
        }
    }
