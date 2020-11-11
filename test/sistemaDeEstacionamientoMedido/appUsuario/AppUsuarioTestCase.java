package sistemaDeEstacionamientoMedido.appUsuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.appMunicipio.Infraccion;
import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;
import sistemaDeEstacionamientoMedido.estacionamiento.EstacionamientoMedianteApp;
import sistemaDeEstacionamientoMedido.gestorDeEstacionamiento.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.notificacion.Notificacion;
import sistemaDeEstacionamientoMedido.sem.ServicioAppUsuario;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;
import sistemaDeEstacionamientoMedido.zona.IGestorDeZonas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.awt.geom.Point2D;
import java.time.LocalDateTime;

class AppUsuarioTestCase {
	private AppUsuario app;
	private ServicioAppUsuario servicio;
	private String patente;
	private ICelular celular;
	private IGestorDeSuscriptores gestorDeSuscriptores;
	private IGestorDeSaldo gestorDeSaldo;
	private IGestorDeEstacionamientos gestorDeEstacionamientos;
	private IGestorDeZonas gestorDeZonas;
	private Modo modo;
	private EstadoDeMovimiento estadoDeMovimiento;
	private Point2D ubicacionActual;

	@BeforeEach
	void setUp() throws Exception {
		//DOC
		servicio = mock(ServicioAppUsuario.class);
		patente = "540-FOR";
		celular = mock(ICelular.class);
		gestorDeSuscriptores = mock(IGestorDeSuscriptores.class);
		gestorDeSaldo = mock (IGestorDeSaldo.class);
		gestorDeEstacionamientos = mock (IGestorDeEstacionamientos.class);
		gestorDeZonas = mock (IGestorDeZonas.class);
		modo = mock(Modo.class);
		estadoDeMovimiento = mock(EstadoDeMovimiento.class);
		ubicacionActual = mock(Point2D.class);
		
		when(celular.getPosicion()).thenReturn(ubicacionActual);
		when(servicio.getGestorDeEstacionamientos()).thenReturn(gestorDeEstacionamientos);
		when(servicio.getGestorDeSaldo()).thenReturn(gestorDeSaldo);
		when(servicio.getGestorDeSuscriptores()).thenReturn(gestorDeSuscriptores);
		when(servicio.getGestorDeZonas()).thenReturn(gestorDeZonas);
		
		//SUT
		app = new AppUsuario(servicio, patente, celular, modo, estadoDeMovimiento);
	}
	
	@Test
	void testConstructor() {
		assertEquals(patente, app.getPatente());
		assertEquals(celular, app.getCelular());
		assertEquals(modo, app.getModo());
		assertEquals(estadoDeMovimiento, app.getEstadoDeMovimiento());
		assertEquals(gestorDeSuscriptores, app.getGestorDeSuscriptores());
		assertEquals(gestorDeSaldo, app.getGestorDeSaldo());
		assertEquals(gestorDeEstacionamientos, app.getGestorDeEstacionamientos());
		assertEquals(gestorDeZonas, app.getGestorDeZonas());
		assertEquals(ubicacionActual, app.getUltimoPuntoDeEstacionamiento());
		assertFalse(app.tieneDesplazamientoActivo());
	}
	
	@Test 
	void testActivarDeteccionDesplazamiento () {
		
		app.activarDeteccionDesplazamiento();
		
		assertTrue (app.tieneDesplazamientoActivo());
	}
	
	@Test
	void testDesactivarDeteccionDesplazamiento() {
		
		app.activarDeteccionDesplazamiento();
		app.desactivarDeteccionDesplazamiento();
		
		assertFalse(app.tieneDesplazamientoActivo());	
	}
	
	@Test
	void testCambiarModo() {
		//DOC
		Modo modo2 = mock(Modo.class);
		
		//Exercise
		app.cambiarModo(modo2);
		
		//Verify
		assertEquals(modo2, app.getModo());
	}
	
	@Test
	void testConsultarSaldo() {
		Integer numeroDeCelular= 42234234;
		Double saldo = 2d;
		when(celular.getNumeroDeCelular()).thenReturn(numeroDeCelular);
		when (gestorDeSaldo.getSaldo(numeroDeCelular)).thenReturn(saldo);
		
		assertEquals(saldo , app.consultarSaldo());
	}
	
