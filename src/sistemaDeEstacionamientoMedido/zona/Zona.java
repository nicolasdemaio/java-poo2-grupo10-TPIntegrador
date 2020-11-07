package sistemaDeEstacionamientoMedido.zona;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Zona {

	private Polygon area;
	private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;
	
	public Zona(Polygon poligono, Inspector inspector) {
		this.area = poligono;
		this.inspector = inspector;
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	public Boolean contieneUnPunto(Point unPunto) {
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
