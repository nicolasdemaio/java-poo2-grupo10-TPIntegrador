package sistemaDeEstacionamientoMedido.appUsuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaminandoTest {

	private EstadoDeMovimiento estadoCaminando;
	private AppUsuario app;
	
	@BeforeEach
	void setUp() throws Exception {
		estadoCaminando = new Caminando();
		app = mock(AppUsuario.class);
	}

	@Test
	void testWalking() {
		//Exercise
		estadoCaminando.walking(app);
		
		//Verify
		verifyNoInteractions(app);
	}
	
	@Test
	void testDriving_cuandoAppTieneDesplazamientoActivadoPeroNoCorrespondeFinalizarEstacionamiento() {
		//Setup - DOC
		when(app.tieneDesplazamientoActivo()).thenReturn(true);
		when(app.correspondeFinalizarEstacionamiento()).thenReturn(false);
		
		//Exercise
		estadoCaminando.driving(app);
		
		//Verify
		verify(app, never()).cambiarEstadoDeMovimiento(any(Manejando.class));
		verify(app, never()).alertarFinEstacionamiento();
	}
	
	@Test
	void testDriving_cuandoAppCorrespondeFinalizarEstacionamientoPeroNoTieneDesplazamientoActivado() {
		//Setup - DOC
		when(app.tieneDesplazamientoActivo()).thenReturn(false);
		when(app.correspondeFinalizarEstacionamiento()).thenReturn(true);
		
		//Exercise
		estadoCaminando.driving(app);
		
		//Verify
		verify(app, never()).cambiarEstadoDeMovimiento(any(Manejando.class));
		verify(app, never()).alertarFinEstacionamiento();
	}
	
	@Test
	void testDriving_cuandoAppTieneDesplazamientoActivadoYCorrespondeFinalizarEstacionamiento() {
		//Setup - DOC
		when(app.tieneDesplazamientoActivo()).thenReturn(true);
		when(app.correspondeFinalizarEstacionamiento()).thenReturn(true);
				
		//Exercise
		estadoCaminando.driving(app);
				
		//Verify
		verify(app, times(1)).cambiarEstadoDeMovimiento(any(Manejando.class));
		verify(app, times(1)).alertarFinEstacionamiento();
	}

}
