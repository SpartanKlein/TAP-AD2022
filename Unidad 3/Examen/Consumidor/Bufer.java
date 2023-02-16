
//La interfaz Bufer especifica los metodos que el Productor y el Consumidor llaman.
public interface Bufer
{
// coloca valor int value en Bufer
public void establecer( int valor ) throws InterruptedException; 
public int obtener() throws InterruptedException; 
public boolean estaVacio();

} 
