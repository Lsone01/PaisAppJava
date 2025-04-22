
package com.paises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paises.modelo.Pais;
import com.paises.servicio.ServicioPais;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static List<Pais> paisesEncontrados = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicioPais servicio = new ServicioPais();
        List<Pais> paisesEncontrados = new ArrayList<>();

        while (true) {
            System.out.print("Introduce el nombre del país (o 'salir' para terminar): ");
            String nombre = scanner.nextLine();
            if (nombre.equalsIgnoreCase("salir")) break;

            List<Pais> resultados = servicio.buscarPais(nombre);
            if (resultados.isEmpty()) {
                System.out.println("❌ País no encontrado.");
            } else {
                for (Pais pais : resultados) {
                    pais.mostrarInformacion();
                    paisesEncontrados.add(pais);
                }
            }
        }

        System.out.println("\n🌍 Consulta finalizada. Total de países consultados: " + paisesEncontrados.size());

        // Guardar el historial en un archivo JSON
        try (FileWriter writer = new FileWriter("historial_pais.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(paisesEncontrados, writer);
            System.out.println("Historial guardado en historial_pais.json");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el historial.");
        }
    }
}
