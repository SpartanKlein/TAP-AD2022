import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Vector;

// Controlador.java
// Componente Controlador de la arquitectura Modelo-Vista-Controlador

public class Controlador {
	private Modelo miModelo;
	private Vista miVista;

	private Point2D puntoInicial;
	private Point2D puntoFinal;

	// Variables a modificar con las opciones del menú
	Modelo.Tipo tipo = Modelo.Tipo.Linea;
	Color color = Color.RED;
	boolean relleno = false, guardado;

	public Controlador( Modelo miModelo, Vista miVista )
	{
		this.miModelo = miModelo;
		this.miVista = miVista;
	}

	public void iniciarVista()
	{
		// Crear oyentes
		OyenteDeRaton odr = new OyenteDeRaton();
		OyenteDeVentana odv = new OyenteDeVentana();
		OyenteElementosArchivo oea = new OyenteElementosArchivo();
		OyenteTipoFigura otf = new OyenteTipoFigura();
		OyenteRelleno oyr = new OyenteRelleno();
		OyenteColor oyc = new OyenteColor();


		miVista.miVentana.addWindowListener(odv);
		// Registrar el oyente en el panel
		miVista.miVentana.miPanel.addMouseListener(odr);
		miVista.miVentana.miPanel.addMouseMotionListener(odr);

		//Registrar Oyente en elementos del menú
		miVista.jmiArchivo.addActionListener(oea);
		miVista.jmiAbrir.addActionListener(oea);
		miVista.jmiGuardar.addActionListener(oea);
		miVista.jmiImprimir.addActionListener(oea);
		miVista.jmiSalir.addActionListener(oea);
		miVista.acercaDe.addActionListener(oea);

		//Registrar Oyentes en el menú de dibujo
		miVista.jrbLinea.addActionListener(otf);
		miVista.jrbRectangulo.addActionListener(otf);
		miVista.jrbElipse.addActionListener(otf);
		miVista.cmbTipo.addActionListener(otf);

		//Registrar oyente de las propiedades que puede llegar a tener nuestra figura
		miVista.chkRelleno.addActionListener(oyr);
		miVista.rellenarFigura.addActionListener(oyr);
		miVista.colorFig.addActionListener(oyc);
		miVista.btnColor.addActionListener(oyc);

		miVista.miVentana.setVisible(true);
	}

	private Figura crearFigura()
	{
		Shape temporal;
		Figura resultado;

		// Preguntar si es una linea
		if ( tipo == Modelo.Tipo.Linea )
		{
			temporal = new Line2D.Double( puntoInicial.getX(), puntoInicial.getY(), puntoFinal.getX(), puntoFinal.getY());
		}
		else
		{
			double x1 = Math.min(puntoInicial.getX(), puntoFinal.getX());
			double y1 = Math.min(puntoInicial.getY(), puntoFinal.getY());
			double ancho = Math.abs( puntoFinal.getX() - puntoInicial.getX());
			double alto  = Math.abs( puntoFinal.getY() - puntoInicial.getY());

			if ( tipo == Modelo.Tipo.Rectangulo )
				temporal = new Rectangle2D.Double( x1, y1, ancho, alto);
			else
				temporal = new Ellipse2D.Double( x1, y1, ancho, alto);
		}

		resultado = new Figura( temporal, color, relleno );
		return resultado;
	}

	// Clase interna para los eventos del ratón
	class OyenteDeRaton extends MouseAdapter
	{
		public void mousePressed( MouseEvent e )
		{
			// Si es botón derecho imprimir
			miVista.miVentana.miPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			puntoInicial = e.getPoint();
			Modelo.f = null;
		}

		public void mouseReleased( MouseEvent e )
		{
			miVista.miVentana.miPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			puntoFinal = e.getPoint();
			Modelo.f = crearFigura();
			Modelo.figuras.add( Modelo.f );
			guardado = false;
			miVista.jmiArchivo.setEnabled(true);
			miVista.miVentana.miPanel.repaint();
		}

		public void mouseDragged( MouseEvent e )
		{
			puntoFinal = e.getPoint();

			if ( Modelo.f != null )
			{
				// Borrar la figura anterior
				Graphics2D g2D = ( Graphics2D ) miVista.miVentana.miPanel.getGraphics();
				g2D.setXORMode(miVista.miVentana.miPanel.getBackground());
				Modelo.f.dibujar(g2D);
			}
			Modelo.f = crearFigura();
			Graphics2D g2D = ( Graphics2D ) miVista.miVentana.miPanel.getGraphics();
			g2D.setXORMode(miVista.miVentana.miPanel.getBackground());
			Modelo.f.dibujar(g2D);
		}

	}

	class OyenteElementosArchivo implements ActionListener {

