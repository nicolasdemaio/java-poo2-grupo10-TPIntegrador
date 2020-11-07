package sistemaDeEstacionamientoMedido.appMunicipio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class GestorDeInfraccionesTestCase {
	GestorDeInfracciones gestor;

	@BeforeEach
	void setUp() throws Exception {
		gestor = new GestorDeInfracciones();
	}
	
	@Test
	void testConstructor() {
		assertTrue(gestor.getInfracciones().isEmpty());
	}
	
	
	@Test
	void testRegistrarInfraccion() {
		Infraccion unaInfraccion = mock(Infraccion.class);
		Infraccion otraInfraccion = mock(Infraccion.class);
		
		gestor.registrarInfraccion(unaInfraccion);
		assertTrue(gestor.getInfracciones().contains(unaInfraccion));
		assertEquals(1,gestor.getInfracciones().size());
		
		gestor.registrarInfraccion(otraInfraccion);
		assertTrue(gestor.getInfracciones().contains(otraInfraccion));
		assertEquals(2,gestor.getInfracciones().size());
	}
	

}
