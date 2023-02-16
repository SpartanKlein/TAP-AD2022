package Unidad_1;
import java.awt.Dialog.ModalityType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import Unidad_1.Vista.MiVentana;

// Controlador.java
// Componente Controlador del MVC

public class Controlador {
	// Instancias de la Vista y el Modelo
	private Modelo miModelo;
	private Vista miVista;
	private Vista.MiCatalogo vistaCatalogo;
	private boolean nuevo;
	
	public Controlador( Modelo mod, Vista vis )
	{
		miModelo = mod;
		miVista = vis;
	}
	
	public void iniciarVista()
	{
		miVista.miVentana.setLocationRelativeTo(null);
		// Crear oyentes de evento
		OyenteElementosMenu oem = new OyenteElementosMenu();
		OyenteVentana ov = new OyenteVentana();
		
		// Asignar oyente a la opción Actualizar Catálogo
		miVista.menuActualizarCatalogo.addActionListener(oem);
		
		// Asignar los oyentes de las demás opciones del menú
		miVista.menuGuardar.addActionListener(oem);
		miVista.menuRecuperar.addActionListener(oem);
		miVista.menuSalir.addActionListener(oem);
		miVista.menuAcercaDe.addActionListener(oem);
		miVista.miVentana.addWindowListener(ov);
		
		miVista.miVentana.setVisible(true);
	}
	
	// Clase Oyente para la casilla de verificación Otros
	class OyenteCasillaOtros implements ItemListener
	{
		public void itemStateChanged( ItemEvent e )
		{
			Vista.txtEspecificar.setEnabled( !Vista.txtEspecificar.isEnabled());
		}
	}
	
