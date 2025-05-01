package com.ventas.vista;


import java.util.Scanner;
import com.ventas.controlador.ControladorProducto;
import com.ventas.modelo.Producto;

/**
 *
 * @author Jona Vicesar
 */
public class MenuProductos{
    public Scanner entrada = new Scanner(System.in);
    public ControladorProducto producto;
    
    public MenuProductos(){
    }
    
    public void mostrar(){
        System.out.println("#MENU PRODUCTOS" );
        System.out.println("Seleccione una opcion" );
        System.out.println("1 - Crear un Producto" );
        System.out.println("2 - Editar un Producto" );
        System.out.println("3 - Eliminar un Producto" );
        System.out.println("4 - Lista de Productos" );
        System.out.println("5 - Volver al Menu Principal" );
        
        seleccionarOpcion();
    }
    
    private void seleccionarOpcion(){
        String opcion = entrada.next();
        
        switch(opcion){
            case "1" :
                System.out.println("Ingrese los datos del producto: ");
                System.out.println("Nombre del Producto: ");
                String nombre = entrada.next();
                System.out.println("ID del producto: ");
                int id = entrada.nextInt();
                System.out.println("Precio unitario del producto: ");
                double precio = entrada.nextDouble();
                System.out.println("Stock del Producto: ");
                int stock = entrada.nextInt();
                producto.crearProducto(nombre, id, precio, stock);
                break;
                
            case "2":
                this.menuEditarProducto();
                break;
                
            case "3":
                System.out.print("Ingrese el nombre del producto que desea eliminar: ");
                //Producto nombreProducto = (Producto)entrada.next();
                //producto.eliminarProducto(nombreProducto);
                break;
                
            case "4":
                producto.listaProductos();
                break;
                
            case "5":
                MenuPrincipal menu = new MenuPrincipal();
                menu.mostrar();
                break;
                
            default:
                System.out.print("Opcion Invalida pedazo de alcornoque!");
                break;
        }
    }
    
    public void menuEditarProducto(){
        System.out.println("#EDITAR PRODUCTO" );
        System.out.println("Seleccione una opcion: " );
        System.out.println("1 - Cambiar Nombre de Producto" );
        System.out.println("2 - Cambiar Precio" );
        System.out.println("3 - Agregar o Disminuir Stock" );
        System.out.println("4 - Volver al MENU PRODUCTOS" );
        
        String opcion = entrada.next();
        
        switch(opcion){
            case "1":
                producto.editarNombre();
                break;
                
            case "2":
                producto.editarPrecio();
                break;
                
            case "3":
                producto.editarStock();
                break;
                
            case "4":
                MenuPrincipal menu = new MenuPrincipal();
                menu.mostrar();
                break;
             
            default:
                System.out.print("No es una opcion valida");
                
        }
    }
}
