package sistemaDeEstacionamientoMedido.respuesta;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;

public class RespuestaDeFin implements Respuesta {

	private LocalDateTime horaDeInicio, horaDeFin;
	private Double costo;
	
	public RespuestaDeFin(Estacionamiento estacionamiento) {
		this.horaDeInicio = estacionamiento.getHoraDeInicio();
		this.horaDeFin = estacionamiento.getHoraDeFin();
		this.costo = estacionamiento.getCosto();
	}

	public LocalDateTime getHoraInicio() {
		return this.horaDeInicio;
	}
	
	public LocalDateTime getHoraFin() {
		return this.horaDeFin;
	}
	
	public Integer getDuracion() {
		return this.getHoraFin().getHour() - this.getHoraInicio().getHour();
	}
	
	public Double getCosto() {
		return this.costo;
	}
	
	public String toString() {
		return "Hora de Inicio: " + this.getHoraInicio().getHour() + 
			   "\nHora de Fin: " + this.getHoraFin().getHour() +
		       "\nDuracion (horas): " + this.getDuracion() +
		       "\nCosto: " + this.getCosto();
	}

}
