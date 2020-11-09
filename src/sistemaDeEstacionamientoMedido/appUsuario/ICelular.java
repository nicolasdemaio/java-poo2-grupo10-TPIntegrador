package sistemaDeEstacionamientoMedido.appUsuario;

import java.awt.geom.Point2D;
import java.time.LocalDateTime;

/*
 * Esta interfaz representa a las funcionalidades provistas por el celular. Se asume que el teléfono celular puede conocer
 * su posición a través de su GPS.
 */
public interface ICelular {
	
	public LocalDateTime getFechaYHoraActual();
	
	public Point2D getPosicion();
	
	public Integer getNumeroDeCelular();
}
