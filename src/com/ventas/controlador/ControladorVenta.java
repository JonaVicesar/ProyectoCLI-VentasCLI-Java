package com.ventas.controlador;

import com.ventas.modelo.Cliente;
import com.ventas.modelo.Producto;
import com.ventas.modelo.Venta;
import com.ventas.repositorio.RepositorioCliente;
import com.ventas.repositorio.RepositorioProductos;
import com.ventas.repositorio.RepositorioVentas;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jona Vicesar
 */
public class ControladorVenta {

    private static final HashMap<Producto, Integer> carritoProductos = new HashMap<>();
    private final RepositorioVentas repositorioVentas;
    private RepositorioProductos repositorioProductos;
    private RepositorioCliente repositorioClientes;
    public LocalDate fecha = LocalDate.now();

    public ControladorVenta() {
        this.repositorioVentas = new RepositorioVentas();
        this.repositorioClientes = new RepositorioCliente();
        this.repositorioProductos = new RepositorioProductos();
    }

    public int crearVenta(int documentoCliente, LocalDate fecha) {
        if (!repositorioClientes.existeCliente(documentoCliente)) {
            throw new IllegalArgumentException("El cliente no existe, verifique el documento o cree uno nuevo!");
        }

        if (carritoProductos.isEmpty()) {
            throw new IllegalArgumentException("No hay productos en el carrito.");
        }

        Cliente cliente = repositorioClientes.getCliente(documentoCliente);
        
        // Actualizar stock de productos
        for (Map.Entry<Producto, Integer> entry : carritoProductos.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            
            // Actualizar la cantidad en el repositorio de productos
            if (!repositorioProductos.actualizarStock(producto.getNombre(), producto.getCantidad() - cantidad)) {
                throw new IllegalArgumentException("No se pudo actualizar el stock del producto: " + producto.getNombre());
            }
        }
        
        int idVenta = repositorioVentas.crearVenta(cliente, new HashMap<>(carritoProductos), fecha);
        limpiarCarrito();
        return idVenta;
    }

