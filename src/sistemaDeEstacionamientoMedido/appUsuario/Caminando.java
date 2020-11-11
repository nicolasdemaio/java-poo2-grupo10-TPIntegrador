package sistemaDeEstacionamientoMedido.appUsuario;

public class Caminando extends EstadoDeMovimiento {
	

	@Override
	public void walking (AppUsuario appUsuario) {
		
	}
	
	@Override
	public void driving (AppUsuario appUsuario) {
		if (appUsuario.tieneDesplazamientoActivo()  && appUsuario.correspondeFinalizarEstacionamiento()) {
			appUsuario.cambiarEstadoDeMovimiento(new Manejando());
			appUsuario.alertarFinEstacionamiento();
		}
	}
}