package sistemaDeEstacionamientoMedido.respuesta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RespuestaNoCorrespondeFinalizarTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testToString() {
		//Setup
		RespuestaNoCorrespondeFinalizar respuestaNoCorrespondeFinalizar = new RespuestaNoCorrespondeFinalizar();
		
		String textoEsperado = "No corresponde finalizar el estacionamiento";
		
		//Verify
		assertEquals(textoEsperado, respuestaNoCorrespondeFinalizar.toString());
	}

}
