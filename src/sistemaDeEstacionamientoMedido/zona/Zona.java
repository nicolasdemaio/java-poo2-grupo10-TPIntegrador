package sistemaDeEstacionamientoMedido.zona;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import sistemaDeEstacionamientoMedido.puntoDeVenta.PuntoDeVenta;

public class Zona {

	private Polygon area;
	private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	
	public Zona(Polygon poligono, Inspector inspector) {
		this.area = poligono;
		this.inspector = inspector;
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	/**
	 * Indica si una zona contiene a un punto dado.
	 * @param unPunto (Point2D)
	 * @return Valor de verdad
	 */
	public Boolean contieneUnPunto(Point2D unPunto) {
		return this.getArea().contains(unPunto);
	}
	
	public void añadirPuntoDeVenta(PuntoDeVenta unPuntoDeVenta) {
		this.getPuntosDeVenta().add(unPuntoDeVenta);
	}

	public Polygon getArea() {
		return this.area;
	}

	public Inspector getInspector() {
		return this.inspector;
	}

	public List<PuntoDeVenta> getPuntosDeVenta() {
		return this.puntosDeVenta;
	}

}
