package sistemaDeEstacionamientoMedido.estacionamiento;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import sistemaDeEstacionamientoMedido.puntoDeVenta.CompraPuntual;
import sistemaDeEstacionamientoMedido.zona.Zona;


class EstacionamientoPorCompraPuntualTestCase {
	private String patente;
	private Double costoPorHora;
	private Integer cantidadDeHoras;
	private Zona zona;
	private CompraPuntual compraPuntual;
	private EstacionamientoPorCompraPuntual estacionamientoCP;
	
	@BeforeEach
	void setUp() throws Exception {
		
		patente = "28LTD24";
		costoPorHora = 40d;
		cantidadDeHoras = 2;
		zona = mock(Zona.class);
		compraPuntual = mock(CompraPuntual.class);

		when(compraPuntual.getCantidadDeHoras()).thenReturn(cantidadDeHoras);
		
		estacionamientoCP = new EstacionamientoPorCompraPuntual (patente, costoPorHora, zona, compraPuntual);
		
	}

	@Test
	void testConstructor() {
				
		assertEquals(patente, estacionamientoCP.getPatente());
		assertEquals(costoPorHora, estacionamientoCP.getCostoPorHora());
		assertEquals(zona, estacionamientoCP.getZona());
		assertEquals(cantidadDeHoras, estacionamientoCP.getCantidadDeHoras());
		assertEquals(compraPuntual, estacionamientoCP.getCompraPuntual());
	}
	
	@Test
	void testEstaVigenteCuandoSeCrea() {
				
		assertTrue(estacionamientoCP.estaVigente());
	}
	
	@Test
	void testNoEstaVigenteCuandoSePasaDeLaCantidadDeHoras() {
				
		LocalDateTime horaEstipulada = LocalDateTime.now().plusHours(4);
		
		try (MockedStatic<LocalDateTime> hora = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			hora.when(LocalDateTime::now).thenReturn(horaEstipulada);
			estacionamientoCP.finalizar();
			assertFalse(estacionamientoCP.estaVigente());
		}
		
	}
	
	@Test
	void testFinalizar() {
		
	}
}
