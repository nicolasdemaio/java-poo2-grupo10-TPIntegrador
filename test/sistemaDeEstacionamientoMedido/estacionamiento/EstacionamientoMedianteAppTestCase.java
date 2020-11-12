package sistemaDeEstacionamientoMedido.estacionamiento;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import sistemaDeEstacionamientoMedido.zona.Zona;


class EstacionamientoMedianteAppTestCase {
	
	private String patente;
	private LocalDateTime horaDeFin;
	private Double costoPorHora;
	private Integer nroCelular;
	private Zona zona;
	private EstacionamientoMedianteApp estacionamientoMA;
	
	
	@BeforeEach
	void setUp() throws Exception {
		patente = "28LTD24";
		horaDeFin = LocalDateTime.now().plusHours(2);
		costoPorHora = 40d;
		nroCelular = 1113933350;
		zona = mock(Zona.class);
		
		estacionamientoMA = new EstacionamientoMedianteApp (patente, horaDeFin, costoPorHora, zona, nroCelular);
	}
	
	
	@Test
	void testConstructor() {
		assertEquals(patente, estacionamientoMA.getPatente());
		assertEquals(horaDeFin, estacionamientoMA.getHoraDeFin());
		assertEquals(costoPorHora, estacionamientoMA.getCostoPorHora());
		assertEquals(nroCelular, estacionamientoMA.getNroCelular());
		assertEquals(zona, estacionamientoMA.getZona());
		assertEquals(nroCelular, estacionamientoMA.getNroCelular());
	}
	
	
	@Test
	void testEstaVigenteCuandoSeCrea() {
				
		assertTrue(estacionamientoMA.estaVigente());
		
	}
	
	
	@Test
	void testFinalizarCambiaElEstaVigenteAFalse() {
				
		assertTrue(estacionamientoMA.getEstaVigente());
		estacionamientoMA.finalizar();
		assertFalse(estacionamientoMA.getEstaVigente());
		
	}
	
	
	@Test
	void testFinalizarCambiaLaHoraDeFinALaActual() {
		
		LocalDateTime horaEstipulada = LocalDateTime.now().plusHours(2);
		
		/*
		MockedStatic<LocalDateTime> localDateTimeMock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS) {
			localDateTimeMock.when(LocalDateTime::now).thenReturn(horaEstipulada);
		};
		*/
		
		
		/*try (MockedStatic<LocalDateTime> horaFin = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			horaFin.when(LocalDateTime::now).thenReturn(horaEstipulada);
			estacionamientoMA.finalizar();
		    assertEquals(horaEstipulada, estacionamientoMA.getHoraDeFin());
		}*/
		
	}	
		
	@Test
	void testFinalizarUnEstacionaminetoYPedirElCosto() {
			
		LocalDateTime horaEstipulada = LocalDateTime.now().plusHours(2);
		
		
		/*try (MockedStatic<LocalDateTime> horaFin = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			horaFin.when(LocalDateTime::now).thenReturn(horaEstipulada);
			estacionamientoMA.finalizar();
			assertEquals(80d, estacionamientoMA.getCosto());
		}*/
		
	}
		

}
