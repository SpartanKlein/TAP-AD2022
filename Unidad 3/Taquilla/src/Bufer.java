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

public interface Bufer {
    public void establecer (int valor) throws InterruptedException;

    public int obtener() throws InterruptedException;

    public boolean estaVacio();
}
