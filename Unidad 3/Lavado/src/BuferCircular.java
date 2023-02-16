// Fig. 23.20: BuferCircular.java
// Sincronizaci�n del acceso a un b�fer delimitado compartido, con tres elementos.
public class BuferCircular implements Bufer
{
   private final int[] bufer = { -1, -1, -1, -1, -1 }; // b�fer compartido

   private int celdasOcupadas = 0; // n�mero de b�feres utilizados
   private int indiceEscritura = 0; // �ndice del siguiente elemento a escribir
   private int indiceLectura = 0; // �ndice del siguiente elemento a leer
   
   // coloca un valor en el b�fer
   public synchronized void establecer( int valor ) throws InterruptedException
   {
      // imprime informaci�n del subproceso y del b�fer, despu�s espera;
      // mientras no haya ubicaciones vac�as, coloca el subproceso en estado de espera
      while ( celdasOcupadas == bufer.length ) 
      {
         System.out.printf( "Lavado est� lleno. Automovilista espera.\n" );
         wait(); // espera hasta que haya una celda libre en el b�fer
      } // fin de while

      bufer[ indiceEscritura ] = valor; // establece nuevo valor del b�fer

      // actualiza �ndice de escritura circular
      indiceEscritura = ( indiceEscritura + 1 ) % bufer.length;

      ++celdasOcupadas; // una celda m�s del b�fer est� llena
      mostrarEstado( "Automovilista solicita sercivio: " + valor );
      notifyAll(); // notifica a los subprocesos en espera para que lean del b�fer
   } // fin del m�todo establecer
    
   // devuelve un valor del b�fer
   public synchronized int obtener() throws InterruptedException
   {
      // espera hasta que el b�fer tenga datos, despu�s lee el valor;
      // mientras no haya datos para leer, coloca el subproceso en estado de espera
      while ( celdasOcupadas == 0 ) 
      {
         System.out.printf( "Lavado está vacio. Trabajadores esperan.\n" );
         wait(); // espera hasta que se llene una celda del b�fer
      } // fin de while

      int valorLeido = bufer[ indiceLectura ]; // lee un valor del b�fer

      // actualiza �ndice de lectura circular
      indiceLectura = ( indiceLectura + 1 ) % bufer.length;

      --celdasOcupadas; // hay una celda ocupada menos en el b�fer
      mostrarEstado( "Servicio otorgado: " + valorLeido );
      notifyAll(); // notifica a los subprocesos en espera que pueden escribir en el b�fer

      return valorLeido;
   } // fin del m�todo obtener
    
   // muestra la operaci�n actual y estado del b�fer
   public void mostrarEstado( String operacion )
   {
      // imprime operacion y n�mero de celdas ocupadas del b�fer
      System.out.printf( "%s%s%d)\n", operacion, 
         " (celdas ocupadas del bufer: ", celdasOcupadas );

      for ( int valor : bufer )
         System.out.printf( " %2d  ", valor ); // imprime los valores que hay en el b�fer

      System.out.print( "\n               " );

      for ( int i = 0; i < bufer.length; i++ )
         System.out.print( "---- " );

      System.out.print( "\n               " );

      for ( int i = 0; i < bufer.length; i++ )
      {
         if ( i == indiceEscritura && i == indiceLectura )
            System.out.print( " WR" ); // indice de escritura y de lectura
         else if ( i == indiceEscritura )
            System.out.print( " W   " ); // s�lo el �ndice de escritura
         else if ( i == indiceLectura )
            System.out.print( "  R  " ); // s�lo el �ndice de lectura
         else
            System.out.print( "     " ); // ning�n �ndice
      } // fin de for

      System.out.println( "\n" );
   } // fin del m�todo mostrarEstado
   
   public boolean estaVacio()
   {
       return celdasOcupadas == 0;
   } //fun del método estaVacio
   
} // fin de la clase BuferCircular


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