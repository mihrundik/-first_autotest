package package_name;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CFirstTest extends AbstractClass {

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        return options;
    }

    @Test
    @Order(1)
    @DisplayName("Проверка формы регистрации")
    void sendOutput() throws Exception {
        String date = ReadConfig.getBirthDayDD() + ReadConfig.getBirthMonthMM() + ReadConfig.getBirthYearYYYY();
        String birthdate = String.format("%s-%s-%s", ReadConfig.getBirthYearYYYY(), ReadConfig.getBirthMonthMM(), ReadConfig.getBirthDayDD());
        page.fillForm(ReadConfig.getUsername(), ReadConfig.getEmail(), ReadConfig.getPassword(), ReadConfig.getConfirmPassword(), date, "Продвинутый");
        page.submitForm(ReadConfig.getPassword(), ReadConfig.getConfirmPassword());
        String resultMessage = page.readOutputForm();

        boolean checkName = resultMessage.contains(ReadConfig.getUsername());
        boolean checkEmail = resultMessage.contains(ReadConfig.getEmail());
        boolean checkBirthdate = resultMessage.contains(birthdate);
        boolean checkLanguage = resultMessage.contains(ReadConfig.getEnglishLevel());

        statusTest(checkName && checkEmail && checkBirthdate && checkLanguage,
                "Проверка формы регистрации 'sendOutput'");

        assertAll(
                () -> assertTrue(resultMessage.contains(ReadConfig.getUsername()), "Имя пользователя не найдено в результате"),
                () -> assertTrue(resultMessage.contains(ReadConfig.getEmail()), "Электронная почта не найдена в результате"),
                () -> assertTrue(resultMessage.contains(birthdate), "Дата рождения не найдена в результате"),
                () -> assertTrue(resultMessage.contains(ReadConfig.getEnglishLevel()), "Уровень языка не найден в результате")
        );
    }
}


