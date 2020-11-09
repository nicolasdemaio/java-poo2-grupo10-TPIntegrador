package sistemaDeEstacionamientoMedido.sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.appMunicipio.IGestorDeInfracciones;
import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;
import sistemaDeEstacionamientoMedido.suscriptores.ISuscriptor;
import sistemaDeEstacionamientoMedido.zona.IGestorDeZonas;

class SEMTest {

	private IGestorDeZonas gestorDeZonas;
	private IGestorDeEstacionamientos gestorDeEstacionamientos;
	private IGestorDeSuscriptores gestorDeSuscriptores;
	private IGestorDeSaldo gestorDeSaldo;
	private IGestorDeInfracciones gestorDeInfracciones;
	
	@BeforeEach
	void setUp() throws Exception {
		// DOCs
		gestorDeZonas = mock(IGestorDeZonas.class);
		gestorDeEstacionamientos = mock(IGestorDeEstacionamientos.class);
		gestorDeSuscriptores = mock(IGestorDeSuscriptores.class);
		gestorDeSaldo = mock(IGestorDeSaldo.class);
		gestorDeInfracciones = mock(IGestorDeInfracciones.class);
	}

	@Test
	void testConstructor() {
		//Setup
		SEM sistema = new SEM(gestorDeZonas, gestorDeEstacionamientos, gestorDeSuscriptores, gestorDeSaldo, gestorDeInfracciones);
		
		//Verify
		assertEquals(gestorDeZonas, sistema.getGestorDeZonas());
		assertEquals(gestorDeEstacionamientos, sistema.getGestorDeEstacionamientos());
		assertEquals(gestorDeSuscriptores, sistema.getGestorDeSuscriptores());
		assertEquals(gestorDeSaldo, sistema.getGestorDeSaldo());
		assertEquals(gestorDeInfracciones, sistema.getGestorDeInfracciones());
		assertTrue(sistema.getAppsUsuario().isEmpty()); //Se testea que el SEM no contenga appsUsuario
	}
	
	@Test
	void cuandoSeAgregaUnAppUsuarioAlSistema_ElSistemaContieneLaApp() {
		//Setup
		//SUT
		SEM sistema = new SEM(gestorDeZonas, gestorDeEstacionamientos, gestorDeSuscriptores, gestorDeSaldo, gestorDeInfracciones);
		//DOC
		AppUsuario appUsuario = mock(AppUsuario.class);
		
		//Exercise
		sistema.agregarAppUsuario(appUsuario);
		
		//Verify
		assertTrue(sistema.getAppsUsuario().contains(appUsuario));
		assertEquals(1, sistema.getAppsUsuario().size());
	}
	
	@Test
	void testAgregarSuscriptor() {
		//SETUP
		//SUT
		SEM sistema = new SEM(gestorDeZonas, gestorDeEstacionamientos, gestorDeSuscriptores, gestorDeSaldo, gestorDeInfracciones);
		//DOC
		ISuscriptor suscriptor = mock(ISuscriptor.class);
		
		//Exercise
		sistema.agregarSuscriptor(suscriptor);
		
		//Verify
		verify(gestorDeSuscriptores, times(1)).agregarSuscriptor(suscriptor);
	}
	
	@Test
	void testRemoverSuscriptor() {
		//SETUP
		//SUT
		SEM sistema = new SEM(gestorDeZonas, gestorDeEstacionamientos, gestorDeSuscriptores, gestorDeSaldo, gestorDeInfracciones);
		//DOC
		ISuscriptor suscriptor = mock(ISuscriptor.class);
				
		//Exercise
		sistema.removerSuscriptor(suscriptor);
				
		//Verify
		verify(gestorDeSuscriptores, times(1)).removerSuscriptor(suscriptor);
	}
	
	@Test
	void testFinalizarEstacionamientos() {
		//Setup
		//SUT
		SEM sistema = new SEM(gestorDeZonas, gestorDeEstacionamientos, gestorDeSuscriptores, gestorDeSaldo, gestorDeInfracciones);
		//DOC
		AppUsuario unAppUsuario = mock(AppUsuario.class);
		AppUsuario otroAppUsuario = mock(AppUsuario.class);
				
		//Exercise
		sistema.agregarAppUsuario(unAppUsuario);
		sistema.agregarAppUsuario(otroAppUsuario);
		sistema.finalizarEstacionamientos();
				
		//Verify
		verify(unAppUsuario, times(1)).finDeEstacionamiento();
		verify(otroAppUsuario, times(1)).finDeEstacionamiento();
	}

}
