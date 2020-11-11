package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistroCelularPatenteTest {
	
	private RegistroCelularPatente registro;
	
	@BeforeEach
	void setUp() throws Exception {
		registro = new RegistroCelularPatente();
	}
	
	@Test
	void testConstructorIniciaVacioElRegistro() {
			
		assertTrue(registro.getRegistroCelularPatente().isEmpty());;
		
	}
	
	@Test
	void testAsociarAUnNuevoRegistroDejaDeSerNullElRegistro() {
		Integer nroCelular = 1130339314;
		String patente = "24LDT28";
		registro.asociar(nroCelular, patente);
		assertFalse(registro.getRegistroCelularPatente().isEmpty());
		
	}
	
	@Test
	void testGetPatenteConCelularTeLoDevuelve() {
		Integer nroCelular = 1130339314;
		String patente = "24LDT28";
		registro.asociar(nroCelular, patente);
		assertEquals(patente, registro.getPatente(nroCelular));
	}

}
