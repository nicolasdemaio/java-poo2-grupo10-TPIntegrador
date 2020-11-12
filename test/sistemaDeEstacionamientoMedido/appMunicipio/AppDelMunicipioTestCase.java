package sistemaDeEstacionamientoMedido.appMunicipio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.gestorDeEstacionamiento.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.sem.ServicioAppMunicipio;
import sistemaDeEstacionamientoMedido.zona.Inspector;
import sistemaDeEstacionamientoMedido.zona.Zona;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

class AppDelMunicipioTestCase {
	ServicioAppMunicipio servicio;
	Zona zona;
	IGestorDeEstacionamientos gestorDeEstacionamientos;
	IGestorDeInfracciones gestorDeInfracciones;
	
	
	@BeforeEach
	void setUp() throws Exception {
		servicio = mock(ServicioAppMunicipio.class);
		zona = mock(Zona.class);
		//Se prepara al servicio para que cuando se le pida los gestores en el constructor de la app le brinde los
		//test doubles a continuación.
		gestorDeEstacionamientos = mock(IGestorDeEstacionamientos.class);
		gestorDeInfracciones = mock(IGestorDeInfracciones.class);
		when(servicio.getGestorDeInfracciones()).thenReturn(gestorDeInfracciones);
		when(servicio.getGestorDeEstacionamientos()).thenReturn(gestorDeEstacionamientos);
	}

	@Test
	void testConstructor() {
		
		AppDelMunicipio app = new AppDelMunicipio(servicio, zona);

		assertEquals(gestorDeEstacionamientos, app.getGestorDeEstacionamientos());
		assertEquals(gestorDeInfracciones, app.getGestorDeInfracciones());
		assertEquals(zona, app.getZona());
	}
	
	@Test
	void testRegistrarInfraccion() {
		String patente = "PSY-420";
		Inspector inspector = mock(Inspector.class);
		when(zona.getInspector()).thenReturn(inspector);
		AppDelMunicipio app = new AppDelMunicipio(servicio, zona);
		
		app.registrarInfraccion(patente);

		verify(gestorDeInfracciones, times(1)).registrarInfraccion(any(Infraccion.class));
	}

	
	@Test
	void testTieneEstacionamientoEnRegla_CuandoEstaEnRegla() {
		String patente = "PSY-420";
		when(gestorDeEstacionamientos.hayEstacionamientoEnRegla(patente)).thenReturn(true);
		
		AppDelMunicipio app = new AppDelMunicipio(servicio, zona);
		
		assertTrue(app.tieneEstacionamientoEnRegla(patente));
	}
	
	void testTieneEstacionamientoEnRegla_CuandoNoEstaEnRegla() {
		String patente = "PSY-420";
		when(gestorDeEstacionamientos.hayEstacionamientoEnRegla(patente)).thenReturn(false);
		
		AppDelMunicipio app = new AppDelMunicipio(servicio, zona);
		
		assertFalse(app.tieneEstacionamientoEnRegla(patente));
	}

}
