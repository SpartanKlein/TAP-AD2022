package Unidad_1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;


public class Modelo implements Serializable {
	// Total de empleados
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
	
	// Métodos set
	public void setTotalEmpleado( int totalEmpleados )
	{
		this.totalEmpleados = totalEmpleados;
	}
	
	public void setEmpleadoActual( int empleadoActual )
	{
		this.empleadoActual = empleadoActual;
	}
	
	public void setEmpleados( Vector<Empleado> empleados)
	{
		this.empleados = empleados;
	}
	
	// Métodos get
	public int getTotalEmpleado()
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
	
	// Método para guardar los datos en un archivo
	public boolean guardarEmpleados()
	{
		try {
			// Regresar true si los datos se pudieron guardar
			FileOutputStream escribiendoFichero = new FileOutputStream( new File("objetos.dat"));
			ObjectOutputStream oos = new ObjectOutputStream(escribiendoFichero);
		    oos.writeObject(empleados);
		    oos.close();
		    escribiendoFichero.close();
		    return true;
		}catch(Exception e) {
			// Regresar false si los datos no se pudieron guardar
			return false;
		}
	}
	
	// Método para recuperar los datos de un archivo
	public boolean recuperarEmpleados()
	{
		
		try {
			// Regresar true si los datos se pudieron recuperar
			 FileInputStream leyendoFichero = new FileInputStream( new File("objetos.dat") );
			 ObjectInputStream ois = new ObjectInputStream(leyendoFichero);
			 empleados = ( Vector <Empleado> )ois.readObject();
			 ois.close();
			 leyendoFichero.close();
			 
			 totalEmpleados = empleados.size();
			 return true;
		}catch(Exception e){
			// Regresar false si los datos no se pudieron recuperar
			return false;
		}
	}

	// Clase interna para los datos del Empleado
	class Empleado implements Serializable 
	{
		private int clave;
		private String nombre;
		private char genero;
		private int edad;
		private char departamento;
		private char turno;		
		private boolean activo;
		private boolean lectura;
		private boolean deportes;
		private boolean cine;
		private boolean teatro;
		private boolean juegoSalon;
		private boolean conciertos;
		private boolean otros;
		private String especificar;
		
		// Constructor por omisión
		public Empleado()
		{			
		}
		
		// Métodos set
		public void setClave( int clave )
		{
			this.clave = clave;
		}
		
		public void setNombre( String nombre )
		{
			this.nombre = nombre;
		}
		
		public void setGenero( char genero )
		{
			if ( genero == 'M' || genero == 'F' )
				this.genero = genero;
		}
		
		public void setEdad( int edad )
		{
			if ( edad > 0 )
				this.edad = edad;
		}
		
		public void setDepartamento( char departamento )
		{
			if ( departamento == 'F' || departamento == 'A' || departamento == 'P' || departamento == 'V' )
				this.departamento = departamento;
		}

		public void setTurno( char turno )
		{
			if ( turno == 'M' || turno == 'V' || turno == 'N' )
				this.turno = turno;
		}
		
		public void setActivo( boolean activo )
		{
			this.activo = activo;
		}

		public void setLectura( boolean lectura )
		{
			this.lectura = lectura;
		}

		public void setDeportes( boolean deportes )
		{
			this.deportes = deportes;
		}

		public void setCine( boolean cine )
		{
			this.cine = cine;
		}

		public void setTeatro( boolean teatro )
		{
			this.teatro = teatro;
		}

		public void setJuegoSalon( boolean juegoSalon )
		{
			this.juegoSalon = juegoSalon;
		}

		public void setConciertos( boolean conciertos )
		{
			this.conciertos = conciertos;
		}

		public void setOtros( boolean otros )
		{
			this.otros = otros;
		}

		public void setEspecificar( String especificar )
		{
			this.especificar = especificar;
		}		

		// Métodos get
		public int getClave()
		{
			return this.clave;
		}
		
		public String getNombre()
		{
			return this.nombre;
		}
		
		public char getGenero()
		{
			return this.genero;
		}
		
		public int getEdad()
		{
			return this.edad;
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

		public boolean getJuegoSalon()
		{
			return this.juegoSalon;
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
}
