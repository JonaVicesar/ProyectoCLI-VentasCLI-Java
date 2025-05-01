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

    private final HashMap<Integer, Cliente> repositorio  = new HashMap<>();;
    

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
    public boolean agregarCliente(String nombre, int edad, int documento, int telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        if (edad <= 0) {
            return false;
        }
        if (documento < 100000) {
            return false;
        }

        if (existeCliente(documento)) {
            return false;
        }

        Cliente cliente = new Cliente(nombre, edad, documento, telefono);
        
        repositorio.put(cliente.getDocumento(), cliente);
        for(Map.Entry<Integer, Cliente> documento2 : repositorio.entrySet()){
            System.out.println(documento2);
        }
        return true;
    }

    /**
     *
     * @param nombreCompleto
     * @param documento
     */
    public boolean eliminarCliente(int documento) {

        if (documento < 100000) {
            System.out.print("Numero de documento incorrecto!");
            return false;
        }

        if (!existeCliente(documento)) {
            System.out.print("NO existe ese cliente!");
            return false;
        }

        repositorio.remove(documento);
        return true;
    }

    public boolean editarNombreCliente(String nuevoNombre, int documento) {
        if (!existeCliente(documento)) {
            System.out.print("NO existe ese cliente!");
            return false;
            
        }
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            System.out.print("Ingrese el nombre de un cliente!");
            return false;
        }

        Cliente clienteSinEditar = repositorio.get(documento);
        Cliente clienteEditado = new Cliente(nuevoNombre, clienteSinEditar.edad, clienteSinEditar.documento, clienteSinEditar.telefono);
        
        repositorio.replace(documento, clienteEditado);
        
        return true;

    }
 
    public void listaClientes(){
       
       
    }
    

    
    /**
     *
     * @param documento
     * @return
     */
    private boolean existeCliente(int documento) {
        return repositorio.containsKey(documento);
    }

}
