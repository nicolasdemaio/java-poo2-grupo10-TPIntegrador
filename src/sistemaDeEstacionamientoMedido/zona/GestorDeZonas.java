package sistemaDeEstacionamientoMedido.zona;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GestorDeZonas implements IGestorDeZonas {

	private List<Zona> zonas;
	
	public GestorDeZonas() {
		zonas = new ArrayList<>();
	}
	
	public List<Zona> getZonas() {
		return this.zonas;
	}

	public void añadirZona(Zona unaZona) {
		this.getZonas().add(unaZona);
	}

	public Boolean perteneceAUnaZona(Point unPunto) {
		return this.getZonas().stream().anyMatch(zona -> zona.contieneUnPunto(unPunto));
	}

	public Zona indicarZona(Point unPunto) {
		return this.getZonas().stream().filter(zona -> zona.contieneUnPunto(unPunto)).findFirst().get();
	}

}
