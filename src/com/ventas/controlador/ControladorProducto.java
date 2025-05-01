package com.ventas.controlador;

import com.ventas.modelo.Producto;
import java.util.HashMap;

/**
 *
 * @author Jona Vicesar
 */
public class ControladorProducto {

    public HashMap<Producto, Integer> listaProductos = new HashMap<>();

    public ControladorProducto() {

    }

    public Producto crearProducto(String nombre, int id,double precio, int cantidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad(stock) debe ser mayor a cero" );
        }
        
        Producto producto = new Producto(nombre, id, precio, cantidad);
        listaProductos.put(producto, cantidad);
        return producto;
    }
    
    public void eliminarProducto(Producto producto){
        if (producto == null) {
            throw new IllegalArgumentException("Ingrese un producto");
        }
        if (!listaProductos.containsKey(producto)){
            throw new IllegalArgumentException("EL producto debe exitir");
        }
        
        listaProductos.remove(producto);
    }
    
    public void editarNombre(){
        
    }
    
    public void editarStock(){
        
    }
    
    public void editarPrecio(){
        
    }
    
    public void listaProductos(){
        
    }
   
}
