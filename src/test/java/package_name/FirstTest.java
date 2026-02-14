package package_name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FirstTest {


    @Nested
    @DisplayName("Проверка наличия полей")
    class CheckPresenceFilds extends AbstractClassTest{

        @Override
        public ChromeOptions createChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            return options;
        }

        @Test
        @DisplayName("Тестирование присутствия всех элементов на странице")
        public void testElementsPresence() {

            List<String> elementsIds = List.of(
                    "username",
                    "email",
                    "password",
                    "confirm_password",
                    "birthdate",
                    "language_level",
                    "//*[@id='registrationForm']/input"
            );

            for (String elementId : elementsIds) {
                By locator = (elementId.startsWith("//")) ? By.xpath(elementId) : By.id(elementId);
                WebElement element = page.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                assertTrue(element.isDisplayed(), "Элемент с ID/XPath '" + elementId + "' отсутствует на странице.");

                boolean isDisplayed = element.isDisplayed();
                statusTest(isDisplayed, "Проверка элемента с ID/XPath '" + elementId + "'");
                assertTrue(isDisplayed);
            }
        }
    }

    // проверка заполнения полей
    @Nested
    @DisplayName("Проверка заполнения полей")
    class CheckNameFild extends AbstractClassTest{

        @Override
        public ChromeOptions createChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            return options;
        }

        @Test
        @DisplayName("Проверка заполнения поля Имя пользователя")
        void sendName() {
            page.fillInputName(Config.getUsername());

            boolean result = page.fillInputName(Config.getUsername());
            statusTest(result, "Проверка отправки текста в форму 'sendName'");
            assertTrue(result);
        }

        @Test
        @DisplayName("Проверка заполнения поля Электронная почта")
        void sendEmail() {
            page.fillInputEmail(Config.getEmail());

            boolean result = page.fillInputEmail(Config.getEmail());
            statusTest(result, "Проверка отправки текста в форму 'sendEmail'");
            assertTrue(result);
        }

        @Test
        @DisplayName("Проверка заполнения поля Пароль")
        void sendPass() {
            page.fillInputPassword(Config.getPassword());

            boolean result = page.fillInputPassword(Config.getPassword());
            statusTest(result, "Проверка отправки текста в форму 'sendPass'");
            assertTrue(result);
        }

        @Test
        @DisplayName("Проверка заполнения поля Подтвердите пароль")
        void sendCPass() {
            page.fillPasswordConfirmation(Config.getConfirmPassword());

            boolean result = page.fillPasswordConfirmation(Config.getConfirmPassword());
            statusTest(result, "Проверка отправки текста в форму 'sendCPass'");
            assertTrue(result);
        }

        @Test
        @DisplayName("Проверка заполнения поля Дата рождения")
        void sendBirthDate() {
            String date = Config.getBirthDayDD()+Config.getBirthMonthMM()+Config.getBirthYearYYYY();
            page.fillBirthDate(date);

            boolean result = page.fillBirthDate(date);
            statusTest(result, "Проверка отправки текста в форму 'sendBirthDate'");
            assertTrue(result);
        }

        @Test
        @DisplayName("Проверка заполнения поля Уровень знания языка")
        void sendLanguageLevel() {
            page.selectInputLanguageLevel(Config.getLanguageLevel());
            boolean result = true;

            statusTest(result, "Проверка отправки текста в форму 'sendLanguageLevel'");
            assertTrue(result);
        }
    }

    // проверка подтверждения пароля
    @Nested
    @DisplayName("Проверка подтверждения пароля")
    class CheckPasswordConfirmation extends AbstractClassTest{

        @Override
        public ChromeOptions createChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--kiosk");
            return options;
        }

        @Test
        @DisplayName("Проверка формы регистрации")
        void sendOutput() throws Exception {
            String date = Config.getBirthDayDD()+Config.getBirthMonthMM()+Config.getBirthYearYYYY();
            String birthdate = String.format("%s-%s-%s", Config.getBirthYearYYYY(), Config.getBirthMonthMM(), Config.getBirthDayDD());
            page.fillForm(Config.getUsername(), Config.getEmail(), Config.getPassword(), Config.getConfirmPassword(), date, "Продвинутый");
            page.submitForm(Config.getPassword(), Config.getConfirmPassword());
            String resultMessage = page.readOutputForm();

            boolean checkName = resultMessage.contains(Config.getUsername());
            boolean checkEmail = resultMessage.contains(Config.getEmail());
            boolean checkBirthdate = resultMessage.contains(birthdate);
            boolean checkLanguage = resultMessage.contains(Config.getEnglishLevel());

            statusTest(checkName && checkEmail && checkBirthdate && checkLanguage,
                    "Проверка формы регистрации 'sendOutput'");

            assertAll(
                    () -> assertTrue(resultMessage.contains(Config.getUsername()), "Имя пользователя не найдено в результате"),
                    () -> assertTrue(resultMessage.contains(Config.getEmail()), "Электронная почта не найдена в результате"),
                    () -> assertTrue(resultMessage.contains(birthdate), "Дата рождения не найдена в результате"),
                    () -> assertTrue(resultMessage.contains(Config.getEnglishLevel()), "Уровень языка не найден в результате")
            );
        }
    }
}
