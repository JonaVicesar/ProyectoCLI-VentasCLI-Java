package com.ventas.repositorio;

import com.ventas.modelo.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ventas.repositorio.RepositorioCliente;
import com.ventas.repositorio.RepositorioProductos;

/**
 *
 * @author Jona Vicesar
 */
public class RepositorioProductos {

    public static final HashMap<String, Producto> repositorio = new HashMap<>();
    private int contadorId = 1;

    public RepositorioProductos() {
    }

    /**
     * 
     * @param nombre
     * @param precio
     * @param cantidad
     * @return 
     */
    public boolean agregarProducto(String nombre, double precio, int cantidad) {
        Producto producto = new Producto(nombre, contadorId, precio, cantidad);
        repositorio.put(nombre.toLowerCase(), producto);
        contadorId += 1;
        return true;
    }

    /**
     * 
     * @param nombre
     * @return 
     */
    public boolean eliminarProducto(String nombre) {
        repositorio.remove(nombre);
        return true;
    }

    /**
     * 
     * @param nombreProducto
     * @param nuevoNombre
     * @return 
     */
    public boolean editarNombre(String nombreProducto, String nuevoNombre) {
        Producto producto = repositorio.remove(nombreProducto.toLowerCase());
        producto.setNombre(nuevoNombre);
        repositorio.put(nuevoNombre.toLowerCase(), producto);
        return true;
    }

    /**
     * 
     * @param nombreProducto
     * @param nuevoPrecio
     * @return 
     */
    public boolean editarPrecio(String nombreProducto, double nuevoPrecio) {
        Producto productoAEditar = repositorio.get(nombreProducto);
        productoAEditar.setPrecio(nuevoPrecio);
        repositorio.replace(nombreProducto, productoAEditar);
        return true;
    }

    /**
     * 
     * @param nombreProducto
     * @param nuevoStock
     * @return 
     */
    public boolean editarStock(String nombreProducto, int nuevoStock) {
        Producto productoAEditar = repositorio.get(nombreProducto);
        productoAEditar.setCantidad(nuevoStock);
        repositorio.replace(nombreProducto, productoAEditar);
        return true;
    }

    /**
     * 
     * @param nombre
     * @return 
     */
    public boolean existeProducto(String nombre) {
        return repositorio.containsKey(nombre.toLowerCase());
    }

    /**
     * 
     * @param nombre
     * @return 
     */
    public Producto getProducto(String nombre) {
        return repositorio.get(nombre);
    }

    /**
     * Actualiza el stock de un producto específico
     *
     * @param nombreProducto Nombre del producto a actualizar
     * @param nuevoStock Nuevo valor de stock para el producto
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean actualizarStock(String nombreProducto, int nuevoStock) {
        if (nuevoStock < 0) {
            return false; // No permitir stock negativo
        }

        Producto producto = repositorio.get(nombreProducto.toLowerCase());
        if (producto == null) {
            return false; // El producto no existe
        }

        producto.setCantidad(nuevoStock);
        return true;
    }

    /**
     * 
     * @return 
     */
    public List<Producto> listaProductos() {
        return new ArrayList<>(repositorio.values());
    }

}
