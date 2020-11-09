package sistemaDeEstacionamientoMedido.sem;

import java.util.ArrayList;
import java.util.List;

import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeInfracciones;
import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;
import sistemaDeEstacionamientoMedido.suscriptores.ISuscriptor;
import sistemaDeEstacionamientoMedido.zona.IGestorDeZonas;

public class SEM implements ServicioAppMunicipio, ServicioAppUsuario, ServicioPuntoDeVenta {

	private IGestorDeZonas gestorDeZonas;
	private IGestorDeEstacionamientos gestorDeEstacionamientos;
	private IGestorDeSuscriptores gestorDeSuscriptores;
	private IGestorDeSaldo gestorDeSaldo;
	private IGestorDeInfracciones gestorDeInfracciones;
	private List<AppUsuario> appsUsuario;
	
	public SEM(IGestorDeZonas gestorDeZonas, IGestorDeEstacionamientos gestorDeEstacionamientos, IGestorDeSuscriptores gestorDeSuscriptores, IGestorDeSaldo gestorDeSaldo, IGestorDeInfracciones gestorDeInfracciones) 
	{
		this.gestorDeEstacionamientos = gestorDeEstacionamientos;
		this.gestorDeInfracciones = gestorDeInfracciones;
		this.gestorDeSaldo = gestorDeSaldo;
		this.gestorDeSuscriptores = gestorDeSuscriptores;
		this.gestorDeZonas = gestorDeZonas;
		this.appsUsuario = new ArrayList<AppUsuario>();
	}

	public void agregarSuscriptor(ISuscriptor suscriptor) {
		this.getGestorDeSuscriptores().agregarSuscriptor(suscriptor);
	}

	public void removerSuscriptor(ISuscriptor suscriptor) {
		this.getGestorDeSuscriptores().removerSuscriptor(suscriptor);
	}

	/**
	 * Finaliza los estacionamientos vigentes de cada una de las aplicaciones registradas.
	 */
	public void finalizarEstacionamientos() {
		this.getAppsUsuario().stream().forEach(AppUsuario::finDeEstacionamiento);
	}
	
	@Override
	public IGestorDeSaldo getGestorDeSaldo() {
		return this.gestorDeSaldo;
	}

	@Override
	public IGestorDeEstacionamientos getGestorDeEstacionamientos() {
		return this.gestorDeEstacionamientos;
	}

	@Override
	public IGestorDeSuscriptores getGestorDeSuscriptores() {
		return this.gestorDeSuscriptores;
	}

	@Override
	public IGestorDeZonas getGestorDeZonas() {
		return this.gestorDeZonas;
	}

	@Override
	public void agregarAppUsuario(AppUsuario appUsuario) {
		this.getAppsUsuario().add(appUsuario);
	}

	@Override
	public IGestorDeInfracciones getGestorDeInfracciones() {
		return this.gestorDeInfracciones;
	}

	public List<AppUsuario> getAppsUsuario() {
		return this.appsUsuario;
	}

}
