package sistemaDeEstacionamientoMedido.appMunicipio;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.zona.Inspector;
import sistemaDeEstacionamientoMedido.zona.Zona;

public class Infraccion {
    private String patente;
    private LocalDateTime fechaYHora;
    private Inspector inspector;
    private Zona zona;

    public Infraccion(String patente, LocalDateTime fechaYHora, Inspector inspector, Zona zona) {
        this.patente = patente;
        this.fechaYHora = fechaYHora;
        this.inspector = inspector;
        this.zona = zona;
    }

    public String getPatente() {
        return patente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public Zona getZona() {
        return zona;
    }

}