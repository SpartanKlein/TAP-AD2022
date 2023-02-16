/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 28/10/22
//Materia: Tópicos Avanzados de Programación 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 1: Componentes y librerías
//Proyecto: Utilerías de Consola
//Docente: Jaime Arturo Félix Medina
---------------------------------------------------------------
 */
import java.util.Scanner;

public class ProbarDLL{

static Scanner input = new Scanner(System.in);

	public static void main(String[] args){
		Consola.limpiarPantalla();
		
		System.out.print("Introduzca el nuevo titulo de la ventana: ");
		String s = input.nextLine();
		Consola.titulo(s);
		
		Consola.colocarCursor(5, 15);
		Consola.colorTexto(1);
		System.out.print("Ruiz Lizarraga Jaime Alonso");

		Consola.colocarCursor(7, 18);
		Consola.colorTexto(2);
		System.out.print("Años de ingreso: 2019 (7mo semestre)");

		Consola.colocarCursor(9, 21);
		Consola.colorTexto(3);
		System.out.print("No. de ctrl: 19170736");

		Consola.colocarCursor(11, 18);
		Consola.colorTexto(4);
		System.out.print("Edad: 21 años");

		Consola.colocarCursor(13, 15);
		Consola.colorTexto(5);
		System.out.print("Especialidad: Gestión en Tecnologías en negocios");

		Consola.colocarCursor(15, 10);
		Consola.colorTexto(0);

		Consola.stop();
	}
}