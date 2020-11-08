package sistemaDeEstacionamientoMedido.puntoDeVenta;

import java.time.LocalDateTime;

public abstract class Compra {
	private Integer nroDeControl;
	private PuntoDeVenta puntoDeVenta;
	private LocalDateTime fechaYHora;
	
	public Compra (PuntoDeVenta puntoDeVenta, LocalDateTime fechaYHora) {
		this.nroDeControl = puntoDeVenta.getContadorDeCompras();
		this.puntoDeVenta = puntoDeVenta;
		this.fechaYHora = fechaYHora;
	}

	public Integer getNroDeControl() {
		return nroDeControl;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return puntoDeVenta;
	}

	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}
	

}
