package sistemaDeEstacionamientoMedido.estacionamiento;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.puntoDeVenta.CompraPuntual;

import sistemaDeEstacionamientoMedido.zona.Zona;


public class EstacionamientoPorCompraPuntual extends Estacionamiento {
	
	private Integer cantidadDeHoras;
	private CompraPuntual compraPuntual;

	public EstacionamientoPorCompraPuntual(String patente, Double costoPorHora, Zona zona,
			CompraPuntual compraPuntual) {
		
		super(patente, LocalDateTime.now(), LocalDateTime.now().plusHours(compraPuntual.getCantidadDeHoras()), costoPorHora, zona);
		this.cantidadDeHoras = compraPuntual.getCantidadDeHoras();
		this.compraPuntual = compraPuntual;
	}

	
	public Integer getCantidadDeHoras() {
		return this.cantidadDeHoras;
	}

	public CompraPuntual getCompraPuntual() {
		return this.compraPuntual;
	}
	
	
	@Override
	public Boolean estaVigente() {
		return LocalDateTime.now().isBefore(getHoraDeFin());
	}

	@Override
	public void finalizar() {
		// Optamos porque sea un metodo vacio puesto que decidimos finalizar los estacionamientos de forma automatica
	}

}
