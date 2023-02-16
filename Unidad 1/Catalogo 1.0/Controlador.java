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

import javax.swing.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// Controlador.java
// Componente Controlador del MVC

public class Controlador {
	Modelo miModelo;
	Vista miVista;
	boolean nuevo;
	
	public Controlador(Modelo mod, Vista vis)
	{
		miModelo = mod;
		miVista = vis;
	}
	
	public void iniciarVista()
	{
		miVista.miVentana.setLocationRelativeTo(null);
		// Crear el Oyente de los Elementos del Menú
		OyenteElementosMenu oem = new OyenteElementosMenu();
		//OyenteVentana ov = new OyenteVentana();

		miVista.miVentana.mnuAcercaDe.addActionListener(oem);
		// Registrar oyente en las opciones del menú
		miVista.miVentana.mnuActualizar.addActionListener(oem);

		//Asignar los oyentes de las demás opciones del menú
		miVista.miVentana.mnuGuardar.addActionListener(oem);
		miVista.miVentana.mnuRecuperar.addActionListener(oem);
		miVista.miVentana.mnuSalir.addActionListener(oem);

		//miVista.miVentana.addWindowListener(ov);
		miVista.miVentana.setVisible(true);

	}
	
	// MostrarActual
	public void mostrarActual()
	{
		int posicion = miModelo.getEmpleadoActual() - 1;
		
		char departamento = miModelo.getEmpleados().get(posicion).getDepartamento();
		char turno = miModelo.getEmpleados().get(posicion).getTurno();
		
		int indiceDepartamento = 0;
		int indiceTurno = 0;
		
		if ( departamento == 'F' )
			indiceDepartamento = 0;
		else if ( departamento == 'A' )
			indiceDepartamento = 1;
		else if ( departamento == 'P' )
			indiceDepartamento = 2;
		else if ( departamento == 'V' )
			indiceDepartamento = 3;
		
		if ( turno == 'M' )
			indiceTurno = 0;
		else if ( turno == 'V' )
			indiceTurno = 1;
		else if ( turno == 'N' )
			indiceTurno = 2;
		
		miVista.miCatalogo.miPanel.txtNombre.setText( miModelo.getEmpleados().get(posicion).getNombre());
		miVista.miCatalogo.miPanel.txtEdad.setText( miModelo.getEmpleados().get(posicion).getEdad() + "" );
		miVista.miCatalogo.miPanel.rbtMasculino.setSelected( miModelo.getEmpleados().get(posicion).getGenero() == 'M' );
		miVista.miCatalogo.miPanel.rbtFemenino.setSelected( miModelo.getEmpleados().get(posicion).getGenero() == 'F' );
		miVista.miCatalogo.miPanel.cmbDepartamento.setSelectedIndex(indiceDepartamento);
		miVista.miCatalogo.miPanel.lstTurno.setSelectedIndex(indiceTurno);
		miVista.miCatalogo.miPanel.chkActivo.setSelected( miModelo.getEmpleados().get(posicion).getActivo() );
		miVista.miCatalogo.miPanel.chkLectura.setSelected( miModelo.getEmpleados().get(posicion).getLectura() );
		miVista.miCatalogo.miPanel.chkDeportes.setSelected( miModelo.getEmpleados().get(posicion).getDeportes() );
		miVista.miCatalogo.miPanel.chkCine.setSelected( miModelo.getEmpleados().get(posicion).getCine() );
		miVista.miCatalogo.miPanel.chkTeatro.setSelected( miModelo.getEmpleados().get(posicion).getTeatro() );
		miVista.miCatalogo.miPanel.chkJuegosDeSalon.setSelected( miModelo.getEmpleados().get(posicion).getJuegosDeSalon() );
		miVista.miCatalogo.miPanel.chkConciertos.setSelected( miModelo.getEmpleados().get(posicion).getConciertos() );
		miVista.miCatalogo.miPanel.chkOtros.setSelected( miModelo.getEmpleados().get(posicion).getOtros() );
		miVista.miCatalogo.miPanel.txtOtros.setText( miModelo.getEmpleados().get(posicion).getEspecificar());
	}
	