	@Test
	void testCambiarEstadoDeMovimiento() {
		EstadoDeMovimiento estadoDeMovimiento2 = mock(EstadoDeMovimiento.class);
		
		app.cambiarEstadoDeMovimiento(estadoDeMovimiento2);
		
		assertEquals(estadoDeMovimiento2, app.getEstadoDeMovimiento());
	}
	
	@Test
	void testAlertarInicioEstacionamiento() {
		String respuestaEsperada = "Esta respuesta varía dependiendo del modo";
		when(modo.alertarInicioDeEstacionamiento(app)).thenReturn(respuestaEsperada);
		
		String alerta = app.alertarInicioEstacionamiento();
		
		verify(modo, times(1)).alertarInicioDeEstacionamiento(app);
		assertEquals(respuestaEsperada, alerta);
	}
	
	@Test
	void testAlertarFinEstacionamiento() {
		String respuestaEsperada = "Esta respuesta varía dependiendo del modo";
		when(modo.alertarFinDeEstacionamiento(app)).thenReturn(respuestaEsperada);
		
		String alerta = app.alertarFinEstacionamiento();
		
		verify(modo, times(1)).alertarFinDeEstacionamiento(app);
		assertEquals(respuestaEsperada, alerta);
	}
	
	@Test
	void testDriving() {
		
		app.driving();
		
		verify(estadoDeMovimiento, times(1)).driving(app);
	}
	
	@Test
	void testWalking() {
		
		app.walking();
		
		verify(estadoDeMovimiento, times(1)).walking(app);
		
	}
	
	@Test
	void testCuandoNoSeEncuentraEnUnaZonaDelSistemaAlIniciarEstacionamiento_SeRecibeUnaRespuestaQueNoCorrespondeEstacionar() {
		//SETUP
		
		//El punto no pertenece a la zona.
		Point2D unPunto = mock(Point2D.class);
		when (celular.getPosicion()).thenReturn(unPunto);
		when (gestorDeZonas.perteneceAUnaZona(unPunto)).thenReturn(false);
		
		//EXERCISE
		
		String respuesta = app.iniciarEstacionamiento().toString();
		String respuestaEsperada = "No corresponde iniciar el estacionamiento";
		
		//VERIFY
		
		assertEquals(respuestaEsperada, respuesta);
		assertEquals(ubicacionActual, app.getUltimoPuntoDeEstacionamiento());
		verify(gestorDeEstacionamientos, never()).añadirEstacionamiento(any(EstacionamientoMedianteApp.class), any(Integer.class));
		verify(gestorDeSuscriptores, never()).notifySuscriptores(any(Notificacion.class));
	}
	
	@Test
	void testCuandoHayEstacionamientoVigenteAlIniciarEstacionamiento_SeRecibeUnaRespuestaQueNoCorrespondeEstacionar() {
		//SETUP
		
		//El punto  pertenece a la zona.
		Point2D unPunto = mock(Point2D.class);
		when (celular.getPosicion()).thenReturn(unPunto);
		when (gestorDeZonas.perteneceAUnaZona(unPunto)).thenReturn(true);
		//Hay estacionamiento Vigente
		when (gestorDeEstacionamientos.hayEstacionamientoVigente(patente)).thenReturn(true);
		
		//EXERCISE
		
		String respuesta = app.iniciarEstacionamiento().toString();
		String respuestaEsperada = "No corresponde iniciar el estacionamiento";
		
		//VERIFY
		
		assertEquals(respuestaEsperada, respuesta);
		assertEquals(ubicacionActual, app.getUltimoPuntoDeEstacionamiento());
		verify(gestorDeEstacionamientos, never()).añadirEstacionamiento(any(EstacionamientoMedianteApp.class), any(Integer.class));
		verify(gestorDeSuscriptores, never()).notifySuscriptores(any(Notificacion.class));
	}
	

