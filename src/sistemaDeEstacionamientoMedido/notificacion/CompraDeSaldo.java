package sistemaDeEstacionamientoMedido.notificacion;

public class CompraDeSaldo extends Notificacion {

	private Double saldoCargado;
	
	public CompraDeSaldo(Integer numeroDeCelular, Double saldoCargado) {
		super(numeroDeCelular);
		this.saldoCargado = saldoCargado;
	}

	public Double getSaldoCargado() {
		return this.saldoCargado;
	}

	@Override
	public String toString() {
		return "Telefono: " + this.getNumeroDeCelular() + 
				"\nSaldo Cargado: " + this.getSaldoCargado();
	}

}
