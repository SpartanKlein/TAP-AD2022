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

public class BuferCircular implements Bufer
{
    public static int[] bufer;
    private int celdasOcupadas = 0;
    private int indiceEscritura = 0;
    private int indiceLectura = 0;

    // coloca un valor en el búfer
    public synchronized void establecer( int valor ) throws InterruptedException
    {

        while ( celdasOcupadas == bufer.length )
        {
            System.out.printf( "Oficina esta llena. Solicitantes esperan.\n" );
            wait();
        }

        bufer[ indiceEscritura ] = valor;


        indiceEscritura = ( indiceEscritura + 1 ) % bufer.length;

        ++celdasOcupadas;
        mostrarEstado( "Solicita trámite: " + valor );
        notifyAll();
    }


    public synchronized int obtener() throws InterruptedException
    {

        while ( celdasOcupadas == 0 )
        {
            System.out.printf( "Oficina esta vacia. Oficinista esperan.\n" );
            wait();
        }

        int valorLeido = bufer[ indiceLectura ];


        indiceLectura = ( indiceLectura + 1 ) % bufer.length;

        --celdasOcupadas;
        mostrarEstado( "Trámite atendido: " + valorLeido );
        notifyAll();

        return valorLeido;
    }


    public void mostrarEstado( String operacion )
    {
        System.out.printf( "%s%s%d)\n", operacion,
                " (Celdas ocupadas en el bufer: ", celdasOcupadas);
    }

    public boolean estaVacio()
    {
        return celdasOcupadas == 0;
    }
}

