package com.ventas.modelo;
/**
 *
 * @author Jona Vicesar
 */
public class Producto {
    private String nombre;
    private int id;  //como el codigo del codigo de barras
    private double precio;
    private int cantidad; //stock
    
    public Producto(){
        
    }
    
    public Producto(String nombre,int id, double precio, int cantidad){
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
