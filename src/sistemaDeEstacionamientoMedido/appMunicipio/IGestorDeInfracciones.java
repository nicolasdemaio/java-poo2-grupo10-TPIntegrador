package sistemaDeEstacionamientoMedido.appMunicipio;

import java.util.List;

public interface IGestorDeInfracciones {
	
	public void registrarInfraccion(Infraccion infraccion);
	
	public List <Infraccion> getInfracciones ();
	
}
