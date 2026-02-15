package package_name.otherTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import package_name.AbstractClass;
import package_name.ReadConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // для воспроизведения тестов в заданном порядке

public class BCheckNameFildTest extends AbstractClass {

    public static void runTest() {
    }

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return options;
    }

    @Test
    @Order(1)
    @DisplayName("Проверка заполнения поля Имя пользователя")
    void sendName() {
        page.fillInputName(ReadConfig.getUsername());

        boolean result = page.fillInputName(ReadConfig.getUsername());
        statusTest(result, "Проверка отправки текста в форму 'sendName'");
        assertTrue(result);
    }

    @Test
    @Order(2)
    @DisplayName("Проверка заполнения поля Электронная почта")
    void sendEmail() {
        page.fillInputEmail(ReadConfig.getEmail());

        boolean result = page.fillInputEmail(ReadConfig.getEmail());
        statusTest(result, "Проверка отправки текста в форму 'sendEmail'");
        assertTrue(result);
    }

    @Test
    @Order(3)
    @DisplayName("Проверка заполнения поля Пароль")
    void sendPass() {
        page.fillInputPassword(ReadConfig.getPassword());

        boolean result = page.fillInputPassword(ReadConfig.getPassword());
        statusTest(result, "Проверка отправки текста в форму 'sendPass'");
        assertTrue(result);
    }

    @Test
    @Order(4)
    @DisplayName("Проверка заполнения поля Подтвердите пароль")
    void sendCPass() {
        page.fillPasswordConfirmation(ReadConfig.getConfirmPassword());

        boolean result = page.fillPasswordConfirmation(ReadConfig.getConfirmPassword());
        statusTest(result, "Проверка отправки текста в форму 'sendCPass'");
        assertTrue(result);
    }

    @Test
    @Order(5)
    @DisplayName("Проверка заполнения поля Дата рождения")
    void sendBirthDate() {
        String date = ReadConfig.getBirthDayDD() + ReadConfig.getBirthMonthMM() + ReadConfig.getBirthYearYYYY();
        page.fillBirthDate(date);

        boolean result = page.fillBirthDate(date);
        statusTest(result, "Проверка отправки текста в форму 'sendBirthDate'");
        assertTrue(result);
    }

    @Test
    @Order(6)
    @DisplayName("Проверка заполнения поля Уровень знания языка")
    void sendLanguageLevel() {
        page.selectInputLanguageLevel(ReadConfig.getLanguageLevel());
        boolean result = true;

        statusTest(result, "Проверка отправки текста в форму 'sendLanguageLevel'");
        assertTrue(result);
    }
}



