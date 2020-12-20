#include<stdio.h>
#include<stdlib.h>

#include<json-c/json.h>

//TEC\Compiladores Interpretes blah blah\Proyecto\tareas de Lenguajes\IceClimber\Servidor

void crearBicho(int* item, int id) {
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
		case 9:
			uno = 2;
			break;
		default:
			printf("Por favor seleccione un numero valido");
			break;
		}
	}
}



