package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

public interface IRegistroCelularPatente {
	
	public void asociar(Integer nroCelular, String patente);
	
	public String getPatente(Integer nroCelular);
	
}
