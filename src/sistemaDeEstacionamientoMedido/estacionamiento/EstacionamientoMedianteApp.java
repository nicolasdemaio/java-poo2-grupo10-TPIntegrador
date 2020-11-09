package sistemaDeEstacionamientoMedido.estacionamiento;

import java.time.LocalDateTime;

import sistemaDeEstacionamientoMedido.zona.Zona;

public class EstacionamientoMedianteApp extends Estacionamiento {

	private Integer nroCelular;
	private Boolean estaVigente;
	
	public EstacionamientoMedianteApp(String patente, LocalDateTime horaDeFin, Double costoPorHora, Zona zona,
			Integer nroCelular) {
		super(patente, LocalDateTime.now(), horaDeFin, costoPorHora, zona);
		this.nroCelular = nroCelular;
		this.estaVigente = true;
	}

	public Integer getNroCelular() {
		return nroCelular;
	}
	
	public Boolean getEstaVigente() {
		return estaVigente;
	}
	
	private void setNoEstaVigente() {
		this.estaVigente = false;
	}

	@Override
	public Boolean estaVigente() {
		return getEstaVigente();
	}

	@Override
	public void finalizar() {
		setNoEstaVigente();
	}

}
