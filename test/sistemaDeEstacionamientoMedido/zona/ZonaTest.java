package sistemaDeEstacionamientoMedido.zona;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.puntoDeVenta.PuntoDeVenta;

import static org.mockito.Mockito.*;


class ZonaTest {

	//SUT
	private Zona zona;
	
	//DOC
	private Polygon poligono;
	private Inspector inspector;
	
	@BeforeEach
	void setUp() throws Exception {
		poligono = mock(Polygon.class);
		
		inspector = mock(Inspector.class);
		
		zona = new Zona(poligono, inspector);
	}

	@Test
	void testConstructor() {	
		//Verify
		assertEquals(poligono, zona.getArea());
		assertEquals(inspector, zona.getInspector());
	}
	
	@Test
	void cuandoUnPuntoNoEstaDentroDelAreaDeLaZona_NoContieneElPunto() {
		//DOC
		Point2D puntoNoValido = mock(Point.class);
		
		//Exercise
		when(poligono.contains(puntoNoValido)).thenReturn(false);
		
		//Verify
		assertFalse(zona.contieneUnPunto(puntoNoValido));
	}
	
	@Test
	void cuandoUnPuntoEstaDentroDelAreaDeLaZona_ContieneElPunto() {
		//DOC
		Point2D puntoValido = mock(Point.class);

		//Exercise
		when(poligono.contains(puntoValido)).thenReturn(true);
		
		//Verify
		assertTrue(zona.contieneUnPunto(puntoValido));
	}
	
	@Test 
	void cuandoUnaZonaEsInicializada_NoTienePuntosDeVenta(){
		//Verify
		assertTrue(zona.getPuntosDeVenta().isEmpty());
	}
	
	@Test
	void cuandoSeAñadeUnPuntoDeVentaALaZona_LaCantidadDePuntosDeVentaEs1() {
		//DOC
		PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class);
		
		//Exercise
		zona.añadirPuntoDeVenta(puntoDeVenta);
		
		//Verify
		assertEquals(1, zona.getPuntosDeVenta().size());
		assertTrue(zona.getPuntosDeVenta().contains(puntoDeVenta));
	}

}
