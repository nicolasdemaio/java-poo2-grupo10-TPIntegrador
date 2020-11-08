package sistemaDeEstacionamientoMedido.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class LibroContableTestCase {
	LibroContable libro;

	@BeforeEach
	void setUp() throws Exception {
		libro = new LibroContable();
	}

	@Test
	void testCuandoSeCreaUnLibroContablesNoHayComprasRegistradas() {
		assertTrue(libro.getCompras().isEmpty());
	}
	
	@Test
	void testRegistrarCompra() {
		Compra compra1 = mock (Compra.class);
		Compra compra2 = mock (Compra.class);
		
		libro.registrarCompra(compra1);
		assertEquals(1, libro.getCompras().size());
		assertTrue (libro.getCompras().contains(compra1));
		
		libro.registrarCompra(compra2);
		assertEquals(2, libro.getCompras().size());
		assertTrue (libro.getCompras().contains(compra2));
	}

}
