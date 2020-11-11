package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

import java.util.HashMap;
import java.util.Map;

public class RegistroCelularPatente implements IRegistroCelularPatente {
	
	private Map<Integer, String> registroCelularPatente;
	
	
	public RegistroCelularPatente() {
		this.registroCelularPatente = new HashMap<>();
	}
	
	
	public Map<Integer, String> getRegistroCelularPatente() {
		return this.registroCelularPatente;
	}
	
	
	@Override
	public void asociar(Integer nroCelular, String patente) {
		this.getRegistroCelularPatente().put(nroCelular, patente);
	}

	@Override
	public String getPatente(Integer nroCelular) {
		return this.getRegistroCelularPatente().get(nroCelular);
	}

}
