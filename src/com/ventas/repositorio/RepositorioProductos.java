package com.ventas.repositorio;

import com.ventas.modelo.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jona Vicesar
 */
public class RepositorioProductos {

    private final HashMap<String, Producto> repositorio = new HashMap<>();
    private int contadorId = 1;

    public RepositorioProductos() {
    }

    public boolean agregarProducto(String nombre, double precio, int cantidad) {
        Producto producto = new Producto(nombre, contadorId, precio, cantidad);
        repositorio.put(nombre.toLowerCase(), producto);
        contadorId += 1;
        return true;
    }

    public boolean eliminarProducto(String nombre) {
        repositorio.remove(nombre);
        return true;
    }

    public boolean editarNombre(String nombreProducto, String nuevoNombre) {
        Producto producto = repositorio.remove(nombreProducto.toLowerCase()); 
        producto.setNombre(nuevoNombre);
        repositorio.put(nuevoNombre.toLowerCase(), producto); 
        return true;
    }

    public boolean editarPrecio(String nombreProducto, double nuevoPrecio) {
        Producto productoAEditar = repositorio.get(nombreProducto);
        productoAEditar.setPrecio(nuevoPrecio);
        repositorio.replace(nombreProducto, productoAEditar);
        return true;
    }

    public boolean editarStock(String nombreProducto, int nuevoStock){
        Producto productoAEditar = repositorio.get(nombreProducto);
        productoAEditar.setCantidad(nuevoStock);
        repositorio.replace(nombreProducto, productoAEditar);
        return true;
    }
    
    public boolean existeProducto(String nombre) {
        return repositorio.containsKey(nombre.toLowerCase());
    }

    public List<Producto> listaProductos() {
        return new ArrayList<>(repositorio.values());
    }

}
