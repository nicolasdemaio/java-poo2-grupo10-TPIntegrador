package sistemaDeEstacionamientoMedido.notificacion;

import java.time.LocalDateTime;

public class FinDeEstacionamiento extends EventoEstacionamiento {

	private LocalDateTime horaFin;
	
	public FinDeEstacionamiento(Integer numeroDeCelular, Double saldoRestante, String patente, LocalDateTime horaFin) {
		super(numeroDeCelular, saldoRestante, patente);
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		return "Telefono: " + this.getNumeroDeCelular() +
				"\nSaldo Restante: " + this.getSaldoRestante() +
				"\nPatente: " + this.getPatente() +
				"\nHora de Fin: " + this.getHoraFin();
	}

	public LocalDateTime getHoraFin() {
		return this.horaFin;
	}

}
