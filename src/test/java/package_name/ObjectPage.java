package package_name;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static package_name.AbstractClassTest.driver;


public class ObjectPage {

    protected final WebDriverWait wait;


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
    public ObjectPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));   // оставила ождание на случай плохого интернета
        PageFactory.initElements(driver, this);
    }


    // функции заполнения полей
    public boolean fillInputName(String value) {
        inputName.clear();
        inputName.sendKeys(value);
        return true;
    }

    public boolean fillInputEmail(String value) {
        inputEmail.clear();
        inputEmail.sendKeys(value);
        return true;
    }

    public boolean fillInputPassword(String value) {
        inputPassword.clear();
        inputPassword.sendKeys(value);
        return true;
    }

    public boolean fillPasswordConfirmation(String value) {
        passwordConfirmation.clear();
        passwordConfirmation.sendKeys(value);
        return true;
    }

    public boolean fillBirthDate(String dateValue) {
        inputBirthdate.clear();
        inputBirthdate.sendKeys(dateValue);
        return true;
    }


    // функция выбора уровня владения языком
    public void selectInputLanguageLevel(String level) {
        WebElement languageDropdown = driver.findElement(By.id("language_level"));
        Select dropdown = new Select(languageDropdown);
        dropdown.selectByVisibleText(level);
    }

    // проверка значений поля ввода
    public boolean heckPasswordsMatch(String password, String confirmPassword) {
        String actualPassword = inputPassword.getAttribute("value");
        String actualConfirmPassword = passwordConfirmation.getAttribute("value");
        return actualPassword.equals(actualConfirmPassword);
    }

    // заполнение полей формы
    public void fillForm(String name, String email, String password, String cPassword, String birthdate, String language) {
        fillInputName(name);
        fillInputEmail(email);
        fillInputPassword(password);
        fillPasswordConfirmation(cPassword);
        fillBirthDate(birthdate);
        selectInputLanguageLevel(language);
    }

    // отправка заполненной формы
    public void submitForm(String password, String cPassword) throws Exception {
        if (!heckPasswordsMatch(password, cPassword)) { // сначала проверяем пароли
            throw new Exception("Пароли не совпадают!");
        }
        inputForm.click(); // если пароли совпадают
    }

    // проверка результата отправки формы
    public String readOutputForm() {
        return outputForm.getText();
    }

//    // проверка наличия модального окна ошибки
//    public boolean isModalErrorPresent() {
//        try {
//            Alert alert = driver.switchTo().alert();
//            String alertText = alert.getText();  // считывание текста алерта
//            System.out.println("Сообщение алерта: " + alertText);
//            alert.accept();                      // нажимаем OK
//        } catch (NoAlertPresentException e) {
//            System.out.println("Окно алерта не появилось.");
//        }
//        return false;
//    }

}