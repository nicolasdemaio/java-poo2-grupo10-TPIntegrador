package sistemaDeEstacionamientoMedido.appMunicipio;

import java.time.LocalDateTime;
import java.util.function.BooleanSupplier;

public class AppDelMunicipio {
	IGestorDeEstacionamientos gestorDeEstacionamientos;
	IGestorDeInfracciones gestorDeInfracciones;
	Zona zona;
	

	public AppDelMunicipio(ServicioAppMunicipio servicio, Zona zona) {
		this.zona = zona;
		this.gestorDeEstacionamientos = servicio.getSectorDeEstacionamientos();
		this.gestorDeInfracciones = servicio.getGestorDeInfracciones();
	}


	public IGestorDeEstacionamientos getGestorDeEstacionamientos() {
		return gestorDeEstacionamientos;
	}


	public IGestorDeInfracciones getGestorDeInfracciones() {
		return gestorDeInfracciones;
	}


	public Zona getZona() {
		return zona;
	}

	/*
	 * Registra una infracción al vehiculo con la patente indicada por parámetro.
	 * Se asume que el inspector a cargo ya comprobó que este vehiculo no tenía en un
	 * estacionamiento en regla.
	 */
	public void registrarInfraccion(String patente, LocalDateTime hora) {
		
		this.getGestorDeInfracciones().registrarInfraccion(new Infraccion (patente,
																	  	   hora,
																	       zona.getInspector(),
																	       zona));
	}


	public Boolean tieneEstacionamientoEnRegla(String patente) {
		
		return this.getGestorDeEstacionamientos().hayEstacionamientoEnRegla(patente);
	}

}