	// Clase interna para Oyentes de Opciones del Menú
	class OyenteElementosMenu implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cual = e.getActionCommand();

			// Preguntar cual opción es la que generó el evento
			if (cual.equals("Actualizar catálogo..."))
			{
				// Crear oyentes
				OyenteCasillaOtros oco = new OyenteCasillaOtros();
				OyenteBotonesDialogo obd = new OyenteBotonesDialogo();
				OyenteBotonesNavegacion obn = new OyenteBotonesNavegacion();
				
				// Mostrar el cuadro de diálogo
				miVista.miCatalogo = new MiCatalogo(miVista.miVentana, "Catálogo de Empleados");

				// Registrar el oyente en la fuente del evento
				miVista.miCatalogo.miPanel.chkOtros.addItemListener(oco);

				miVista.miCatalogo.miPanel.btnInicio.addActionListener(obn);
				miVista.miCatalogo.miPanel.btnAnterior.addActionListener(obn);
				miVista.miCatalogo.miPanel.btnSiguiente.addActionListener(obn);
				miVista.miCatalogo.miPanel.btnUltimo.addActionListener(obn);
				miVista.miCatalogo.miPanel.txtElementoActual.addActionListener(obn);
				
				miVista.miCatalogo.miPanel.btnNuevo.addActionListener(obd);
				miVista.miCatalogo.miPanel.btnGuardar.addActionListener(obd);
				miVista.miCatalogo.miPanel.btnModificar.addActionListener(obd);
				miVista.miCatalogo.miPanel.btnCancelar.addActionListener(obd);
				miVista.miCatalogo.miPanel.btnSalir.addActionListener(obd);
				

				// Si hay empleados, mostrar el primer empleado y el total de empleados
				if (miModelo.getEmpleados().size() > 0)
				{
					miModelo.setEmpleadoActual(1);
					miModelo.setTotalEmpleados(miModelo.getEmpleados().size());
					miVista.miCatalogo.miPanel.txtElementoActual.setText(miModelo.getEmpleadoActual() + "");
					miVista.miCatalogo.miPanel.txtTotalElementos.setText(miModelo.getTotalEmpleados() + "");
					miVista.miCatalogo.miPanel.mostrarModificacion = true;
					miVista.miCatalogo.miPanel.btnModificar.setEnabled(true);
					mostrarActual();
				}

				// Hacer cuadro de diálogo de tipo Modal
				miVista.miCatalogo.setModalityType(ModalityType.APPLICATION_MODAL);
				miVista.miCatalogo.setVisible(true);

			} else if (cual.equals("Guardar")) {

				if (Controlador.this.miModelo.getEmpleados().size() == 0){
					JOptionPane.showMessageDialog(Controlador.this.miVista.miVentana, "No se encontraron empleados", "cerrar", 0);
					return;
				}
				if (Controlador.this.miModelo.guardarEmpleados()) {
					JOptionPane.showMessageDialog(Controlador.this.miVista.miVentana, "Empleados Guardados en el Disco", "Guardar",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(Controlador.this.miVista.miVentana, "No fue posible guardar los datos de los empleados", "Cerrar", 0);
				}
			}

			else if (cual.equals("Recuperar")) {

				if (Controlador.this.miModelo.getEmpleados().size() > 0) {

				}
				if (1 == JOptionPane.showConfirmDialog(Controlador.this.miVista.miVentana, "La información no respaldada se perderá, ¿Deseas Guardar?","Guardar",0)){
					return;
				}
				if (Controlador.this.miModelo.recuperarEmpleados()) {
					JOptionPane.showMessageDialog(Controlador.this.miVista.miVentana,"Datos de empleados recuperados con éxito", "Recuperar",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(Controlador.this.miVista.miVentana,"No fue posible recuperar los datos de los empleados","Cerrar", 0);
				}
			}

			else if (cual.equals("Acerca de...")) {
				JOptionPane.showMessageDialog(miVista.miVentana, "Programa Catálogo de tipo Modelo - Vista - Controlado \n Elaborado por: Jaime Alonso Ruiz Lizarraga  \n No. de control: 19170736", "Datos del autor", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	// Clase interna para la casilla de verificación Otros
	class OyenteCasillaOtros implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			miVista.miCatalogo.miPanel.txtOtros.setEnabled(miVista.miCatalogo.miPanel.chkOtros.isSelected());
		}
	}
	
	// Clase interna para los botones del cuadro de dialogo
	class OyenteBotonesDialogo implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cual = e.getActionCommand();

			if ( cual.equals("Nuevo"))
			{
				nuevo = true;
				miVista.miCatalogo.miPanel.limpiarComponentes();
				miVista.miCatalogo.miPanel.habilitarComponentes();
			}

			else if (cual.equals("Modificar")) {
				miVista.miCatalogo.miPanel.habilitarComponentes();
				miModelo.setEmpleadoActual(miModelo.getEmpleadoActual());
			}

			else if ( cual.equals("Guardar"))
			{
				// Preguntar si el empleado es nuevo
				// para agregarlo al vector
				if ( nuevo )
				{
					miModelo.getEmpleados().add(new Empleado());
					miModelo.setTotalEmpleados(miModelo.getTotalEmpleados() + 1);
					miModelo.setEmpleadoActual(miModelo.getTotalEmpleados());
					miModelo.getEmpleados().get(miModelo.getTotalEmpleados() - 1 ).setClave(miModelo.getTotalEmpleados());
					
					// Actualizar datos del catálogo
					miVista.miCatalogo.miPanel.txtElementoActual.setText(miModelo.getTotalEmpleados() + "");
					miVista.miCatalogo.miPanel.txtTotalElementos.setText(miModelo.getTotalEmpleados() + "");
					miVista.miCatalogo.miPanel.mostrarModificacion = true;
					nuevo = false;
				}
				
				// Actualizar los datos Departamento y turno
				int indiceDepartamento = miVista.miCatalogo.miPanel.cmbDepartamento.getSelectedIndex();
				int indiceTurno = miVista.miCatalogo.miPanel.lstTurno.getSelectedIndex();
				
				char departamento = 'F';
				char turno = 'M';
				
				if ( indiceDepartamento == 0 )
					departamento = 'F';
				else if ( indiceDepartamento == 1 )
					departamento = 'A';
				else if ( indiceDepartamento == 2 )
					departamento = 'P';
				else if ( indiceDepartamento == 3 )
					departamento = 'V';
				
				if ( indiceTurno == 0 )
					turno = 'M';
				else if ( indiceTurno == 1 )
					turno = 'V';
				else if ( indiceTurno == 2 )
					turno = 'N';
				
				// Validar la edad
				try
				{
					Integer.parseInt( miVista.miCatalogo.miPanel.txtEdad.getText());
				}
				catch ( Exception ex )
				{
					miVista.miCatalogo.miPanel.txtEdad.setText("0");
				}
				
				// Determinar la posición actual
				int posicion = miModelo.getEmpleadoActual() - 1;
				
				// Guardar todo en el vector
				miModelo.getEmpleados().get(posicion).setNombre(miVista.miCatalogo.miPanel.txtNombre.getText());
				miModelo.getEmpleados().get(posicion).setEdad(Integer.parseInt(miVista.miCatalogo.miPanel.txtEdad.getText()));
				miModelo.getEmpleados().get(posicion).setGenero(miVista.miCatalogo.miPanel.rbtMasculino.isSelected() ? 'M' : 'F');
				miModelo.getEmpleados().get(posicion).setDepartamento(departamento);
				miModelo.getEmpleados().get(posicion).setTurno(turno);
				miModelo.getEmpleados().get(posicion).setActivo(miVista.miCatalogo.miPanel.chkActivo.isSelected());
				miModelo.getEmpleados().get(posicion).setLectura(miVista.miCatalogo.miPanel.chkLectura.isSelected());
				miModelo.getEmpleados().get(posicion).setDeportes(miVista.miCatalogo.miPanel.chkDeportes.isSelected());
				miModelo.getEmpleados().get(posicion).setCine(miVista.miCatalogo.miPanel.chkCine.isSelected());
				miModelo.getEmpleados().get(posicion).setTeatro(miVista.miCatalogo.miPanel.chkTeatro.isSelected());
				miModelo.getEmpleados().get(posicion).setJuegosDeSalon(miVista.miCatalogo.miPanel.chkJuegosDeSalon.isSelected());
				miModelo.getEmpleados().get(posicion).setConciertos(miVista.miCatalogo.miPanel.chkConciertos.isSelected());
				miModelo.getEmpleados().get(posicion).setOtros(miVista.miCatalogo.miPanel.chkOtros.isSelected());
				miModelo.getEmpleados().get(posicion).setEspecificar(miVista.miCatalogo.miPanel.txtOtros.getText());
				miVista.miCatalogo.miPanel.deshabilitarComponentes(true);
			}
			else if (cual.equals( "Cancelar" ))
			{
				// Si existen empleados, mostrar el empleado actual
				// si no, mostrar los campos deshabilitados
				if (miModelo.getEmpleados().size() != 0)
				{
					mostrarActual();
					miVista.miCatalogo.miPanel.deshabilitarComponentes(true);
				}
				else
				{
					miVista.miCatalogo.miPanel.limpiarComponentes();
					miVista.miCatalogo.miPanel.deshabilitarComponentes(false);
				}
			}
			else if ( cual.equals( "Salir" )) {
				miVista.miCatalogo.dispose();
			}
		}
	}
	
