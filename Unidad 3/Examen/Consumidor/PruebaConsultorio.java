

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;


public class PruebaConsultorio {

   static Scanner scanner = new Scanner(System.in);
   public static void main(String[] args) {
      // crea nueva reserva con dos subprocesos
      ExecutorService aplicacion = Executors.newCachedThreadPool();

      // crea objeto BuferCircular para almacenar valores int
      BuferCircular ubicacionCompartida = new BuferCircular();

      // muestra el estado inicial del objeto BuferCircular
      System.out.print("Escriba la cantidad de trámites que se realizarán: ");
      Productor.tramites = scanner.nextInt();

      BuferCircular.bufer = new int[Productor.tramites];

      for (int i = 0; i < BuferCircular.bufer.length; i++) {
         BuferCircular.bufer[i] = -1;
      }

      ubicacionCompartida.mostrarEstado("Estado inicial");

      // ejecuta las tareas Productor y Consumidor
      aplicacion.execute(new Productor(ubicacionCompartida));
      aplicacion.execute(new Consumidor(ubicacionCompartida));

      aplicacion.shutdown();
   }
}
