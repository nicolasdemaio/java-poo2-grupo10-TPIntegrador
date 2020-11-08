package sistemaDeEstacionamientoMedido.notificacion;

public abstract class Notificacion {

	protected Integer numeroDeCelular;
	
	public Notificacion(Integer numeroDeCelular) {
		this.numeroDeCelular = numeroDeCelular;
	}
	
	public Integer getNumeroDeCelular() {
		return this.numeroDeCelular;
	}
	
	public abstract String toString();

}
