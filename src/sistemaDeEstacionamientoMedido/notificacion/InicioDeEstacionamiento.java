package sistemaDeEstacionamientoMedido.notificacion;

import java.time.LocalDateTime;

public class InicioDeEstacionamiento extends EventoEstacionamiento {

	private LocalDateTime horaInicio;
	
	public InicioDeEstacionamiento(Integer numeroDeCelular, Double saldoRestante, String patente, LocalDateTime horaInicio) {
		super(numeroDeCelular, saldoRestante, patente);
		this.horaInicio = horaInicio;
	}

	public LocalDateTime getHoraInicio() {
		return this.horaInicio;
	}
	
	@Override
	public String toString() {
		return "Telefono: " + this.getNumeroDeCelular() +
				"\nSaldo Restante: " + this.getSaldoRestante() +
				"\nPatente: " + this.getPatente() +
				"\nHora de Inicio: " + this.getHoraInicio();
	}
	
}
