package Unidad_1;
// Catalogo.java
// Clase principal de la aplicación

public class Catalogo {

	public static void main(String[] args) {
		Vista miVista = new Vista();
		Modelo miModelo = new Modelo();
		Controlador miControlador = new Controlador( miModelo, miVista );
		miControlador.iniciarVista();
	}

}
