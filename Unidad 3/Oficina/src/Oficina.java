/* Datos del desarrollo
---------------------------------------------------------------
- Autor: Jaime Alonso Ruiz Lizarraga
- Fecha: 13/12/22
- Materia: Tópicos Avanzados de Programación 17:00 - 18:00
- Docente: Jaime Arturo Félix Medina
- Semestre: Ago - Dic 2022
- Unidad 3: Programación Concurrente (Multihilos)
- Proyecto: Examen de recuperación - Oficina de archivo de salud gubernamental
---------------------------------------------------------------
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;


public class Oficina {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // crea nueva reserva con dos subprocesos
        ExecutorService aplicacion = Executors.newCachedThreadPool();

        // crea objeto BuferCircular para almacenar valores int
        BuferCircular ubicacionCompartida = new BuferCircular();

        // muestra el estado inicial del objeto BuferCircular
        System.out.println("Tramites de la oficina de archivos\nNo.\tTipo de tramite");
        System.out.println("1\tAltas");
        System.out.println("2\tRenovación de vigencias");
        System.out.println("3\tSolicitud de credencial");
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

