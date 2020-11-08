package sistemaDeEstacionamientoMedido.puntoDeVenta;

import java.time.LocalDateTime;

public class CompraPuntual extends Compra{
	private Integer cantidadDeHoras;
	
	public CompraPuntual (PuntoDeVenta puntoDeVenta, LocalDateTime fechaYHora, Integer cantidadDeHoras) {
		super(puntoDeVenta,fechaYHora);
		this.cantidadDeHoras = cantidadDeHoras;
	}

	public Integer getCantidadDeHoras() {
		return cantidadDeHoras;
	}

}
