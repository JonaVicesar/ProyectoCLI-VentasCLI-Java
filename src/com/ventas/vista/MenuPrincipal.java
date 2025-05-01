package com.ventas.vista;

import com.ventas.controlador.ControladorCliente;
import com.ventas.modelo.Cliente;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 1
 */
public class MenuPrincipal {
    public Scanner entrada = new Scanner(System.in);
    public ControladorCliente controlador = new ControladorCliente();
    public MenuCliente menuCliente = new MenuCliente();
    public MenuProductos menuProductos = new MenuProductos();
    public MenuVentas menuVentas = new MenuVentas();
    
    public MenuPrincipal(){
    }
    
    public void mostrar(){
        System.out.println("|||| BIENVENIDO! |||| |^o^/ " );
        System.out.println("Seleccione una opcion:" );
        System.out.println("1 - Gestion de Productos");
        System.out.println("2 - Gestion de Clientes" );
        System.out.println("3 - Gestion de Ventas" );
        System.out.println("4 - Salir");
        
        seleccionarOpcion();
    }
    
    private void seleccionarOpcion(){
        String opcion = entrada.next();
         
        switch(opcion){
            
            case "1":
                menuProductos.mostrar();
                break;
                
            case "2":
                menuCliente.mostrar();
                break;
            
            case "3":
                menuVentas.mostrar();
               
                break;
                
            case "4":
                System.out.print("Muchas Gracias!");
                break;
                
            default:
                System.out.print("Opcion Invalida pedazo de alcornoque!");
        }
        
    }
    
    
}
