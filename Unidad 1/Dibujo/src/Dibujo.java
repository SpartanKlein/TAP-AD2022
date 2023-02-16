// Dibujo.java
// Programa principal de la aplicación Dibujo

public class Dibujo {
	public static void main( String[] args )
	{
		Vista miVista = new Vista();
		Modelo miModelo = new Modelo();
		Controlador miControlador = new Controlador( miModelo, miVista );
		miControlador.iniciarVista();
	}
}
