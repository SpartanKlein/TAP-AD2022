/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 07/10/22
//Materia: Tópicos Avanzados de Programación 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 1: Interfaz Gráfica de Usuario
//Proyecto: Programa MVC Catálogo
//Docente: Jaime Arturo Félix Medina
---------------------------------------------------------------
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.Border;

// Vista.java
// Componente Vista de la aplicación Catalogo

public class Vista {
	MiVentana miVentana;
	MiCatalogo miCatalogo;
	
	public Vista()
	{
		miVentana = new MiVentana();
	}	
}

class MiVentana extends JFrame
{
	// Opciones del menú
	JMenuItem mnuActualizar;
	JMenuItem mnuGuardar;
	JMenuItem mnuRecuperar;
	JMenuItem mnuSalir;

	JMenuItem mnuAcercaDe;
		
	public MiVentana()
	{
		super("Proyecto MVC Catálogo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Crear barra de menu
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		// Menu Archivo
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic('A');
		
		// Elementos del menú
		mnuActualizar = new JMenuItem("Actualizar catálogo...");
		mnuActualizar.setMnemonic('A');
		mnuGuardar = new JMenuItem("Guardar");
		mnuGuardar.setMnemonic('G');
		mnuRecuperar = new JMenuItem("Recuperar");
		mnuRecuperar.setMnemonic('R');
		mnuSalir = new JMenuItem("Salir");
		mnuSalir.setMnemonic('S');
		
		// Agregar elementos del menú al menú
		menuArchivo.add(mnuActualizar);
		menuArchivo.addSeparator();
		menuArchivo.add(mnuGuardar);
		menuArchivo.add(mnuRecuperar);
		menuArchivo.addSeparator();
		menuArchivo.add(mnuSalir);
		
		// Agregar el menu a la barra de menús
		barraMenu.add(menuArchivo);

		// Menu Ayuda
		JMenu menuAyuda = new JMenu("Ayuda");
		menuArchivo.setMnemonic('y');

		// Elementos del menú
		mnuAcercaDe = new JMenuItem("Acerca de...");
		mnuAcercaDe.setMnemonic('d');
		
		menuAyuda.add(mnuAcercaDe);
		
		// Agregar el menu a la barra de menús
		barraMenu.add(menuAyuda);
		
		setSize(600, 400);
		//setVisible( true );
	}
}

class MiCatalogo extends JDialog
{
	MiPanel miPanel;
	
