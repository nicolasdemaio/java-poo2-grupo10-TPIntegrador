package sistemaDeEstacionamientoMedido.sem;

import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;

public interface ServicioPuntoDeVenta {
	
	public IGestorDeSaldo getGestorDeSaldo();
	public IGestorDeEstacionamientos getGestorDeEstacionamientos();
	public IGestorDeSuscriptores getGestorDeSuscriptores();
	
}
