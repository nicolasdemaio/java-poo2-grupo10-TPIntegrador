package sistemaDeEstacionamientoMedido.appUsuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModoManualTest {

	private Modo modoManual;
	private ModeClient appUser;
	
	@BeforeEach
	void setUp() throws Exception {
		modoManual = new ModoManual();
		appUser = mock(ModeClient.class);
	}

	@Test
	void testAlertarInicioEstacionamiento() {
		String textoEsperado = "Se deberia realizar el inicio de estacionamiento";
		
		//Verify
		assertEquals(textoEsperado, modoManual.alertarInicioDeEstacionamiento(appUser));
	}
	
	@Test
	void testAlertarFinEstacionamiento() {
		String textoEsperado = "Se deberia realizar el fin de estacionamiento";
		
		//Verify
		assertEquals(textoEsperado, modoManual.alertarFinDeEstacionamiento(appUser));
	}

}
