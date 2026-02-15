package package_name;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ReadConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        InputStream inputStream = null;
        try {
            inputStream = ReadConfig.class.getResourceAsStream("/constant.properties"); // ищем ресурс относительно classpath
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки файла конфигурации.", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    //извлекаем данные из файла пропертис
    public static String getUrl() {
        return PROPERTIES.getProperty("training.url");
    }

    public static String getUsername() {
        return PROPERTIES.getProperty("training.username");
    }

    public static String getEmail() {
        return PROPERTIES.getProperty("training.email");
    }

    public static String getPassword() {
        return PROPERTIES.getProperty("training.password");
    }

    public static String getConfirmPassword() {
        return PROPERTIES.getProperty("training.cPassword");
    }

    public static String getBirthDayDD() {
        return PROPERTIES.getProperty("training.birthdate.dd");
    }

    public static String getBirthMonthMM() {
        return PROPERTIES.getProperty("training.birthdate.mm");
    }

    public static String getBirthYearYYYY() {
        return PROPERTIES.getProperty("training.birthdate.yyyy");
    }

    public static String getLanguageLevel() {
        return PROPERTIES.getProperty("training.level");
    }

    public static String getEnglishLevel() {
        return PROPERTIES.getProperty("training.levelIngl");
    }
}