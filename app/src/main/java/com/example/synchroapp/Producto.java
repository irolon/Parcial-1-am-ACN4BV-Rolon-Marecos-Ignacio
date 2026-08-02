package com.example.synchroapp;

public class Producto {

    private String marca;
    private String detalle;
    private String precio;
    private String imagen;


    public Producto(){
    }

    public Producto(String marca, String detalle, String precio, String imagen){
        this.marca = marca;
        this.detalle = detalle;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getMarca() {
        return marca;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }
}
