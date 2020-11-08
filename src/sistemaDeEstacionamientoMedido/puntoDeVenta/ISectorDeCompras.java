package sistemaDeEstacionamientoMedido.puntoDeVenta;

import java.util.List;

public interface ISectorDeCompras {
	
	public List <Compra> getCompras();
	
	public void registrarCompra(Compra compra);
}
