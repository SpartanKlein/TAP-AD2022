// Consola.c
// Implementación de los métodos nativos
// de la clase Consola.java y almacenados en la librería
// Consola.dll


// Incluir archivo de interfaz Java - C
#include <jni.h>

// Incluir archivo de cabecera de la clase Consola.java
#include "Consola.h"

// Incluir archivo requerido por la función system
#include <stdlib.h>

// Incluir archivo requerido por la función SetConsoleCursorPosition
#include <windows.h>

// Implementar el método limpiarPantalla
JNIEXPORT void JNICALL Java_Consola_limpiarPantalla(JNIEnv *env, jclass objeto)
{
	system( "cls" );
}



JNIEXPORT void JNICALL Java_Consola_colocarCursor(JNIEnv *env, jclass objeto, jint renglon, jint columna)
{
	// Obtener manejador de salida de la consola
	HANDLE hStdout = GetStdHandle(STD_OUTPUT_HANDLE);

	// Variable para almacenar la posicion
	COORD posicion;
	posicion.X = columna;
	posicion.Y = renglon;

	// Llamar a SetConsoleCursorPosition
	SetConsoleCursorPosition(hStdout, posicion);
}


















