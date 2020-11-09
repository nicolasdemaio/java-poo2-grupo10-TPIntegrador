package sistemaDeEstacionamientoMedido.estacionamiento;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.zona.Zona;

public abstract class Estacionamiento {
	
	private String patente;
	private LocalDateTime horaDeInicio;
	private LocalDateTime horaDeFin;
	private Double costoPorHora;
	private Zona zona;
	
	public Estacionamiento(String patente, LocalDateTime horaDeInicio, LocalDateTime horaDeFin, Double costoPorHora, Zona zona) {
		this.patente = patente;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFin = horaDeFin;
		this.costoPorHora = costoPorHora;
		this.zona = zona;
	}

	public String getPatente() {
		return patente;
	}

	public LocalDateTime getHoraDeInicio() {
		return horaDeInicio;
	}
	
	public LocalDateTime getHoraDeFin() {
		return horaDeFin;
	}

	public Double getCostoPorHora() {
		return costoPorHora;
	}

	public Zona getZona() {
		return zona;
	}

	public abstract Boolean estaVigente();
	public abstract void finalizar();
	
}
