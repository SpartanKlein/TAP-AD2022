public class Consola {
	
	public native static void limpiarPantalla();	
	public native static void colocarCursor(int linea, int columna);
	public native static void titulo(String s);
	public native static void colorTexto(int n);
	public native static void stop();
	
	static{
		System.loadLibrary("Consola");	
	}		
}