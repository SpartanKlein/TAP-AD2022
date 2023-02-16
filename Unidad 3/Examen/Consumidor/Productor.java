
import java.util.Random;

public class Productor implements Runnable {
   private final static Random generador = new Random();
   private final Bufer ubicacionCompartida; 
   public static int tramites;

   // constructor
   public Productor(Bufer compartido) {
      ubicacionCompartida = compartido;
   } 

   public void run() {
      int altas = 0;
      int renovacion = 0;
      int solicitud = 0;
      int i = 0;

      while (i < tramites) {
         try {
            // Generar el tipo de pacientes
            int tipoTramite = generador.nextInt(3) + 1;
            ubicacionCompartida.establecer(tipoTramite);
            if (tipoTramite == 1)
               altas++;
            else if (tipoTramite == 2)
               renovacion++;
            else
               solicitud++;
         } // fin de try
         catch (InterruptedException excepcion) {
            excepcion.printStackTrace();
         } // fin de catch

         // Actualizar la cuenta
         i++;
      }

      System.out.println("Oficina cerrada\nTrámites solicitados");

      System.out.printf("Tipo de trámite\tCantidad\n");
      System.out.printf("Altas\t\t%8d\n", altas);
      System.out.printf("Renovacion\t%8d\n", renovacion);
      System.out.printf("Solicitud\t%8d\n", solicitud);

   } // fin del método run
}
