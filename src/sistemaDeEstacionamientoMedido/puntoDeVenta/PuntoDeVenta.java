package sistemaDeEstacionamientoMedido.puntoDeVenta;

import java.time.LocalDateTime;
import java.util.List;

import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.estacionamiento.EstacionamientoPorCompraPuntual;
import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.sem.ServicioPuntoDeVenta;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;
import sistemaDeEstacionamientoMedido.zona.Zona;
import sistemaDeEstacionamientoMedido.notificacion.CompraDeSaldo;

public class PuntoDeVenta {
	
	private Integer contadorDeCompras;
	private IGestorDeSuscriptores gestorDeSuscriptores;
	private IGestorDeSaldo gestorDeSaldo;
	private IGestorDeEstacionamientos gestorDeEstacionamientos;
	private Zona zona;
	private ISectorDeCompras sectorDeCompras;


	public PuntoDeVenta(ServicioPuntoDeVenta servicio, Zona zona, ISectorDeCompras sectorDeCompras) {
		this.contadorDeCompras = 0;
		this.gestorDeSuscriptores = servicio.getGestorDeSuscriptores();
		this.gestorDeSaldo = servicio.getGestorDeSaldo();
		this.gestorDeEstacionamientos = servicio.getGestorDeEstacionamientos();
		this.zona = zona;
		this.sectorDeCompras = sectorDeCompras;
	}


	public Integer getContadorDeCompras() {
		return contadorDeCompras;
	}


	public IGestorDeSuscriptores getGestorDeSuscriptores() {
		return gestorDeSuscriptores;
	}


	public IGestorDeSaldo getGestorDeSaldo() {
		return gestorDeSaldo;
	}


	public IGestorDeEstacionamientos getGestorDeEstacionamientos() {
		return gestorDeEstacionamientos;
	}


	public Zona getZona() {
		return zona;
	}


	public ISectorDeCompras getSectorDeCompras() {
		return sectorDeCompras;
	}


	public void cargarCredito(Integer numeroDeCelular, Double monto) {
		
		this.getGestorDeSaldo().añadirSaldo(numeroDeCelular, monto);
		this.gestorDeSuscriptores.notifySuscriptores(new CompraDeSaldo(numeroDeCelular, monto));
		this.sectorDeCompras.registrarCompra(new RecargaDeCelular(this, LocalDateTime.now(), monto, numeroDeCelular));
		this.contadorDeCompras ++;
	}


	public List <Compra> getCompras() {
		 
		return this.getSectorDeCompras().getCompras();
	}


	public void registrarEstacionamiento(String patente, Integer cantidadDeHoras) {
		CompraPuntual compraGenerada = new CompraPuntual (this, LocalDateTime.now(), cantidadDeHoras);
		this.sectorDeCompras.registrarCompra(compraGenerada);
		this.contadorDeCompras ++;
		this.gestorDeEstacionamientos.añadirEstacionamiento(new EstacionamientoPorCompraPuntual(patente, 40d, cantidadDeHoras, compraGenerada ));
		
	}

}
