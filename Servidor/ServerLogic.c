#include<stdio.h>
#include<stdlib.h>

#include<json-c/json.h>

//TEC\Compiladores Interpretes blah blah\Proyecto\tareas de Lenguajes\IceClimber\Servidor

void crearBicho(int* item, int id) {

	/*
	Entradas:
	item -Puntero con la direccion de memoria de la lista creacionar
	id - entero relacionado al item que esta por crearse
	Salida: nada
	Descripcion:
	Esta funcion es la encargada de determinar donde se van a crear los items dentro del juego, esta selecciona la posicion correcta en Y
	dependiendo del nivel en el que se desea crear El enemigo deseado deseada, de manera tal que pone en la segunda casilla del array 
	la DIRECCION en la que se va a crear al enemigo, de manera tal que cuando se crea, este se va a mover hacia la direccion dada en donde 
	0 lo dirigira a la Derecha y 1 a la izquierda .
	*/

	printf("_____________|Posicionar enemigo|_____________\n"
		   "direccion a la que ve       Ingrese en nivel \n"
			"_____________________________________________\n"
			"0-Derecha                 Num de nivel 1->10 \n"
			"1-Izquierda\n"
			"_____________________________________________\n"
			"ejemplo: 1 0\n"
			"_____________________________________________\n");
	int x, y;
	scanf("%i %i", &x,&y); 
	if (x < 0 || x > 1 ) {}
	else{
		*item = id;
		*(item + 1) = x;
		switch (y)
		{
		case 1:
			*(item + 2) = 30;
			break;
		case 2:
			*(item + 2) = 120;
			break;
		case 3:
			*(item + 2) = 240;
			break;
		case 4:
			*(item + 2) = 300;
			break;
		case 5:
			*(item + 2) = 390;
			break;
		case 6:
			*(item + 2) = 440;
			break;
		case 7:
			*(item + 2) = 480;
			break;
		case 8:
			*(item + 2) = 560;
			break;
		case 9:
			*(item + 2) = 640;
			break;
		case 10:
			*(item + 2) = 700;
			break;
		default:
			*item = 0;
			*(item + 1) = 0;
			break;
		}
	}
}

void crearFruta(int* item, int id) {

	/*
	Entradas: 
	item -Puntero con la direccion de memoria de la lista creacionar
	id - entero relacionado al item que esta por crearse
	Salida: nada
	Descripcion:
	Esta funcion es la encargada de determinar donde se van a crear los items dentro del juego, esta selecciona la posicion correcta en Y
	dependiendo del nivel en el que se desea crear la Fruta deseada, de manera tal que pone en la segunda casilla del array la posicion  en X deseada
	*/
	printf("_____________|Posicionar Fruta|____________\n"
		"Ingrese las posiciones X y Nivel respectivamente \n"
		"Separados por un espacio\n"
		"Pos X debe estar en un rango de 30->270 \n"
		"Nivel debe ser un numero del 1->10 \n"
		"_____________________________________________\n");
	int x, y;
	scanf("%i %i", &x, &y);
	if (x < 30 || x>270) {}
	else {
		*item = id;
		*(item + 1) = x;
		switch (y)
		{
		case 1:
			*(item + 2) = 30;
			break;
		case 2:
			*(item + 2) = 120;
			break;
		case 3:
			*(item + 2) = 240;
			break;
		case 4:
			*(item + 2) = 300;
			break;
		case 5:
			*(item + 2) = 390;
			break;
		case 6:
			*(item + 2) = 440;
			break;
		case 7:
			*(item + 2) = 480;
			break;
		case 8:
			*(item + 2) = 560;
			break;
		case 9:
			*(item + 2) = 640;
			break;
		case 10:
			*(item + 2) = 700;
			break;
		default:
			*item = 0;
			*(item + 1) = 0;
			break;
		}
	}
}

void consoleMenu(int* item) {
	/*
	Entradas: Pointer que va a contener 
	Salida: nada
	Descripcion:
	Esta funcion hace un recorrido de entradas por medio de la terminal en la cual recibe los valores necesarios 
	para cada item posible de crear dentro de ICE Climber, una vez seleccionado un ID correcto de la lista dada,
	redirige los usuarios a un menu especializado dependiendo de si es fruta o enemigo, con diferentes limitantes.
	*/

	int uno = 1;

	while (uno == 1)
	{
		printf(
			"/////////////////////////////////////////////\n \n"
			"//////////////|Crear un Item|////////////////\n"
			"    Enemigos           |      Frutas (pts)   \n"
			"_____________________________________________\n"
			"1-Yeti                    5-Zanahoria   (100)  \n"
			"2-Pajaro                  6-Cebolla (300)  \n"
			"3-Oso                     7-Calabaza (600)  \n"
			"4-Foca\n"
			"_____________________________________________\n"
			"Por favor seleccione un numero\n"
			"_____________________________________________\n");
		int seleccion;
		scanf("%i", &seleccion);
		switch (seleccion) {
		case 1:
			printf("Ha seleccionado Yeti\n" 
				"_____________________________________________\n");

			crearBicho(item, seleccion);
			break;
		case 2:
			printf("Ha seleccionado Pajaro \n"
				"_____________________________________________\n");
			crearBicho(item, seleccion);
			break;
		case 3:
			printf("Ha seleccionado Oso \n"
				"_____________________________________________\n");
			crearBicho(item, seleccion);
			break;
		case 4:
			printf("Ha seleccionado Foca \n"
				"_____________________________________________\n");
			crearBicho(item, seleccion);
			break;
		case 5:
			printf("Ha seleccionado Zanahoria \n"
				"_____________________________________________\n");
			crearFruta(item, seleccion);
			break;
		case 6:
			printf("Ha seleccionado Cebolla \n"
				"_____________________________________________\n");
			crearFruta(item, seleccion);
			break;
		case 7:
			printf("Ha seleccionado Calabaza \n"
				"_____________________________________________\n");
			crearFruta(item, seleccion);
			break;
		default:
			printf("Por favor seleccione un numero valido");
			break;
		}
	}
}



