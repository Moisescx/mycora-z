package Models;

public class Coleccion {
    private String[] tipoHongos = {"Basidiomycota", "Ascomycota", "Zygomycota", "Chytridiomycota"};

    public String[] getTipoHongos() {
        return tipoHongos;
    }

    public String obtenerDescripcionHongo(String tipoHongo) {
        switch (tipoHongo) {
            case "Basidiomycota":
                return "Los basidiomicetos (Basidiomycota) son hongos que producen basidios con basidiosporas. Incluyen setas, hongos comestibles, tóxicos y alucinógenos.";
            case "Ascomycota":
                return "Los ascomicetos (Ascomycota) son hongos que producen ascosporas. Incluyen trufas, levaduras y mohos importantes en fermentación y medicina.";
            case "Zygomycota":
                return "Zygomycota incluye hongos que forman zigosporas. Ejemplo: moho negro del pan.";
            case "Chytridiomycota":
                return "Chytridiomycota, o quítridos, son hongos acuáticos que se reproducen mediante zoosporas.";
            default:
                return "Tipo de hongo no encontrado.";
        }
    }

    public String obtenerLocalizacionHongo(String tipoHongo) {
        switch (tipoHongo) {
            case "Basidiomycota":
                return "Chile, Europa, Asia";
            case "Ascomycota":
                return "Chile, Estados Unidos, Europa";
            case "Zygomycota":
                return "Chile, Asia";
            case "Chytridiomycota":
                return "Aguas de Chile y otros lugares acuáticos";
            default:
                return "Localización no disponible.";
        }
    }

    public String obtenerEstadoHongo(String tipoHongo) {
        switch (tipoHongo) {
            case "Basidiomycota":
                return "Comestible o venenoso";
            case "Ascomycota":
                return "Comestible";
            case "Zygomycota":
                return "Ninguno";
            case "Chytridiomycota":
                return "Ninguno";
            default:
                return "Estado no disponible.";
        }
    }
}
