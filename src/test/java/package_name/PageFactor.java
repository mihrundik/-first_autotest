package package_name;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PageFactor {

    public final WebDriverWait wait;

    // используемые в тестах элементы страницы
    @FindBy(id = "username")
    private WebElement inputName;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "confirm_password")
    private WebElement passwordConfirmation;

    @FindBy(id = "birthdate")
    private WebElement inputBirthdate;

    @FindBy(id = "language_level")
    private WebElement inputLanguageLevel;

    @FindBy(xpath = "//*[@id=\"registrationForm\"]/input")
    private WebElement inputForm;

    @FindBy(id = "output")
    private WebElement outputForm;


    // инициализируем класс и связываем объекты элементов на странице
    public PageFactor(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        PageFactory.initElements(driver, this);
    }


    // единая функция для заполнения полей
    private boolean fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        return true;
    }

    public boolean fillInputName(String username) {
        return fillField(inputName, username);
    }

    public boolean fillInputEmail(String email) {
        return fillField(inputEmail, email);
    }

    public boolean fillInputPassword(String password) {
        return fillField(inputPassword, password);
    }

    public boolean fillPasswordConfirmation(String cPassword) {
        return fillField(passwordConfirmation, cPassword);
    }

    public boolean fillBirthDate(String dateValue) {
        return fillField(inputBirthdate, dateValue);
    }


    // функция выбора уровня владения языком
    public void selectInputLanguageLevel(String level) {
        Select dropdown = new Select(inputLanguageLevel); // используем элемент из @FindBy
        dropdown.selectByVisibleText(level);
    }

    // проверка значений поля ввода
    public boolean checkPasswordsMatch(String password, String confirmPassword) {
        String actualPassword = inputPassword.getAttribute("value");
        String actualConfirmPassword = passwordConfirmation.getAttribute("value");
        return actualPassword.equals(actualConfirmPassword);
    }

    // заполнение полей формы
    public void fillForm(String name, String email, String password, String cPassword,
                         String birthdate, String language) {
        fillInputName(name);
        fillInputEmail(email);
        fillInputPassword(password);
        fillPasswordConfirmation(cPassword);
        fillBirthDate(birthdate);
        selectInputLanguageLevel(language);
    }

    // отправка заполненной формы
    public void submitForm(String password, String cPassword) throws Exception {
        if (!checkPasswordsMatch(password, cPassword)) { // исправлено название метода
            throw new Exception("Пароли не совпадают!");
        }
        inputForm.click();
    }

    // проверка результата отправки формы
    public String readOutputForm() {
        return outputForm.getText();
    }

}