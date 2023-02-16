package Unidad_1;
import java.awt.BorderLayout;
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

// Vista.java
// Componente Vista del MVC
public class Vista {
	MiVentana miVentana;
	// Elementos de la Vista utilizados por el Controlador y el Modelo
	JMenuItem menuActualizarCatalogo;
	JMenuItem menuGuardar;
	JMenuItem menuRecuperar;
	JMenuItem menuSalir;
	JMenuItem menuAcercaDe;
	
	// Elementos del Cuadro de Diálogo
	static JButton btnInicio;
	static JButton btnAnterior;
	static JTextField txtClave;
	static JTextField txtTotal;
	static JButton btnSiguiente;
	static JButton btnFinal;
	
	static JTextField txtNombre;
	static JTextField txtEdad;
	static JRadioButton rbMasculino;
	static JRadioButton rbFemenino;
	static JComboBox<String> cmbDepartamento;
	static JList<String> lstTurno;
	static JCheckBox chkActivo;
	
	static JCheckBox chkLectura;
	static JCheckBox chkDeportes;
	static JCheckBox chkCine;
	static JCheckBox chkTeatro;
	static JCheckBox chkJuegoSalon;
	static JCheckBox chkConciertos;
	static JCheckBox chkOtros;
	static JTextField txtEspecificar;
	
	static JButton btnNuevo;
	static JButton btnModificar;	
	static JButton btnGuardar;
	static JButton btnCancelar;	
	static JButton btnSalir;
	
	static boolean mostrarModificacion = false;
	
	public Vista()
	{
		miVentana = new MiVentana( "Proyecto MVC Catálogo" );
		miVentana.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		
		miVentana.setSize( 600, 400 );
	}
	
	
	// clase interna MiVentana
	class MiVentana extends JFrame
	{
		public MiVentana( String titulo )
		{
			super( titulo );
			
			// Crear una barra de menú
			JMenuBar barraMenu = new JMenuBar();
			// Poner la barra de menú en la ventana
			setJMenuBar( barraMenu );
			
			// Menú Archivo
			JMenu menuArchivo = new JMenu( "Archivo" );
			menuArchivo.setMnemonic( 'A' );
			
			// Crear elementos del menú Archivo
			menuActualizarCatalogo = new JMenuItem( "Actualizar catálogo..." );
			menuActualizarCatalogo.setMnemonic( 'c' );
			
			menuGuardar = new JMenuItem( "Guardar" );
			menuGuardar.setMnemonic( 'G' );
			
			menuRecuperar = new JMenuItem( "Recuperar" );
			menuRecuperar.setMnemonic( 'R' );
			
			menuSalir = new JMenuItem( "Salir" );
			menuSalir.setMnemonic( 'S' );
			
			// Agregar elementos del menú Archivo
			menuArchivo.add( menuActualizarCatalogo );
			menuArchivo.addSeparator();
			menuArchivo.add(menuGuardar);
			menuArchivo.add(menuRecuperar);
			menuArchivo.addSeparator();
			menuArchivo.add(menuSalir);
			
			// Agregar el menú Archivo a la barra del menú
			barraMenu.add(menuArchivo);
			
			// Menú Ayuda
			JMenu menuAyuda = new JMenu( "Ayuda" );
			menuAyuda.setMnemonic( 'y' );
			
			// Crear elementos del menú Ayuda
			menuAcercaDe = new JMenuItem( "Acerca de..." );
			menuAcercaDe.setMnemonic( 'e' );
						
			// Agregar elementos del menú Ayuda
			menuAyuda.add( menuAcercaDe );
			
			// Agregar el menú Ayuda a la barra del menú
			barraMenu.add(menuAyuda);			
		}
	}
	
	// Clase interna para implementar el cuadro de diálogo del catálogo de empleados
	static class MiCatalogo extends JDialog
	{
		public MiCatalogo( JFrame padre, String titulo )
		{
			super( padre, titulo );
			setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE);
			
			MiPanel miPanel = new MiPanel();
			add( miPanel );
			
			setSize( 475, 325 );
			setResizable( false );
			// setVisible( true );
		}
		
