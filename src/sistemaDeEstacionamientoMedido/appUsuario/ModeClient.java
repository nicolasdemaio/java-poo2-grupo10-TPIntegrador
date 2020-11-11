package sistemaDeEstacionamientoMedido.appUsuario;

import sistemaDeEstacionamientoMedido.respuesta.Respuesta;

public interface ModeClient {
	
	public Respuesta iniciarEstacionamiento();
	
	public Respuesta finDeEstacionamiento ();
}
