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

	/**
	 * Indica si un punto dado pertenece a alguna zona del gestor de zonas.
	 * @param unPunto (Point)
	 * @return Valor de verdad
	 */
	public Boolean perteneceAUnaZona(Point unPunto) {
		return this.getZonas().stream().anyMatch(zona -> zona.contieneUnPunto(unPunto));
	}

	/**
	 * Para un punto dado, retorna la zona que incluya ese punto.
	 * @param unPunto (Point)
	 * @return una Zona
	 * @exception ZonaNoEncontradaException cuando no hay zona que contenga el punto recibido.
	 */
	public Zona indicarZona(Point unPunto) {
		try {
			return this.getZonas().stream().filter(zona -> zona.contieneUnPunto(unPunto)).findFirst().get();
		} catch(Exception ex) {
			throw new ZonaNoEncontradaException("No existe una zona que contenga el punto dado.");
		}

	}

}
