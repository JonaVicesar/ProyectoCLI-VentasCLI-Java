package com.ventas.modelo;

import java.time.LocalDate;
import java.util.HashMap;

public class Venta {

    private int idVenta;
    private Cliente cliente;
    private HashMap<Producto, Integer> listaCompras;
    private LocalDate fecha;
    private double total;

    public Venta(int idVenta, Cliente cliente, HashMap<Producto, Integer> listaCompras, LocalDate fecha) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.listaCompras = listaCompras;
        this.fecha = fecha;
        calcularTotal();
    }

    public void calcularTotal() {
        this.total = listaCompras.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrecio() * entry.getValue())
                .sum();
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public HashMap<Producto, Integer> getListaCompras() {
        return listaCompras;
    }

    public double getTotal() {
        return total;
    }
}
