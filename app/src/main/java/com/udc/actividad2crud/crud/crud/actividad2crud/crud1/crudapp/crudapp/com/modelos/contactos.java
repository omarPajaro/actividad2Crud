package com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.modelos;

public class contactos {
    // atributos de la clase contactos
    private int id;
    private int telefono;
    private String nombre, correo;

    // Constructor sin argumentos
    public contactos() {
    }

    // Constructor con argumentos
    public contactos(int id, int telefono, String nombre, String correo) {
        this.id = id;
        this.telefono = telefono;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Métodos Get y Set para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "contactos{" +
                "id=" + id +
                ", telefono=" + telefono +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }


}