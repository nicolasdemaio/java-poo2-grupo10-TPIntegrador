package sistemaDeEstacionamientoMedido.appUsuario;

public class ModoManual extends Modo {
	
	@Override
	public String alertarInicioDeEstacionamiento(ModeClient appUsuario) {
		
		return "Se deberia realizar el inicio de estacionamiento";
	}
	
	@Override
	public String alertarFinDeEstacionamiento(ModeClient appUsuario) {

		return "Se deberia realizar el fin de estacionamiento";
	}
}