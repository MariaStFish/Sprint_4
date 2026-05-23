package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pageobjects.MainPage;

import java.util.Collection;

@RunWith(Parameterized.class)
public class FaqDropdownTest extends BaseTest{

    private MainPage homePage;
    private int questionIndex;
    private String expectedAnswer;

    public FaqDropdownTest(int questionIndex, String expectedAnswer) {
            this.questionIndex = questionIndex;
            this.expectedAnswer = expectedAnswer;
        }

        @Parameterized.Parameters(name = "{index}: Вопрос {0}")
        public static Collection<Object[]> testData() {
            return TestDataProvider.getFaqData();
        }

        @Before
        public void setUpPages() {
            homePage = new MainPage(driver);
        }

        @Test
        public void testFaqAccordion() {
            String actualAnswer = homePage.openQuestionAndGetAnswer(questionIndex);
            Assert.assertEquals("Текст ответа для вопроса " + questionIndex + " не совпадает",
                    expectedAnswer, actualAnswer);
        }

    }
