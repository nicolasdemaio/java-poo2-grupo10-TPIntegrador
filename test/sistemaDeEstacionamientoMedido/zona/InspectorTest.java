package sistemaDeEstacionamientoMedido.zona;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InspectorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConstructor() {
		//Setup
		
		//SUT
		Inspector inspector = new Inspector("Juan");
		
		//Verify
		assertEquals("Juan", inspector.getNombre());
	}

}
