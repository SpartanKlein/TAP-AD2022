/* Datos del desarrollo
---------------------------------------------------------------
- Autor: Jaime Alonso Ruiz Lizarraga
- Fecha: 26/11/22
- Materia: Tópicos Avanzados de Programación 17:00 - 18:00
- Docente: Jaime Arturo Félix Medina
- Semestre: Ago - Dic 2022
- Unidad 3: Programación Concurrente (Multihilos)
- Proyecto: Examen Ordinario - Complejo de Cines
---------------------------------------------------------------
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Taquilla {
    public static void main( String[] args )
    {
        // crea nueva reserva con dos subprocesos
        ExecutorService aplicacion = Executors.newCachedThreadPool();

        // crea objeto BuferCircular para almacenar valores int
        BuferCircular ubicacionCompartida = new BuferCircular();

        // muestra el estado inicial del objeto BuferCircular
        ubicacionCompartida.mostrarEstado( "Estado inicial" );

        // ejecuta las tareas Productor y Consumidor
        aplicacion.execute( new Productor( ubicacionCompartida ) );
        aplicacion.execute( new Consumidor( ubicacionCompartida ) );

        aplicacion.shutdown();
    } // fin de main
}
