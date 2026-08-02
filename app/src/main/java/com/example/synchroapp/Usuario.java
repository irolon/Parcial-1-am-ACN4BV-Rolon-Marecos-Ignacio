package com.example.synchroapp;

public class Usuario {
    private String usuario;
    private String nombre;
    private String email;

    public Usuario() {
    }

    public Usuario(String usuario, String nombre, String email) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}