	// Clase Interna para Oyentes de los Botones de Navegación
	class OyenteBotonesNavegacion implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String cual = e.getActionCommand();
			
			if (cual.equals( "<<" ))
			{   // Ir al inicio
				if (miModelo.getEmpleadoActual() > 1) {
					miModelo.setEmpleadoActual(1);
					miVista.miCatalogo.miPanel.txtElementoActual.setText(miModelo.getEmpleadoActual() + "");
					mostrarActual();
				}
			}
			else if (cual.equals( "<" ))
			{
				// Ir al anterior
				if (miModelo.getEmpleadoActual() > 1)
				{
					miModelo.setEmpleadoActual(miModelo.getEmpleadoActual() - 1);
					miVista.miCatalogo.miPanel.txtElementoActual.setText(miModelo.getEmpleadoActual() + "" );
					mostrarActual();
				}
			}
			else if ( cual.equals( ">" ) )
			{
				// Ir al siguiente
				if(miModelo.getEmpleadoActual() < miModelo.getTotalEmpleados()){
					miModelo.setEmpleadoActual(miModelo.getEmpleadoActual() + 1);
					miVista.miCatalogo.miPanel.txtElementoActual.setText(miModelo.getEmpleadoActual()+ "" );
					mostrarActual();
				}

			}
			else if ( cual.equals( ">>" ) )
			{
				// Ir al último
				if (miModelo.getEmpleadoActual() <= miModelo.getTotalEmpleados()){
					miModelo.setEmpleadoActual(miModelo.getTotalEmpleados());
					miVista.miCatalogo.miPanel.txtElementoActual.setText(miModelo.getEmpleadoActual() + "");
					mostrarActual();
				}

			}
			else { // Ir a la clave del empleado mediante ElementoActual
				try{
					int idEmpleado = Integer.parseInt(miVista.miCatalogo.miPanel.txtElementoActual.getText());
					if (idEmpleado > 0 && idEmpleado <= miModelo.getTotalEmpleados()){
						miModelo.setEmpleadoActual(idEmpleado);
						mostrarActual();
					}
					else {
						miVista.miCatalogo.miPanel.txtElementoActual.setText((new String(String.valueOf(miModelo.getEmpleadoActual()))).toString());
					}
				}catch (Exception ex){
					miVista.miCatalogo.miPanel.txtElementoActual.setText((new String(String.valueOf(miModelo.getEmpleadoActual()))).toString());
				}
			}
		}
	}
}
