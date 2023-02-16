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
