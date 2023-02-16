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
import java.security.spec.ECField;

// Controlador.java
// Componente Controlador de la arquitectura Modelo-Vista-Controlador

public class Controlador {
	private Modelo miModelo;
	private Vista miVista;
	
	private Point2D puntoInicial;
	private Point2D puntoFinal;
	
	// Variables a modificar con las opciones del menú
	Modelo.Tipo tipo = Modelo.Tipo.Linea;
	Color color = Color.BLACK;
	boolean relleno = true, dibujoGuardado;
	
	public Controlador( Modelo miModelo, Vista miVista )
	{
		this.miModelo = miModelo;
		this.miVista = miVista;
	}
	
	public void iniciarVista()
	{
		// Crear OyenteDeRaton
		OyenteDeRaton odr = new OyenteDeRaton();
		OyenteVentana oV = new OyenteVentana();
		OyenteNuevo oN = new OyenteNuevo();
		OyenteAbrirYGuardarComo oaygc = new OyenteAbrirYGuardarComo();
		OyenteImprimir oi = new OyenteImprimir();
		OyenteAcercaDe oad = new OyenteAcercaDe();
		OyenteSalir os = new OyenteSalir();
		OyenteTipoFigura otf = new OyenteTipoFigura();
		OyenteRelleno or = new OyenteRelleno();
		OyenteColor oc = new OyenteColor();
		
		// Registrar el oyente en el panel
		miVista.miVentana.miPanel.addMouseListener(odr);
		miVista.miVentana.miPanel.addMouseMotionListener(odr);

		miVista.miVentana.addWindowListener(oV);
		miVista.nuevoArchivo.addActionListener(oN);
		miVista.abrirArchivo.addActionListener(oaygc);
		miVista.guardarComo.addActionListener(oaygc);
		miVista.imprimirArchivo.addActionListener(oi);
		miVista.acercaDe.addActionListener(oad);
		miVista.salir.addActionListener(os);

		miVista.dibujarLinea.addActionListener(otf);
		miVista.dibujarRectangulo.addActionListener(otf);
		miVista.dibujarElipse.addActionListener(otf);
		miVista.cmbTipo.addActionListener(otf);

		miVista.rellenarFigura.addActionListener(or);
		miVista.chkRelleno.addActionListener(or);

		miVista.colorFigura.addActionListener(oc);
		miVista.btnColor.addActionListener(oc);
		
		miVista.miVentana.setVisible(true);
	}
	
	private Figura crearFigura()
	{
		Shape temporal;
		Figura resultado = null;
		
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

	private void Salir(){
		if (!dibujoGuardado && Modelo.figuras.size() != 0){
			if (JOptionPane.showConfirmDialog(miVista.miVentana, "Desea Guardar su dibujo antes de salir", "Programa de Dibujo", 0) == 0&& !guardarDibujo())
				return;
				System.exit(0);

		}
		else if (JOptionPane.showConfirmDialog(miVista.miVentana,"Deseas salir de la aplicación?", "Programa de Dibujo", 0)==0){
			System.exit(0);
		}
	}

	private boolean abrirDibujo(){
		boolean regresar = false;
		File dircActual = new File(".");
		try{
			JFileChooser archivo = new JFileChooser(dircActual.getCanonicalPath());
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Mi Dibujo", new String[]{"dbj"});
			archivo.setFileFilter(filter);
			if (archivo.showOpenDialog(miVista.miVentana)==0){
				File archivoAbrir = new File(NombreDelArchivo(archivo.getSelectedFile().getName(), ".dbj"));
				FileInputStream fis = new FileInputStream(archivoAbrir);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Modelo.figuras = (Vector) ois.readObject();
				fis.close();
				ois.close();
				dibujoGuardado = false;
				miVista.nuevoArchivo.setEnabled(true);
				regresar = true;
				miVista.miPanel.repaint();
			}
		}catch (Exception ex){
			JOptionPane.showMessageDialog(miVista.miVentana,"Error al abrir archivo", "Programa de Dibujo", 2);
		}

		return regresar;
	}

	private boolean guardarDibujo() {
		boolean regresar = false;
		File directorioActual = new File(".");
		try {
			JFileChooser archivo = new JFileChooser(directorioActual.getCanonicalPath());
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Mi Dibujo", new String[] { "dbj" });
			archivo.setFileFilter(filtro);
			if (archivo.showSaveDialog(miVista.miVentana) == 0) {
				File archivoGuardar = new File(NombreDelArchivo(archivo.getSelectedFile().getName(), ".dbj"));
				FileOutputStream fos = new FileOutputStream(archivoGuardar);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(Modelo.figuras);
				fos.close();
				oos.close();
				JOptionPane.showMessageDialog(miVista.miVentana, "Archivo guardado correctamente", "Programa de Dibujo", -1);
				dibujoGuardado = true;
				regresar = true;
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(miVista.miVentana, "Error al guardar el archivo.", "Programa de Dibujo", 2);
		}
		return regresar;
	}

	private String NombreDelArchivo(String nombre, String extension) {
		String s = "";
		if (nombre.lastIndexOf(extension) == nombre.length() - 4)
			s = nombre;
		else
			s = new StringBuilder(String.valueOf(nombre)).append(extension).toString();
		;
		return s;
	}

	// Clase interna para los eventos del ratón
	class OyenteDeRaton extends MouseAdapter
	{
		public void mousePressed( MouseEvent e )
		{
			miVista.miVentana.miPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			puntoInicial = e.getPoint();

		}
		
		public void mouseReleased( MouseEvent e )
		{
			miVista.miVentana.miPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			puntoFinal = e.getPoint();
			Modelo.f = crearFigura();
			Modelo.figuras.add( Modelo.f );
			Modelo.f = null;
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

	class OyenteVentana extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {
			Salir();
		}
	}

	class OyenteNuevo implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!dibujoGuardado && Modelo.figuras.size() != 0 && JOptionPane.showConfirmDialog(miVista.miVentana, "¿Deseas guardar su dibujo?", "Programa de Dibujo", 0) == 0 && !guardarDibujo()) {
				return;
			} else {
				Modelo.figuras = new Vector();
				dibujoGuardado = false;
				miVista.nuevoArchivo.setEnabled(false);
				miVista.miPanel.repaint();
				return;
			}
		}
	}

