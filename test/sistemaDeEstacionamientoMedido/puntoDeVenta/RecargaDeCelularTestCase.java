package sistemaDeEstacionamientoMedido.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
class RecargaDeCelularTestCase {
	RecargaDeCelular recarga;
	PuntoDeVenta puntoDeVenta;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConstructor() {
		LocalDateTime horaDeVenta= LocalDateTime.now();
		Double monto = 200d;
		Integer nroCelular = 42223420;
		Integer nroDeControl = 1;
		puntoDeVenta = mock (PuntoDeVenta.class);
		when (puntoDeVenta.getContadorDeCompras()).thenReturn(nroDeControl);
		
		recarga = new RecargaDeCelular (puntoDeVenta, horaDeVenta, monto, nroCelular);
		
		assertEquals(puntoDeVenta, recarga.getPuntoDeVenta());
		assertEquals(horaDeVenta, recarga.getFechaYHora());
		assertEquals(nroDeControl, recarga.getNroDeControl());
		assertEquals(monto, recarga.getMonto());
		assertEquals(nroCelular, recarga.getNumeroDeCelular());
	}

}
