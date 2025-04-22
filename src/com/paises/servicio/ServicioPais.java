
package com.paises.servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.paises.modelo.Pais;
import com.paises.modelo.PaisDetalle;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServicioPais {
    private static final String API_URL = "https://restcountries.com/v3.1/all";

    public List<Pais> buscarPais(String nombre) {
        List<Pais> lista = new ArrayList<>();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                String nombreComun = obj.getAsJsonObject("name").get("common").getAsString();

                if (nombreComun.toLowerCase().contains(nombre.toLowerCase())) {
                    String capital = obj.has("capital") ? obj.getAsJsonArray("capital").get(0).getAsString() : "Desconocida";
                    String region = obj.has("region") ? obj.get("region").getAsString() : "Desconocida";
                    long poblacion = obj.has("population") ? obj.get("population").getAsLong() : 0;
                    String subregion = obj.has("subregion") ? obj.get("subregion").getAsString() : "N/A";
                    String idiomaPrincipal = "Desconocido";

                    if (obj.has("languages")) {
                        JsonObject idiomas = obj.getAsJsonObject("languages");
                        idiomaPrincipal = idiomas.entrySet().iterator().next().getValue().getAsString();
                    }

                    PaisDetalle pais = new PaisDetalle(nombreComun, capital, region, poblacion, subregion, idiomaPrincipal);
                    lista.add(pais);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
        }

        return lista;
    }
}
