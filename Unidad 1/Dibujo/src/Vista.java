import java.awt.*;

import javax.swing.*;

import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;

// Vista.java
// Implementacion del componente Vista de la arquitectura Modelo-Vista-Controlador

public class Vista {
	MiVentana miVentana;
	MiPanel miPanel;
	JMenuItem nuevoArchivo, abrirArchivo, guardarComo, imprimirArchivo, salir, colorFigura, acercaDe;
	JRadioButtonMenuItem dibujarLinea,dibujarRectangulo,dibujarElipse;
	JCheckBoxMenuItem rellenarFigura;
	JCheckBox chkRelleno;
	JComboBox cmbTipo;
	JButton btnColor;

	
	public Vista()
	{
		miVentana = new MiVentana( "Proyecto MVC Dibujo" );
		miVentana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		miVentana.setLayout(new BorderLayout());
		miVentana.setSize( 600, 400 );


		JMenuBar barraMenu = new JMenuBar();
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('A');

		nuevoArchivo = new JMenuItem("Nuevo");
		nuevoArchivo.setMnemonic('N');
		nuevoArchivo.setEnabled(false);
		abrirArchivo = new JMenuItem("Abrir...");
		abrirArchivo.setMnemonic('A');
		guardarComo = new JMenuItem("Guardar como...");
		guardarComo.setMnemonic('G');
		imprimirArchivo = new JMenuItem("Imprimir...");
		imprimirArchivo.setMnemonic('I');
		salir = new JMenuItem("Salir");
		salir.setMnemonic('S');

		menuArchivo.add(nuevoArchivo);
		menuArchivo.addSeparator();
		menuArchivo.add(abrirArchivo);
		menuArchivo.add(guardarComo);
		menuArchivo.addSeparator();
		menuArchivo.add(imprimirArchivo);
		menuArchivo.addSeparator();
		menuArchivo.add(salir);
		barraMenu.add(menuArchivo);

		JMenu mnuDibujo = new JMenu("Dibujar");
		mnuDibujo.setMnemonic('D');
		dibujarLinea =  new JRadioButtonMenuItem("Linea", true);
		dibujarLinea.setMnemonic('L');
		dibujarRectangulo = new JRadioButtonMenuItem("Rectagulo");
		dibujarRectangulo.setMnemonic('R');
		dibujarElipse = new JRadioButtonMenuItem("Elipse");
		dibujarElipse.setMnemonic('E');
		rellenarFigura = new JCheckBoxMenuItem("Relleno", false);
		rellenarFigura.setMnemonic('R');
		rellenarFigura.setEnabled(false);
		colorFigura = new JMenuItem("Color...");
		colorFigura.setMnemonic('C');

		ButtonGroup btgTipos = new ButtonGroup();
		btgTipos.add(dibujarLinea);
		btgTipos.add(dibujarRectangulo);
		btgTipos.add(dibujarElipse);
		mnuDibujo.add(dibujarLinea);
		mnuDibujo.add(dibujarRectangulo);
		mnuDibujo.add(dibujarElipse);
		mnuDibujo.addSeparator();
		mnuDibujo.add(colorFigura);

		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setMnemonic('u');
		acercaDe = new JMenuItem("Acerca de...");
		acercaDe.setMnemonic('A');
		menuAyuda.add(acercaDe);

		barraMenu.add(menuArchivo);
		barraMenu.add(mnuDibujo);
		barraMenu.add(menuAyuda);
		miVentana.setJMenuBar(barraMenu);

		JToolBar barraHerramientas = new JToolBar("Seleccionar tipo de dibujo");
		JLabel labelTipo = new JLabel("Tipo de dibujo: ");
		cmbTipo = new JComboBox(new String[] { "Linea", "Rectangulo", "Elipse" });
		cmbTipo.setSelectedIndex(0);
		chkRelleno = new JCheckBox("Relleno", false);
		chkRelleno.setEnabled(false);
		btnColor = new JButton("Color...");
		barraHerramientas.add(labelTipo);
		barraHerramientas.add(cmbTipo);
		barraHerramientas.add(chkRelleno);
		barraHerramientas.add(btnColor);
		barraHerramientas.addSeparator(new Dimension(300, 10));
		miVentana.add(barraHerramientas, "North");

	}
}

class MiVentana extends JFrame
{
	MiPanel miPanel;
	
	public MiVentana( String titulo )
	{
		super( titulo );
		
		miPanel = new MiPanel();
		add( miPanel );
	}
}

class MiPanel extends JPanel
{
	public MiPanel()
	{
		setOpaque( true );
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		
		// Convertir de Graphics a Graphics2D
		Graphics2D g2D = ( Graphics2D ) g;
		
		for ( Figura f : Modelo.figuras)
			f.dibujar( g2D );
	}
	
	public void print(){
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName(" Programa de Dibujo ");

		pj.setPrintable (new Printable() {    
			public int print(Graphics pg, PageFormat pf, int pageNum){
				if (pageNum > 0){
					return Printable.NO_SUCH_PAGE;
				}

				Graphics2D g2 = (Graphics2D) pg;
				g2.translate(pf.getImageableX(), pf.getImageableY());
				double factorEscalaX = 50.0;
				double factorEscalaY = 50.0;
				g2.scale(factorEscalaX/pf.getImageableWidth(), factorEscalaY/pf.getImageableHeight());
				paint(g2);
				return Printable.PAGE_EXISTS;
			}
		});
		if (pj.printDialog() == false)
			return;

		try {
			pj.print();
		} catch (PrinterException ex) {
			// handle exception
		}
	}
	
}





