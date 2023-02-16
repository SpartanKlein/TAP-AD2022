// Consola.java
// Clase que contiene los métodos nativos

public class Consola
{
	public native static void limpiarPantalla();
	public native static void colocarCursor( int renglon, int columna );

	static
	{
		System.loadLibrary("Consola");
	}
}