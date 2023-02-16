import java.lang.Thread;

public class CreadorSubproceso {
    public static void main(String[] args)
    {
        System.out.println("Creador de subprocesos");

        //Crea un subproceso con un nuevo objeto Runnable
        Thread subproceso1 = new Thread( new TareaImprimir ("Tarea1"));
        Thread subproceso2 = new Thread( new TareaImprimir ("Tarea1"));
        Thread subproceso3 = new Thread( new TareaImprimir ("Tarea1"));

        System.out.println("Subprocesos creados, iniciando tareas...");

        //inicia los subprocesos y los coloca enn el estado "en ejecución"
        subproceso1.start();
        subproceso2.start();
        subproceso3.start();

        System.out.println("Tareas iniciadas, main termina");
    }//fin de main
}//fin de la clase CreadorSubprocesos