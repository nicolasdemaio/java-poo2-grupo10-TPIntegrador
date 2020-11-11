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
		return this.patente;
	}

	public LocalDateTime getHoraDeInicio() {
		return this.horaDeInicio;
	}
	
	public LocalDateTime getHoraDeFin() {
		return this.horaDeFin;
	}

	public Double getCostoPorHora() {
		return this.costoPorHora;
	}

	public Zona getZona() {
		return this.zona;
	}
	
	protected void setHoraDeFinWithNow() {
		this.horaDeFin = LocalDateTime.now();
	}
	
	public Double getCosto() {
		return (this.getDuracionDeEstacionamiento() * this.getCostoPorHora());
	}

	private int getDuracionDeEstacionamiento() {
		return this.getHoraDeFin().getHour() - this.getHoraDeInicio().getHour();
	}
	
	
	public abstract Boolean estaVigente();
	public abstract void finalizar();
	
}