		// Clase interna MiPanel con la interfaz de usuario del cuadro de diálogo
		class MiPanel extends JPanel
		{
			public MiPanel()
			{
				setLayout( new BorderLayout() );
				
				// Panel de navegación superior
				JPanel panelNavegacion = new JPanel();
				panelNavegacion.setLayout( new FlowLayout( FlowLayout.CENTER ));
				
				// Elementos del panel de navegación
				btnInicio = new JButton( "<<" );
				btnAnterior = new JButton( "<" );
				JLabel lblClave = new JLabel( "Clave:" );
				txtClave = new JTextField( "0",3 );
				txtClave.setHorizontalAlignment(JTextField.CENTER);
				JLabel lblDe = new JLabel( "de" );
				txtTotal = new JTextField( "0",3 );
				txtTotal.setHorizontalAlignment(JTextField.CENTER);
				txtTotal.setEditable( false );
				btnSiguiente = new JButton( ">" );
				btnFinal = new JButton( ">>" );
				
				// Agregar elementos al panel de navegación
				panelNavegacion.add( btnInicio );
				panelNavegacion.add( btnAnterior );
				panelNavegacion.add( lblClave );
				panelNavegacion.add( txtClave );
				panelNavegacion.add( lblDe );
				panelNavegacion.add( txtTotal );
				panelNavegacion.add( btnSiguiente );
				panelNavegacion.add( btnFinal );
				
				// Panel de Datos Generales
				JPanel panelDatosGenerales = new JPanel();
				
				// Componentes diversos de datos generales
				JLabel lblNombre = new JLabel( "Nombre:" );
				txtNombre = new JTextField(25);
				JLabel lblEdad = new JLabel( "Edad:" );
				txtEdad = new JTextField(5);
				txtEdad.setHorizontalAlignment(JTextField.RIGHT);
				JLabel lblGenero = new JLabel( "Genero:" );
				rbMasculino = new JRadioButton( "Masculino", true);
				rbFemenino = new JRadioButton( "Femenino" );
				JLabel lblDepartamento = new JLabel( "Departamento:" );
				cmbDepartamento = new JComboBox<String>( new String[] {"Finanzas", "Administración", "Producción", "Vigilancia"} );
				JLabel lblTurno = new JLabel( "Turno:");
				lstTurno = new JList<String>( new String[] {"Matutino", "Vespertino", "Nocturno"} );
				lstTurno.setVisibleRowCount(2);
				chkActivo = new JCheckBox( "Activo" );
				
				// Agregar componentes al panelDatosGenerales
				panelDatosGenerales.add( lblNombre );
				panelDatosGenerales.add( txtNombre );
				panelDatosGenerales.add( lblEdad );
				panelDatosGenerales.add( txtEdad );
				panelDatosGenerales.add( lblGenero );
				panelDatosGenerales.add( rbMasculino );
				panelDatosGenerales.add( rbFemenino );
				panelDatosGenerales.add( lblDepartamento );
				panelDatosGenerales.add( cmbDepartamento );				
				panelDatosGenerales.add( lblTurno );
				panelDatosGenerales.add( new JScrollPane(lstTurno) );
				panelDatosGenerales.add( chkActivo );
				
				// Grupo de botones
				ButtonGroup bg = new ButtonGroup();
				bg.add(rbMasculino);
				bg.add(rbFemenino);
				
				// Panel de actividades recreativas
				JPanel panelActividades = new JPanel();
				panelActividades.setLayout( new GridLayout( 3, 3 ));
				panelActividades.setBorder( BorderFactory.createTitledBorder( "Actividades Recreativas:" ));
				
				// Crear casillas de verificación y el cuadro de texto
				chkLectura = new JCheckBox( "Lectura" );
				chkDeportes = new JCheckBox( "Deportes" );
				chkCine = new JCheckBox( "Cine" );
				chkTeatro = new JCheckBox( "Teatro" );
				chkJuegoSalon = new JCheckBox( "Juegos de Salón" );
				chkConciertos = new JCheckBox( "Conciertos" );
				chkOtros = new JCheckBox( "Otros" );
				txtEspecificar = new JTextField( 13 );
				txtEspecificar.setEnabled(false);
				
				// Agregar casillas y cuadro de texto al panel de actividades
				panelActividades.add( chkLectura );
				panelActividades.add( chkDeportes );
				panelActividades.add( chkCine );
				panelActividades.add( chkTeatro );
				panelActividades.add( chkJuegoSalon );
				panelActividades.add( chkConciertos );
				panelActividades.add( chkOtros );
				panelActividades.add( txtEspecificar );
				
				// Agregar panel de actividades al panel de datos generales
				panelDatosGenerales.add( panelActividades );
				
				// Panel de botones inferior
				JPanel panelBotones = new JPanel();
				panelBotones.setLayout( new FlowLayout( FlowLayout.CENTER ));
				
				// Botones Nuevo, Modificar, Guardar, Cancelar y Salir
				btnNuevo = new JButton( "Nuevo" );
				btnNuevo.setMnemonic('N');
				btnModificar = new JButton( "Modificar" );
				btnModificar.setMnemonic('M');
				btnGuardar = new JButton( "Guardar" );
				btnGuardar.setMnemonic('G');
				btnCancelar = new JButton( "Cancelar" );
				btnCancelar.setMnemonic('C');
				btnSalir = new JButton( "Salir" );
				btnSalir.setMnemonic('S');
				
				
				// Agregar los botones al panel
				panelBotones.add(btnNuevo);
				panelBotones.add(btnModificar);
				panelBotones.add(btnGuardar);
				panelBotones.add(btnCancelar);				
				panelBotones.add(btnSalir);
				
				// Agregar paneles al cuadro de diálogo
				add( panelNavegacion, BorderLayout.NORTH);
				add( panelDatosGenerales, BorderLayout.CENTER);
				add( panelBotones, BorderLayout.SOUTH);
				
				//Deshabilitar todos los campos
				deshabilitarCampos( mostrarModificacion );
			}
		}
		
