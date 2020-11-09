package sistemaDeEstacionamientoMedido.respuesta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RespuestaDeFinTest {

	private Estacionamiento estacionamiento;
	private RespuestaDeFin respuestaDeFin;
	private Double costo;
	private Integer duracion;
	private LocalDateTime horaDeInicio, horaDeFin;
	
	
	@BeforeEach
	void setUp() throws Exception {
		//DOC
		estacionamiento = mock(Estacionamiento.class);
		
		costo = 80d;
		duracion = 2;
		
		horaDeInicio = LocalDateTime.of(2020, 12, 1, 10, 00);
		horaDeFin = LocalDateTime.of(2020, 12, 1, 15, 30);
		
		when(estacionamiento.getHoraDeFin()).thenReturn(horaDeFin);
		when(estacionamiento.getHoraDeInicio()).thenReturn(horaDeInicio);
		when(estacionamiento.getCosto()).thenReturn(costo);
		when(estacionamiento.getDuracion()).thenReturn(duracion);
		
		//SUT
		respuestaDeFin = new RespuestaDeFin(estacionamiento);
	}

	@Test
	void testConstructor() {
		//Verify
		assertEquals(horaDeInicio, respuestaDeFin.getHoraInicio());
		assertEquals(horaDeFin, respuestaDeFin.getHoraFin());
		assertEquals(costo, respuestaDeFin.getCosto());
		assertEquals(duracion, respuestaDeFin.getDuracion());
	}
	
	@Test
	void testToString() {
		String textoEsperado = "Hora de Inicio: " + this.horaDeInicio + 
							   "\nHora de Fin: " + this.horaDeFin +
							   "\nDuracion (horas): " + this.duracion +
							   "\nCosto: " + this.costo;
		//Verify
		assertEquals(textoEsperado, respuestaDeFin.toString());
	}

}
