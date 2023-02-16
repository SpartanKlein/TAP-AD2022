/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 25/10/22
//Materia: T�picos Avanzados de Programaci�n 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 1: Interfaz Gr�fica de Usuario
//Proyecto: Programa MVC Dibujo
//Docente: Jaime Arturo F�lix Medina
---------------------------------------------------------------
 */

public class Dibujo {
	public static void main( String[] args )
	{
		Vista miVista = new Vista();
		Modelo miModelo = new Modelo();
		Controlador miControlador = new Controlador( miModelo, miVista );
		miControlador.iniciarVista();
	}
}