		// Habilitar los campos
		public void habilitarCampos()
		{
			btnInicio.setEnabled(false);
			btnAnterior.setEnabled(false);
			txtClave.setEnabled( false );
			btnSiguiente.setEnabled(false);
			btnFinal.setEnabled(false);
			
			txtNombre.setEnabled(true);
			txtEdad.setEnabled( true );
			rbMasculino.setEnabled(true);
			rbFemenino.setEnabled(true);
			cmbDepartamento.setEnabled(true);
			lstTurno.setEnabled( true );
			chkActivo.setEnabled(true);
			chkLectura.setEnabled(true);
			chkDeportes.setEnabled(true);
			chkCine.setEnabled(true);
			chkTeatro.setEnabled(true);
			chkJuegoSalon.setEnabled(true);
			chkConciertos.setEnabled(true);
			chkOtros.setEnabled(true);
			txtEspecificar.setEnabled(chkOtros.isSelected());
			
			btnNuevo.setEnabled(false);
			btnModificar.setEnabled(false);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnSalir.setEnabled(false);			
		}
		
		// Deshabilitar los campos
		public void deshabilitarCampos( boolean modificar)
		{
			btnInicio.setEnabled(true);
			btnAnterior.setEnabled(true);
			txtClave.setEnabled( true);
			btnSiguiente.setEnabled(true);
			btnFinal.setEnabled(true);
			
			txtNombre.setEnabled(false);
			txtEdad.setEnabled( false );
			rbMasculino.setEnabled(false);
			rbFemenino.setEnabled(false);
			cmbDepartamento.setEnabled(false);
			lstTurno.setEnabled( false );
			chkActivo.setEnabled(false);
			chkLectura.setEnabled(false);
			chkDeportes.setEnabled(false);
			chkCine.setEnabled(false);
			chkTeatro.setEnabled(false);
			chkJuegoSalon.setEnabled(false);
			chkConciertos.setEnabled(false);
			chkOtros.setEnabled(false);
			txtEspecificar.setEnabled(false);
			
			btnNuevo.setEnabled(true);
			btnModificar.setEnabled(modificar);
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
			btnSalir.setEnabled(true);			
		}
		
		public void limpiarCampos()
		{
			// Limpiar campos
			txtNombre.setText("");
			txtEdad.setText( "" );
			rbMasculino.setSelected(true);
			rbFemenino.setSelected(false);
			cmbDepartamento.setSelectedIndex(0);
			lstTurno.setSelectedIndex( 0 );
			chkActivo.setSelected(true);
			chkLectura.setSelected(false);
			chkDeportes.setSelected(false);
			chkCine.setSelected(false);
			chkTeatro.setSelected(false);
			chkJuegoSalon.setSelected(false);
			chkConciertos.setSelected(false);
			chkOtros.setSelected(false);
			txtEspecificar.setText("");			
		}
		
	}
	
}















