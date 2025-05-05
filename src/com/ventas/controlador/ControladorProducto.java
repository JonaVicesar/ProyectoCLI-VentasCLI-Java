package com.ventas.controlador;

import com.ventas.modelo.Producto;
import com.ventas.repositorio.RepositorioProductos;
import java.util.List;

/**
 *
 * @author Jona Vicesar
 */
public class ControladorProducto {

    RepositorioProductos repositorio = new RepositorioProductos();

    public ControladorProducto() {

    }
    /**
     * 
     * @param nombre
     * @param precio
     * @param cantidad
     * @return 
     */
    public boolean agregarProducto(String nombre, double precio, int cantidad) {
        if (repositorio.existeProducto(nombre.trim().toLowerCase())) {
            throw new IllegalArgumentException("El producto ya existe!");
        }
        if (precio <= 0 || cantidad <= 0) {
            throw new IllegalArgumentException("El precio y el stock deben ser mayores a cero.");
        }

        repositorio.agregarProducto(nombre, precio, cantidad);
        return true;

    }

    /**
     * 
     * @param nombreProducto
     * @return 
     */
    public boolean eliminarProducto(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puedo estar en blanco o estar vacio");
        }
        if (!repositorio.existeProducto(nombreProducto)) {
            throw new IllegalArgumentException("El producto no existe ￣へ￣");
        }
        return repositorio.eliminarProducto(nombreProducto);
    }

    /**
     * 
     * @param nombreProducto
     * @param nuevoNombre
     * @return 
     */
    public boolean editarNombre(String nombreProducto, String nuevoNombre) {
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puedo estar en blanco o estar vacio");
        }
        if (nuevoNombre == null || nuevoNombre.isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre no puedo estar en blanco o estar vacio");
        }
        if (!repositorio.existeProducto(nombreProducto)) {
            throw new IllegalArgumentException("El producto no existe ￣へ￣");
        }

        return repositorio.editarNombre(nombreProducto, nuevoNombre);
    }

    /**
     * 
     * @param nombreProducto
     * @param nuevoPrecio
     * @return 
     */
    public boolean editarPrecio(String nombreProducto, double nuevoPrecio) {
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puedo estar en blanco o estar vacio");
        }

        if (nuevoPrecio <= 0) {
            throw new IllegalArgumentException("EL precio no puede ser menor a cero");
        }

        if (!repositorio.existeProducto(nombreProducto)) {
            throw new IllegalArgumentException("El producto no existe ￣へ￣");
        }

        return repositorio.editarPrecio(nombreProducto, nuevoPrecio);
    }

    /**
     * 
     * @param nombreProducto
     * @param nuevoStock
     * @return 
     */
    public boolean editarStock(String nombreProducto, int nuevoStock) {
        if (nombreProducto == null || nombreProducto.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puedo estar en blanco o estar vacio");
        }
        
        if(nuevoStock < 0){
            throw new IllegalArgumentException("El nuevo stock no puede ser un numero negativo");
        }
        
        if (!repositorio.existeProducto(nombreProducto)) {
            throw new IllegalArgumentException("El producto no existe ￣へ￣");
        }
        
        if(nuevoStock == 0){
            eliminarProducto(nombreProducto);
        }
        
        return repositorio.editarStock(nombreProducto, nuevoStock);

    }
    

    /**
     * 
     * @return 
     */
    public List<Producto> listaProductos() {
        return repositorio.listaProductos();
    }

}
