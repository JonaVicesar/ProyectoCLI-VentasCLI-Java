package com.ventas.modelo;

import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Jona Vicesar
 */
public class Venta {
   public Cliente cliente;
   public LocalDate fecha;
   public HashMap<Producto, Integer> venta;
   public double total;
   
   public Venta(){
       this.venta = new HashMap<>();
       this.fecha = LocalDate.now();
   }
   
   public Venta(Cliente cliente, HashMap<Producto, Integer> venta, LocalDate fecha){
       this.cliente = cliente;
       this.venta = venta; 
       this.fecha = fecha;
   }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public HashMap<Producto, Integer> getVenta() {
        return venta;
    }

    public void setVenta(HashMap<Producto, Integer> venta) {
        this.venta = venta;
    }
   
    public void agregarProducto(Producto producto, Integer cantidad){
       if(producto.getCantidad() < cantidad) System.out.print("No hay suficientes productos");
       else{
           venta.put(producto, cantidad);
           System.out.print("Producto agregado!");
       }
   }
    
    public void eliminarProducto(Producto producto, int cantidad){
        int cantidadActual = venta.get(producto);
        Integer nuevaCantidad = cantidadActual - cantidad;
        if(nuevaCantidad == 0){
            venta.remove(producto);
        }
        else{
            venta.replace(producto, nuevaCantidad);
        }
    }
    
    public double calcularTotal(HashMap<Producto, Integer> venta){
       for(HashMap.Entry<Producto, Integer> item : venta.entrySet()){
           Producto producto = item.getKey();
           Integer cantidad = item.getValue();
           total += producto.getPrecio()*cantidad;
       }
       return total;
    }
   
}
