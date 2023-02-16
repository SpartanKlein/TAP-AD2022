// Fig. 23.27: CalculadoraPrimos.java
// Calcula los primeros n n�meros primos, y muestra a medida que los va encontrando.
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.util.Random;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CalculadoraPrimos extends SwingWorker< Integer, Integer >
{
   private final Random generador = new Random();
   private final JTextArea intermedioJTextArea; // muestra los n�meros primos encontrados
   private final JButton obtenerPrimosJButton; 
   private final JButton cancelarJButton; 
   private final JLabel estadoJLabel; // muestra el estado del c�lculo
   private final boolean primos[]; // arreglo booleano para buscar n�meros primos
   private boolean detenido = false; // bandera que indica la cancelaci�n
   
   // constructor
   public CalculadoraPrimos( int max, JTextArea intermedio, JLabel estado,
      JButton obtenerPrimos, JButton cancel )
   {
      intermedioJTextArea = intermedio;
      estadoJLabel = estado;
      obtenerPrimosJButton = obtenerPrimos;
      cancelarJButton = cancel;
      primos = new boolean[ max ]; 

      // inicializa todos los valores del arreglo primos con true
      for ( int i = 0; i < max; i ++ )
         primos[ i ] = true;
   } // fin del constructor

   // busca todos los n�meros primos hasta max, usando la Criba de Erat�stenes
   public Integer doInBackground()
   {
      int cuenta = 0; // la cantidad de n�meros primos encontrados

      // empezando en el tercer valor, itera a trav�s del arreglo y pone
      // false como el valor de cualquier n�mero mayor que sea m�ltiplo
      for ( int i = 2; i < primos.length; i++ )
      {
         if ( detenido ) // si se cancel� un c�lculo
            return cuenta;
         else
         { 
            setProgress( 100 * ( i + 1 ) / primos.length );

            try
            { 
               Thread.currentThread().sleep( generador.nextInt( 5 ) );
            } // fin de try
            catch ( InterruptedException ex )
            {
               estadoJLabel.setText( "Se interrumpio subproceso Trabajador" );
               return cuenta;
            } // fin de catch

            if ( primos[ i ] ) // i es primo
            {
               publish( i ); // hace a i disponible para mostrarlo en la lista de primos
               ++cuenta;

               for ( int j = i + i; j < primos.length; j += i )
                  primos[ j ] = false; // i no es primo
            } // fin de if
         } // fin de else 
      } // fin de for
      
      return cuenta;
   } // fin del m�todo doInBackground

   // muestra los valores publicados en la lista de n�meros primos
   protected void process( List< Integer > valsPublicados )
   {
      for ( int i = 0; i < valsPublicados.size(); i++ )
         intermedioJTextArea.append( valsPublicados.get( i ) + "\n" );
   } // fin del m�todo process

   // c�digo a ejecutar cuando se completa doInBackground
   protected void done()
   {
      obtenerPrimosJButton.setEnabled( true ); // habilita el bot�n Obtener primos
      cancelarJButton.setEnabled( false ); // deshabilita el bot�n Cancelar

      int numPrimos;

      try
      {
         numPrimos = get(); // obtiene el valor de retorno de doInBackground
      } // fin de try
      catch ( InterruptedException ex )
      {
         estadoJLabel.setText( "Se interrumpio mientras se esperaban los resultados." );
         return;
      } // fin de catch
      catch ( ExecutionException ex )
      {
         estadoJLabel.setText( "Error al realizar el calculo." );
         return;
      } // fin de catch
      
      estadoJLabel.setText( "Se encontraron " + numPrimos + " primos." );
   } // fin del m�todo done  
   
   // establece la bandera para dejar de buscar n�meros primos
   public void detenerCalculo()
   {
      detenido = true;
   } // fin del m�todo detenerCalculo
} // fin de la clase CalculadoraPrimos

/**************************************************************************
 * (C) Copyright 1992-2007 por Deitel & Associates, Inc. y                *
 * Pearson Education, Inc. Todos los derechos reservados.                 *
 *                                                                        *
 * RENUNCIA: Los autores y el editor de este libro han realizado su mejor *
 * esfuerzo para preparar este libro. Esto incluye el desarrollo, la      *
 * investigaci�n y prueba de las teor�as y programas para determinar su   *
 * efectividad. Los autores y el editor no hacen ninguna garant�a de      *
 * ning�n tipo, expresa o impl�cita, en relaci�n con estos programas o    *
 * con la documentaci�n contenida en estos libros. Los autores y el       *
 * editor no ser�n responsables en ning�n caso por los da�os consecuentes *
 * en conexi�n con, o que surjan de, el suministro, desempe�o o uso de    *
 * estos programas.                                                       *
 *************************************************************************/