    public boolean agregarProductoAlCarrito(String nombreProducto, int cantidad) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }

        // Verificar si el producto existe
        if (!repositorioProductos.existeProducto(nombreProducto)) {
            throw new IllegalArgumentException("El producto no existe.");
        }

        // Obtener el producto del repositorio de productos
        Producto producto = repositorioProductos.getProducto(nombreProducto);

        // Verificar que hay suficiente stock
        if (producto.getCantidad() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock del producto.");
        }

        // Agregar o actualizar la cantidad en el carrito
        if (carritoProductos.containsKey(producto)) {
            int cantidadActual = carritoProductos.get(producto);
            carritoProductos.put(producto, cantidadActual + cantidad);
        } else {
            carritoProductos.put(producto, cantidad);
        }

        return true;
    }
    
    public boolean quitarProductoDelCarrito(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        
        Producto producto = obtenerProducto(nombreProducto);
        if (producto == null || !carritoProductos.containsKey(producto)) {
            throw new IllegalArgumentException("El producto no está en el carrito.");
        }
        
        carritoProductos.remove(producto);
        return true;
    }
    
    public boolean disminuirCantidadEnCarrito(String nombreProducto, int cantidad) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        
        Producto producto = obtenerProducto(nombreProducto);
        if (producto == null || !carritoProductos.containsKey(producto)) {
            throw new IllegalArgumentException("El producto no está en el carrito.");
        }
        
        int cantidadActual = carritoProductos.get(producto);
        if (cantidadActual <= cantidad) {
            // Si la cantidad a disminuir es mayor o igual a la actual, se elimina el producto
            carritoProductos.remove(producto);
        } else {
            carritoProductos.put(producto, cantidadActual - cantidad);
        }
        
        return true;
    }

    public Producto obtenerProducto(String nombreProducto) {
        if (!repositorioProductos.existeProducto(nombreProducto)) {
            return null;
        }
        return repositorioProductos.getProducto(nombreProducto);
    }

    public HashMap<Producto, Integer> getCarritoProductos() {
        return new HashMap<>(carritoProductos); // Devuelve una copia para evitar modificaciones externas
    }

    public void limpiarCarrito() {
        carritoProductos.clear();
    }

    public boolean agregarProductoAVenta(int idVenta, String nombreProducto, int cantidad) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        
        Producto producto = obtenerProducto(nombreProducto);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe.");
        }
        
        // Verificar que hay suficiente stock
        if (producto.getCantidad() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock del producto.");
        }
        
        // Actualizar el stock del producto
        if (!repositorioProductos.actualizarStock(nombreProducto, producto.getCantidad() - cantidad)) {
            throw new IllegalArgumentException("No se pudo actualizar el stock del producto.");
        }
        
        return repositorioVentas.agregarProducto(idVenta, producto, cantidad);
    }

    public boolean eliminarProductoDeVenta(int idVenta, String nombreProducto) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        
        Producto producto = obtenerProducto(nombreProducto);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe.");
        }
        
        // Obtener la venta y verificar si el producto está en ella
        Venta venta = repositorioVentas.obtenerVentaPorId(idVenta);
        if (venta == null) {
            throw new IllegalArgumentException("La venta no existe.");
        }
        
        HashMap<Producto, Integer> listaCompras = venta.getListaCompras();
        if (!listaCompras.containsKey(producto)) {
            throw new IllegalArgumentException("El producto no está en esta venta.");
        }
        
        // Obtener la cantidad que se va a restaurar al stock
        int cantidadRestaurar = listaCompras.get(producto);
        
        // Eliminar el producto de la venta
        boolean eliminado = repositorioVentas.eliminarProducto(idVenta, producto);
        
        if (eliminado) {
            // Restaurar el stock
            repositorioProductos.actualizarStock(nombreProducto, producto.getCantidad() + cantidadRestaurar);
        }
        
        return eliminado;
    }
    
    public boolean disminuirCantidadProductoEnVenta(int idVenta, String nombreProducto, int cantidad) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        
        Producto producto = obtenerProducto(nombreProducto);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe.");
        }
        
        // Obtener la venta y verificar si el producto está en ella
        Venta venta = repositorioVentas.obtenerVentaPorId(idVenta);
        if (venta == null) {
            throw new IllegalArgumentException("La venta no existe.");
        }
        
        HashMap<Producto, Integer> listaCompras = venta.getListaCompras();
        if (!listaCompras.containsKey(producto)) {
            throw new IllegalArgumentException("El producto no está en esta venta.");
        }
        
        int cantidadActual = listaCompras.get(producto);
        if (cantidad > cantidadActual) {
            throw new IllegalArgumentException("La cantidad a disminuir no puede ser mayor que la cantidad en la venta.");
        }
        
        boolean actualizado = repositorioVentas.disminuirCantidadProducto(idVenta, producto, cantidad);
        
        if (actualizado) {
            // Restaurar la cantidad al stock
            repositorioProductos.actualizarStock(nombreProducto, producto.getCantidad() + cantidad);
        }
        
        return actualizado;
    }

    public boolean eliminarVenta(int idVenta) {
        // Obtener la venta antes de eliminarla para restaurar el stock
        Venta venta = repositorioVentas.obtenerVentaPorId(idVenta);
        if (venta == null) {
            return false;
        }
        
        // Restaurar el stock de todos los productos
        for (Map.Entry<Producto, Integer> entry : venta.getListaCompras().entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            repositorioProductos.actualizarStock(producto.getNombre(), producto.getCantidad() + cantidad);
        }
        
        return repositorioVentas.eliminarVenta(idVenta);
    }

    public Venta obtenerVentaPorId(int idVenta) {
        return repositorioVentas.obtenerVentaPorId(idVenta);
    }
    
    public List<Venta> listarVentas() {
        return repositorioVentas.listarVentas();
    }
    
    public Map<Producto, Integer> generarInformeProductosVendidos(LocalDate desde, LocalDate hasta) {
        return repositorioVentas.generarInformeProductosVendidos(desde, hasta);
    }
}