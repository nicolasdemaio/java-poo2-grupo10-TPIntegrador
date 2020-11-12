package sistemaDeEstacionamientoMedido.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaDeEstacionamientoMedido.estacionamiento.EstacionamientoPorCompraPuntual;
import sistemaDeEstacionamientoMedido.gestorDeEstacionamiento.IGestorDeEstacionamientos;
import sistemaDeEstacionamientoMedido.gestorDeSaldo.IGestorDeSaldo;
import sistemaDeEstacionamientoMedido.sem.ServicioPuntoDeVenta;
import sistemaDeEstacionamientoMedido.suscriptores.IGestorDeSuscriptores;
import sistemaDeEstacionamientoMedido.zona.Zona;
import sistemaDeEstacionamientoMedido.notificacion.CompraDeSaldo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

class PuntoDeVentaTestCase {
	//SUT
	PuntoDeVenta puntoDeVenta;
	//DOC
	Zona zona;
	IGestorDeSuscriptores gestorDeSuscriptores;
	IGestorDeSaldo gestorDeSaldo;
	IGestorDeEstacionamientos gestorDeEstacionamientos;
	ISectorDeCompras sectorDeCompras;
	ServicioPuntoDeVenta servicio;
	
	
	@BeforeEach
	void setUp() throws Exception {
		zona = mock (Zona.class);
		gestorDeSuscriptores = mock (IGestorDeSuscriptores.class);
		gestorDeSaldo = mock (IGestorDeSaldo.class);
		gestorDeEstacionamientos = mock (IGestorDeEstacionamientos.class);
		sectorDeCompras = mock (ISectorDeCompras.class);
		servicio = mock (ServicioPuntoDeVenta.class);
		
		when(servicio.getGestorDeSaldo()).thenReturn(gestorDeSaldo);
		when(servicio.getGestorDeEstacionamientos()).thenReturn(gestorDeEstacionamientos);
		when(servicio.getGestorDeSuscriptores()).thenReturn(gestorDeSuscriptores);
		
		puntoDeVenta = new PuntoDeVenta(servicio, zona, sectorDeCompras);
	}

	@Test
	void testConstructor() {
		assertEquals(zona, puntoDeVenta.getZona());
		assertEquals(gestorDeEstacionamientos, puntoDeVenta.getGestorDeEstacionamientos());
		assertEquals(gestorDeSaldo, puntoDeVenta.getGestorDeSaldo());
		assertEquals(gestorDeSuscriptores, puntoDeVenta.getGestorDeSuscriptores());
		assertEquals(sectorDeCompras, puntoDeVenta.getSectorDeCompras());
		assertEquals(0,puntoDeVenta.getContadorDeCompras());
	}
	
	@Test
	void testCuandoSeRegistraUnEstacionamientoElSectorDelEstacionamientoRecibeUnEstacionamiento() {
		String patente = "AAA-345";
		Integer cantidadDeHoras = 3;
		
		puntoDeVenta.registrarEstacionamiento(patente,cantidadDeHoras);
		
		verify(gestorDeEstacionamientos, times(1)).añadirEstacionamiento(any(EstacionamientoPorCompraPuntual.class));
		
	}
	
	@Test
	void testCuandoSeRegistraUnEstacionamientoElSectorDeComprasRecibeUnaCompraPuntual() {
		String patente = "AAA-345";
		Integer cantidadDeHoras = 3;
		
		puntoDeVenta.registrarEstacionamiento(patente,cantidadDeHoras);
		
		verify(sectorDeCompras, times(1)).registrarCompra(any(CompraPuntual.class));
	}
	
	@Test
	void testCuandoSeRegistraUnEstacionamientoSeIncrementaElContadorDeComprasEnUno() {
		String patente = "AAA-345";
		Integer cantidadDeHoras = 3;
		
		puntoDeVenta.registrarEstacionamiento(patente,cantidadDeHoras);
		
		assertEquals(1,puntoDeVenta.getContadorDeCompras());
	}
	
	@Test
	void testCuandoSeCargaCreditoSeLeIndicaAlGestorDeSaldosQueAcrediteLaCarga() {
		Integer numeroDeCelular = 42341324;
		Double monto = 420d;
		
		puntoDeVenta.cargarCredito(numeroDeCelular, monto);
		
		verify(gestorDeSaldo, times(1)).añadirSaldo(numeroDeCelular,monto);
	}
	
	@Test
	void testCuandoSeCargaCreditoElSectorDeComprasRecibeUnaRecargaDeCelular() {
		Integer numeroDeCelular = 42341324;
		Double monto = 420d;
		
		puntoDeVenta.cargarCredito(numeroDeCelular, monto);
		
		verify(sectorDeCompras, times(1)).registrarCompra(any(RecargaDeCelular.class));
	}
	
	@Test
	void testCuandoSeCargaCreditoSeIncrementaElContadorDeComprasEnUno() {
		Integer numeroDeCelular = 42341324;
		Double monto = 420d;
		
		puntoDeVenta.cargarCredito(numeroDeCelular, monto);
		
		assertEquals(1,puntoDeVenta.getContadorDeCompras());
	}
	
	@Test
	void testCuandoSeCargaCreditoSeNotificaAlSectorDeSuscriptores() {
		Integer numeroDeCelular = 42341324;
		Double monto = 420d;
		
		puntoDeVenta.cargarCredito(numeroDeCelular, monto);
		
		verify(gestorDeSuscriptores, times(1)).notifySuscriptores(any(CompraDeSaldo.class));
	}
	
	@Test
	void testGetCompras() {
		Compra compra1 = mock(Compra.class);
		Compra compra2 = mock(Compra.class);
		ArrayList <Compra> listaConMocks = new ArrayList <Compra>();
		listaConMocks.add(compra1);
		listaConMocks.add(compra2);
		when (sectorDeCompras.getCompras()).thenReturn(listaConMocks);
		
		assertEquals (listaConMocks, puntoDeVenta.getCompras());
		
	}

}