	@Test
	void testCuandoNoEsHorarioDeEstacionamientoAlIniciarEstacionamiento_SeRecibeUnaRespuestaQueNoCorrespondeEstacionar() {
		//SETUP
		
		//El punto  pertenece a la zona.
		Point2D unPunto = mock(Point2D.class);
		when (celular.getPosicion()).thenReturn(unPunto);
		when (gestorDeZonas.perteneceAUnaZona(unPunto)).thenReturn(true);
		//No Hay estacionamiento Vigente
		when (gestorDeEstacionamientos.hayEstacionamientoVigente(patente)).thenReturn(false);
		//No es Horario de estacionamiento
		LocalDateTime horaActual = LocalDateTime.now();
		when (celular.getFechaYHoraActual()).thenReturn(horaActual);
		when (gestorDeEstacionamientos.esHorarioDeEstacionamiento(horaActual)).thenReturn(false);
		
		//EXERCISE
		
		String respuesta = app.iniciarEstacionamiento().toString();
		String respuestaEsperada = "No corresponde iniciar el estacionamiento";
		
		//VERIFY
		
		assertEquals(respuestaEsperada, respuesta);
		assertEquals(ubicacionActual, app.getUltimoPuntoDeEstacionamiento());
		verify(gestorDeEstacionamientos, never()).añadirEstacionamiento(any(EstacionamientoMedianteApp.class), any(Integer.class));
		verify(gestorDeSuscriptores, never()).notifySuscriptores(any(Notificacion.class));
	}
	
	@Test
	void testCuandoNoHaySaldoSuficienteAlIniciarEstacionamiento_SeRecibeUnaRespuestaQueNoHaySaldoSuficiente() {
		//SETUP
		
		//El punto  pertenece a la zona.
		Point2D unPunto = mock(Point2D.class);
		when (celular.getPosicion()).thenReturn(unPunto);
		when (gestorDeZonas.perteneceAUnaZona(unPunto)).thenReturn(true);
		//No Hay estacionamiento Vigente
		when (gestorDeEstacionamientos.hayEstacionamientoVigente(patente)).thenReturn(false);
		//Es Horario de estacionamiento
		LocalDateTime horaActual = LocalDateTime.now();
		when (celular.getFechaYHoraActual()).thenReturn(horaActual);
		when (gestorDeEstacionamientos.esHorarioDeEstacionamiento(horaActual)).thenReturn(true);
		//No hay Saldo
		Integer nroCelu = 42214560;
		Double mockSaldo = 25d;
		when (celular.getNumeroDeCelular()).thenReturn(nroCelu);
		when (gestorDeSaldo.getSaldo(nroCelu)).thenReturn(mockSaldo);
		when (gestorDeEstacionamientos.esSaldoSuficiente(mockSaldo)).thenReturn(false);
		
		//EXERCISE
	
		String respuesta = app.iniciarEstacionamiento().toString();
		String respuestaEsperada = "Saldo insuficiente. Estacionamiento no permitido.";
		
		//VERIFY
		
		assertEquals(respuestaEsperada, respuesta);
		assertEquals(ubicacionActual, app.getUltimoPuntoDeEstacionamiento());
		verify(gestorDeEstacionamientos, never()).añadirEstacionamiento(any(EstacionamientoMedianteApp.class), any(Integer.class));
		verify(gestorDeSuscriptores, never()).notifySuscriptores(any(Notificacion.class));
	}
	
	@Test
	void testIniciarEstacionamientoCuandoEstanDadasLasCondiciones() {
		//SETUP
		
		//El punto  pertenece a la zona.
		Point2D unPunto = mock(Point2D.class);
		when (celular.getPosicion()).thenReturn(unPunto);
		when (gestorDeZonas.perteneceAUnaZona(unPunto)).thenReturn(true);
		//No Hay estacionamiento Vigente
		when (gestorDeEstacionamientos.hayEstacionamientoVigente(patente)).thenReturn(false);
		//Es Horario de estacionamiento
		LocalDateTime horaActual = LocalDateTime.now();
		when (celular.getFechaYHoraActual()).thenReturn(horaActual);
		when (gestorDeEstacionamientos.esHorarioDeEstacionamiento(horaActual)).thenReturn(true);
		//Hay Saldo
		Integer nroCelu = 42214560;
		Double mockSaldo = 25d;
		when (celular.getNumeroDeCelular()).thenReturn(nroCelu);
		when (gestorDeSaldo.getSaldo(nroCelu)).thenReturn(mockSaldo);
		when (gestorDeEstacionamientos.esSaldoSuficiente(mockSaldo)).thenReturn(true);
		//Se mockea la hora maxima
		LocalDateTime horaMaxima = LocalDateTime.now().plusHours(3);
		when (gestorDeEstacionamientos.horaMaximaDeFin(mockSaldo)).thenReturn(horaMaxima);
		
		//EXERCISE
		
		String respuesta = app.iniciarEstacionamiento().toString();
		String respuestaEsperada = "Hora de Inicio: " + horaActual.getHour() +
				                   "\nHora Maxima: " + horaMaxima.getHour();
		//VERIFY
		
		assertEquals(respuestaEsperada, respuesta);
		assertEquals(unPunto, app.getUltimoPuntoDeEstacionamiento());
		verify(gestorDeEstacionamientos, times(1)).añadirEstacionamiento(any(EstacionamientoMedianteApp.class), any(Integer.class));
		verify(gestorDeSuscriptores, times(1)).notifySuscriptores(any(Notificacion.class));
	}
	
