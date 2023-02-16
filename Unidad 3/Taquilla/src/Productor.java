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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Productor implements  Runnable{

    private final static Random generador = new Random();
    private final BuferCircular ubicacionCompartida; // referencia al objeto compartido
    private final int DOCE_HORAS = 1000 * 60 * 10 ; //milisegundos * segundos * minutos * horas
    private final int TREINTA_MINUTOS = 1000 * 60; //milisengudos * segundos * minutos

    // constructor
    public Productor( BuferCircular compartido )
    {
        ubicacionCompartida = compartido;
    } // fin del constructor de Productor

    // almacena valores del 1 al 10 en ubicacionCompartida
    public void run()
    {
        int sala1 = 0;
        int sala2 = 0;
        int sala3 = 0;
        int sala4 = 0;
        int sala5 = 0;
        int totalClientes = 0;

        long horaInicial = 0;
        long horaActual = 0;

        //obtener hora actual en milisegunsdos
        Calendar tiempo = new GregorianCalendar();
        horaInicial = tiempo.getTimeInMillis();
        horaActual = horaInicial;

        //ciclo 12 Horas (Jornada laboral)
        while ((horaActual - horaInicial) <= DOCE_HORAS)
        {
            try // permanece inactivo de 0 a 3 segundos, despu�s coloca valor en Bufer
            {
                Thread.sleep( generador.nextInt(TREINTA_MINUTOS) ); // periodo de inactividad aleatorio
                int numSala = generador.nextInt( (5) + 1);
                ubicacionCompartida.establecer( numSala ); // establece el valor en el b�fer

                //actualizar el contador de clientes

                if (numSala ==1)
                    sala1++;
                else if (numSala == 2)
                    sala2++;
                else if (numSala == 3)
                    sala3++;
                else if (numSala == 4)
                    sala4++;
                else
                    sala5++;
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
        totalClientes = sala1 + sala2 + sala3 + sala4 + sala5;


        System.out.printf("No. Sala\tCantidad Clientes atendidos\n");
        System.out.printf("Sala 1\t%,9d\n", sala1);
        System.out.printf("Sala 2\t%,9d\n", sala2);
        System.out.printf("Sala 3\t%,9d\n", sala3);
        System.out.printf("Sala 4\t%,9d\n", sala4);
        System.out.printf("Sala 5\t%,9d\n", sala5);
        System.out.printf("Total \t%,9d\n", totalClientes);


        System.out.println( "Complejo de Cine terminó jornada laboral\nTerminando Productor" );
    } // fin del m�todo run
}
