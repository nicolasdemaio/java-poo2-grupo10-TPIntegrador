package sistemaDeEstacionamientoMedido.respuesta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;

class RespuestaDeInicioPositivaTest {

	private Estacionamiento estacionamiento;
	private RespuestaDeInicioPositiva respuestaDeInicioPositiva;
	private LocalDateTime horaDeInicio, horaMaxima;
	
	@BeforeEach
	void setUp() throws Exception {
		//DOC
		estacionamiento = mock(Estacionamiento.class);
		
		horaDeInicio = LocalDateTime.of(2020, 12, 1, 10, 00);
		horaMaxima = LocalDateTime.of(2020, 12, 1, 15, 30);
		
		when(estacionamiento.getHoraDeInicio()).thenReturn(horaDeInicio);
		when(estacionamiento.getHoraDeFin()).thenReturn(horaMaxima);
		
		//SUT
		respuestaDeInicioPositiva = new RespuestaDeInicioPositiva(estacionamiento);
	}

	@Test
	void cuandoUnaRespuestaDeInicioPositivaEsInicializada_LaHoraDeInicioYHoraMaximaSonLasDelEstacionamiento() {
		//Verify
		assertEquals(horaDeInicio, respuestaDeInicioPositiva.getHoraDeInicio());
		assertEquals(horaMaxima, respuestaDeInicioPositiva.getHoraMaxima());
	}
	
	@Test
	void testToString() {
		String textoEsperado = "Hora de Inicio: " + this.horaDeInicio.getHour() + 
							   "\nHora Maxima: " + this.horaMaxima.getHour();
		
		//Verify
		assertEquals(textoEsperado, respuestaDeInicioPositiva.toString());
	}

}
