package sistemaDeEstacionamientoMedido.appMunicipio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
		when(servicio.getSectorDeEstacionamientos()).thenReturn(gestorDeEstacionamientos);
	}

	@Test
	void testConstructor() {
		
		AppDelMunicipio app = new AppDelMunicipio(servicio, zona);

		assertEquals(gestorDeEstacionamientos, app.getGestorDeEstacionamientos());
		assertEquals(gestorDeInfracciones, app.getGestorDeInfracciones());
		assertEquals(zona, app.getZona());
	}
	
	@Test
	/*
	 * Se quiere verificar que cuando se registre una infracción brindándole la patente de un vehiculo al
	 * sistema, esta sea creada de manera correcta con los datos pertinentes almacenados en la aplicación del 
	 * municipio, siendo estos la zona, el inspector, la patente recibida como argumento, y la hora actual.
	 */
	void testRegistrarInfraccion() {
		String patente = "PSY-420";
		LocalDateTime hora = LocalDateTime.now();
		Inspector inspector = mock(Inspector.class);
		when(zona.getInspector()).thenReturn(inspector);
		AppDelMunicipio app = new AppDelMunicipio(servicio, zona);
		
		app.registrarInfraccion(patente,hora);

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
