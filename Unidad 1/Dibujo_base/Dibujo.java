/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 25/10/22
//Materia: Tópicos Avanzados de Programación 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 1: Interfaz Gráfica de Usuario
//Proyecto: Programa MVC Dibujo
//Docente: Jaime Arturo Félix Medina
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
