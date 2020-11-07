package sistemaDeEstacionamientoMedido.appMunicipio;

import java.util.ArrayList;
import java.util.List;

public class GestorDeInfracciones implements IGestorDeInfracciones {
	private List <Infraccion> infracciones;
	
	public GestorDeInfracciones () {
		this.infracciones = new ArrayList <Infraccion>();
	}

	@Override
	public void registrarInfraccion(Infraccion infraccion) {
		this.getInfracciones().add(infraccion);
		
	}

	@Override
	public List<Infraccion> getInfracciones() {

		return this.infracciones;
	}

}
