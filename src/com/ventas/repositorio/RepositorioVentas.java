package com.ventas.repositorio;

import com.ventas.modelo.Cliente;
import com.ventas.modelo.Producto;
import com.ventas.modelo.Venta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositorioVentas {
    private static final HashMap<Integer, Venta> repositorio = new HashMap<>();
    private static int idVenta = 1;
    /**
     * 
     * @param cliente
     * @param listaCompras
     * @param fecha
     * @return 
     */
    public int crearVenta(Cliente cliente, HashMap<Producto, Integer> listaCompras, LocalDate fecha) {
        Venta nuevaVenta = new Venta(idVenta, cliente, listaCompras, fecha);
        repositorio.put(idVenta, nuevaVenta);
        return idVenta++;
    }

    
    /**
     * 
     * @param idVenta
     * @param producto
     * @param cantidad
     * @return 
     */
    public boolean agregarProducto(int idVenta, Producto producto, int cantidad) {
        Venta venta = repositorio.get(idVenta);
        if (venta == null || producto == null || cantidad <= 0) return false;

        HashMap<Producto, Integer> listaCompras = venta.getListaCompras();
        listaCompras.put(producto, listaCompras.getOrDefault(producto, 0) + cantidad);
        venta.calcularTotal(); // Actualizar total
        return true;
    }

    /**
     * 
     * @param idVenta
     * @param producto
     * @param cantidad
     * @return 
     */
    public boolean disminuirCantidadProducto(int idVenta, Producto producto, int cantidad) {
        Venta venta = repositorio.get(idVenta);
        if (venta == null || producto == null || cantidad <= 0) return false;

        HashMap<Producto, Integer> listaCompras = venta.getListaCompras();
        if (!listaCompras.containsKey(producto)) return false;

        int cantidadActual = listaCompras.get(producto);
        if (cantidadActual <= cantidad) {
            // Si la cantidad a disminuir es mayor o igual a la actual, se elimina el producto
            listaCompras.remove(producto);
        } else {
            listaCompras.put(producto, cantidadActual - cantidad);
        }
        venta.calcularTotal();
        return true;
    }

    /**
     * 
     * @param idVenta
     * @param producto
     * @return 
     */
    public boolean eliminarProducto(int idVenta, Producto producto) {
        Venta venta = repositorio.get(idVenta);
        if (venta == null || producto == null) return false;

        HashMap<Producto, Integer> listaCompras = venta.getListaCompras();
        if (listaCompras.remove(producto) != null) {
            venta.calcularTotal();
            return true;
        }
        return false;
    }

    /**
     * 
     * @param idVenta
     * @return 
     */
    public boolean eliminarVenta(int idVenta) {
        return repositorio.remove(idVenta) != null;
    }
    
    /**
     * 
     * @param desde
     * @param hasta
     * @return 
     */
    public List<Venta> obtenerVentasEntreFechas(LocalDate desde, LocalDate hasta) {
        return repositorio.values().stream()
            .filter(venta -> !venta.getFecha().isBefore(desde) && !venta.getFecha().isAfter(hasta))
            .collect(Collectors.toList());
    }

    /**
     * 
     * @param idVenta
     * @return 
     */
    public Venta obtenerVentaPorId(int idVenta) {
        return repositorio.get(idVenta);
    }
    
    /**
     * 
     * @return 
     */
    public List<Venta> listarVentas() {
        return new ArrayList<>(repositorio.values());
       
    }
    
    /**
     * 
     * @param desde
     * @param hasta
     * @return 
     */
    public Map<Producto, Integer> generarInformeProductosVendidos(LocalDate desde, LocalDate hasta) {
        Map<Producto, Integer> productosVendidos = new HashMap<>();
        
        obtenerVentasEntreFechas(desde, hasta).forEach(venta -> {
            venta.getListaCompras().forEach((producto, cantidad) -> {
                productosVendidos.put(producto, productosVendidos.getOrDefault(producto, 0) + cantidad);
            });
        });
        
        return productosVendidos;
    }
}