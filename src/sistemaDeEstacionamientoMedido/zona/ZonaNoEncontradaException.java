package sistemaDeEstacionamientoMedido.zona;

/**
 * Error lanzado en caso de que un punto dado no sea contenido por alguna zona del gestor de zonas.
 *
 */
public class ZonaNoEncontradaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ZonaNoEncontradaException(String mensaje) {
		super(mensaje);
	}
	
}
