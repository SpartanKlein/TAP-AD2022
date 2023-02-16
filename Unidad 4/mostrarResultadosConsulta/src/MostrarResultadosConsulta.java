// Fig. 25.29: MostrarResultadosConsulta.java
// Muestra el contenido de la tabla Autores en la base de datos libros.
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;

public class MostrarResultadosConsulta extends JFrame 
{
   // URL de la base de datos, nombre de usuario y contraseña para JDBC
   static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";             
   static final String URL_BASEDATOS = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Libros;";
   static final String NOMBREUSUARIO = "jhtp7";
   static final String CONTRASENIA = "jhtp7";
   
   // la consulta predeterminada obtiene todos los datos de la tabla autores
   static final String CONSULTA_PREDETERMINADA = "SELECT * FROM Autores";
   
   private ResultSetTableModel modeloTabla;
   private JTextArea areaConsulta;
   
   // crea objeto ResultSetTableModel y GUI
   public MostrarResultadosConsulta() 
   {   
      super( "Visualizacion de los resultados de la consulta" );
        
      // crea objeto ResultSetTableModel y muestra la tabla de la base de datos
      try 
      {
         // crea objeto TableModel para los resultados de la consulta SELECT * FROM autores
         modeloTabla = new ResultSetTableModel( CONTROLADOR, URL_BASEDATOS,
            NOMBREUSUARIO, CONTRASENIA, CONSULTA_PREDETERMINADA );

         // establece objeto JTextArea en el que el usuario escribe las consultas
         areaConsulta = new JTextArea( CONSULTA_PREDETERMINADA, 3, 100 );
         areaConsulta.setWrapStyleWord( true );
         areaConsulta.setLineWrap( true );
         
         JScrollPane scrollPane = new JScrollPane( areaConsulta,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
         
         // establece objeto JButton para enviar las consultas
         JButton botonEnviar = new JButton( "Enviar consulta" );

         // crea objeto Box para manejar la colocación de areaConsulta y 
         // botonEnviar en la GUI
         Box boxNorte = Box.createHorizontalBox();
         boxNorte.add( scrollPane );
         boxNorte.add( botonEnviar );

         // crea delegado de JTable para modeloTabla 
         JTable tablaResultados = new JTable( modeloTabla );
         
         JLabel etiquetaFiltro = new JLabel( "Filtro:" );
         final JTextField textoFiltro = new JTextField();
         JButton botonFiltro = new JButton( "Aplicar filtro" );
         Box boxSur = boxNorte.createHorizontalBox();
         
         boxSur.add( etiquetaFiltro );
         boxSur.add( textoFiltro );
         boxSur.add( botonFiltro );
         
         // coloca los componentes de la GUI en el panel de contenido
         add( boxNorte, BorderLayout.NORTH );
         add( new JScrollPane( tablaResultados ), BorderLayout.CENTER );
         add( boxSur, BorderLayout.SOUTH );

         // crea componente de escucha de eventos para botonEnviar
         botonEnviar.addActionListener( 
         
            new ActionListener() 
            {
               // pasa la consulta al modelo de la tabla
               public void actionPerformed( ActionEvent evento )
               {
                  // realiza una nueva consulta
                  try 
                  {
                     modeloTabla.establecerConsulta( areaConsulta.getText() );
                  } // fin de try
                  catch ( SQLException excepcionSql ) 
                  {
                     JOptionPane.showMessageDialog( null, 
                        excepcionSql.getMessage(), "Error en base de datos", 
                        JOptionPane.ERROR_MESSAGE );
                     
                     // trata de recuperarse de una consulta inválida del usuario
                     // ejecutando la consulta predeterminada
                     try 
                     {
                        modeloTabla.establecerConsulta( CONSULTA_PREDETERMINADA );
                        areaConsulta.setText( CONSULTA_PREDETERMINADA );
                     } // fin de try
                     catch ( SQLException excepcionSql2 ) 
                     {
                        JOptionPane.showMessageDialog( null, 
                           excepcionSql2.getMessage(), "Error en base de datos", 
                           JOptionPane.ERROR_MESSAGE );
         
                        // verifica que esté cerrada la conexión a la base de datos
                        modeloTabla.desconectarDeBaseDatos();
         
                        System.exit( 1 ); // termina la aplicación
                     } // fin de catch interior                   
                  } // fin de catch exterior
               } // fin de actionPerformed
            }  // fin de la clase interna ActionListener 
         ); // fin de la llamada a addActionListener
         
         final TableRowSorter< TableModel > sorter = 
            new TableRowSorter< TableModel >( modeloTabla );
         tablaResultados.setRowSorter( sorter );
         setSize( 500, 250 ); // establece el tamaño de la ventana
         setVisible( true ); // muestra la ventana
         
         // crea componente de escucha para botonFiltro
         botonFiltro.addActionListener(            
            new ActionListener() 
            {
               // pasa el texto del filtro al componente de escucha
               public void actionPerformed( ActionEvent e ) 
               {
                  String texto = textoFiltro.getText();

                  if ( texto.length() == 0 )
                     sorter.setRowFilter( null );
                  else
                  {
                     try
                     {
                        sorter.setRowFilter( 
                           RowFilter.regexFilter( texto ) );
                     } // fin de try
                     catch ( PatternSyntaxException pse ) 
                     {
                        JOptionPane.showMessageDialog( null,
                           "Patron de exp reg incorrecto", "Patron de exp reg incorrecto",
                           JOptionPane.ERROR_MESSAGE );
                     } // fin de catch
                  } // fin de else
               } // fin del método actionPerfomed
            } // fin de la clase interna anónima
         ); // fin de la llamada a addActionLister
      } // fin de try

      catch ( ClassNotFoundException noEncontroClase ) 
      {
         JOptionPane.showMessageDialog( null, 
            "No se encontro controlador de base de datos", "No se encontro el controlador",
            JOptionPane.ERROR_MESSAGE );
         
         System.exit( 1 ); // termina la aplicación
      } // fin de catch

      catch ( SQLException excepcionSql ) 
      {
         JOptionPane.showMessageDialog( null, excepcionSql.getMessage(), 
            "Error en base de datos", JOptionPane.ERROR_MESSAGE );
               
         // verifica que esté cerrada la conexión a la base de datos
         modeloTabla.desconectarDeBaseDatos();
         
         System.exit( 1 ); // termina la aplicación
      } // fin de catch
      
      // cierra la ventana cuando el usuario sale de la aplicación (se sobrescribe
      // el valor predeterminado de HIDE_ON_CLOSE)
      setDefaultCloseOperation( DISPOSE_ON_CLOSE );
      
      // verifica que esté cerrada la conexión a la base de datos cuando el usuario sale de la aplicación
      addWindowListener(
      
         new WindowAdapter() 
         {
            // se desconecta de la base de datos y sale cuando se ha cerrado la ventana
            public void windowClosed( WindowEvent evento )
            {
               modeloTabla.desconectarDeBaseDatos();
               System.exit( 0 );
            } // fin del método windowClosed
         } // fin de la clase interna WindowAdapter
      ); // fin de la llamada a addWindowListener
   } // fin del constructor de MostrarResultadosConsulta
   
   // ejecuta la aplicación
   public static void main( String args[] ) 
   {
      new MostrarResultadosConsulta();     
   } // fin de main
} // fin de la clase MostrarResultadosConsulta



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

