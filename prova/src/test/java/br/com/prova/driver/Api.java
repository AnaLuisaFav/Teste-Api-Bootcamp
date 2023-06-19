package br.com.prova.driver;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Api {

    static String apiKey = "fcc391982f70cc4d5aafac9d0f17c04e";

    public static String obterCidade(String cidade) {
        String name = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet("http://api.openweathermap.org/geo/1.0/direct?q=" + URLEncoder.encode(cidade, StandardCharsets.UTF_8.toString()) + "&appid=" + apiKey);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONArray jsonArray = new JSONArray(resp);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            name = jsonObject.getString("name");

        } catch (Exception e) {
            System.out.println(e);
        }
        return name;
    }

    public static String obterLatLon(String cidade) {
        String retorno = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet("http://api.openweathermap.org/geo/1.0/direct?q=" + URLEncoder.encode(cidade, StandardCharsets.UTF_8.toString()) + "&appid=" + apiKey);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONArray jsonArray = new JSONArray(resp);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            double lat = jsonObject.getDouble("lat");
            double lon = jsonObject.getDouble("lon");

            retorno = "lat="
                    .concat(String.valueOf(lat))
                    .concat("&lon=")
                    .concat(String.valueOf(lon));

        } catch (Exception e) {
            System.out.println(e);
    }
        return retorno;
    }

    public static String obterApiTempC(String cidade) {
        String retorno = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String uri = "https://api.openweathermap.org/data/2.5/weather?"+obterLatLon(cidade)+"&appid=fcc391982f70cc4d5aafac9d0f17c04e&units=metric";
            HttpGet get = new HttpGet(uri);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(resp);
            JSONObject main = obj.getJSONObject("main");
            retorno = String.valueOf(main.get("temp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return String.valueOf(Math.round(Double.parseDouble(retorno)));
    }

    public static String obterApiTempF(String cidade) {
        String retorno = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String uri = "https://api.openweathermap.org/data/2.5/weather?"+obterLatLon(cidade)+"&appid=fcc391982f70cc4d5aafac9d0f17c04e&units=imperial";
            HttpGet get = new HttpGet(uri);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(resp);
            JSONObject main = obj.getJSONObject("main");
            retorno = String.valueOf(main.get("temp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return String.valueOf(Math.round(Double.parseDouble(retorno)));
    }
}
