import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class mostrarAutores
{
    // nombre del controlador de JDBC y URL de la base de datos
    static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String URL_BASEDATOS = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Libros;";

    // inicia la aplicaci�n
    public static void main( String args[] )
    {
        Connection conexion = null; // maneja la conexi�n
        Statement instruccion = null; // instrucci�n de consulta
        ResultSet conjuntoResultados = null; // maneja los resultados
        // se conecta a la base de datos libros y realiza una consulta
        try
        {
            // carga la clase controlador
            Class.forName( CONTROLADOR );

            // establece la conexi�n a la base de datos
            conexion = DriverManager.getConnection( URL_BASEDATOS, "jhtp7", "jhtp7" );

            // crea objeto Statement para consultar la base de datos
            instruccion = conexion.createStatement();

            // consulta la base de datos

            conjuntoResultados = instruccion.executeQuery("SELECT IDAutor, NombrePila, ApellidoPaterno FROM Autores" );

            // procesa los resultados de la consulta
            ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
            int numeroDeColumnas = metaDatos.getColumnCount();
            System.out.println( "Tabla Autores de la base de datos Libros:\n" );

            for ( int i = 1; i <= numeroDeColumnas; i++ )
                System.out.printf( "%-8s\t", metaDatos.getColumnName( i ) );
            System.out.println();

            while ( conjuntoResultados.next() )
            {
                for ( int i = 1; i <= numeroDeColumnas; i++ )
                    System.out.printf( "%-8s\t", conjuntoResultados.getObject( i ) );
                System.out.println();
            } // fin de while
        }  // fin de try
        catch ( SQLException excepcionSql )
        {
            excepcionSql.printStackTrace();
        } // fin de catch

        catch ( ClassNotFoundException noEncontroClase )
        {
            noEncontroClase.printStackTrace();
        } // fin de catch

        finally // asegura que conjuntoResultados, instruccion y conexion est�n cerrados
        {
            try
            {
                conjuntoResultados.close();
                instruccion.close();
                conexion.close();
            } // fin de try
            catch ( Exception excepcion )
            {
                excepcion.printStackTrace();
            } // fin de catch
        } // fin de finally
    } // fin de main
}//fin de la clase mostrarAutores
