package sistemaDeEstacionamientoMedido.sem;

import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeInfracciones;

public interface ServicioAppMunicipio {
	
	public IGestorDeInfracciones getGestorDeInfracciones();
	public IGestorDeEstacionamientos getGestorDeEstacionamientos();
	
}
