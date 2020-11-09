package sistemaDeEstacionamientoMedido.estacionamiento;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.puntoDeVenta.CompraPuntual;
import sistemaDeEstacionamientoMedido.zona.Zona;


class EstacionamientoPorCompraPuntualTestCase {
	Zona zona;
	CompraPuntual compraPuntual;
	EstacionamientoPorCompraPuntual estacionamientoCP;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testConstructor() {
		String patente = "28ULI24";
		Double costoPorHora = 40d;
		Integer cantidadDeHoras = 4;
		//zona = mock(Zona.class);
		//compraPuntual = mock(CompraPuntual.class);
		
		estacionamientoCP = new EstacionamientoPorCompraPuntual (patente, costoPorHora, zona, cantidadDeHoras, compraPuntual);
		
		assertEquals(patente, estacionamientoCP.getPatente());
		assertEquals(costoPorHora, estacionamientoCP.getCostoPorHora());
		assertEquals(zona, estacionamientoCP.getZona());
		assertEquals(cantidadDeHoras, estacionamientoCP.getCantidadDeHoras());
		assertEquals(compraPuntual, estacionamientoCP.getCompraPuntual());
	}
	
	@Test
	void testEstaVigente() {
		String patente = "28ULI24";
		Double costoPorHora = 40d;
		Integer cantidadDeHoras = 4;
		
		estacionamientoCP = new EstacionamientoPorCompraPuntual (patente, costoPorHora, zona, cantidadDeHoras, compraPuntual);
		
		assertTrue(estacionamientoCP.estaVigente());
	}
	
	@Test
	void testNoEstaVigente() {
		String patente = "28ULI24";
		Double costoPorHora = 40d;
		Integer cantidadDeHoras = 0;
		
		estacionamientoCP = new EstacionamientoPorCompraPuntual (patente, costoPorHora, zona, cantidadDeHoras, compraPuntual);
		
		assertFalse(estacionamientoCP.estaVigente());
	}
	
	@Test
	void testFinalizar() {
		
	}
}
