package package_name.otherTest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import package_name.AbstractClass;
import package_name.EnvConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static package_name.EnvConfig.getBirthYear;


public class CheckNameFildTest extends AbstractClass {

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return options;
    }

    @Test
    @DisplayName("Проверка заполнения поля Имя пользователя")
    void sendName() {
        page.fillInputName(EnvConfig.getUsername());

        boolean result = page.fillInputName(EnvConfig.getUsername());
        statusTest(result, "Проверка отправки текста в форму 'sendName'");
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка заполнения поля Электронная почта")
    void sendEmail() {
        page.fillInputEmail(EnvConfig.getEmail());

        boolean result = page.fillInputEmail(EnvConfig.getEmail());
        statusTest(result, "Проверка отправки текста в форму 'sendEmail'");
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка заполнения поля Пароль")
    void sendPass() {
        page.fillInputPassword(EnvConfig.getPassword());

        boolean result = page.fillInputPassword(EnvConfig.getPassword());
        statusTest(result, "Проверка отправки текста в форму 'sendPass'");
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка заполнения поля Подтвердите пароль")
    void sendCPass() {
        page.fillPasswordConfirmation(EnvConfig.getCPassword());

        boolean result = page.fillPasswordConfirmation(EnvConfig.getCPassword());
        statusTest(result, "Проверка отправки текста в форму 'sendCPass'");
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка заполнения поля Дата рождения")
    void sendBirthDate() {
        String date = EnvConfig.getBirthDay() + EnvConfig.getBirthMonth() + getBirthYear();
        page.fillBirthDate(date);

        boolean result = page.fillBirthDate(date);
        statusTest(result, "Проверка отправки текста в форму 'sendBirthDate'");
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка заполнения поля Уровень знания языка")
    void sendLanguageLevel() {
        page.selectInputLanguageLevel(EnvConfig.getLevel());
        boolean result = true;

        statusTest(result, "Проверка отправки текста в форму 'sendLanguageLevel'");
        assertTrue(result);
    }
}