	public MiCatalogo(JFrame padre, String titulo)
	{
		super(padre, titulo, false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Crear panel personalizado y agregarlo al cuadro de diálogo
		miPanel = new MiPanel();
		add(miPanel);
		
		setSize(475, 325);
		setResizable(false);
		setVisible(true);
	}
	
	// Panel personalizado
	class MiPanel extends JPanel
	{
		// Declarar componentes del panel de navegación
		JButton btnInicio;
		JButton btnAnterior;
		JLabel et1;
		JTextField txtElementoActual;
		JLabel et2;
		JTextField txtTotalElementos;
		JButton btnSiguiente;
		JButton btnUltimo;
		
		// Declarar componentes del panel de datos generales
		JLabel et3;
		JTextField txtNombre;
		JLabel et4;
		JTextField txtEdad;
		JLabel et5;
		JRadioButton rbtMasculino;
		JRadioButton rbtFemenino;
		JLabel et6;
		JComboBox<String> cmbDepartamento;
		JLabel et7;
		JList<String> lstTurno;
		JCheckBox chkActivo;
		
		// Declarar componentes del panel de actividades
		JCheckBox chkLectura;
		JCheckBox chkDeportes;
		JCheckBox chkCine;
		JCheckBox chkTeatro;
		JCheckBox chkJuegosDeSalon;
		JCheckBox chkConciertos;
		JCheckBox chkOtros;
		JTextField txtOtros;

		// Declarar componentes del panel de botones
		JButton btnNuevo;
		JButton btnModificar;
		JButton btnGuardar;
		JButton btnCancelar;
		JButton btnSalir;
		
		boolean mostrarModificacion = false;
		
		public MiPanel()
		{
			setLayout( new BorderLayout());
			
			// Crear el panel de navegación
			JPanel panelNavegacion = new JPanel();
			panelNavegacion.setLayout(new FlowLayout());
			
			// Crear los componentes del panel de navegación
			btnInicio = new JButton("<<");
			btnAnterior = new JButton("<");
			et1 = new JLabel( "Clave:");
			txtElementoActual = new JTextField("0", 3);
			txtElementoActual.setHorizontalAlignment(JTextField.CENTER);
			et2 = new JLabel("de");
			txtTotalElementos = new JTextField("0", 3);
			txtTotalElementos.setHorizontalAlignment(JTextField.CENTER);
			txtTotalElementos.setEditable(false);
			btnSiguiente = new JButton(">");
			btnUltimo = new JButton(">>");
			
			// Agregar componentes al panel de navegacion
			panelNavegacion.add(btnInicio);
			panelNavegacion.add(btnAnterior);
			panelNavegacion.add(et1);
			panelNavegacion.add(txtElementoActual);
			panelNavegacion.add(et2);
			panelNavegacion.add(txtTotalElementos);
			panelNavegacion.add(btnSiguiente);
			panelNavegacion.add(btnUltimo);
			
			// Crear el panel de datos generales
			JPanel panelDatosGenerales = new JPanel();
			
			// Componentes de los datos generales
			et3 = new JLabel("Nombre:");
			txtNombre = new JTextField(25);
			et4 = new JLabel("Edad:");
			txtEdad = new JTextField(5);
			txtEdad.setHorizontalAlignment(JTextField.RIGHT);
			et5 = new JLabel( "Genero:");
			rbtMasculino = new JRadioButton("Masculino", true);
			rbtFemenino = new JRadioButton("Femenino");
			et6 = new JLabel("Departamento:");
			String[] opDepartamento = {"Finanzas","Administración","Producción","Vigilancia"};
			cmbDepartamento = new JComboBox<String>(opDepartamento);
			et7 = new JLabel("Turno:");
			String[] opTurno = {"Matutino","Vespertino","Nocturno"};
			lstTurno = new JList<String>(opTurno);
			lstTurno.setVisibleRowCount(2);
			chkActivo = new JCheckBox("Activo", true);
			
			// Crear un grupo de botones para que los botones de radio
			// sean mutuamente excluyentes
			ButtonGroup bg = new ButtonGroup();
			bg.add(rbtMasculino);
			bg.add(rbtFemenino);
			
			// Agregar componentes al panel de datos generales
			panelDatosGenerales.add(et3);
			panelDatosGenerales.add(txtNombre);
			panelDatosGenerales.add(et4);
			panelDatosGenerales.add(txtEdad);
			panelDatosGenerales.add(et5);
			panelDatosGenerales.add(rbtMasculino);
			panelDatosGenerales.add(rbtFemenino);
			panelDatosGenerales.add(et6);
			panelDatosGenerales.add(cmbDepartamento);
			panelDatosGenerales.add(et7);
			panelDatosGenerales.add(new JScrollPane(lstTurno));
			panelDatosGenerales.add(chkActivo);
			
			// Crear un panel de actividades
			JPanel panelActividades = new JPanel();
			panelActividades.setLayout( new GridLayout( 3, 3 ));
			
			// Poner un borde
			Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK);
			//panelActividades.setBorder(bordeNegro);
			panelActividades.setBorder(BorderFactory.createTitledBorder(bordeNegro, "Actividades Recreativas"));
			
			// Crear las casillas de verificación de las actividades
			chkLectura = new JCheckBox("Lectura");
			chkDeportes = new JCheckBox("Deportes");
			chkCine = new JCheckBox("Cine");
			chkTeatro = new JCheckBox("Teatro");
			chkJuegosDeSalon = new JCheckBox("Juegos de Salón");
			chkConciertos = new JCheckBox("Conciertos");
			chkOtros = new JCheckBox("Otros");
			txtOtros = new JTextField(13);
			txtOtros.setEnabled(false);
			
			// Agregar casillas al panel de Actividades
			panelActividades.add(chkLectura);
			panelActividades.add(chkDeportes);
			panelActividades.add(chkCine);
			panelActividades.add(chkTeatro);
			panelActividades.add(chkJuegosDeSalon);
			panelActividades.add(chkConciertos);
			panelActividades.add(chkOtros);
			panelActividades.add(txtOtros);
			
			// Agregar panel de actividades al panel de datos generales
			panelDatosGenerales.add(panelActividades);
			
			// Crear el panel de botones
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			
			// Crear botones
			btnNuevo = new JButton("Nuevo");
			btnNuevo.setMnemonic('N');
			btnModificar = new JButton("Modificar");
			btnModificar.setMnemonic('M');
			btnGuardar = new JButton("Guardar");
			btnGuardar.setMnemonic('G');
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('C');
			btnSalir = new JButton("Salir");
			btnSalir.setMnemonic('S');

			// Agregar componentes al panel de botones
			panelBotones.add(btnNuevo);
			panelBotones.add(btnModificar);
			panelBotones.add(btnGuardar);
			panelBotones.add(btnCancelar);
			panelBotones.add(btnSalir);
			
			// Agregar panel de navegación en la parte Norte
			add(panelNavegacion, BorderLayout.NORTH);
			// Agregar panel de datos generales en la parte Central
			add(panelDatosGenerales, BorderLayout.CENTER);
			// Agregar panel de botones en la parte Sur
			add(panelBotones, BorderLayout.SOUTH);
			
			deshabilitarComponentes(mostrarModificacion);
		}
		
		// Método para deshabilitar componentes
		public void deshabilitarComponentes(boolean mostrarModificacion)
		{
			// Panel de navegación
			btnInicio.setEnabled(true);
			btnAnterior.setEnabled(true);
			txtElementoActual.setEnabled(true);
			btnSiguiente.setEnabled(true);
			btnUltimo.setEnabled(true);
			
			// Panel de datos generales
			txtNombre.setEnabled(false);
			txtEdad.setEnabled(false);
			rbtMasculino.setEnabled(false);
			rbtFemenino.setEnabled(false);
			cmbDepartamento.setEnabled(false);
			lstTurno.setEnabled(false);
			chkActivo.setEnabled(false);
			
			// Panel de actividades recreativas
			chkLectura.setEnabled(false);
			chkDeportes.setEnabled(false);
			chkCine.setEnabled(false);
			chkTeatro.setEnabled(false);
			chkJuegosDeSalon.setEnabled(false);
			chkConciertos.setEnabled(false);
			chkOtros.setEnabled(false);
			txtOtros.setEnabled(false);
			
			// Panel de botones
			btnNuevo.setEnabled(true);
			btnModificar.setEnabled(mostrarModificacion);
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
			btnSalir.setEnabled(true);
		}

		// Método para habilitar componentes
		public void habilitarComponentes()
		{
			// Panel de navegación
			btnInicio.setEnabled(false);
			btnAnterior.setEnabled(false);
			txtElementoActual.setEnabled(false);
			btnSiguiente.setEnabled(false);
			btnUltimo.setEnabled(false);
			
			// Panel de datos generales
			txtNombre.setEnabled(true);
			txtEdad.setEnabled(true);
			rbtMasculino.setEnabled(true);
			rbtFemenino.setEnabled(true);
			cmbDepartamento.setEnabled(true);
			lstTurno.setEnabled(true);
			chkActivo.setEnabled(true);
			
			// Panel de actividades recreativas
			chkLectura.setEnabled(true);
			chkDeportes.setEnabled(true);
			chkCine.setEnabled(true);
			chkTeatro.setEnabled(true);
			chkJuegosDeSalon.setEnabled(true);
			chkConciertos.setEnabled(true);
			chkOtros.setEnabled(true);
			txtOtros.setEnabled(chkOtros.isSelected());
			
			// Panel de botones
			btnNuevo.setEnabled(false);
			btnModificar.setEnabled(false);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnSalir.setEnabled(false);
		}
		
		public void limpiarComponentes()
		{
			txtNombre.setText("");
			txtEdad.setText("");
			rbtMasculino.setSelected(true);
			rbtFemenino.setSelected(false);
			cmbDepartamento.setSelectedIndex(0);
			lstTurno.setSelectedIndex(0);
			chkActivo.setSelected(true);

			chkLectura.setSelected(false);
			chkDeportes.setSelected(false);
			chkCine.setSelected(false);
			chkTeatro.setSelected(false);
			chkJuegosDeSalon.setSelected(false);
			chkConciertos.setSelected(false);
			chkOtros.setSelected(false);
			txtOtros.setText("");
		}
	}
}

