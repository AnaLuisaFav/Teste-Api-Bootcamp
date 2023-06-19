package br.com.prova.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Browser extends DriverFactory {

    public static boolean elementoExiste(By element) {
        return driver.findElements(element) != null;
    }
    public static void digitar(By element, String valor) {
        driver.findElement(element).sendKeys(valor);
    }
    public static void clicar(By element) {
        driver.findElement(element).click();
    }
    public static void aguardar(Integer segundos) {
        try {
            Thread.sleep(segundos*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static String obterSiteTemp() {
        WebElement elemento = driver.findElement(By.xpath("//*[@id=\"weather-widget\"]/div[2]/div[1]/div[1]/div[2]/div[1]/span"));
        return elemento.getText();
    }
    public static String obterSiteCidade() {
        WebElement elemento = driver.findElement(By.xpath("//*[@id=\"weather-widget\"]/div[2]/div[1]/div[1]/div[1]/h2"));
        String[] fragmento = elemento.getText().split(",");
        return fragmento[0];
    }
    public static void fecharNavegador() {
        driver.quit();
        System.out.println("Navegador fechado");
    }

}
