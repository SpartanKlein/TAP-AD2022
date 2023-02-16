import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Vector;

// Modelo.java
// Componente Modelo de la arquitectura Modelo - Vista - Controlador

public class Modelo {
	enum Tipo {Linea, Rectangulo, Elipse};
	
	static Figura f;
	static Vector<Figura> figuras;
	
	public Modelo()
	{
		figuras = new Vector<Figura>();
	}
}

class Figura implements Serializable
{
	private	Shape	figura;
	private	Color	color;
	private	boolean	relleno;
	
	public Figura( Shape f, Color c, boolean r )
	{
		figura = f;
		color = c;
		relleno = r;
	}
	
	// Método para dibujar la figura
	public void dibujar( Graphics2D g2D )
	{
		g2D.setColor( color );
		if ( relleno )
			g2D.fill( figura );
		else
			g2D.draw( figura );
	}
}












