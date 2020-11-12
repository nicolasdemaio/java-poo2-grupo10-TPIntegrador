package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;

public interface IGestorDeEstacionamientos {
	
	public void añadirEstacionamiento(Estacionamiento estacionamiento, Integer nroCelular);

	public void añadirEstacionamiento(Estacionamiento estacionamiento);

	public Estacionamiento finalizarVigenciaDeEstacionamiento(Integer nroCelular);

	public boolean hayEstacionamientoVigente(String patente);

	public boolean hayEstacionamientoEnRegla(String patente);

	public boolean esHorarioDeEstacionamiento(LocalDateTime date);

	public boolean esSaldoSuficiente(Double saldo);

	public Double getCostoPorHora();

	public LocalDateTime horaMaximaDeFin(Double saldo);

}
