package sistemaDeEstacionamientoMedido.estacionamiento;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.puntoDeVenta.CompraPuntual;
import sistemaDeEstacionamientoMedido.zona.Zona;

public class EstacionamientoPorCompraPuntual extends Estacionamiento {
	
	private Integer cantidadDeHoras;
	private CompraPuntual compraPuntual;

	public EstacionamientoPorCompraPuntual(String patente, Double costoPorHora, Zona zona,
			Integer cantidadDeHoras, CompraPuntual compraPuntual) {
		super(patente, LocalDateTime.now(), LocalDateTime.now().plusHours(cantidadDeHoras), costoPorHora, zona);
		this.cantidadDeHoras = cantidadDeHoras;
		this.compraPuntual = compraPuntual;
	}

	public Integer getCantidadDeHoras() {
		return cantidadDeHoras;
	}

	public CompraPuntual getCompraPuntual() {
		return compraPuntual;
	}

	@Override
	public Boolean estaVigente() {
		return LocalDateTime.now().isBefore(getHoraDeFin());
	}

	@Override
	public void finalizar() {
		
	}

}
