#include<stdio.h>
#include<json-c/json.h>
int jsonWriter(char *ID,int Listitems[], char *lvl) {

    /*
    Entradas:
    ID -identificador de cliente
    Listitems - lista generadora de items
    lvl - flag que permite identificar el estado del juego actual
    Salida: interger que funciona como flag de identificacion
    Descripcion:
    Esta funcion es la encargada de genrar un Json a base de las entradas dadas por la funcion listeningproced() la cual se encarga de 
    toda la comunicacion con el cliente, la informacion generada es cargada en el buffer
    */
    /*Creating a json object*/
    json_object* jobj = json_object_new_object();

    /*Creating a json string*/
    json_object* id = json_object_new_string(ID);
    json_object* items = json_object_new_array();
    json_object* level = json_object_new_string(lvl);

    /*Creating json strings*/
    json_object* idItem = json_object_new_int(Listitems[0]);
    json_object* posX = json_object_new_int(Listitems[1]);
    json_object* posY = json_object_new_int(Listitems[2]);

    /*Adding the above created json strings to the array*/
    json_object_array_add(items, idItem);
    json_object_array_add(items, posX);
    json_object_array_add(items, posY);


    /*Form the json object*/
    /*Each of these is like a key value pair*/
    json_object_object_add(jobj, "ID", id);
    json_object_object_add(jobj, "ITEMS", items);
    json_object_object_add(jobj, "LVL", level);

    /*Now printing the json object*/
    printf("The json object created: %sn", json_object_to_json_string(jobj));
    return 1;
}
int jsonReader(char *buffer) {
    /*
    Entradas:
    Puntero que contiene al buffer
    Salida: interger que funciona como flag de identificacion
    Descripcion:
    Esta funcion es la encargada de deserializar el Json de manera tal que pueden ser  manipulados por la logica del servidor dentro de
    listening procedure.
    */

	//FILE* fp;
	//char buffer[1024];
    struct json_object *parsed_json;
    struct json_object *id;
    struct json_object *items;
    struct json_object *item;
    struct json_object *level;
    size_t n_items;
    int i;
	//fp = fopen("test.json", "r");
	//fread(buffer, 1024, 1, fp);
	//fclose(fp);
	parsed_json = json_tokener_parse(buffer);

    json_object_object_get_ex(parsed_json, "ID", &id);
    json_object_object_get_ex(parsed_json, "ITEMS", &items);
    json_object_object_get_ex(parsed_json, "LVL", &level);

    printf("EL ID recibido: %s\n", json_object_get_string(id));

    //if (json_object_get_string(id))

    //Cantidad de items
    n_items = json_object_array_length(items);
    for (i = 0; i < n_items; i++) {
        item = json_object_array_get_idx(items, i);
        printf("Primera Lista, ITEM: %lu.Su valor es: %i\n",i+1,json_object_get_int(item));
    }

    printf("EL LVL recibido: %s\n", json_object_get_string(level));

    return 1;
}