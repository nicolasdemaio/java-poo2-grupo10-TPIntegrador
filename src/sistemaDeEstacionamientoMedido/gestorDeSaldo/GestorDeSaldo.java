package sistemaDeEstacionamientoMedido.gestorDeSaldo;

import java.util.HashMap;
import java.util.Map;

/**
 * Inveriante de representación: El nro del celular consiste de un Integer de 8 digitos, y 
 *                               no puede existir un numero de celular que corresponda a 2
 *                               celulares distintos.
 */

public class GestorDeSaldo {
	private Map <Integer, Double> saldoPorCelular;
	
	public GestorDeSaldo() {
		this.saldoPorCelular = new HashMap <Integer, Double>();
	}

	public Map <Integer, Double> getSaldoPorCelular() {
		
		return this.saldoPorCelular;
	}
	
	/**
	 * Actualiza el saldo asignado al celular sumandole la cantidad indicada por parametro.
	 * Si es la primera vez que se carga saldo, se hara una referencia al celular con la
	 * cantidad indicada. 
	 */
	public void añadirSaldo(Integer nroCelular, Double saldo) {
		this.getSaldoPorCelular().put(nroCelular, this.getSaldo(nroCelular) + saldo);
	}
	
	/**
	 * Describe el saldo que posee el celular indicado. En caso de no estar registrado,
	 * indica que posee saldo 0.
	 * @return Double 
	 */
	public Double getSaldo(Integer nroCelular) {
		
		return this.getSaldoPorCelular().getOrDefault(nroCelular,0d);
	}
	
	/**
	 * Se disminuye la cantidad de saldo indicada por parametro al nro de celular indicado.
	 * Si la cantidad a disminuir excede al saldo, este queda en 0.
	 * Si el nro de celular no se encuentra registrado en el sistema, se levantará una excepción.
	 * @exception ActualizacionDeSaldoException
	 */
	public void disminuirSaldo(Integer nroCelular, Double saldoARestar) {
		try {
			this.getSaldoPorCelular().put(nroCelular,
					                      Math.max(this.getSaldoPorCelular().get(nroCelular) - saldoARestar,
					                    		   0));	
		} catch (NullPointerException e){
			throw new ActualizacionDeSaldoException ("El celular no se encuentra en el sistema", e);
		}
	}
	
	

}
