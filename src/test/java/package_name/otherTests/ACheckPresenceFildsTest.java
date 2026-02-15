package package_name.otherTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import package_name.AbstractClass;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ACheckPresenceFildsTest extends AbstractClass {

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return options;
    }

    @Test
    @Order(1)
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
