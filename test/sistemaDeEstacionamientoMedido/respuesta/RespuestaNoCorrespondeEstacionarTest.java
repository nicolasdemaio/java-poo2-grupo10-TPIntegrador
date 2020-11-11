package sistemaDeEstacionamientoMedido.respuesta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RespuestaNoCorrespondeEstacionarTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testToString() {
		//Setup
		RespuestaNoCorrespondeEstacionar respuestaNoCorrespondeEstacionar = new RespuestaNoCorrespondeEstacionar();
		
		String textoEsperado = "No corresponde iniciar el estacionamiento";
		
		//Verify
		assertEquals(textoEsperado, respuestaNoCorrespondeEstacionar.toString());
	}

}
