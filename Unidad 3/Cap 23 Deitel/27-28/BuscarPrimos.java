// Fig 23.28: BuscarPrimos.java
// Uso de un objeto SwingWorker para mostrar números primos y actualizar un objeto
// JProgressBar mientras se calculan los números primos.
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class BuscarPrimos extends JFrame 
{
   private final JTextField primoMayor = new JTextField(); 
   private final JButton obtenerPrimosJButton = new JButton( "Obtener primos" );
   private final JTextArea mostrarPrimosJTextArea = new JTextArea(); 
   private final JButton cancelarJButton = new JButton( "Cancelar" );
   private final JProgressBar progresoJProgressBar = new JProgressBar();
   private final JLabel estadoJLabel = new JLabel();
   private CalculadoraPrimos calculadora;

   // constructor
   public BuscarPrimos()
   {
      super( "Busqueda de primos con SwingWorker" );
      setLayout( new BorderLayout() );

      // inicializa el panel para obtener un número del usuario
      JPanel norteJPanel = new JPanel();
      norteJPanel.add( new JLabel( "Buscar primos menores que: " ) );
      primoMayor.setColumns( 5 );
      norteJPanel.add( primoMayor );
      obtenerPrimosJButton.addActionListener( 
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e )
            {
               progresoJProgressBar.setValue( 0 ); // restablece JProgressBar
               mostrarPrimosJTextArea.setText( "" ); // borra JTextArea
               estadoJLabel.setText( "" ); // borra JLabel

               int numero;

               try
               {
                  // obtiene la entrada del usuario
                  numero = Integer.parseInt( 
                     primoMayor.getText() );
               } // fin de try
               catch ( NumberFormatException ex )
               {
                  estadoJLabel.setText( "Escriba un entero." );
                  return;
               } // fin de catch

               // construye un nuevo objeto CalculadoraPrimos
               calculadora = new CalculadoraPrimos( numero, 
                  mostrarPrimosJTextArea, estadoJLabel, obtenerPrimosJButton,
                  cancelarJButton );

               // escucha en espera de cambios en la propiedad de la barra de progreso
               calculadora.addPropertyChangeListener(
                  new PropertyChangeListener()
                  {
                     public void propertyChange( PropertyChangeEvent e )
                     {
                        // si la propiedad modificada es progreso (progress), 
                        // actualiza la barra de progreso
                        if ( e.getPropertyName().equals( "progress" ) )
                        {
                           int nuevoValor = ( Integer ) e.getNewValue();
                           progresoJProgressBar.setValue( nuevoValor );
                        } // fin de if
                     } // fin del método propertyChange
                  } // fin de la clase interna anónima
               ); // fin de la llamada a addPropertyChangeListener
               
               // deshabilita el botón Obtener primos y habilita el botón Cancelar
               obtenerPrimosJButton.setEnabled( false );
               cancelarJButton.setEnabled( true );

               calculadora.execute(); // ejecuta el objeto CalculadoraPrimos
            } // fin del método ActionPerformed
         } // fin de la clase interna anónima
      ); // fin de la llamada a addActionListener
      norteJPanel.add( obtenerPrimosJButton );

      // agrega un objeto JList desplazable para mostrar el resultado del cálculo
      mostrarPrimosJTextArea.setEditable( false );
      add( new JScrollPane( mostrarPrimosJTextArea,
         ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER ) );

      // inicializa un panel para mostrar a cancelarJButton, 
      // progresoJProgressBar y estadoJLabel 
      JPanel surJPanel = new JPanel( new GridLayout( 1, 3, 10, 10 ) );
      cancelarJButton.setEnabled( false );
      cancelarJButton.addActionListener( 
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e )
            {
               calculadora.detenerCalculo(); // cancela el cálculo
            } // fin del método ActionPerformed
         } // fin de la clase interna anónima
      ); // fin de la llamada a addActionListener
      surJPanel.add( cancelarJButton );
      progresoJProgressBar.setStringPainted( true );
      surJPanel.add( progresoJProgressBar );
      surJPanel.add( estadoJLabel );
     
      add( norteJPanel, BorderLayout.NORTH );
      add( surJPanel, BorderLayout.SOUTH );
      setSize( 550, 300 );
      setVisible( true );
   } // fin del constructor
   
   // el método main empieza la ejecución del programa
   public static void main( String[] args )
   {
      BuscarPrimos aplicacion = new BuscarPrimos();
      aplicacion.setDefaultCloseOperation( EXIT_ON_CLOSE );
   } // fin de main
} // fin de la clase BuscarPrimos

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