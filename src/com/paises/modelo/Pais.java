
package com.paises.modelo;

public class Pais {
    protected String nombreComun;
    protected String capital;
    protected String region;
    protected long poblacion;

    public Pais(String nombreComun, String capital, String region, long poblacion) {
        this.nombreComun = nombreComun;
        this.capital = capital;
        this.region = region;
        this.poblacion = poblacion;
    }

    public void mostrarInformacion() {
        System.out.println("\n🗺️ País: " + nombreComun);
        System.out.println("🏛️ Capital: " + capital);
        System.out.println("🌎 Región: " + region);
        System.out.println("👥 Población: " + poblacion);
    }
}
