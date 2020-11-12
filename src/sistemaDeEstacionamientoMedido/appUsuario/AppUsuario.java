package sistemaDeEstacionamientoMedido.appUsuario;

import java.awt.geom.Point2D;
import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.estacionamiento.*;
import sistemaDeEstacionamientoMedido.gestorDeEstacionamiento.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.notificacion.*;
import sistemaDeEstacionamientoMedido.respuesta.Respuesta;
import sistemaDeEstacionamientoMedido.respuesta.*;
import sistemaDeEstacionamientoMedido.sem.ServicioAppUsuario;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;
import sistemaDeEstacionamientoMedido.zona.IGestorDeZonas;
import sistemaDeEstacionamientoMedido.zona.Zona;

public class AppUsuario implements MovementSensor, ModeClient{
	
	private String patente;
	private Point2D ultimoPuntoDeEstacionamiento; // Se inicia en la ubicación actual del celular.
	private Boolean tieneDesplazamientoActivo;
	private Modo modo;
	private EstadoDeMovimiento estadoDeMovimiento;
	private IGestorDeEstacionamientos gestorDeEstacionamientos;
	private IGestorDeSaldo gestorDeSaldo;
	private IGestorDeSuscriptores gestorDeSuscriptores;
	private IGestorDeZonas gestorDeZonas;
	private ICelular celular;
	
	/**
	 * Se instancia un objeto de la clase AppUsuario y ServicioAppUsuario registra la aplicacion creada.
	 * @param servicio
	 * @param patente
	 * @param celular
	 * @param modo
	 * @param estadoDeMovimiento
	 */
	public AppUsuario(ServicioAppUsuario servicio, String patente, 
			          ICelular celular, Modo modo, EstadoDeMovimiento estadoDeMovimiento) {
		this.patente = patente;
		this.ultimoPuntoDeEstacionamiento = celular.getPosicion();
		this.tieneDesplazamientoActivo = false;
		this.modo = modo;
		this.estadoDeMovimiento = estadoDeMovimiento;
		this.gestorDeEstacionamientos = servicio.getGestorDeEstacionamientos();
		this.gestorDeSaldo = servicio.getGestorDeSaldo();
		this.gestorDeSuscriptores = servicio.getGestorDeSuscriptores();
		this.gestorDeZonas = servicio.getGestorDeZonas();
		this.celular = celular;
		
		servicio.agregarAppUsuario(this);
	}
	

	public void activarDeteccionDesplazamiento() {
		this.tieneDesplazamientoActivo = true;
	}
	
	public void desactivarDeteccionDesplazamiento() {
		this.tieneDesplazamientoActivo = false;
	}
	
	/**
	 * Cambia el modo de la aplicación. Se puede elegir entre ModoAutomatico y ModoManual.
	 * @param unModo  
	 */
	public void cambiarModo(Modo unModo) {
		
		this.modo = unModo;
		
	}
	/**
	 * Describe el saldo en el sistema  del celular al que esta vinculado la aplicación.
	 * @return Double saldo
	 */
	public Double consultarSaldo() {
		
		return this.getGestorDeSaldo().getSaldo(celular.getNumeroDeCelular());
		
	}
	
	private Boolean hayEstacionamientoVigente() {
		
		return this.getGestorDeEstacionamientos().hayEstacionamientoVigente(this.getPatente());
		
	}
	
	public void cambiarEstadoDeMovimiento(EstadoDeMovimiento unEstadoDeMovimiento) {
		
		this.estadoDeMovimiento = unEstadoDeMovimiento;
		
	}
	
	public Respuesta iniciarEstacionamiento() {
		if (this.correspondeEstacionar()) {
			
			return iniciarEstacionamientoSiDisponeDeSaldoSuficiente();
			
		} else {
			
			return new RespuestaNoCorrespondeEstacionar();
			
		}
	}

	private Respuesta iniciarEstacionamientoSiDisponeDeSaldoSuficiente() {
		
		if (this.haySaldoSuficiente()) {
			
			LocalDateTime horaDeFin = this.getGestorDeEstacionamientos().horaMaximaDeFin(this.consultarSaldo());
			Zona zona = this.getGestorDeZonas().indicarZona(this.getCelular().getPosicion());
			Double costo = this.getGestorDeEstacionamientos().getCostoPorHora();
			Integer nroCelular = this.getNumeroCelular();
			
			EstacionamientoMedianteApp estacionamiento = 
					new EstacionamientoMedianteApp(this.getPatente(),horaDeFin,costo,zona,nroCelular);
			
			this.ultimoPuntoDeEstacionamiento = this.getCelular().getPosicion();
			this.getGestorDeEstacionamientos().añadirEstacionamiento(estacionamiento, nroCelular);
			this.notificarSuscriptoresDeInicio();
			
			return new RespuestaDeInicioPositiva(estacionamiento);
			
		} else {
			
			return new RespuestaDeInicioNegativa();
			
		}
	}

