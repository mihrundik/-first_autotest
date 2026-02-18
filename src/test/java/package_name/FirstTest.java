package package_name;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest extends AbstractClass {

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        return options;
    }

    @Test
    @DisplayName("Проверка формы регистрации")
    void sendOutput() throws Exception {
        String date = EnvConfig.getBirthDay() + EnvConfig.getBirthMonth() + EnvConfig.getBirthYear();
        String birthdate = String.format("%s-%s-%s", EnvConfig.getBirthYear(), EnvConfig.getBirthMonth(), EnvConfig.getBirthDay());
        page.fillForm(EnvConfig.getUsername(), EnvConfig.getEmail(), EnvConfig.getPassword(), EnvConfig.getCPassword(), date, EnvConfig.getLevel());
        page.submitForm(EnvConfig.getPassword(), EnvConfig.getCPassword());


        String resultMessage = page.readOutputForm();

        boolean checkName = resultMessage.contains(EnvConfig.getUsername());
        boolean checkEmail = resultMessage.contains(EnvConfig.getEmail());
        boolean checkBirthdate = resultMessage.contains(birthdate);
        boolean checkLanguage = resultMessage.contains(EnvConfig.getLevelIngl());

        statusTest(checkName && checkEmail && checkBirthdate && checkLanguage,
                "Проверка формы регистрации 'sendOutput'");

        assertAll(
                () -> assertTrue(resultMessage.contains(EnvConfig.getUsername()), "Имя пользователя не найдено в результате"),
                () -> assertTrue(resultMessage.contains(EnvConfig.getEmail()), "Электронная почта не найдена в результате"),
                () -> assertTrue(resultMessage.contains(birthdate), "Дата рождения не найдена в результате"),
                () -> assertTrue(resultMessage.contains(EnvConfig.getLevelIngl()), "Уровень языка не найден в результате")
        );
    }
}


