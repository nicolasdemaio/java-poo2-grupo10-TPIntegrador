package sistemaDeEstacionamientoMedido.appUsuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManejandoTest {

	private EstadoDeMovimiento estadoManejando;
	private AppUsuario app;
	
	@BeforeEach
	void setUp() throws Exception {
		estadoManejando = new Manejando();
		app = mock(AppUsuario.class);
	}

	@Test
	void testDriving() {
		estadoManejando.driving(app);
		
		//Verify
		verifyNoInteractions(app);
	}
	
	@Test
	void testWalking_cuandoAppTieneDesplazamientoActivoPeroNoLeCorrespondeEstacionar() {
		//Setup - DOC
		when(app.tieneDesplazamientoActivo()).thenReturn(true);
		when(app.correspondeEstacionar()).thenReturn(false);
		
		estadoManejando.walking(app);
		
		//Verify
		verify(app, never()).cambiarEstadoDeMovimiento(any(Caminando.class));
		verify(app, never()).alertarInicioEstacionamiento();
	}
	
	@Test
	void testWalking_cuandoAppLeCorrespondeEstacionarPeroNoTieneDesplazamientoActivado() {
		//Setup - DOC
		when(app.tieneDesplazamientoActivo()).thenReturn(false);
		when(app.correspondeEstacionar()).thenReturn(true);
				
		estadoManejando.walking(app);
				
		//Verify
		verify(app, never()).cambiarEstadoDeMovimiento(any(Caminando.class));
		verify(app, never()).alertarInicioEstacionamiento();
	}
	
	@Test
	void testWalking_cuandoAppTieneDesplazamientoActivadoYLeCorrespondeEstacionar() {
		//Setup - DOC
		when(app.tieneDesplazamientoActivo()).thenReturn(true);
		when(app.correspondeEstacionar()).thenReturn(true);
						
		estadoManejando.walking(app);
						
		//Verify
		verify(app, times(1)).cambiarEstadoDeMovimiento(any(Caminando.class));
		verify(app, times(1)).alertarInicioEstacionamiento();
	}

}
