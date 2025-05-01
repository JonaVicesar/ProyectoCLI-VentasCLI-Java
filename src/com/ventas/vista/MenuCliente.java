package com.ventas.vista;

import com.ventas.controlador.ControladorCliente;
import com.ventas.modelo.Cliente;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jona Vicesar
 */
public class MenuCliente {
    public Scanner entrada = new Scanner(System.in);
    public ControladorCliente controladorClientes = new ControladorCliente();
    
    public void mostrar(){
        System.out.println("#MENU CLIENTES" );
        System.out.println("Seleccione una opcion" );
        System.out.println("1 - Agregar Cliente" );
        System.out.println("2 - Eliminar Cliente" );
        System.out.println("3 - Editar Cliente" );
        System.out.println("4 - Lista de Clientes" );
        System.out.println("5 - Volver al Menu Principal" );
        
        seleccionarOpcion();
       
    }
    
    private void seleccionarOpcion(){
        String opcion = entrada.next();
        
        switch(opcion){
            case "1":
               controladorClientes.agregarCliente();
               break;
               
            case "2":
                if(!controladorClientes.eliminarCliente()){
                    mostrar();
                }
                break;
                
            case "3":
                menuEditar();
                break;   
                
            case "4":
                listarClientes();
                break;
                
        }
    }
    
    public void menuEditar(){
        System.out.println("#MENU CLIENTES" );
        System.out.println("Seleccione una opcion ^_^: " );
        System.out.println("1 - Editar Nombre" );
        System.out.println("2 - Editar Telefono" );
        System.out.println("3 - Volver al Menu Principal" );
        
        seleccionarOpcionEditar();
    }
    
    private void seleccionarOpcionEditar(){
        String opcion = entrada.next();
        switch(opcion){
            case "1":
                controladorClientes.editarNombre();
                break;
                
            case "2":
                controladorClientes.editarTelefono();
                break;
            
            case "3":
                MenuPrincipal menu = new MenuPrincipal();
                menu.mostrar();
                break;
             
            default:
                System.out.println("Opcion Invalida ￣へ￣");
                break;
        }
    }
    
    
    private void listarClientes() {
        System.out.println("\n=== LISTADO DE CLIENTES ===");
        controladorClientes.listaClientes();
        ///List<Cliente> clientes = controladorClientes.listaClientes();
        
      /*  if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        
        // Encabezado de la tabla
        System.out.printf("%-30s %-10s %-15s %-15s%n", "NOMBRE", "EDAD", "DOCUMENTO", "TELÉFONO");
        System.out.println("-------------------------------------------------------------------------");
        
        // Datos de los clientes
        for (Cliente c : clientes) {
            System.out.printf("%-30s %-10d %-15d %-15d%n", 
                    c.getNombreCompleto(), 
                    c.getEdad(), 
                    c.getDocumento(), 
                    c.getTelefono());
        }
        
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Total de clientes: " + clientes.size());*/
    }
}
