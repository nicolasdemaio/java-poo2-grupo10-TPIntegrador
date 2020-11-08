package sistemaDeEstacionamientoMedido.puntoDeVenta;

import java.time.LocalDateTime;

public class RecargaDeCelular extends Compra {
	
	private Double monto;
	private Integer numeroDeCelular;
	
	public RecargaDeCelular (PuntoDeVenta puntoDeVenta, LocalDateTime fechaYHora, Double monto, Integer numeroDeCelular) {
		super(puntoDeVenta, fechaYHora);
		this.monto = monto;
		this.numeroDeCelular = numeroDeCelular;
	}

	public Double getMonto() {
		return monto;
	}

	public Integer getNumeroDeCelular() {
		return numeroDeCelular;
	}
}
