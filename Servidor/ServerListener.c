#include<io.h>
#include<stdio.h>
#include<winsock2.h>
#include<pthread.h>
#include "../servidor/jsonHandler.c"
#include "json-c/json.h"
#include "../servidor/ServerLogic.c"

#pragma comment(lib,"ws2_32.lib") //Winsock Library


void* listenProced(void* arg, int* lista) {

	/*
	Entradas:
	item - Puntero necesario para permitir el formato de funcion thread de pthread
	Salida: puntero necesario para permitir el formato de funcion de thread de pthread
	Descripcion:
	Esta funcion es la encargada de Inicializar la comunicacion por medio de sockets, utilizando la libreria Winsock2 para abrir el puerto o socket correcto
	estableciendo el socket como un socked de escucha y generando una escructura correcta del socked t bindeandolo para determinar el error que permita identificar
	si algo falla. Una vez prepado el socked, se espera a que un cliente entre para conciliar una comunicacion correcta por medio de Json. Esta funcion recibe, analiza, 
	prepara y envia los mensajes por medio de las funciones en JsonHandler.c y la libreria Json-c para mantener un standard correcto de Json.
	*/

	int i;
	for (i = 0; i < size; i++)
	{
		printf("inicia %i \n", *(lis + i));
	}

	WSADATA wsData;
	SOCKET listening;
	struct sockaddr_in server;
	char* message;
	int i;

	//Iniciando Winsock
	if (WSAStartup(MAKEWORD(2, 2), &wsData) != 0) {
		printf("Failed. Error Code : %d", WSAGetLastError());
	}

	//Creando el socket para escucha de server
	if ((listening = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET) {
		printf("Could not create socket : %d", WSAGetLastError());
	}

	//Llenando estructura sockaddr_in
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(8888);

	//Bind
	if (bind(listening, (struct sockaddr*)&server, sizeof(server)) == SOCKET_ERROR) {
		printf("Bind failed with error code : %d", WSAGetLastError());
		exit(EXIT_FAILURE);
	}

	puts("Bind done");

	//Escuchando nuevas conexiones
	listen(listening, SOMAXCONN);

	//Accept and incoming connection
	puts("Waiting for incoming connections...");

	// master contiene array de sockets
	fd_set master;
	FD_ZERO(&master);

	// Anadiendo primer socket para interactuar, el socket que 'escucha' conexiones
	FD_SET(listening, &master);

	int running = 1;

	while (running == 1) {

		// Hacer copia de master, contiene sockets que se encuentran en ciclo de conexion o mensaje
		// La copia antes de pasar a select(), pues borra todo al seleccionar 1, retorna esa interaccion, lo demas se borra
		fd_set copy = master;

		// cliente que esta interactuando con el server
		int socketCount = select(0, &copy, NULL, NULL, NULL);

		for (i = 0; i < socketCount; i++) {

			// Asignacion de interaccion
			SOCKET sock = copy.fd_array[i];


			// Es una inbound communication? o es un inbound message?
			if (sock == listening) {
				// Acepta la nueva conexion
				SOCKET client = accept(listening, NULL, NULL);

				// Anade la conexion a la lista de clientes
				FD_SET(client, &master);

				// Envia mensaje de bienvenida
				message = "Welcome to the Server!\r\n";
				send(client, message, strlen(message), 0);

			}
			else {
				char buf[4096];
				ZeroMemory(buf, 4096);

				// Recibe el mensaje
				int bytesIn = recv(sock, buf, 4096, 0);
				if (bytesIn <= 0) {
					closesocket(sock);
					FD_CLR(sock, &master);

				}
				else {
					// Sirve para el comando \quit
					if (buf[0] == '\\') {
						if (buf == "\\quit") {
							running = 0;
							break;
						}
						continue;
					}
					if (jsonReader(buf) == 1) {
						int lista[3] = { 1, 22, 33 };
						printf(" PARSED WORKED\n");
						jsonWriter("nana", lista, "bonus");
						continue;
					}
					// Envia mensaje a todos los demas clientes conectados en el server
					for (i = 0; i < master.fd_count; i++) {
						SOCKET outSock = master.fd_array[i];
						if (outSock != listening && outSock != sock) {
							message = buf;
							send(outSock, message, strlen(message), 0);
						}
					}
				}
			}
		}
	}

	return NULL;
}

int main(int argc, char* argv[]) {

	/*
	Entradas: entradas standard de Main de 
	Salida: int que permite indetificar errores (Standard de C)
	Descripcion:
	Esta funcion es la encargada de generar los threads que permiten generar una ejecucion correcta del servidor de manera que 
	tanto la comunicacion como la consola son habiles al mismo tiempo, el pntero lis es el puntero que es generado por la consola para crear items en el 
	juego de manera que el ID permite identificar el contexto en que van a ser utilizados los valores del array que representa el puntero
	*/

	/*asignar la lista org
	siempre que se tenga un ID != algun numero de [1-8] no se debe crear nada
	*/
	int size = 3;
	int* lis = malloc(size * sizeof(int));
	lis[0] = 0;
	lis[1] = 0;
	lis[2] = 0;

	/*El array es {ID, PosX/Direccion, PosY/Nivel }
	Si es PosX o direccion va a depender del ID del item generado
	al igual que  su es Posy o Nivel.
	*/
	/*Muetra el estado inicial*/
	int i;
	for (i = 0; i < size; i++)
	{
		printf("inicia %i \n", *(lis + i));
	}
	pthread_t newthread;
	pthread_create(&newthread, NULL, listenProced(), &lis);
	consoleMenu(lis);
	/*Demuetra el cambio ejecutado*/
	for (i = 0; i < 3; i++)
	{
		printf("imagine %i \n", *(lis + i));
	}
	/*Liberar  la mem de la lista usada*/
	free(lis);

}






