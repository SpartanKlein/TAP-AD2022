/* Datos del desarrollo
---------------------------------------------------------------
//Autor: Jaime Alonso Ruiz Lizarraga
//Fecha: 07/10/22
//Materia: T�picos Avanzados de Programaci�n 17:00 - 18:00
//Semestre: Ago - Dic 2022
//Unidad 1: Interfaz Gr�fica de Usuario
//Proyecto: Programa MVC Cat�logo
//Docente: Jaime Arturo F�lix Medina
---------------------------------------------------------------
 */


// Aplicaci�n Catalogo MVC

public class Catalogo {
	public static void main(String[] args) {
		Modelo miModelo = new Modelo();
		Vista miVista = new Vista();
		Controlador miControlador = new Controlador(miModelo, miVista);
		miControlador.iniciarVista();
	}
}