		public void actionPerformed(ActionEvent e){
			String cual = e.getActionCommand();

			if (cual.equals("Nuevo")){
				nuevo();
			}
			else if (cual.equals("Abrir...")) {
				if (!guardado && Modelo.figuras.size() != 0 && JOptionPane.showConfirmDialog(miVista.miVentana, "¿Desea guardar su dibujo antes de abrir uno nuevo?", "Programa de Dibujo", 0) == 0 && !guardarDibujo())
					return;
				abrir();
			}
			else if(cual.equals("Guardar como...")){
				guardarDibujo();
			}
			else if (cual.equals("Imprimir...")) {
				miVista.miVentana.miPanel.print();
			}
			else if (cual.equals("Salir")) {
				salir();
			}

			else if (cual.equals("Acerca de...")){
				JOptionPane.showMessageDialog(miVista.miVentana, "Programa de Dibujo de arquitectura Modelo - Vista - Controlador \n Elaborado por: Jaime Alonso Ruiz Lizarraga  \n No. de control: 19170736", "Datos del autor", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	class OyenteTipoFigura implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String cual = e.getActionCommand();

			if (cual.equals("Linea")){
				tipo = Modelo.Tipo.Linea;
				miVista.cmbTipo.setSelectedIndex(0);
				miVista.rellenarFigura.setEnabled(false);
				miVista.chkRelleno.setEnabled(false);
			}
			else if (cual.equals("Rectangulo")) {
				tipo = Modelo.Tipo.Rectangulo;
				miVista.cmbTipo.setSelectedIndex(1);
				miVista.rellenarFigura.setEnabled(true);
				miVista.chkRelleno.setEnabled(true);
			}
			else if (cual.equals("Elipse")){
				tipo = Modelo.Tipo.Rectangulo;
				miVista.cmbTipo.setSelectedIndex(2);
				miVista.rellenarFigura.setEnabled(true);
				miVista.chkRelleno.setEnabled(true);
			}
			else{
				switch (miVista.cmbTipo.getSelectedIndex()) {
					case 0:
						tipo = Modelo.Tipo.Linea;
						miVista.jrbLinea.setSelected(true);
						miVista.rellenarFigura.setEnabled(false);
						miVista.chkRelleno.setEnabled(false);
						break;

					case 1:
						tipo = Modelo.Tipo.Rectangulo;
						miVista.jrbRectangulo.setSelected(true);
						miVista.rellenarFigura.setEnabled(true);
						miVista.chkRelleno.setEnabled(true);
						break;

					case 2:
						tipo = Modelo.Tipo.Elipse;
						miVista.jrbElipse.setSelected(true);
						miVista.rellenarFigura.setEnabled(true);
						miVista.chkRelleno.setEnabled(true);
						break;
				}
			}
		}
	}

	class OyenteDeVentana extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			salir();
		}
	}

	class OyenteRelleno implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			relleno = !relleno;
			miVista.rellenarFigura.setSelected(relleno);
			miVista.chkRelleno.setSelected(relleno);
		}
	}

	class OyenteColor implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Color colorAntecesor = color;
			colorAntecesor = JColorChooser.showDialog(miVista.miVentana, "Seleccione un color", colorAntecesor);
			if (colorAntecesor != null)
				color = colorAntecesor;
		}
	}


	private void nuevo(){
			if (!guardado && Modelo.figuras.size()!=0 && JOptionPane.showConfirmDialog(miVista.miVentana, "Desea guardar su dibujo antes de uno nuevo", "Programa de Dibujo", 0) ==0 && !guardarDibujo())
				return;
			else {
				Modelo.figuras = new Vector();
				guardado = false;
				miVista.miVentana.setEnabled(true);
				miVista.miVentana.miPanel.repaint();
				return;
			}
	}

	private void salir(){
		if (!guardado && Modelo.figuras.size() != 0){
			if (JOptionPane.showConfirmDialog(miVista.miVentana, "¿Desea guardar su dibujo antes de salir?", "Programa de Dibujo", 0) == 0 && !guardarDibujo())
			return;
		System.exit(0);
		}
		else if (JOptionPane.showConfirmDialog(miVista.miVentana, "¿Desea Salir de la aplicación?", "Programa de Dibujo", 0) == 0)
			System.exit(0);
	}

	private boolean abrir(){
		boolean regresar = false;
		File directorio = new File (".");

		try {
			JFileChooser archivo = new JFileChooser(directorio.getCanonicalPath());
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Mi Dibujo", new String[] {"dbj"});
			archivo.setFileFilter(filtro);
			if (archivo.showOpenDialog(miVista.miVentana) == 0){
				File abrir = new File(NombreArchivo(archivo.getSelectedFile().getName(),".dbj"));
				FileInputStream fis = new FileInputStream(abrir);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Modelo.figuras = (Vector) ois.readObject();
				fis.close();
				ois.close();
				guardado = false;
				miVista.jmiArchivo.setEnabled(true);
				regresar = true;
				miVista.miVentana.miPanel.repaint();
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(miVista.miVentana, "Error no se pudo guardar el dibujo", "Programa de dibujo", 2);
		}
		return regresar;
	}

	private boolean guardarDibujo(){
		boolean regresar = false;
		File direccionActual = new File(".");

		try{
			JFileChooser archivo = new JFileChooser(direccionActual.getCanonicalPath());
			FileNameExtensionFilter extension = new FileNameExtensionFilter("Mi dibujo", new String[] {"dbj"});
			archivo.setFileFilter(extension);
			if (archivo.showSaveDialog(miVista.miVentana)==0){
				File archivoGuardar = new File(NombreArchivo(archivo.getSelectedFile().getName(), ".dbj"));
				FileOutputStream fos = new FileOutputStream(archivoGuardar);
				ObjectOutputStream ous = new ObjectOutputStream(fos);
				ous.writeObject(Modelo.figuras);
				fos.close();
				ous.close();
				JOptionPane.showMessageDialog(miVista.miVentana, "Archivo guardado de manera exitosa", "Programa de Dibujo", -1);
				guardado = true;
				regresar = true;
			}
		}catch (IOException ex){
			JOptionPane.showConfirmDialog(miVista.miVentana, "Error al guardar el dibujo", "Programa de Dibujo", 0);
		}
		return regresar;
	}

	private String NombreArchivo(String nombre, String extension){
		String s = "";
		if (nombre.lastIndexOf(extension)==nombre.length() - 4)
			s = nombre;
		else
			s = new StringBuilder(String.valueOf(nombre)).append(extension).toString();

		return s;
	}


}




















