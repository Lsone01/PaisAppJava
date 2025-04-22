
package com.paises.modelo;

public class PaisDetalle extends Pais {
    private String subregion;
    private String idiomaPrincipal;

    public PaisDetalle(String nombreComun, String capital, String region, long poblacion,
                       String subregion, String idiomaPrincipal) {
        super(nombreComun, capital, region, poblacion);
        this.subregion = subregion;
        this.idiomaPrincipal = idiomaPrincipal;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("🧭 Subregión: " + subregion);
        System.out.println("🗣️ Idioma Principal: " + idiomaPrincipal);
    }
}
