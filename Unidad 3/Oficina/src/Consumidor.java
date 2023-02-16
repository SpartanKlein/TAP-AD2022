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

import java.util.Random;

public class Consumidor implements Runnable
{
    private final static Random generador = new Random();
    private final Bufer ubicacionCompartida; // referencia al objeto compartido
    private final int diezSegundos = 1000 * 60 ;

    // constructor
    public Consumidor( Bufer compartido )
    {
        ubicacionCompartida = compartido;
    } // fin del constructor de Productor


    public void run()
    {
        int altas = 0;
        int renovacion = 0;
        int solicitud = 0;

        while (!ubicacionCompartida.estaVacio() )
        {
            try
            {
                // Los trámites son atendidos entre 10 y 20 segundos
                Thread.sleep(diezSegundos + generador.nextInt(diezSegundos));
                // Leer tipo de trámite de 1 al 3
                int tipoTramite = ubicacionCompartida.obtener();
                if ( tipoTramite == 1 )
                    altas ++;
                else if ( tipoTramite == 2 )
                    renovacion ++;
                else
                    solicitud ++;
            } // fin de try
            catch ( InterruptedException excepcion )
            {
                excepcion.printStackTrace();
            } // fin de catch

        }

        System.out.println( "Trámites atendidos" );

        // Calcular importes

        System.out.printf("Tipo de trámite\tCantidad\n");
        System.out.printf("Altas\t\t%8d\n", altas);
        System.out.printf("Renovacion\t%8d\n", renovacion);
        System.out.printf("Solicitud\t%8d\n", solicitud);

    } // fin del método run
}
