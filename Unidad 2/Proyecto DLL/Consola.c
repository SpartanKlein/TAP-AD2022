// Incluir archivo de interfaz Java - c
#include <jni.h>

// incluir archivo de cabecera de la clase Consola.java
#include "Consola.h"

//incluir archivo requerido por la funcion system
#include <stdlib.h>

// Incluir archivo requerido por la funcion setConsoleCursorPosition
#include <windows.h>

//Implementar el metodo limpiarPantalla
JNIEXPORT void JNICALL Java_Consola_limpiarPantalla(JNIEnv *env, jclass objeto)
{
	system("cls");
}


JNIEXPORT void JNICALL Java_Consola_colocarCursor(JNIEnv *env, jclass objeto, jint linea, jint columna)
{
	HANDLE hStdout = GetStdHandle(STD_OUTPUT_HANDLE);
	COORD posicion;
	posicion.X = columna;
	posicion.Y = linea;
	SetConsoleCursorPosition(hStdout, posicion);
}

JNIEXPORT void JNICALL Java_Consola_titulo(JNIEnv *env, jclass object, jstring titulo)
{
	const char *str = (*env)->GetStringUTFChars(env, titulo, NULL);
	SetConsoleTitle(str);
}

JNIEXPORT void JNICALL Java_Consola_stop(JNIEnv *env, jclass object)
{
	system("pause");
}

JNIEXPORT void JNICALL Java_Consola_colorTexto(JNIEnv *env, jclass object, jint color)
{
	HANDLE hStdout = GetStdHandle(STD_OUTPUT_HANDLE);

	switch (color)
	{
	case 1:
		SetConsoleTextAttribute(hStdout, FOREGROUND_BLUE);
		break;
	case 2:
		SetConsoleTextAttribute(hStdout, FOREGROUND_BLUE | FOREGROUND_RED);
		break;
	case 3:
		SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_GREEN);
		break;
	case 4:
		SetConsoleTextAttribute(hStdout, FOREGROUND_BLUE | FOREGROUND_GREEN);
		break;
	case 5:
		SetConsoleTextAttribute(hStdout, FOREGROUND_RED);
		break;
	case 6:
		SetConsoleTextAttribute(hStdout, FOREGROUND_GREEN); 
		break;
	default:
		SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE); 

		break;
	}
}
