package sistemaDeEstacionamientoMedido.notificacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinDeEstacionamientoTest {

	private Integer numeroDeCelular;
	private Double saldoRestante;
	private String patente;
	private LocalDateTime horaFin;
	private FinDeEstacionamiento finDeEstacionamiento;
	
	@BeforeEach
	void setUp() throws Exception {
		numeroDeCelular = 1234;
		saldoRestante = 200d;
		patente = "ADD";
		horaFin = LocalDateTime.now();
		finDeEstacionamiento = new FinDeEstacionamiento(numeroDeCelular, saldoRestante, patente, horaFin);
	}

	@Test
	void testConstructor() {
		//Verify
		assertEquals(numeroDeCelular, finDeEstacionamiento.getNumeroDeCelular());
		assertEquals(saldoRestante, finDeEstacionamiento.getSaldoRestante());
		assertEquals(patente, finDeEstacionamiento.getPatente());
		assertEquals(horaFin, finDeEstacionamiento.getHoraFin());
	}
	
	@Test
	void testToString() {
		String textoEsperado = "Telefono: " + numeroDeCelular + 
								"\nSaldo Restante: " + saldoRestante + 
								"\nPatente: " + patente + 
								"\nHora de Fin: " + horaFin;
		
		//Verify
		assertEquals(textoEsperado, finDeEstacionamiento.toString());
	}

}
