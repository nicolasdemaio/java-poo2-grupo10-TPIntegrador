package sistemaDeEstacionamientoMedido.suscriptores;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.notificacion.Notificacion;

class GestorDeSuscriptoresTest {

	private GestorDeSuscriptores gestorDeSuscriptores;
	
	@BeforeEach
	void setUp() throws Exception {
		gestorDeSuscriptores = new GestorDeSuscriptores();
	}

	@Test
	void testConstructor() {
		//Verify
		assertTrue(gestorDeSuscriptores.getSuscriptores().isEmpty());
	}
	
	@Test
	void testAgregarSuscriptor() {
		//Setup - DOC
		ISuscriptor suscriptor = mock(ISuscriptor.class);
		
		//Exercise
		gestorDeSuscriptores.agregarSuscriptor(suscriptor);
		
		//Verify
		assertEquals(1, gestorDeSuscriptores.getSuscriptores().size());
		assertTrue(gestorDeSuscriptores.getSuscriptores().contains(suscriptor));
	}
	
	@Test
	void testRemoverSuscriptor() {
		//Setup - DOC
		ISuscriptor suscriptor = mock(ISuscriptor.class);
		
		//Exercise
		gestorDeSuscriptores.agregarSuscriptor(suscriptor);
		gestorDeSuscriptores.removerSuscriptor(suscriptor);
		
		//Verify
		assertEquals(0, gestorDeSuscriptores.getSuscriptores().size());
		assertFalse(gestorDeSuscriptores.getSuscriptores().contains(suscriptor));
	}
	
	@Test
	void cuandoElGestorNotificaALosSuscriptores_SuscriptorRecibeLaNotificacion() {
		//Setup - DOC
		ISuscriptor suscriptor = mock(ISuscriptor.class);
		
		Notificacion notificacion = mock(Notificacion.class);
		
		//Exercise
		gestorDeSuscriptores.agregarSuscriptor(suscriptor);
		gestorDeSuscriptores.notifySuscriptores(notificacion);
		
		//Verify
		verify(suscriptor, atMost(1)).update(notificacion);
	}

}
