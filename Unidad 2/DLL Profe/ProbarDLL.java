// ProbarDLL.java
// Programa para probar una DLL

public class ProbarDLL
{
	public static void main(String[] args)
	{
		// limpiar la pantalla
		Consola.limpiarPantalla();

		// colocar el cursor en el renglón 10 y columna 20
		Consola.colocarCursor( 10, 20 );

		// Mostrar un mensaje
		System.out.print( "Iniciamos en (10, 20)..." );
	}
}
