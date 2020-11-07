package sistemaDeEstacionamientoMedido.gestorDeSaldo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestorDeSaldoTestCase {
	GestorDeSaldo gestor;
	@BeforeEach
	void setUp() throws Exception {
		gestor = new GestorDeSaldo();
	}

	@Test
	/*
	 * Cuando se crea un gestor de saldo, esten no posee ninguna asociacion de celular->saldo
	 */
	void testConstructor() {
		assertTrue (gestor.getSaldoPorCelular().isEmpty());	
	}
	
	@Test
	void testCuandoSeConsultaElSaldoDeUnCelularNoRegistradoSeLeIndicaQueNoTieneSaldo() {
		assertEquals(0, gestor.getSaldo(42254321));
	}
	
	@Test
	void testCuandoSeAñadeSaldoPorPrimeraVezAUnCelularEsteQuedaAsociadoALaCantidadAsignada() {
		gestor.añadirSaldo(42215964, 400d);
		
		assertEquals(400, gestor.getSaldo(42215964));
	}
	
	@Test
	void testCuandoSeAñadeSaldoAUnCelularYaRegistradoSuSaldoIncrementaEnLaCantidadAsignada() {
		gestor.añadirSaldo(42215964, 400d);
		gestor.añadirSaldo(42215964, 300d);
		
		assertEquals(700, gestor.getSaldo(42215964));
	}
	
	@Test
	void testCuandoSeDisminuyeSaldoAUnCelularNoRegistradoSuSaldoIncrementaEnLaCantidadAsignada() {
		
		assertThrows(ActualizacionDeSaldoException.class, ()-> gestor.disminuirSaldo(42215964, 400d));
	}
	
	@Test
	void testCuandoSeDisminuyeSaldoAUnCelularConMenosSaldoDelQueSeRestaElSaldoResultanteEsCero() {
		gestor.añadirSaldo(42215964, 400d);
		
		gestor.disminuirSaldo(42215964, 500d);
		
		assertEquals(0, gestor.getSaldo(42215964));
	}
	
	@Test
	void testCuandoSeDisminuyeSaldoAUnCelularConSaldoSuficienteSeLeRestaElSaldoIndicado() {
		gestor.añadirSaldo(42215964, 400d);
		
		gestor.disminuirSaldo(42215964, 300d);
		
		assertEquals(100, gestor.getSaldo(42215964));
	}
}
