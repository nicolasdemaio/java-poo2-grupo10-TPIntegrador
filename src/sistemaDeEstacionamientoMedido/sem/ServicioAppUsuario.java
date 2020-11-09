package sistemaDeEstacionamientoMedido.sem;

import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.appUsuario.AppUsuario;
import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;
import sistemaDeEstacionamientoMedido.zona.IGestorDeZonas;

public interface ServicioAppUsuario {
	
	public IGestorDeSaldo getGestorDeSaldo();
	
	public IGestorDeEstacionamientos getGestorDeEstacionamientos();
	
	public IGestorDeSuscriptores getGestorDeSuscriptores();
	
	public IGestorDeZonas getGestorDeZonas();
	
	public void agregarAppUsuario(AppUsuario appUsuario);
}
