package sistemaDeEstacionamientoMedido.appUsuario;

public class ModoAutomatico extends Modo {
	
	@Override
	public String alertarInicioDeEstacionamiento(ModeClient appUsuario) {
		
		appUsuario.iniciarEstacionamiento();
		return "Se ha iniciado el estacionamiento";
	}
	@Override
	public String alertarFinDeEstacionamiento(ModeClient appUsuario) {
		appUsuario.finDeEstacionamiento();
		return "Se ha finalizado el estacionamiento";
	}
}
