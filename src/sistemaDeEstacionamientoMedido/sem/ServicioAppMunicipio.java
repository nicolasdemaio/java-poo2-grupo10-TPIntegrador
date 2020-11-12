package sistemaDeEstacionamientoMedido.sem;

import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeInfracciones;
import sistemaDeEstacionamientoMedido.gestorDeEstacionamiento.IGestorDeEstacionamientos;

public interface ServicioAppMunicipio {
	
	public IGestorDeInfracciones getGestorDeInfracciones();
	
	public IGestorDeEstacionamientos getGestorDeEstacionamientos();
	
}
