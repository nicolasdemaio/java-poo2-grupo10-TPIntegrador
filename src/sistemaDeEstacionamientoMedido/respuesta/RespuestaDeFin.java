package sistemaDeEstacionamientoMedido.respuesta;

import java.time.LocalDateTime;

public class RespuestaDeFin implements Respuesta {

	private LocalDateTime horaDeInicio, horaDeFin;
	private Integer duracion;
	private Double costo;
	
	public RespuestaDeFin(Estacionamiento estacionamiento) {
		this.horaDeInicio = estacionamiento.getHoraDeInicio();
		this.horaDeFin = estacionamiento.getHoraDeFin();
		this.duracion = estacionamiento.getDuracion();
		this.costo = estacionamiento.getCosto();
	}

	public LocalDateTime getHoraInicio() {
		return this.horaDeInicio;
	}
	
	public LocalDateTime getHoraFin() {
		return this.horaDeFin;
	}
	
	public Integer getDuracion() {
		return this.duracion;
	}
	
	public Double getCosto() {
		return this.costo;
	}
	
	public String toString() {
		return "Hora de Inicio: " + this.getHoraInicio() + 
			   "\nHora de Fin: " + this.getHoraFin() +
		       "\nDuracion (horas): " + this.getDuracion() +
		       "\nCosto: " + this.getCosto();
	}

}
