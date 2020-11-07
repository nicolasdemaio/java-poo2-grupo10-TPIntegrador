package sistemaDeEstacionamientoMedido.gestorDeSaldo;

public class ActualizacionDeSaldoException extends RuntimeException{
	
	public ActualizacionDeSaldoException (String mensajeError, Throwable causa) {
		super(mensajeError,causa);
	}
}
