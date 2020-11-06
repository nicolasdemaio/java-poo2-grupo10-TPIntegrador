package sistemaDeEstacionamientoMedido.appMunicipio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class InfraccionTestCase {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testConstructor() {
        LocalDateTime fechaYHora = LocalDateTime.now();
        String patente = "AAA-998";
        Inspector inspector = mock(Inspector.class);
        Zona zona = mock(Zona.class);
        
        Infraccion infraccion = new Infraccion(patente,fechaYHora ,inspector,zona);
        
        
        assertEquals(fechaYHora, infraccion.getFechaYHora());
        assertEquals(patente, infraccion.getPatente());
        assertEquals(inspector, infraccion.getInspector());
        assertEquals(zona, infraccion.getZona());
    }

}