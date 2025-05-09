package com.ventas.repositorio;

import com.ventas.modelo.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repositorio encargado de manejar la persistencia en memoria de los objetos Cliente.
 * Utiliza un HashMap con el número de documento como clave única para cada cliente.
 * 
 * @author Jona Vicesar
 */
public class RepositorioCliente {

    // Mapa que almacena los clientes, con el documento como clave
    private static final HashMap<Integer, Cliente> repositorio = new HashMap<>();

    public RepositorioCliente() {
    }

    /**
     * Agrega un nuevo cliente sin tarjeta de crédito al repositorio.
     *
     * @param nombre Nombre completo
     * @param edad Edad del cliente
     * @param documento Documento (clave única)
     * @param telefono Número telefónico
     * @param metodo Método de pago (por ejemplo, "Efectivo")
     * @return true si se agrega correctamente
     */
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono, String metodo) {
        Cliente cliente = new Cliente(nombre, edad, documento, telefono, metodo);
        repositorio.put(cliente.getDocumento(), cliente);
        return true;
    }

    /**
     * Agrega un nuevo cliente con tarjeta al repositorio.
     *
     * @param nombre Nombre completo
     * @param edad Edad
     * @param documento Documento (clave única)
     * @param telefono Teléfono
     * @param metodo Método de pago
     * @param tarjeta Número de tarjeta
     * @return true si se agrega correctamente
     */
    public boolean agregarCliente(String nombre, int edad, int documento, String telefono, String metodo, String tarjeta) {
        Cliente cliente = new Cliente(nombre, edad, documento, telefono, metodo, tarjeta);
        repositorio.put(cliente.getDocumento(), cliente);
        return true;
    }

    /**
     * Elimina un cliente del repositorio por su documento.
     *
     * @param documento Documento del cliente a eliminar
     * @return true si se elimina correctamente
     */
    public boolean eliminarCliente(int documento) {
        repositorio.remove(documento);
        return true;
    }

    /**
     * Edita el nombre de un cliente existente.
     *
     * @param nuevoNombre Nuevo nombre
     * @param documento Documento del cliente
     * @return true si se edita correctamente; false si el cliente no existe
     */
    public boolean editarNombreCliente(String nuevoNombre, int documento) {
        if (!existeCliente(documento)) return false;

        Cliente cliente = repositorio.get(documento);
        cliente.setNombreCompleto(nuevoNombre);
        repositorio.replace(documento, cliente);
        return true;
    }

    /**
     * Edita el número de teléfono de un cliente existente.
     *
     * @param nuevoTelefono Nuevo número
     * @param documento Documento del cliente
     * @return true si se edita correctamente; false si el cliente no existe
     */
    public boolean editarTelefonoCliente(String nuevoTelefono, int documento) {
        if (!existeCliente(documento)) return false;

        Cliente cliente = repositorio.get(documento);
        cliente.setTelefono(nuevoTelefono);
        repositorio.replace(documento, cliente);
        return true;
    }

    /**
     * Edita el método de pago y tarjeta de un cliente.
     *
     * @param documento Documento del cliente
     * @param metodo Nuevo método de pago
     * @param tarjeta Nueva tarjeta (si aplica)
     * @return true si se edita correctamente; false si el cliente no existe
     */
    public boolean editarMetodoPago(int documento, String metodo, String tarjeta) {
        if (!existeCliente(documento)) return false;

        Cliente cliente = repositorio.get(documento);
        cliente.setMetodoPago(metodo);
        cliente.setTarjeta(tarjeta); // Solo se usa si es necesario
        repositorio.put(documento, cliente);
        return true;
    }

    /**
     * Retorna una lista con todos los clientes almacenados.
     *
     * @return Lista de clientes
     */
    public List<Cliente> listaClientes() {
        return new ArrayList<>(repositorio.values());
    }

    /**
     * Verifica si existe un cliente en el repositorio.
     *
     * @param documento Documento a buscar
     * @return true si existe
     */
    public boolean existeCliente(int documento) {
        return repositorio.containsKey(documento);
    }

    /**
     * Obtiene un cliente a partir de su documento.
     *
     * @param documento Documento del cliente
     * @return El cliente correspondiente o null si no existe
     */
    public Cliente getCliente(int documento) {
        return repositorio.get(documento);
    }

}
