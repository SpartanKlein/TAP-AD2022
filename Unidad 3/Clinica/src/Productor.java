import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Productor implements  Runnable{

    private final static Random generador = new Random();
    private final BuferCircular ubicacionCompartida; // referencia al objeto compartido
    private final int OCHO_HORAS = 1000 * 60 *60 * 8 ; //milisegundos * segundos * minutos * horas
    private final int UNA_HORA = 1000 * 60 * 60; //milisengudos * segundos * minutos

    // constructor
    public Productor( BuferCircular compartido )
    {
        ubicacionCompartida = compartido;
    } // fin del constructor de Productor

    // almacena valores del 1 al 10 en ubicacionCompartida
    public void run()
    {
        int partConsulta = 0;
        int partSeguimiento = 0;
        int trabajador = 0;

        long horaInicial = 0;
        long horaActual = 0;

        //obtener hora actual en milisegunsdos
        Calendar tiempo = new GregorianCalendar();
        horaInicial = tiempo.getTimeInMillis();
        horaActual = horaInicial;

        //ciclo 8 Horas (Jornada laboral)
        while ((horaActual - horaInicial) <= OCHO_HORAS )
        {
            try // permanece inactivo de 0 a 3 segundos, despu�s coloca valor en Bufer
            {
                Thread.sleep( generador.nextInt(UNA_HORA) ); // periodo de inactividad aleatorio
                int tipoPaciente = generador.nextInt( (3) + 1);
                ubicacionCompartida.establecer( tipoPaciente ); // establece el valor en el b�fer

                //actualizar el contador de tipo de servicio
                if (tipoPaciente ==1)
                    partConsulta++;
                else if (tipoPaciente ==2)
                    partSeguimiento++;
                else
                    trabajador++;
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
        int totalServicios = partConsulta + partSeguimiento + trabajador;

        //Calcular los importes
        double impConsulta = partConsulta * 300;
        double impSeguimiento = partSeguimiento * 100;
        double importeExtra = 0 ;
        double importeTotal = impConsulta + impSeguimiento + importeExtra;


        System.out.printf("\tServicio\t\t\tCantidad\tImporte\n");
        System.out.printf("Particular-Consulta   \t%,9d\t%,9.2f\n", partConsulta, impConsulta);
        System.out.printf("Particular-Seguimiento\t%,9d\t%,9.2f\n", partSeguimiento, impSeguimiento);
        System.out.printf("Trabajador del banco  \t%,9d\t%,9.2f\n", trabajador, importeExtra);
        System.out.printf("Total                  \t%,9d\t%,9.2f\n", totalServicios, importeTotal);


        System.out.println( "Clinica terminó jornada laboral\nTerminando Productor" );
    } // fin del m�todo run
}
