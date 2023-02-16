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

public class BuferCircular implements Bufer
{
    private final int[] bufer = { -1, -1, -1, -1, -1}; // b�fer compartido

    private int celdasOcupadas = 0; // n�mero de b�feres utilizados
    private int indiceEscritura = 0; // �ndice del siguiente elemento a escribir
    private int indiceLectura = 0; // �ndice del siguiente elemento a leer

    // coloca un valor en el b�fer
    public synchronized void establecer( int valor ) throws InterruptedException
    {
        // imprime informaci�n del subproceso y del b�fer, despu�s espera;
        // mientras no haya ubicaciones vac�as, coloca el subproceso en estado de espera
        while ( celdasOcupadas == bufer.length )
        {
            System.out.printf( "Cajas llenas. Cliente en espera.\n" );
            wait(); // espera hasta que haya una celda libre en el b�fer
        } // fin de while

        bufer[ indiceEscritura ] = valor; // establece nuevo valor del b�fer

        // actualiza �ndice de escritura circular
        indiceEscritura = ( indiceEscritura + 1 ) % bufer.length;

        ++celdasOcupadas; // una celda m�s del b�fer est� llena
        mostrarEstado( "Cliente desea comprar boletos: " + valor );
        notifyAll(); // notifica a los subprocesos en espera para que lean del b�fer
    } // fin del m�todo establecer

    // devuelve un valor del b�fer
    public synchronized int obtener() throws InterruptedException
    {
        // espera hasta que el b�fer tenga datos, despu�s lee el valor;
        // mientras no haya datos para leer, coloca el subproceso en estado de espera
        while ( celdasOcupadas == 0 )
        {
            System.out.printf( "Taquilla está vacía. Cajas en espera\n" );
            wait(); // espera hasta que se llene una celda del b�fer
        } // fin de while

        int valorLeido = bufer[ indiceLectura ]; // lee un valor del b�fer

        // actualiza �ndice de lectura circular
        indiceLectura = ( indiceLectura + 1 ) % bufer.length;

        --celdasOcupadas; // hay una celda ocupada menos en el b�fer
        mostrarEstado( "Cliente atendido: " + valorLeido );
        notifyAll(); // notifica a los subprocesos en espera que pueden escribir en el b�fer

        return valorLeido;
    } // fin del m�todo obtener

    // muestra la operaci�n actual y estado del b�fer
    public void mostrarEstado( String operacion )
    {
        // imprime operacion y n�mero de celdas ocupadas del b�fer
        System.out.printf( "%s%s%d)\n", operacion, " (celdas ocupadas del bufer: ", celdasOcupadas );

        for ( int valor : bufer )
            System.out.printf( " %2d  ", valor ); // imprime los valores que hay en el b�fer

        System.out.print( "\n" );

        for ( int i = 0; i < bufer.length; i++ )
            System.out.print( "---- " );

        System.out.print( "\n" );

        for ( int i = 0; i < bufer.length; i++ )
        {
            if ( i == indiceEscritura && i == indiceLectura )
                System.out.print( " WR" ); // indice de escritura y de lectura
            else if ( i == indiceEscritura )
                System.out.print( " W   " ); // s�lo el �ndice de escritura
            else if ( i == indiceLectura )
                System.out.print( "  R  " ); // s�lo el �ndice de lectura
            else
                System.out.print( "     " ); // ning�n �ndice
        } // fin de for

        System.out.println( "\n" );
    } // fin del m�todo mostrarEstado

    public boolean estaVacio()
    {
        return celdasOcupadas == 0;
    } //fun del método estaVacio

}