	public void mostrarActual()
	{
		char departamento = miModelo.getEmpleados().get(miModelo.getEmpleadoActual() - 1 ).getDepartamento();
		char turno = miModelo.getEmpleados().get(miModelo.getEmpleadoActual() - 1 ).getTurno();
		
		int indiceDepartamento = 0;
		int indiceTurno = 0;
		
		if ( departamento == 'F' )
			indiceDepartamento = 0;
		else if ( departamento == 'A' )
			indiceDepartamento = 1;
		else if ( departamento == 'P' )
			indiceDepartamento = 2;
		else
			indiceDepartamento = 3;
		
		if ( turno == 'M' )
			indiceTurno = 0;
		else if ( turno == 'V' )
			indiceTurno = 1;
		else
			indiceTurno = 2;
		
		// Asignar valores a los campos
		Vista.txtNombre.setText( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getNombre());
		Vista.txtEdad.setText( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getEdad() + "" );
		Vista.rbMasculino.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getGenero() == 'M');
		Vista.rbFemenino.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getGenero() == 'F');
		Vista.cmbDepartamento.setSelectedIndex( indiceDepartamento );
		Vista.lstTurno.setSelectedIndex( indiceTurno );
		Vista.chkActivo.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getActivo());
		Vista.chkLectura.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getLectura());
		Vista.chkDeportes.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getDeportes());
		Vista.chkCine.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getCine());
		Vista.chkTeatro.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getTeatro());
		Vista.chkJuegoSalon.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getJuegoSalon());
		Vista.chkConciertos.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getConciertos());
		Vista.chkOtros.setSelected( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getOtros());
		Vista.txtEspecificar.setText( miModelo.getEmpleados().get(miModelo.getEmpleadoActual()-1).getEspecificar());
	}
	
	// Clase Interna para Oyentes de Opciones de Menú
	class OyenteElementosMenu implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			String cual = e.getActionCommand();
			
			// Preguntar cuál opción del menú generó el evento
			if ( cual.equals("Actualizar catálogo..."))
			{
				
				
				// Oyente de Casilla de Verificación
				OyenteCasillaOtros oco = new OyenteCasillaOtros();
				OyenteBotonesDialogo obd = new OyenteBotonesDialogo();
				OyenteBotonesNavegacion obn = new OyenteBotonesNavegacion();
				
				// Código para mostrar la pantalla de Actualizar Catálogo
				vistaCatalogo = new Vista.MiCatalogo(miVista.miVentana, "Catálogo de Empleados");
				
				// Oyente para los elementos del cuadro de diálogo
				Vista.chkOtros.addItemListener(oco);
				Vista.btnNuevo.addActionListener(obd);
				Vista.btnModificar.addActionListener(obd);
				Vista.btnGuardar.addActionListener(obd);
				Vista.btnCancelar.addActionListener(obd);
				Vista.btnSalir.addActionListener(obd);
				
				Vista.btnInicio.addActionListener(obn);
				Vista.btnAnterior.addActionListener(obn);
				Vista.btnSiguiente.addActionListener(obn);
				Vista.btnFinal.addActionListener(obn);
				Vista.txtClave.addActionListener(obn);

				// Si hay empleados, mostrar el primer empleado y el total de empleados
				if ( miModelo.getEmpleados().size() > 0 )
				{
					miModelo.setEmpleadoActual(1);
					miModelo.setTotalEmpleado( miModelo.getEmpleados().size() );
					Vista.txtClave.setText( miModelo.getEmpleadoActual() + "" );
					Vista.txtTotal.setText( miModelo.getTotalEmpleado() + "" );
					
					mostrarActual();
				}
								
				// Hacer cuadro de diálogo modal y visible
				vistaCatalogo.setModalityType(ModalityType.APPLICATION_MODAL);
				vistaCatalogo.setLocationRelativeTo(null);
				vistaCatalogo.setVisible(true);
			}
			else if ( cual.equals("Guardar") )
			{
				boolean confirmacion = miModelo.guardarEmpleados();
				if (confirmacion)
					JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
				else
					JOptionPane.showMessageDialog(null, "Operación realizada fallida");
				
			}
			else if ( cual.equals("Recuperar") )
			{
				boolean confirmacion = miModelo.recuperarEmpleados();
				if (confirmacion)
					JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
				else
					JOptionPane.showMessageDialog(null, "Operación realizada fallida");
			}
			else if ( cual.equals("Salir") )
			{
				if(JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION) == 0)
					System.exit(0);
			}
			else if ( cual.equals("Acerca de...") )
			{
				JOptionPane.showMessageDialog(miVista.miVentana, "Autor: José Alberto Velasco Barrón","Acerca De", 1);
			}
			
		}		
		
	}
	
	// Clase Interna para Oyentes de los botones de navegacion
	class OyenteBotonesNavegacion implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			String cual = e.getActionCommand();
			
			if ( cual.equals( "<<" ))
			{
				if ( miModelo.getEmpleadoActual() > 1 )
				{
					miModelo.setEmpleadoActual( 1 );
					Vista.txtClave.setText( miModelo.getEmpleadoActual() + "" );
					mostrarActual();
				}
			}
			else if ( cual.equals( "<" ))
			{
				// Regresar 1 empleado atras
				if ( miModelo.getEmpleadoActual() > 1 )
				{
					miModelo.setEmpleadoActual( miModelo.getEmpleadoActual() - 1 );
					Vista.txtClave.setText( miModelo.getEmpleadoActual() + "" );
					mostrarActual();
				}
			}
			else if ( cual.equals( ">" ))
			{
				// Avanzar 1 empleado
				if ( miModelo.getEmpleadoActual() < miModelo.getTotalEmpleado() )
				{
					miModelo.setEmpleadoActual( miModelo.getEmpleadoActual() + 1 );
					Vista.txtClave.setText( miModelo.getEmpleadoActual() + "" );
					mostrarActual();
				}
			}
			else if ( cual.equals( ">>" ))
			{
				if ( miModelo.getEmpleadoActual() < miModelo.getTotalEmpleado() )
				{
					miModelo.setEmpleadoActual( miModelo.getTotalEmpleado());
					Vista.txtClave.setText( miModelo.getEmpleadoActual() + "" );
					mostrarActual();
				}
			}
			else
			{
				// Para el cuadro de texto
				try {
					int idEmpleado = Integer.parseInt(Vista.txtClave.getText());
					if (idEmpleado > 0 && idEmpleado <= miModelo.getTotalEmpleado()) {
						miModelo.setEmpleadoActual(idEmpleado);
						mostrarActual();
					}else {
						
						Vista.txtClave.setText((new StringBuilder(String.valueOf(miModelo.getEmpleadoActual()))).toString());
					}
					
				}catch(Exception ex) {
					Vista.txtClave.setText((new StringBuilder(String.valueOf(miModelo.getEmpleadoActual()))).toString());
				}
				
			}
		}
	}
	
	class OyenteBotonesDialogo implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			String cual = e.getActionCommand();
			
			if ( cual.equals("Nuevo"))
			{
				// Código para permitir agregar un nuevo empleado
				nuevo = true;
				vistaCatalogo.limpiarCampos();
				vistaCatalogo.habilitarCampos();
			}
			else if ( cual.equals( "Modificar" ))
			{
				// Código para permitir modificar un empleado
				vistaCatalogo.habilitarCampos();
				
				miModelo.setEmpleadoActual( miModelo.getEmpleadoActual());
			}
			else if ( cual.equals( "Guardar" ))
			{
				// Si es empleado nuevo agregarlo al vector
				if ( nuevo )
				{
					miModelo.setEmpleadoActual( miModelo.getTotalEmpleado() + 1 );
					miModelo.setTotalEmpleado( miModelo.getTotalEmpleado() + 1 );
					// Agregar nuevo elemento al vector
					miModelo.getEmpleados().add( miModelo.new Empleado() );
					miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setClave( miModelo.getEmpleadoActual() );
					// Actualizar la vista
					Vista.txtClave.setText( miModelo.getEmpleadoActual() + "" );
					Vista.txtTotal.setText( miModelo.getEmpleadoActual() + "" );
					Vista.mostrarModificacion = true;
					nuevo = false;
				}
				// Modificar los datos del empleado según los valores de la vista
				int indiceDepartamento = Vista.cmbDepartamento.getSelectedIndex();
				int indiceTurno = Vista.lstTurno.getSelectedIndex();
				
				char departamento = 'F';
				char turno = 'M';
				
				if ( indiceDepartamento == 0 )
					departamento = 'F';
				else if ( indiceDepartamento == 1 )
					departamento = 'A';
				else if ( indiceDepartamento == 2 )
					departamento = 'P';
				else
					departamento = 'V';
				
				if ( indiceTurno == 0 )
					turno = 'M';
				else if ( indiceTurno == 1 )
					turno = 'V';
				else
					turno = 'N';
				
				// Para validar si en el campo Edad se puso un valor correcto
				try
				{
					Integer.parseInt( Vista.txtEdad.getText() );
				}
				catch ( Exception ex )
				{
					Vista.txtEdad.setText( "0" );
				}
				
				// Guardar todos los valores en el Vector
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setNombre( Vista.txtNombre.getText());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setEdad( Integer.parseInt( Vista.txtEdad.getText() ));
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setGenero(  Vista.rbMasculino.isSelected() ? 'M' : 'F');
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setDepartamento( departamento );
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setTurno( turno );
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setActivo( Vista.chkActivo.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setLectura( Vista.chkLectura.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setDeportes( Vista.chkDeportes.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setCine( Vista.chkCine.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setTeatro( Vista.chkTeatro.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setJuegoSalon( Vista.chkJuegoSalon.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setConciertos( Vista.chkConciertos.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setOtros( Vista.chkOtros.isSelected());
				miModelo.getEmpleados().get( miModelo.getEmpleadoActual() - 1 ).setEspecificar( Vista.txtEspecificar.getText());
				
				mostrarActual();
				vistaCatalogo.deshabilitarCampos( true );
			}
			else if ( cual.equals( "Cancelar" ))
			{
				// Código para deshacer el empleado nuevo o la modificación del empleado.
				// Si no hay empleados limpiar los campos
				// Caso contrario, mostrar el empleado actual
				if ( miModelo.getEmpleados().size() == 0 )
				{
					vistaCatalogo.limpiarCampos();
					Vista.chkActivo.setSelected(true);
					vistaCatalogo.deshabilitarCampos(false);
				}
				else
				{
					mostrarActual();
					vistaCatalogo.deshabilitarCampos(true);					
				}
			}
			else if ( cual.equals( "Salir" ))
			{
				// Código para eliminar el cuadro de dialogo
				vistaCatalogo.dispose();
			}
		}
	}
	class OyenteVentana extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {
			int respuesta=JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
			if(respuesta == 0)
				System.exit(0);
		}
	}
}














