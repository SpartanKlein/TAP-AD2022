// Fig. 25.26: ResultSetTableModel.java
// Un objeto TableModel que suministra datos ResultSet a un objeto JTable.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

// Las filas y columnas del objeto ResultSet se cuentan desde 1 y
// las filas y columnas del objeto JTable se cuentan desde 0. Al procesar 
// filas o columnas de ResultSet para usarlas en un objeto JTable, es 
// necesario sumar 1 al número de fila o columna para manipular
// la columna apropiada del objeto ResultSet (es decir, la columna 0 de JTable 
// es la columna 1 de ResultSet y la fila 0 de JTable es la fila 1 de ResultSet).
public class ResultSetTableModel extends AbstractTableModel 
{
   private Connection conexion;
   private Statement instruccion;
   private ResultSet conjuntoResultados;
   private ResultSetMetaData metaDatos;
   private int numeroDeFilas;

   // lleva la cuenta del estado de la conexión a la base de datos
   private boolean conectadoABaseDatos = false;
   
   // el constructor inicializa conjuntoResultados y obtiene su objeto de metadatos;
   // determina el número de filas
   public ResultSetTableModel( String controlador,String url, String nombreusuario,
      String contrasenia, String consulta ) 
      throws SQLException, ClassNotFoundException
   {         
      // se conecta a la base de datos
      Class.forName( controlador );
      conexion = DriverManager.getConnection( url, nombreusuario, contrasenia );

      // crea objeto Statement para consultar la base de datos
      instruccion = conexion.createStatement( 
         ResultSet.TYPE_SCROLL_INSENSITIVE,
         ResultSet.CONCUR_READ_ONLY );

      // actualiza el estado de la conexión a la base de datos
      conectadoABaseDatos = true;

      // establece consulta y la ejecuta
      establecerConsulta( consulta );
   } // fin del constructor ResultSetTableModel

   // obtiene la clase que representa el tipo de la columna
   public Class getColumnClass( int columna ) throws IllegalStateException
   {
      // verifica que esté disponible la conexión a la base de datos
      if ( !conectadoABaseDatos ) 
         throw new IllegalStateException( "No hay conexion a la base de datos" );

      // determina la clase de Java de la columna
      try 
      {
         String nombreClase = metaDatos.getColumnClassName( columna + 1 );
         
         // devuelve objeto Class que representa a nombreClase
         return Class.forName( nombreClase );
      } // fin de try
      catch ( Exception excepcion ) 
      {
         excepcion.printStackTrace();
      } // fin de catch
      
      return Object.class; // si ocurren problemas en el código anterior, asume el tipo Object
   } // fin del método getColumnClass

   // obtiene el número de columnas en el objeto ResultSet
   public int getColumnCount() throws IllegalStateException
   {   
      // verifica que esté disponible la conexión a la base de datos
      if ( !conectadoABaseDatos ) 
         throw new IllegalStateException( "No hay conexión a la base de datos" );

      // determina el número de columnas
      try 
      {
         return metaDatos.getColumnCount(); 
      } // fin de try
      catch ( SQLException excepcionSql ) 
      {
         excepcionSql.printStackTrace();
      } // fin de catch
      
      return 0; // si ocurren problemas en el código anterior, devuelve 0 para el número de columnas
   } // fin del método getColumnCount

   // obtiene el nombre de una columna específica en el objeto ResultSet
   public String getColumnName( int columna ) throws IllegalStateException
   {    
      // verifica que esté disponible la conexión a la base de datos
      if ( !conectadoABaseDatos ) 
         throw new IllegalStateException( "No hay conexion a la base de datos" );

      // determina el nombre de la columna
      try 
      {
         return metaDatos.getColumnName( columna + 1 );  
      } // fin de try
      catch ( SQLException excepcionSql ) 
      {
         excepcionSql.printStackTrace();
      } // end catch
      
      return ""; // si hay problemas, devuelve la cadena vacía para el nombre de la columna
   } // fin del método getColumnName

   // devuelve el número de filas en el objeto ResultSet
   public int getRowCount() throws IllegalStateException
   {      
      // verifica que esté disponible la conexión a la base de datos
      if ( !conectadoABaseDatos ) 
         throw new IllegalStateException( "No hay conexion a la base de datos" );
 
      return numeroDeFilas;
   } // fin del método getRowCount

   // obtiene el valor en la fila y columna específicas
   public Object getValueAt( int fila, int columna ) 
      throws IllegalStateException
   {
      // verifica que esté disponible la conexión a la base de datos
      if ( !conectadoABaseDatos ) 
         throw new IllegalStateException( "No hay conexion a la base de datos" );

      // obtiene un valor en una fila y columna especificadas del objeto ResultSet
      try 
      {
         conjuntoResultados.absolute( fila + 1 );
         return conjuntoResultados.getObject( columna + 1 );
      } // fin de try
      catch ( SQLException excepcionSql ) 
      {
         excepcionSql.printStackTrace();
      } // fin de catch
      
      return ""; // si hay problemas, devuelve el objeto cadena vacía
   } // fin del método getValueAt
   
   // establece nueva cadena de consulta en la base de datos
   public void establecerConsulta( String consulta ) 
      throws SQLException, IllegalStateException 
   {
      // verifica que esté disponible la conexión a la base de datos
      if ( !conectadoABaseDatos ) 
         throw new IllegalStateException( "No hay conexion a la base de datos" );

      // especifica la consulta y la ejecuta
      conjuntoResultados = instruccion.executeQuery( consulta );

      // obtiene metadatos para el objeto ResultSet
      metaDatos = conjuntoResultados.getMetaData();

      // determina el número de filas en el objeto ResultSet
      conjuntoResultados.last();                   // avanza a la última fila
      numeroDeFilas = conjuntoResultados.getRow();  // obtiene el número de fila      
      
      // notifica al objeto JTable que el modelo ha cambiado
      fireTableStructureChanged();
   } // fin del método establecerConsulta

   // cierra objetos Statement y Connection               
   public void desconectarDeBaseDatos()            
   {              
      if ( conectadoABaseDatos )                  
      {
         // cierra objetos Statement y Connection            
         try                                          
         {                                            
            conjuntoResultados.close();                        
            instruccion.close();                        
            conexion.close();                       
         } // fin de try                                 
         catch ( SQLException excepcionSql )          
         {                                            
            excepcionSql.printStackTrace();           
         } // fin de catch                               
         finally  // actualiza el estado de la conexión a la base de datos
         {                                            
            conectadoABaseDatos = false;              
         } // fin de finally                             
      } // fin de if
   } // fin del método desconectarDeBaseDatos          
}  // fin de la clase ResultSetTableModel




/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigación y prueba de las teorías y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garantía de      *
 * ningún tipo, expresa o implícita, en relación con estos programas o    *
 * con la documentación contenida en estos libros. Los autores y el       *
 * editor no serán responsables en ningún caso por los daños consecuentes *
 * en conexión con, o que surjan de, el suministro, desempeño o uso de    *
 * estos programas.                                                       *
 *************************************************************************/
