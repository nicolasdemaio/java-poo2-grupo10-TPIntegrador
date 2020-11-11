package sistemaDeEstacionamientoMedido.appUsuario;

public abstract class Modo {

	public abstract String alertarInicioDeEstacionamiento(ModeClient appUsuario);
	
	public abstract String alertarFinDeEstacionamiento(ModeClient appUsuario);
}