	class OyenteAbrirYGuardarComo implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String cual = e.getActionCommand();
			if (cual.equals("Abrir...")) {
				if (!dibujoGuardado && Modelo.figuras.size() != 0 && JOptionPane.showConfirmDialog(miVista.miVentana, "¿Deseas guardar su dibujo?", "Programa de Dibujo", 0) == 0 && !guardarDibujo())
					return;
				abrirDibujo();
			} else {
				guardarDibujo();
			}
		}
	}

	class OyenteImprimir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			miVista.miPanel.print();
		}
	}

	class OyenteAcercaDe implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(miVista.miVentana, "Proyecto de Dibujo de tipo Modelo-Vista-Controlador \n Autor: Jaime Alonso Ruiz Lizarraga \n No. de Control: 19170736","Programa de Dibujo", -1);
		}
	}

	class OyenteSalir implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Salir();
		}
	}

	class OyenteTipoFigura implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String cual = e.getActionCommand();
			if (cual.equals("Linea")) {
				tipo = Modelo.Tipo.Linea;
				miVista.cmbTipo.setSelectedIndex(0);
				miVista.rellenarFigura.setEnabled(false);
				miVista.chkRelleno.setEnabled(false);
			} else if (cual.equals("Rectangulo")) {
				tipo = Modelo.Tipo.Rectangulo;
				miVista.cmbTipo.setSelectedIndex(1);
				miVista.rellenarFigura.setEnabled(true);
				miVista.chkRelleno.setEnabled(true);
			} else if (cual.equals("Elipse")) {
				tipo = Modelo.Tipo.Elipse;
				miVista.cmbTipo.setSelectedIndex(2);
				miVista.rellenarFigura.setEnabled(true);
				miVista.chkRelleno.setEnabled(true);
			} else {
				switch (miVista.cmbTipo.getSelectedIndex()) {
					case 0:
						tipo = Modelo.Tipo.Linea;
						miVista.dibujarLinea.setSelected(true);
						miVista.rellenarFigura.setEnabled(false);
						miVista.chkRelleno.setEnabled(false);
						break;

					case 1:
						tipo = Modelo.Tipo.Rectangulo;
						miVista.dibujarRectangulo.setSelected(true);
						miVista.rellenarFigura.setEnabled(true);
						miVista.chkRelleno.setEnabled(true);
						break;

					case 2:
						tipo = Modelo.Tipo.Elipse;
						miVista.dibujarElipse.setSelected(true);
						miVista.rellenarFigura.setEnabled(true);
						miVista.chkRelleno.setEnabled(true);
						break;
				}
			}
		}
	}
	class OyenteColor implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Color colorAnterior = color;
			colorAnterior = JColorChooser.showDialog(miVista.miVentana, "Seleccione el color de la figura:",
					colorAnterior);
			if (colorAnterior != null)
				color = colorAnterior;
		}

	}

	class OyenteRelleno implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			relleno = !relleno;
			miVista.rellenarFigura.setSelected(relleno);
			miVista.chkRelleno.setSelected(relleno);
		}

	}

}




















