package sistemaDeEstacionamientoMedido.puntoDeVenta;

import java.util.ArrayList;
import java.util.List;

public class LibroContable {
	private List <Compra> compras;
	
	public LibroContable () {
		this.compras = new ArrayList <Compra>();
	}

	public List <Compra> getCompras() {
		return this.compras;
	}

	public void registrarCompra(Compra compra) {
		
		this.getCompras().add(compra);
	}
	
	
}
