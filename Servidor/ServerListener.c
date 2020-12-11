#include<io.h>
#include<stdio.h>
#include<winsock2.h>

#pragma comment(lib,"ws2_32.lib") //Winsock Library

int main(int argc, char* argv[]){
	WSADATA wsData;
	SOCKET listening;
	struct sockaddr_in server;
	char* message;

	//Iniciando Winsock
	if(WSAStartup(MAKEWORD(2, 2), &wsData) != 0){
		printf("Failed. Error Code : %d", WSAGetLastError());
		return 1;
	}
	
	//Creando el socket para escucha de server
	if ((listening = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET){
		printf("Could not create socket : %d", WSAGetLastError());
	}

	//Llenando estructura sockaddr_in 
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(8888
	);
	
	//Bind
	if (bind(listening, (struct sockaddr*)&server, sizeof(server)) == SOCKET_ERROR){
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

	while(running == 1){

		// Hacer copia de master, contiene sockets que se encuentran en ciclo de conexion o mensaje
		// La copia antes de pasar a select(), pues borra todo al seleccionar 1, retorna esa interaccion, lo demas se borra
		fd_set copy = master;

		// cliente que esta interactuando con el server
		int socketCount = select(0, &copy, NULL, NULL, NULL);

		for (int i = 0; i < socketCount; i++) {

			// Asignacion de interaccion
			SOCKET sock = copy.fd_array[i];
			
			
			// Es una inbound communication? o es un inbound message?
			if (sock == listening) {
				// Acepta la nueva conexion
				SOCKET client = accept(listening, NULL, NULL);

				// Anade la conexion a la lista de clientes
				FD_SET(client, &master);

				// Envia mensaje de bienvenida
				message = "Welcome to the Server!\r\n" ;
				send(client, message, strlen(message), 0);

			}else{
				char buf[4096];
				ZeroMemory(buf, 4096);

				// Recibe el mensaje
				int bytesIn = recv(sock, buf, 4096, 0);
				if (bytesIn <= 0) {
					closesocket(sock);
					FD_CLR(sock, &master);

				}else{
					// Sirve para el comando \quit
					if (buf[0] == '\\') {
						if (buf == "\\quit") {
							running = 0;
							break;
						}
						continue;
					}

					// Envia mensaje a todos los demas clientes conectados en el server
					for (int i = 0; i < master.fd_count; i++) {
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
}