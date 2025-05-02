
package com.ventas.repositorio;

import com.ventas.modelo.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Jona Vicesar
 */

public class RepositorioCliente {

    private final HashMap<Integer, Cliente> repositorio  = new HashMap<>();
    

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
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono) {
       
        Cliente cliente = new Cliente(nombre, edad, documento, telefono);
        
        repositorio.put(cliente.getDocumento(), cliente);
        return true;
    }

    /**
     *
     * @param nombreCompleto
     * @param documento
     */
    public boolean eliminarCliente(int documento) {
        if (!repositorio.containsKey(documento)) return false;
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
        if (!repositorio.containsKey(documento)) return false;
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
        if (!repositorio.containsKey(documento)) return false;
        Cliente cliente = repositorio.get(documento);
        cliente.setTelefono(nuevoTelefono); 
        repositorio.replace(documento, cliente);
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

}
