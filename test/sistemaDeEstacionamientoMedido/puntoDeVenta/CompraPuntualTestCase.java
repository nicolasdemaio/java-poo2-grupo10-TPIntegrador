package sistemaDeEstacionamientoMedido.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class CompraPuntualTestCase {
	PuntoDeVenta puntoDeVenta;
	CompraPuntual compra;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testConstructor() {
		LocalDateTime horaDeVenta= LocalDateTime.now();
		Integer cantidadDeHoras = 2;
		Integer nroDeControl = 1;
		puntoDeVenta = mock (PuntoDeVenta.class);
		when (puntoDeVenta.getContadorDeCompras()).thenReturn(nroDeControl);
		
		compra = new CompraPuntual (puntoDeVenta, horaDeVenta, cantidadDeHoras);
		
		assertEquals(puntoDeVenta, compra.getPuntoDeVenta());
		assertEquals(horaDeVenta, compra.getFechaYHora());
		assertEquals(nroDeControl, compra.getNroDeControl());
		assertEquals(cantidadDeHoras, compra.getCantidadDeHoras());
	}

}
