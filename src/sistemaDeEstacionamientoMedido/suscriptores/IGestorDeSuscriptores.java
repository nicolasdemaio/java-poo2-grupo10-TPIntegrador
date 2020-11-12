package sistemaDeEstacionamientoMedido.suscriptores;

import sistemaDeEstacionamientoMedido.notificacion.Notificacion;

public interface IGestorDeSuscriptores {
	
	void notifySuscriptores(Notificacion notificacion);
	
	void agregarSuscriptor(ISuscriptor suscriptor);
	
	void removerSuscriptor(ISuscriptor suscriptor);
	
}