	@Test
	void testCuandoNoSeEstaEnLaUltimaPosicionDeEstacionamientoYSeFinalizaEstacionamiento_SeRecibeUnaRespuestaQueNoCorrespondeFinalizar() {
		//Se cambia la posicion actual
		Point2D otroPunto = mock(Point2D.class);
		when(celular.getPosicion()).thenReturn(otroPunto);
		
		String respuesta = app.finDeEstacionamiento().toString();
		String respuestaEsperada = "No corresponde finalizar el estacionamiento";
		
		assertEquals (respuesta, respuestaEsperada);
		verify (gestorDeEstacionamientos, never()).finalizarVigenciaDeEstacionamiento(any(Integer.class));
		verify (gestorDeSaldo, never()).disminuirSaldo(any(Integer.class), any(Double.class));
		verify (gestorDeSuscriptores, never()).notifySuscriptores(any(Notificacion.class));
		
	}
	
	@Test
	void testCuandoNoHayEstacionamientoVigenteYSeFinalizaEstacionamiento_SeRecibeUnaRespuestaQueNoCorrespondeFinalizar() {
		//No hay estacionamiento Vigente.
		when(gestorDeEstacionamientos.hayEstacionamientoVigente(patente)).thenReturn(false);
		
		String respuesta = app.finDeEstacionamiento().toString();
		String respuestaEsperada = "No corresponde finalizar el estacionamiento";
	
		
		assertEquals (respuesta, respuestaEsperada);
		verify (gestorDeEstacionamientos, times(0)).finalizarVigenciaDeEstacionamiento(any(Integer.class));
		verify (gestorDeSaldo, times(0)).disminuirSaldo(any(Integer.class), any(Double.class));
		verify (gestorDeSuscriptores, times(0)).notifySuscriptores(any(Notificacion.class));
	}
	
	@Test
	void testFinDeEstacionamientoCuandoEstanDadasLasCondiciones() {
		//SETUP
		//Esta en el mismo punto, y hay estacionamiento vigente
		when(gestorDeEstacionamientos.hayEstacionamientoVigente(patente)).thenReturn(true);

		Integer nroCelular = 42243021;
		when(celular.getNumeroDeCelular()).thenReturn(nroCelular);
		//Se mockea el estacionamiento finalizado
		Estacionamiento estacionamiento = mock(Estacionamiento.class);
		LocalDateTime horaDeInicio = LocalDateTime.now();
		LocalDateTime horaFin = horaDeInicio.plusHours(2);
		Double costoEstacionamiento = 80d;
		when(estacionamiento.getCosto()).thenReturn(costoEstacionamiento);
		when(estacionamiento.getHoraDeInicio()).thenReturn(horaDeInicio);
		when(estacionamiento.getHoraDeInicio()).thenReturn(horaFin);
		when(gestorDeEstacionamientos.finalizarVigenciaDeEstacionamiento(nroCelular)).thenReturn(estacionamiento);

		//EXERCISE
		
		String respuesta = app.finDeEstacionamiento().toString();
		String respuestaEsperada = "Hora de Inicio: " + horaDeInicio.getHour() +
		                           "\nHora de Fin: " + horaFin.getHour() +
		                           "\nDuracion (horas): " + 2 +
		                           "\nCosto: " + costoEstacionamiento;
				
		//VERIFY 
		
		verify (gestorDeEstacionamientos, times(1)).finalizarVigenciaDeEstacionamiento(nroCelular);
		verify (gestorDeSaldo, times(1)).disminuirSaldo(nroCelular, costoEstacionamiento);
		verify (gestorDeSuscriptores, times(1)).notifySuscriptores(any(Notificacion.class));
		assertEquals(respuestaEsperada,respuesta);
		
	}

	

}
