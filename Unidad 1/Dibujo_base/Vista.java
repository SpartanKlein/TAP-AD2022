import java.awt.*;

import javax.swing.*;

import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;

// Vista.java
// Implementacion del componente Vista de la arquitectura modelo-Vista-Controlador

public class Vista {
	MiVentana miVentana;

	JMenuItem jmiArchivo;
	JMenuItem jmiAbrir;
	JMenuItem jmiGuardar;
	JMenuItem jmiImprimir;
	JMenuItem jmiSalir;
	JMenuItem colorFig;
	JMenuItem acercaDe;

	JRadioButtonMenuItem jrbLinea;
	JRadioButtonMenuItem jrbRectangulo;
	JRadioButtonMenuItem jrbElipse;

	JCheckBoxMenuItem rellenarFigura;

	JCheckBox chkRelleno;
	JComboBox cmbTipo;
	JButton btnColor;


	public Vista()
	{
		miVentana = new MiVentana( "Proyecto MVC Dibujo" );
		miVentana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		miVentana.setSize( 600, 400 );
		miVentana.setLocationRelativeTo(null);

		JMenuBar barraMenu = new JMenuBar();
		JMenu mnuArchivo = new JMenu("Archivo");
		mnuArchivo.setMnemonic('A');

		JMenu mnuDibujo = new JMenu ("Dibujar");
		mnuDibujo.setMnemonic('D');

		JMenu mnuAyuda = new JMenu("Ayuda");
		mnuAyuda.setMnemonic('A');

		jmiArchivo = new JMenuItem("Nuevo");
		jmiArchivo.setMnemonic('N');
		jmiAbrir = new JMenuItem("Abrir...");
		jmiAbrir.setMnemonic('A');
		jmiGuardar = new JMenuItem("Guardar como...");
		jmiGuardar.setMnemonic('G');
		jmiImprimir = new JMenuItem("Imprimir...");
		jmiImprimir.setMnemonic('I');
		jmiSalir = new JMenuItem("Salir");
		jmiSalir.setMnemonic('S');

		mnuArchivo.add(jmiArchivo);
		mnuArchivo.addSeparator();
		mnuArchivo.add(jmiAbrir);
		mnuArchivo.add(jmiGuardar);
		mnuArchivo.addSeparator();
		mnuArchivo.add(jmiImprimir);
		mnuArchivo.addSeparator();
		mnuArchivo.add(jmiSalir);

		jrbLinea = new JRadioButtonMenuItem("Linea", true);
		jrbLinea.setMnemonic('L');
		jrbRectangulo = new JRadioButtonMenuItem("Rectangulo", false);
		jrbRectangulo.setMnemonic('R');
		jrbElipse = new JRadioButtonMenuItem("Elipse", false);
		jrbElipse.setMnemonic('E');

		rellenarFigura = new JCheckBoxMenuItem("Relleno", false);
		rellenarFigura.setEnabled(false);
		colorFig = new JMenuItem("Color");
		colorFig.setMnemonic('C');

		ButtonGroup tiposDibujos = new ButtonGroup();

		tiposDibujos.add(jrbLinea);
		tiposDibujos.add(jrbRectangulo);
		tiposDibujos.add(jrbElipse);

		mnuDibujo.add(jrbLinea);
		mnuDibujo.add(jrbRectangulo);
		mnuDibujo.add(jrbElipse);
		mnuDibujo.addSeparator();
		mnuDibujo.add(rellenarFigura);
		mnuDibujo.addSeparator();
		mnuDibujo.add(colorFig);

		acercaDe = new JMenuItem("Acerca de...");
		acercaDe.setMnemonic('A');

		mnuAyuda.add(acercaDe);

		barraMenu.add(mnuArchivo);
		barraMenu.add(mnuDibujo);
		barraMenu.add(mnuAyuda);

		miVentana.setJMenuBar(barraMenu);

		JToolBar jtbHerramientas = new JToolBar("Seleccione tipo de dibujo");
		cmbTipo = new JComboBox(new String[] {"Linea","Rectangulo","Elipse"});
		cmbTipo.setSelectedIndex(0);

		chkRelleno = new JCheckBox("Relleno", false);
		chkRelleno.setEnabled(false);
		btnColor = new JButton("Color...");
		JLabel lblTipo = new JLabel("Tipo de Figura: ");

		jtbHerramientas.add(lblTipo);
		jtbHerramientas.add(cmbTipo);
		jtbHerramientas.add(chkRelleno);
		jtbHerramientas.add(btnColor);
		jtbHerramientas.addSeparator(new Dimension(300, 10));

		miVentana.add(jtbHerramientas, "North");

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





