package sistemaDeEstacionamientoMedido.notificacion;

public abstract class EventoEstacionamiento extends Notificacion {

	protected Double saldoRestante;
	protected String patente;
	
	public EventoEstacionamiento(Integer numeroDeCelular, Double saldoRestante, String patente) {
		super(numeroDeCelular);
		this.saldoRestante = saldoRestante;
		this.patente = patente;
	}
	
	public Double getSaldoRestante() {
		return this.saldoRestante;
	}

	public String getPatente() {
		return this.patente;
	}
	
}
