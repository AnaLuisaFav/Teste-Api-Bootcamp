package br.com.prova;

import br.com.prova.driver.Api;
import br.com.prova.driver.Browser;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class provaTest {
    String cidade = "Recife";

    @BeforeAll
    static void inicio() throws InterruptedException {
        Browser.abrirWeather("https://openweathermap.org/");
        Browser.aguardar(5);
    }

    @Test
    @Order(1)
    public void validarSite() {
        boolean validate = Browser.elementoExiste(By.xpath("//*[@id=\"footer-website\"]/div/div[2]/div[3]/div/ul/li[3]/a"));
        assertTrue(validate);
    }

    @Test
    @Order(2)
    void pesquisar() {
        Browser.digitar(By.xpath("//input[@placeholder='Search city']"), cidade);
        Browser.clicar(By.xpath("//button[@class=\"button-round dark\"]"));
        assertTrue(Browser.elementoExiste(By.xpath("//input[@placeholder='Search city']")));
        Browser.aguardar(2);
        Browser.clicar(By.xpath("//div[@id=\"weather-widget\"]//ul/li"));
        Browser.aguardar(2);
    }

    @Test
    @Order(3)
    void validarCidade() {
        String valorSiteCidade = Browser.obterSiteCidade();
        String valorApiCidade = Api.obterCidade(cidade);
        System.out.println("Retorno do nome da cidade pela API: " + valorApiCidade);
        System.out.println("Nome da cidade obtido do site: " + valorSiteCidade);
        assertEquals(valorSiteCidade,valorApiCidade);
        System.out.printf("Validado que o site e a API apresentam o mesmo nome de cidade: " + valorSiteCidade + "\n\n");
    }

   @Test
   @Order(4)
    void validarCelsius() {
       String valorSiteC = Browser.obterSiteTemp().replace("°C", "");
       String valorApiC = Api.obterApiTempC(cidade);
       System.out.println("Retorno de valor da API em ºC: " + valorApiC);
       System.out.println("Temperatura em ºC obtida do site: " + valorSiteC);
       assertEquals(valorSiteC,valorApiC);
       System.out.printf("Validado que o site e a API apresentam a mesma temperatura em ºC: " + valorApiC + "\n\n");
   }

    @Test
    @Order(5)
    void validarFahrenheit() {
        Browser.clicar(By.xpath("//*[@id=\"weather-widget\"]/div[1]/div/div/div[1]/div[2]/div[3]"));
        Browser.aguardar(2);
        String valorSiteF = Browser.obterSiteTemp().replace("°F", "");
        String valorApiF = Api.obterApiTempF(cidade);
        System.out.println("Retorno de valor da API em ºF: " + valorApiF);
        System.out.println("Temperatura em ºF obtida do site: " + valorSiteF);
        assertEquals(valorSiteF,valorApiF);
        System.out.printf("Validado que o site e a API apresentam a mesma temperatura em ºF: " + valorApiF + "\n\n");
    }

    @AfterAll
    static void fim() {
        Browser.fecharNavegador();
    }
}

