package sistemaDeEstacionamientoMedido.suscriptores;

import java.util.ArrayList;
import java.util.List;

import sistemaDeEstacionamientoMedido.notificacion.Notificacion;

public class GestorDeSuscriptores implements IGestorDeSuscriptores {

	List<ISuscriptor> suscriptores;
	
	public GestorDeSuscriptores() {
		this.suscriptores = new ArrayList<ISuscriptor>();
	}
	
	public void agregarSuscriptor(ISuscriptor suscriptor) {
		this.getSuscriptores().add(suscriptor);
	}
	
	public void removerSuscriptor(ISuscriptor suscriptor) {
		this.getSuscriptores().remove(suscriptor);
	}
	
	/**
	 * Notifica a cada suscriptor de una notificacion recibida.
	 * @param notificacion
	 */
	public void notifySuscriptores(Notificacion notificacion) {
		this.getSuscriptores().stream().forEach(suscriptor -> suscriptor.update(notificacion));
	}
	
	public List<ISuscriptor> getSuscriptores() {
		return this.suscriptores;
	}

}
