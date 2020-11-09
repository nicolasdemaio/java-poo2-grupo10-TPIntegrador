package sistemaDeEstacionamientoMedido.estacionamiento;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.zona.Zona;

class EstacionamientoMedianteAppTestCase {
	
	Zona zona;
	EstacionamientoMedianteApp estacionamientoMA;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	void testConstructor() {
		String patente = "28ULI24";
		LocalDateTime horaDeFin = LocalDateTime.now().plusHours(2);
		Double costoPorHora = 40d;
		Integer nroCelular = 1113933350;
		//zona = mock(Zona.class);
		
		estacionamientoMA = new EstacionamientoMedianteApp (patente, horaDeFin, costoPorHora, zona, nroCelular);
		
		assertEquals(patente, estacionamientoMA.getPatente());
		assertEquals(horaDeFin, estacionamientoMA.getHoraDeFin());
		assertEquals(costoPorHora, estacionamientoMA.getCostoPorHora());
		assertEquals(zona, estacionamientoMA.getZona());
		assertEquals(nroCelular, estacionamientoMA.getNroCelular());
	}
	
	@Test
	void testEstaVigenteCuandoSeCrea() {
		String patente = "28ULI24";
		LocalDateTime horaDeFin = LocalDateTime.now().plusHours(2);
		Double costoPorHora = 40d;
		Integer nroCelular = 1113933350;
		//zona = mock(Zona.class);
		
		estacionamientoMA = new EstacionamientoMedianteApp (patente, horaDeFin, costoPorHora, zona, nroCelular);
		
		assertTrue(estacionamientoMA.estaVigente());
	}
	
	@Test
	void testFinalizarCambiaElEstaVigenteAFalse() {
		String patente = "28ULI24";
		LocalDateTime horaDeFin = LocalDateTime.now().plusHours(4);
		Double costoPorHora = 40d;
		Integer nroCelular = 1113933350;
		//zona = mock(Zona.class);
		
		estacionamientoMA = new EstacionamientoMedianteApp (patente, horaDeFin, costoPorHora, zona, nroCelular);
		
		assertTrue(estacionamientoMA.getEstaVigente());
		estacionamientoMA.finalizar();
		assertFalse(estacionamientoMA.getEstaVigente());
	}

}
