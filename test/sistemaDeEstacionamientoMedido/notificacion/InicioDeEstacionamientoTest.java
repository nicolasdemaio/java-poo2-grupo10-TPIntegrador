package sistemaDeEstacionamientoMedido.notificacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InicioDeEstacionamientoTest {

	private Integer numeroDeCelular;
	private Double saldoRestante;
	private String patente;
	private LocalDateTime horaInicio;
	private InicioDeEstacionamiento inicioDeEstacionamiento;
	
	@BeforeEach
	void setUp() throws Exception {
		numeroDeCelular = 1234;
		saldoRestante = 80d;
		patente = "ABC";
		horaInicio = LocalDateTime.now();
		
		inicioDeEstacionamiento = new InicioDeEstacionamiento(numeroDeCelular, saldoRestante, patente, horaInicio);
	}

	@Test
	void testConstructor() {
		//Verify
		assertEquals(numeroDeCelular, inicioDeEstacionamiento.getNumeroDeCelular());
		assertEquals(saldoRestante, inicioDeEstacionamiento.getSaldoRestante());
		assertEquals(patente, inicioDeEstacionamiento.getPatente());
		assertEquals(horaInicio, inicioDeEstacionamiento.getHoraInicio());
	}
	
	@Test
	void testToString() {
		String textoEsperado = "Telefono: " + numeroDeCelular + 
								"\nSaldo Restante: " + saldoRestante + 
								"\nPatente: " + patente + 
								"\nHora de Inicio: " + horaInicio;
		
		//Verify
		assertEquals(textoEsperado, inicioDeEstacionamiento.toString());
	}

}
