package com.ventas.controlador;

import com.ventas.modelo.Cliente;
import com.ventas.repositorio.RepositorioCliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jona Vicesar
 */
public class ControladorCliente {

    private static final RepositorioCliente repositorio = new RepositorioCliente();
    public Scanner entrada = new Scanner(System.in);

    public ControladorCliente() {
    }

    /**
     * 
     * @param nombre
     * @param edad
     * @param documento
     * @param telefono
     * @return 
     */
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono, String metodo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (edad <= 13) {
            throw new IllegalArgumentException("El cliente debe ser mayor de 13");
        }
        if (documento < 100000) {
            throw new IllegalArgumentException("Numero de documento incorrecto");
        }

        if (repositorio.existeCliente(documento)) {
            throw new IllegalArgumentException("Ya existe un cliente con el mismo documento");
        }
                
        return repositorio.agregarCliente(nombre, edad, documento, telefono, metodo);

    }
    
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono, String metodo, String tarjeta) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (edad <= 13) {
             throw new IllegalArgumentException("El cliente debe ser mayor de 13");
        }
        if (documento < 100000) {
           throw new IllegalArgumentException("Numero de documento incorrecto");
        }

        if (repositorio.existeCliente(documento)) {
            throw new IllegalArgumentException("Ya existe un cliente con el mismo documento");
        }

        return repositorio.agregarCliente(nombre, edad, documento, telefono, metodo, tarjeta);

    }

    public boolean eliminarCliente(int documento) {
        if (documento < 100000) {
            throw new IllegalArgumentException("Numero de documento incorrecto");
        }
        if (!repositorio.existeCliente(documento)) {
            throw new IllegalArgumentException("No existe el cliente");
        }
        return repositorio.eliminarCliente(documento);

    }

    /**
     * 
     * @param nuevoNombre
     * @param documento
     * @return 
     */
    public boolean editarNombre(String nuevoNombre, int documento) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");

        }
        
        if (!repositorio.existeCliente(documento)) {
            throw new IllegalArgumentException("No existe el cliente");
        }

        
        return repositorio.editarNombreCliente(nuevoNombre, documento);
    }

    /**
     * 
     * @param nuevoTelefono
     * @param documento
     * @return 
     */
     public boolean editarTelefono(String nuevoTelefono, int documento) {
        String regex = "^(09\\d{8})$"; //validacion de numero de telefono
        if (!nuevoTelefono.matches(regex)) {
            throw new IllegalArgumentException("El numero de telefono es incorrecto");
        }

        if (!repositorio.existeCliente(documento)) {
            throw new IllegalArgumentException("No existe el cliente");
        }

        return repositorio.editarTelefonoCliente(nuevoTelefono, documento);
    }

     public List<Cliente> listaClientes() {
        return repositorio.listaClientes();
    }

}
