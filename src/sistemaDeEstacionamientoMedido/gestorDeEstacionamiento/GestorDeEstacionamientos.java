package sistemaDeEstacionamientoMedido.gestorDeEstacionamiento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sistemaDeEstacionamientoMedido.estacionamiento.Estacionamiento;

public class GestorDeEstacionamientos implements IGestorDeEstacionamientos {
	
	private LocalDateTime horaInicioEstacionamiento;
	private LocalDateTime horaFinEstacionamiento;
	private Double costoPorHora;
	private RegistroCelularPatente registroCelularPatente;
	private List <Estacionamiento> estacionamientos;
	
	
	public GestorDeEstacionamientos(LocalDateTime horaInicioEstacionamiento, LocalDateTime horaFinEstacionamiento,
			Double costoPorHoraDeEstacionamiento, RegistroCelularPatente registroCelularPatente) {
		
		this.horaInicioEstacionamiento = horaInicioEstacionamiento;
		this.horaFinEstacionamiento = horaFinEstacionamiento;
		this.costoPorHora = costoPorHoraDeEstacionamiento;
		this.registroCelularPatente = registroCelularPatente;
		this.estacionamientos = new ArrayList <Estacionamiento>();
		
	}

	public LocalDateTime getHoraInicioEstacionamiento() {
		return horaInicioEstacionamiento;
	}

	public LocalDateTime getHoraFinEstacionamiento() {
		return horaFinEstacionamiento;
	}

	public RegistroCelularPatente getRegistroCelularPatente() {
		return registroCelularPatente;
	}
	
	public List <Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}
	

	@Override
	public void añadirEstacionamiento(Estacionamiento estacionamiento, Integer nroCelular) {
		this.registroCelularPatente.asociar(nroCelular, estacionamiento.getPatente());
		this.añadirEstacionamiento(estacionamiento);

	}

	@Override
	public void añadirEstacionamiento(Estacionamiento estacionamiento) {
		this.getEstacionamientos().add(estacionamiento);

	}

	@Override
	public Estacionamiento finalizarVigenciaDeEstacionamiento(Integer nroCelular) {
		String patente = this.getRegistroCelularPatente().getPatente(nroCelular);
		return this.finalizarVigenciaDeEstacionamiento(patente);
	}

	private Estacionamiento finalizarVigenciaDeEstacionamiento(String patente) {
		Estacionamiento estacionamientoAFinalizar = 
				this.getEstacionamientos().stream()
											.filter(estacionamiento -> estacionamiento.estaVigente())
											.filter(estacionamiento -> estacionamiento.getPatente() == patente)
											.findFirst().get();
		estacionamientoAFinalizar.finalizar();
		return estacionamientoAFinalizar;
	}

	@Override
	public boolean hayEstacionamientoVigente(String patente) {
		
		return this.getEstacionamientos().stream()
				.filter(estacionamiento -> estacionamiento.estaVigente())
				.anyMatch(estacionamiento -> estacionamiento.getPatente() == patente);
	}

	@Override
	public boolean hayEstacionamientoEnRegla(String patente) {
		Estacionamiento estacionamientoPedido =
		this.getEstacionamientos().stream()
				.filter(estacionamiento -> estacionamiento.estaVigente())
				.filter(estacionamiento -> estacionamiento.getPatente() == patente)
				.findFirst().get();
		 return estacionamientoPedido.getHoraDeFin().isAfter(LocalDateTime.now());
	}

	@Override
	public boolean esHorarioDeEstacionamiento(LocalDateTime date) {
		return date.isAfter(getHoraInicioEstacionamiento()) && date.isBefore(getHoraFinEstacionamiento()) ;
	}

	@Override
	public boolean esSaldoSuficiente(Double saldo) {
		return saldo > this.getCostoPorHora();
	}

	@Override
	public Double getCostoPorHora() {
		return this.costoPorHora;
	}

	@Override
	public LocalDateTime horaMaximaDeFin(Double saldo) {
		
		LocalDateTime horaActual = LocalDateTime.now();
		Integer horasPosiblesDeEstacionamiento = (int)(saldo / this.getCostoPorHora());
		Integer horaMaximaDeEstacionamiento = Math.min(horaActual.getHour() + horasPosiblesDeEstacionamiento,
				 						this.getHoraFinEstacionamiento().getHour());
		
		return LocalDateTime.now().withHour(horaMaximaDeEstacionamiento);
	}
	
}
