/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 07/10/22
//Materia: T�picos Avanzados de Programaci�n 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 1: Interfaz Gr�fica de Usuario
//Proyecto: Programa MVC Cat�logo
//Docente: Jaime Arturo F�lix Medina
---------------------------------------------------------------
 */

import javax.swing.*;
import java.io.*;
import java.util.Vector;

// Modelo.java
// Componente Modelo de la Aplicaci�n
public class Modelo {
	private int totalEmpleados;
	private int empleadoActual;
	private Vector<Empleado> empleados;

	// Constructor
	public Modelo()
	{
		totalEmpleados = 0;
		empleadoActual = 0;
		empleados = new Vector<Empleado>();
	}

	// M�todos set
	public void setTotalEmpleados(int totalEmpleados)
	{
		this.totalEmpleados = totalEmpleados;
	}

	public void setEmpleadoActual(int empleadoActual)
	{
		this.empleadoActual = empleadoActual;
	}

	public void setEmpleados(Vector<Empleado> empleados)
	{
		this.empleados = empleados;
	}

	// M�todos get
	public int getTotalEmpleados()
	{
		return this.totalEmpleados;
	}

	public int getEmpleadoActual()
	{
		return this.empleadoActual;
	}

	public Vector<Empleado> getEmpleados()
	{
		return this.empleados;
	}

	// M�todo para guardar los datos en un archivo
	public boolean guardarEmpleados()
	{
		boolean resultado = true;
		try {
			File guardarVector = new File("Empleados.dat");
			FileOutputStream fos = new FileOutputStream(guardarVector);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(empleados);
			fos.close();
			oos.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			resultado = false;
		}
		return resultado;
	}

	// M�todo para recuperar los datos de un archivo
	public boolean recuperarEmpleados()
	{
		boolean resultado = true;
		try {
			File recuperarVector = new File("Empleados.dat");
			FileInputStream fis = new FileInputStream(recuperarVector);
			ObjectInputStream ois = new ObjectInputStream(fis);
			empleados = (Vector<Empleado>)ois.readObject();
			fis.close();
			ois.close();
			empleadoActual = 1;
			totalEmpleados = empleados.size();
		} catch(ClassNotFoundException ex){
			resultado = false;
		} catch(IOException ex){
			resultado = false;
		}
		return resultado;
	}
}

class Empleado implements Serializable
{
	private	int clave;
	private	String nombre;
	private int edad;
	private char genero;
	private char departamento;
	private char turno;
	private boolean activo;
	private boolean lectura;
	private boolean deportes;
	private boolean cine;
	private boolean teatro;
	private boolean juegosDeSalon;
	private boolean conciertos;
	private boolean otros;
	private String especificar;

	// Constructor por omisi�n
	public Empleado()
	{
	}

	// M�todos set
	public void setClave(int clave)
	{
		this.clave = clave;
	}
	public void setNombre(String nombre)
	{
		this.nombre = (nombre != "") ? nombre : this.nombre;
	}
	public void setEdad(int edad)
	{
		this.edad = (edad>0) ? edad : this.edad;
	}

	public void setGenero(char genero)
	{
		if ( genero == 'M' || genero == 'F' )
			this.genero = genero;
	}

	public void setDepartamento(char departamento)
	{
		if (departamento == 'F' || departamento == 'A' || departamento == 'P' || departamento == 'V')
			this.departamento = departamento;
	}

	public void setTurno(char turno)
	{
		if (turno == 'M' || turno == 'V' || turno == 'N')
			this.turno = turno;
	}

	public void setActivo(boolean activo)
	{
		this.activo = activo;
	}

	public void setLectura(boolean lectura)
	{
		this.lectura = lectura;
	}

	public void setDeportes(boolean deportes)
	{
		this.deportes = deportes;
	}

	public void setCine(boolean cine)
	{
		this.cine = cine;
	}

	public void setTeatro(boolean teatro)
	{
		this.teatro = teatro;
	}

	public void setJuegosDeSalon(boolean juegosDeSalon)
	{
		this.juegosDeSalon = juegosDeSalon;
	}

	public void setConciertos(boolean conciertos)
	{
		this.conciertos = conciertos;
	}

	public void setOtros(boolean otros)
	{
		this.otros = otros;
	}

	public void setEspecificar(String especificar)
	{
		this.especificar = especificar;
	}

	// M�todos get
	public int getClave()
	{
		return this.clave;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public int getEdad()
	{
		return this.edad;
	}

	public char getGenero()
	{
		return this.genero;
	}

	public char getDepartamento()
	{
		return this.departamento;
	}

	public char getTurno()
	{
		return this.turno;
	}

	public boolean getActivo()
	{
		return this.activo;
	}

	public boolean getLectura()
	{
		return this.lectura;
	}

	public boolean getDeportes()
	{
		return this.deportes;
	}

	public boolean getCine()
	{
		return this.cine;
	}

	public boolean getTeatro()
	{
		return this.teatro;
	}

	public boolean getJuegosDeSalon()
	{
		return this.juegosDeSalon;
	}

	public boolean getConciertos()
	{
		return this.conciertos;
	}

	public boolean getOtros()
	{
		return this.otros;
	}

	public String getEspecificar()
	{
		return this.especificar;
	}

}
