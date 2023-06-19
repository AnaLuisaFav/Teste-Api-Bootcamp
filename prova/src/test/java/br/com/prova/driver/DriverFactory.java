package br.com.prova.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverFactory {

    private static final String DRIVER_CHROME = "C:\\Users\\joey\\Downloads\\chromedriver_win32\\chromedriver.exe";
    static WebDriver driver;

    public static void abrirWeather(String url) {
        //remover mensagens de erro do log
        Logger.getLogger("").setLevel(Level.WARNING);
        //remover mensagens de erro do log
        System.setProperty("webdriver.chrome.silentOutput", "true");

        System.setProperty("webdriver.chrome.driver", DRIVER_CHROME);
        driver = new ChromeDriver();
        driver.get(url);
    }

    public static void fecharWeather() {
        driver.quit();
    }
}
