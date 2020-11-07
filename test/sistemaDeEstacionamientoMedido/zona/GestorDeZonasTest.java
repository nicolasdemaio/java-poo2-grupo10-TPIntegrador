package sistemaDeEstacionamientoMedido.zona;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Point;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestorDeZonasTest {

	private GestorDeZonas gestorDeZonas;
	
	@BeforeEach
	void setUp() throws Exception {
		gestorDeZonas = new GestorDeZonas();
	}

	@Test
	void cuandoUnGestorDeZonasEsInicializado_NoTieneNingunaZona() {
		//Verify
		assertTrue(gestorDeZonas.getZonas().isEmpty());
	}
	
	@Test
	void cuandoSeAñadeUnaZonaAUnGestorDeZonas_LaCantidadDeZonasAumentaEn1() {
		//Setup - DOC
		Zona zona = mock(Zona.class);
		
		//Exercise
		gestorDeZonas.añadirZona(zona);
		
		//verify
		assertEquals(1, gestorDeZonas.getZonas().size());
		assertTrue(gestorDeZonas.getZonas().contains(zona));
	}
	
	@Test
	void cuandoUnPuntoNoEsContenidoPorNingunaZonaDelGestor() {
		//Setup - DOC
		Point puntoNoValido = mock(Point.class);
		
		//Verify
		assertFalse(gestorDeZonas.perteneceAUnaZona(puntoNoValido));
	}
	
	@Test
	void cuandoAlgunaZonaDelGestorDeZonasContieneUnPuntoDado() {
		//Setup - DOC
		Point puntoValido = mock(Point.class);
		Zona zona = mock(Zona.class);
		
		when(zona.contieneUnPunto(puntoValido)).thenReturn(true);
		
		//Exercise
		gestorDeZonas.añadirZona(zona);
		
		//Verify
		assertTrue(gestorDeZonas.perteneceAUnaZona(puntoValido));
	}
	
	@Test
	void cuandoUnaZonaContieneUnPuntoDado_IndicarZonaRetornaEsaZona() {
		//Setup
		Point puntoValido = mock(Point.class);
		Zona zona = mock(Zona.class);
		
		when(zona.contieneUnPunto(puntoValido)).thenReturn(true);
		
		//Exercise
		gestorDeZonas.añadirZona(zona);
		
		//Verify
		assertEquals(zona, gestorDeZonas.indicarZona(puntoValido));
	}
	
	// TODO: Fijarse si hacer que indicar zona lance excepcion si el punto no es contenido

}
