package sistemaDeEstacionamientoMedido.respuesta;

public class RespuestaDeInicioNegativa implements Respuesta {

	public RespuestaDeInicioNegativa() {
		super();
	}
	
	public String toString() {
		return "Saldo insuficiente. Estacionamiento no permitido.";
	}

}
