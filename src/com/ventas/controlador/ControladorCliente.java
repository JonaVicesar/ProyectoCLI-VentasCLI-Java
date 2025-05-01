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
    
    public ControladorCliente(){
    }
    
    public void agregarCliente(){
        System.out.println("Ingrese los requeridos para agregar un nuevo cliente");
        System.out.println("Ingrese su Nombre(El nombre no puede contener numeros ni caracteres especiales))");
        String nombre = entrada.next();
        System.out.println("Ingrese su edad(debe ser mayor  a 13");
        int edad = entrada.nextInt();
        System.out.println("Ingrese su numero de documento");
        System.out.println("RECUERDE QUE UNA VEZ CREADO EL NUMERO DE DOCUMENTO NO SE PUEDE EDITAR");
        int documento = entrada.nextInt();
        System.out.println("Ingrese su numero de telefono");
        int telefono = entrada.nextInt();
        
        repositorio.agregarCliente(nombre, edad, documento, telefono);
        System.out.println("Cliente agregado exitosamente!(*^_^*)");
            

    }
    
    public boolean eliminarCliente(){
        System.out.println("Ingrese el documento del cliente que desea eliminar");
        int documento = entrada.nextInt();
        
        if(repositorio.eliminarCliente(documento)){
            System.out.println("Cliente eliminado exitosamente! (*^_^*)");
            return true;
        }
        else{
            System.out.println("Ocurio un error al momento de eliminar el cliente, porfavor verifique el documento y vuelva a intentar");
            return false;
        }
    }
    
    public void editarNombre(){
        System.out.println("Ingrese el numero de documento del Cliente que va a editar  ");
        int documento =  entrada.nextInt();
        System.out.println("Ingrese el Nuevo Nombre");
        String nuevoNombre = entrada.next();
        
        if(repositorio.editarNombreCliente(nuevoNombre, documento)){
            System.out.println("Nombre editado exitosamente! ヾ(≧▽≦*)o");
        }
        
        else{
            System.out.println("Ocurrio un error al editar el nombre X﹏X");
        }
    }
    
    public void editarTelefono(){
        
    }
    
    public void listaClientes(){
        repositorio.listaClientes();
    }
    
}