	private void notificarSuscriptoresDeInicio() {
		this.getGestorDeSuscriptores().notifySuscriptores(new InicioDeEstacionamiento(this.getNumeroCelular(), 
				                                                                      this.consultarSaldo(), 
				                                                                      this.getPatente(),
				                                                                      LocalDateTime.now()));
	}
	
	
	public Respuesta finDeEstacionamiento () {
		
		if (correspondeFinalizarEstacionamiento()) {
			
			Estacionamiento estacionamientoFinalizado = this.getGestorDeEstacionamientos().
					                                    finalizarVigenciaDeEstacionamiento(this.getNumeroCelular());
			this.facturarEstacionamiento(estacionamientoFinalizado);
			this.notificarSuscriptoresDeFin(estacionamientoFinalizado);

			return new RespuestaDeFin(estacionamientoFinalizado);
			
		} else {
			
			return new RespuestaNoCorrespondeFinalizar();
			
		}
		
	}
	
	public String alertarInicioEstacionamiento() {
		
		return this.getModo().alertarInicioDeEstacionamiento(this);
		
	}
	
	public String alertarFinEstacionamiento() {
		
		return this.getModo().alertarFinDeEstacionamiento(this);
		
	}
	
	
	@Override
	public void driving() {
		
		this.getEstadoDeMovimiento().driving(this);
	}

	@Override
	public void walking() {
		
		this.getEstadoDeMovimiento().walking(this);
	}
	
	
	private void notificarSuscriptoresDeFin(Estacionamiento estacionamientoFinalizado) {
		
		this.getGestorDeSuscriptores().notifySuscriptores(new FinDeEstacionamiento(this.getNumeroCelular(), 
				                                                                   this.consultarSaldo(),
				                                                                   this.getPatente(),
				                                                                   estacionamientoFinalizado.getHoraDeFin()));
	}

	private void facturarEstacionamiento(Estacionamiento estacionamientoFinalizado) {
		
		this.getGestorDeSaldo().disminuirSaldo(this.getNumeroCelular(), estacionamientoFinalizado.getCosto());
		
	}

	protected boolean correspondeFinalizarEstacionamiento() {
		
		return esUltimaPosicionDeEstacionamiento() && this.hayEstacionamientoVigente();
		
	}

	
	private boolean esUltimaPosicionDeEstacionamiento() {
		
		return this.getCelular().getPosicion().equals(this.getUltimoPuntoDeEstacionamiento());
		
	}
	
	
	private boolean haySaldoSuficiente() {
		return this.getGestorDeEstacionamientos().esSaldoSuficiente(this.consultarSaldo());
	}

	protected boolean correspondeEstacionar() {
		return estaEnZonaDelSistema() && !this.hayEstacionamientoVigente() && esHorarioDeEstacionamiento();
	}

	private boolean esHorarioDeEstacionamiento() {
		return this.getGestorDeEstacionamientos().esHorarioDeEstacionamiento(celular.getFechaYHoraActual());
	}

	private Boolean estaEnZonaDelSistema() {
		return this.getGestorDeZonas().perteneceAUnaZona(this.getCelular().getPosicion());
	}

	
	private Integer getNumeroCelular() {
		return this.getCelular().getNumeroDeCelular();
	}
	
	
	public String getPatente() {
		return patente;
	}

	public Point2D getUltimoPuntoDeEstacionamiento() {
		return ultimoPuntoDeEstacionamiento;
	}

	public Boolean tieneDesplazamientoActivo() {
		return tieneDesplazamientoActivo;
	}
	

	public Modo getModo() {
		return modo;
	}

	public EstadoDeMovimiento getEstadoDeMovimiento() {
		return estadoDeMovimiento;
	}

	public IGestorDeEstacionamientos getGestorDeEstacionamientos() {
		return gestorDeEstacionamientos;
	}

	public IGestorDeSaldo getGestorDeSaldo() {
		return gestorDeSaldo;
	}

	public IGestorDeSuscriptores getGestorDeSuscriptores() {
		return gestorDeSuscriptores;
	}

	public IGestorDeZonas getGestorDeZonas() {
		return gestorDeZonas;
	}

	public ICelular getCelular() {
		return celular;
	}

}
