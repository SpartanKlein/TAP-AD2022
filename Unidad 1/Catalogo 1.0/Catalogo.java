/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 07/10/22
//Materia: Tópicos Avanzados de Programación 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 1: Interfaz Gráfica de Usuario
//Proyecto: Programa MVC Catálogo
//Docente: Jaime Arturo Félix Medina
---------------------------------------------------------------
 */


// Aplicación Catalogo MVC

public class Catalogo {
	public static void main(String[] args) {
		Modelo miModelo = new Modelo();
		Vista miVista = new Vista();
		Controlador miControlador = new Controlador(miModelo, miVista);
		miControlador.iniciarVista();
	}
}
