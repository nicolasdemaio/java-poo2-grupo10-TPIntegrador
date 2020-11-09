package sistemaDeEstacionamientoMedido.sem;

import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeInfracciones;

public interface ServicioAppMunicipio {
	
	public IGestorDeInfracciones getGestorDeInfracciones();
	
	public IGestorDeEstacionamientos getSectorDeEstacionamientos();
}
