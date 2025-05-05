package com.ventas.repositorio;

import com.ventas.modelo.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jona Vicesar
 */
public class RepositorioCliente {

    private static final HashMap<Integer, Cliente> repositorio = new HashMap<>();

    public RepositorioCliente() {
    }

    /**
     * private
     *
     * @param nombre
     * @param edad
     * @param documento
     * @param telefono
     * @return
     */
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono, String metodo) {
        Cliente cliente = new Cliente(nombre, edad, documento, telefono, metodo);
        repositorio.put(cliente.getDocumento(), cliente);
        return true;
    }

    /**
     *
     * @param nombre
     * @param edad
     * @param documento
     * @param telefono
     * @param metodo
     * @param tarjeta
     * @return
     */
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono, String metodo, String tarjeta) {
        Cliente cliente = new Cliente(nombre, edad, documento, telefono, metodo, tarjeta);
        repositorio.put(cliente.getDocumento(), cliente);
        return true;
    }

    /**
     * 
     * @param documento
     * @return 
     */
    public boolean eliminarCliente(int documento) {
        repositorio.remove(documento);
        return true;
    }

    /**
     *
     * @param nuevoNombre
     * @param documento
     * @return
     */
    public boolean editarNombreCliente(String nuevoNombre, int documento) {
        if (!existeCliente(documento)) {
            return false;
        }
        Cliente cliente = repositorio.get(documento);
        cliente.setNombreCompleto(nuevoNombre); //actualiza el nuevo nombre
        repositorio.replace(documento, cliente);
        return true;

    }

    /**
     *
     * @param nuevoTelefono
     * @param documento
     * @return
     */
    public boolean editarTelefonoCliente(String nuevoTelefono, int documento) {
        if (!existeCliente(documento)) {
            return false;
        }
        Cliente cliente = repositorio.get(documento);
        cliente.setTelefono(nuevoTelefono);
        repositorio.replace(documento, cliente);
        return true;
    }

    public boolean editarMetodoPago(int documento, String metodo, String tarjeta) {
        if (!existeCliente(documento)) {
            return false;
        }
        Cliente cliente = repositorio.get(documento);
        cliente.setMetodoPago(metodo);
        cliente.setTarjeta(tarjeta); // Solo aplica si es TARJETA
        repositorio.put(documento, cliente);
        return true;
    }

    public List<Cliente> listaClientes() {
        return new ArrayList<>(repositorio.values());
    }

    /**
     *
     * @param documento
     * @return
     */
    public boolean existeCliente(int documento) {
        return repositorio.containsKey(documento);
    }

    public Cliente getCliente(int documento){
        return repositorio.get(documento);
    }
    
}
