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
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        if (edad <= 0) {
            return false;
        }
        if (documento < 100000) {
            return false;
        }

        if (repositorio.existeCliente(documento)) {
            return false;
        }

        return repositorio.agregarCliente(nombre, edad, documento, telefono);

    }

    public boolean eliminarCliente(int documento) {
        if (documento < 100000) {
            return false;
        }
        if (!repositorio.existeCliente(documento)) {
            return false;
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
        if (!repositorio.existeCliente(documento)) {
            return false;
        }

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            return false;
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
        String regex = "^(09\\d{8})$"; //validacion de numero de telefono, debe comenzar con 09 y tener 8 numeros 
        if (!nuevoTelefono.matches(regex)) {
            System.out.println("Formato de teléfono inválido.");
            return false;
        }

        if (!repositorio.existeCliente(documento)) {
            System.out.println("Cliente no encontrado.");
            return false;
        }

        return repositorio.editarTelefonoCliente(nuevoTelefono, documento);
    }

     public List<Cliente> listaClientes() {
        return repositorio.listaClientes();
    }

}
