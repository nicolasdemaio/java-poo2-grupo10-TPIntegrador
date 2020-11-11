package sistemaDeEstacionamientoMedido.appUsuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModoAutomaticoTest {
	
	private Modo modoAutomatico;
	private ModeClient appUser;
	
	@BeforeEach
	void setUp() throws Exception {
		modoAutomatico = new ModoAutomatico();
		appUser = mock(ModeClient.class);
	}

	@Test
	void testAlertarInicioEstacionamiento() {
		
		String textoEsperado = "Se ha iniciado el estacionamiento";
		
		//Verify
		assertEquals(textoEsperado, modoAutomatico.alertarInicioDeEstacionamiento(appUser));
		verify(appUser, times(1)).iniciarEstacionamiento();
	}
	
	@Test
	void testAlertarFinDeEstacionamiento() {
		
		String textoEsperado = "Se ha finalizado el estacionamiento";
		
		//Verify
		assertEquals(textoEsperado, modoAutomatico.alertarFinDeEstacionamiento(appUser));
		verify(appUser, times(1)).finDeEstacionamiento();
	}

}
