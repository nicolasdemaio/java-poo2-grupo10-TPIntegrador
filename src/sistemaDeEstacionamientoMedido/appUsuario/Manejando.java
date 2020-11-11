package sistemaDeEstacionamientoMedido.appUsuario;

public class Manejando extends EstadoDeMovimiento {
	
	@Override
	public void walking (AppUsuario appUsuario) {
		if (appUsuario.tieneDesplazamientoActivo() && appUsuario.correspondeEstacionar()  ) {
			appUsuario.cambiarEstadoDeMovimiento(new Caminando());
			appUsuario.alertarInicioEstacionamiento();
		}
	}
	
	@Override
	public void driving (AppUsuario appUsuario) {
		
	}
}
