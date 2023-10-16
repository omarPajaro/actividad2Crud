package com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.controladores;

import com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.modelos.contactos;

import java.util.List;

public interface IcontactosBD {

    contactos elemento(int id); //devuelve el elemento dando su id|
    contactos elemento(String nombre); // devuelve dando el nombre

    List<contactos> Lista(); //devuelve la lista de contactos agregados

    void agregar(contactos contact); //añade el elemento indicado
    void actualizar(int id, contactos contact); //actualiza datos dando su id

    void  borrar(int id); //elimina dando el id




}
