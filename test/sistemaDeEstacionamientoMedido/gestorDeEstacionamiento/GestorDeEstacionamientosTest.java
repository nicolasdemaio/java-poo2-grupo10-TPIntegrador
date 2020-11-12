package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import org.mockito.Mockito;
//import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;




class GestorDeEstacionamientosTest {
	
	private LocalDateTime inicioDeHorarioDeEstacionamiento;
	private LocalDateTime finDeHorarioDeEstacionamiento;
	private Double costoPorHoraDeEstacionamiento;
	private IRegistroCelularPatente registroCelularPatente;
	private GestorDeEstacionamientos gestorDeEstacionamientos;
	private Integer nroCelular;
	private Estacionamiento estacionamiento;
	private String patente;
	
	@BeforeEach
	void setUp() throws Exception {
		
		inicioDeHorarioDeEstacionamiento = LocalDateTime.now().withHour(7);
		finDeHorarioDeEstacionamiento = inicioDeHorarioDeEstacionamiento.withHour(20);
		costoPorHoraDeEstacionamiento = 40d;
		registroCelularPatente = mock(IRegistroCelularPatente.class);
		gestorDeEstacionamientos = new GestorDeEstacionamientos(inicioDeHorarioDeEstacionamiento, 
										finDeHorarioDeEstacionamiento, costoPorHoraDeEstacionamiento, registroCelularPatente);
		
		nroCelular = 1149247785;
		estacionamiento = mock(Estacionamiento.class);
		patente = "42TLD82";
		when(estacionamiento.getPatente()).thenReturn(patente);
		when(registroCelularPatente.getPatente(nroCelular)).thenReturn(patente);
		
	}
	
	
	@Test
	void testConstructor() {
		
		assertEquals(inicioDeHorarioDeEstacionamiento, gestorDeEstacionamientos.getHoraInicioEstacionamiento());
		assertEquals(finDeHorarioDeEstacionamiento, gestorDeEstacionamientos.getHoraFinEstacionamiento());
		assertEquals(costoPorHoraDeEstacionamiento, gestorDeEstacionamientos.getCostoPorHora());
		assertEquals(registroCelularPatente, gestorDeEstacionamientos.getRegistroCelularPatente());
		assertTrue(gestorDeEstacionamientos.getEstacionamientos().isEmpty());
		
	}
	
	
	@Test
	void testAñadirEstacionamientoYElRegistroAsociaElCelularYPatenteDeEstacionamiento() {
				
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		verify(registroCelularPatente, times(1)).asociar(nroCelular, patente);
		
	}
	
	
	@Test
	void testAñadirEstacionamientoYLaListaDejaDeSerVacia() {
		
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		assertFalse(gestorDeEstacionamientos.getEstacionamientos().isEmpty());
		
	}
	
	
	@Test
	void testFinalizarVigenciaDeEstacionamientoPidePatenteYLoFinaliza() {
		
		
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		when(registroCelularPatente.getPatente(nroCelular)).thenReturn(patente);
		when(estacionamiento.estaVigente()).thenReturn(true);
		
		
		gestorDeEstacionamientos.finalizarVigenciaDeEstacionamiento(nroCelular);
		
		verify(estacionamiento,times(1)).finalizar();
		
	}
	
	
	@Test
	void testHayEstacionamientoVigente() {
		
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		when(estacionamiento.estaVigente()).thenReturn(true);
		
		assertTrue(gestorDeEstacionamientos.hayEstacionamientoVigente(patente));
		
	}
	
	@Test
	void testHayEstacionamientoVigenteCuandoNoHayUnoVigente() {
		
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		when(estacionamiento.estaVigente()).thenReturn(false);
		
		assertFalse(gestorDeEstacionamientos.hayEstacionamientoVigente(patente));
		
	}
	
	@Test
	void testHayEstacionamientoVigenteCuandoNingunoPoseeLaPatenteIndicada() {
		
		String patenteIncorrecta = "tmm294";
		
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		when(estacionamiento.estaVigente()).thenReturn(true);
		
		assertFalse(gestorDeEstacionamientos.hayEstacionamientoVigente(patenteIncorrecta));
		
	}
	
	@Test
	void testHayEstacionamientoEnRegla() {
		
		LocalDateTime horaValida = LocalDateTime.now().plusHours(1);
		
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(estacionamiento.getHoraDeFin()).thenReturn(horaValida);
		
		assertTrue(gestorDeEstacionamientos.hayEstacionamientoEnRegla(patente));
		
	}
	
	@Test
	void testHayEstacionamientoEnReglaCuandoNoHay() {
		LocalDateTime horaInvalida = LocalDateTime.now().minusHours(1);
		
		gestorDeEstacionamientos.añadirEstacionamiento(estacionamiento, nroCelular);
		
		when(estacionamiento.estaVigente()).thenReturn(true);
		when(estacionamiento.getHoraDeFin()).thenReturn(horaInvalida);
		
		assertFalse(gestorDeEstacionamientos.hayEstacionamientoEnRegla(patente));
	}
	
	@Test
	void testEsHorarioDeEstacionamientoCuandoElHorarioEstaDentroDelHorarioEstipulado() {
		
		LocalDateTime horaDentroDelRango = inicioDeHorarioDeEstacionamiento.plusHours(2);
		assertTrue(gestorDeEstacionamientos.esHorarioDeEstacionamiento(horaDentroDelRango));
		
	}
	
	@Test
	void testEsHorarioDeEstacionamientoCuandoElHorarioEsSuperiorALaHoraDeFin() {
		
		LocalDateTime horaFueraDelRango = finDeHorarioDeEstacionamiento.plusHours(2);
		assertFalse(gestorDeEstacionamientos.esHorarioDeEstacionamiento(horaFueraDelRango));
		
	}
	
	@Test
	void testEsHorarioDeEstacionamientoCuandoElHorarioEsInferiorALaHoraInicial() {
		
		LocalDateTime horaFueraDelRango = inicioDeHorarioDeEstacionamiento.minusHours(2);
		assertFalse(gestorDeEstacionamientos.esHorarioDeEstacionamiento(horaFueraDelRango));
		
	}
	
	
	@Test
	void testEsSaldoSuficiente() {
		
		Double saldoSufuciente = 80d;
		assertTrue(gestorDeEstacionamientos.esSaldoSuficiente(saldoSufuciente));
		
		Double saldoInsuficiente = 20d;
		assertFalse(gestorDeEstacionamientos.esSaldoSuficiente(saldoInsuficiente));
		
	}
	
	/*
	@Test
	void testHoraMaximaDeFin() {
		
		Double saldoUsuario = 80d;
		
		LocalDateTime horaEstipulada = LocalDateTime.now().withHour(12);
		
		try (MockedStatic<LocalDateTime> horaFin = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			horaFin.when(LocalDateTime::now).thenReturn(horaEstipulada);
			assertEquals(horaEstipulada.plusHours(2), gestorDeEstacionamientos.horaMaximaDeFin(saldoUsuario));
		}
		
	}
	*/
	
	@Test
	void testHoraMaximaDeFinCuandoElSaldoAlcanzaParaEStacionarUnaCantidadDeHorasMayorAlhorarioDeFinDeSistemaLaHoraDeFinEsEstaUltima() {
		
		Double saldoUsuario = 1000000d;
		
		assertEquals(finDeHorarioDeEstacionamiento.getHour(), gestorDeEstacionamientos.horaMaximaDeFin(saldoUsuario).getHour());
		
	}
}
