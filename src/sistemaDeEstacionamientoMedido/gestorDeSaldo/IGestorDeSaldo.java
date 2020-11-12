package sistemaDeEstacionamientoMedido.gestorDeSaldo;

public interface IGestorDeSaldo {
	
	public void añadirSaldo(Integer nroCelular, Double saldo);
	
	public Double getSaldo(Integer nroCelular);
	
	public void disminuirSaldo(Integer nroCelular, Double saldoARestar);
	
	
}
