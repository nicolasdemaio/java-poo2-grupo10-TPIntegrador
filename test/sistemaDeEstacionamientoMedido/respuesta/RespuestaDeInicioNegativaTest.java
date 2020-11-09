package sistemaDeEstacionamientoMedido.respuesta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RespuestaDeInicioNegativaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testToString() {
		//Setup
		RespuestaDeInicioNegativa respuestaDeInicioNegativa = new RespuestaDeInicioNegativa();
		
		String textoEsperado = "Saldo insuficiente. Estacionamiento no permitido.";
		
		//Verify
		assertEquals(textoEsperado, respuestaDeInicioNegativa.toString());
	}

}
