package sistemaDeEstacionamientoMedido.notificacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompraDeSaldoTest {

	private Integer numeroDeCelular;
	private Double saldoCargado;
	private CompraDeSaldo compraDeSaldo;
	
	@BeforeEach
	void setUp() throws Exception {
		numeroDeCelular = 1111;
		saldoCargado = 150d;
		compraDeSaldo = new CompraDeSaldo(numeroDeCelular, saldoCargado);
	}

	@Test
	void testConstructor() {
		assertEquals(numeroDeCelular, compraDeSaldo.getNumeroDeCelular());
		assertEquals(saldoCargado, compraDeSaldo.getSaldoCargado());
	}
	
	@Test
	void testToString() {
	String textoEsperado = "Telefono: " + numeroDeCelular + 
							"\nSaldo Cargado: " + saldoCargado;

	//Verify
	assertEquals(textoEsperado, compraDeSaldo.toString());
	}

}
