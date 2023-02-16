// Fig. 23.13: Consumidor.java
// Consumidor con un m�todo run que itera y lee 10 valores del b�fer.
import java.util.Random;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Consumidor implements Runnable
{ 
   private final static Random generador = new Random();
   private final Bufer ubicacionCompartida; // referencia al objeto compartido
   private final int DIEZ_MINUTOS = 1000 * 60 * 3; //milisegundos * segundos * minutos
   private final int DIEZ_SEGUNDOS = 1000 * 10; //milisengudos * segundos
   private final int CINCO_SEGUNDOS = 1000 * 5;

   // constructor
   public Consumidor( Bufer compartido )
   {
      ubicacionCompartida = compartido;
   } // fin del constructor de Consumidor

   // lee el valor de ubicacionCompartida 10 veces y suma los valores
   public void run()                                           
   {
        int basico = 0;
        int normal = 0;
        int extra = 0;
      
        long horaInicial = 0;
        long horaActual = 0;
      
      //obtener hora actual en milisegunsdos
        Calendar tiempo = new GregorianCalendar();
        horaInicial = tiempo.getTimeInMillis();
        horaActual = horaInicial;
        while ( ( ( horaActual - horaInicial ) <= DIEZ_MINUTOS ) || !ubicacionCompartida.estaVacio())
        {
            try // permanece inactivo de 0 a 3 segundos, despu�s coloca valor en Bufer
         {
            Thread.sleep( CINCO_SEGUNDOS + generador.nextInt( DIEZ_SEGUNDOS ) ); // periodo de inactividad aleatorio
            int tipoServicio = generador.nextInt( (3) + 1);
            ubicacionCompartida.obtener(); // establece el valor en el b�fer
            
            //actualizar el contador de tipo de servicio
            if (tipoServicio ==1)
                basico++;
            else if (tipoServicio ==2)
                normal++;
            else 
                extra++;
         } // fin de try
         // si las l�neas 25 o 26 se interrumpen, imprime el rastreo de la pila
         catch ( InterruptedException excepcion ) 
         {
            excepcion.printStackTrace();
         } // fin de catch
          
          //Obtener la nueva hora
          tiempo = new GregorianCalendar();
          horaActual = tiempo.getTimeInMillis();
        }//fin del while
      
      //Totalizar servicios
      int totalServicios = basico + normal + extra;
      
      //Calcular los importes
      double importeBasico = basico * 80;
      double importeNormal = normal * 100;
      double importeExtra = extra * 120;
      double importeTotal = importeBasico + importeNormal + importeExtra;
      
      System.out.printf("Servicio\tCantidad\tImporte\n");
      System.out.printf("Basico\t%,9d\t%,9.2f\n", basico, importeBasico);
      System.out.printf("Normal\t%,9d\t%,9.2f\n", normal, importeNormal);
      System.out.printf("Extra\t%,9d\t%,9.2f\n", extra, importeExtra);
      System.out.printf("Total\t%,9d\t%,9.2f\n", totalServicios, importeTotal);
      
      System.out.println( "Lavado terminó jornada laboral\nTerminando Productor" );
      
   } // fin del m�todo run
} // fin de la clase Consumidor


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