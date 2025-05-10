package com.ventas.vista;

import java.util.Scanner;
import com.ventas.controlador.ControladorProducto;
import com.ventas.modelo.Producto;
import java.util.List;

/**
 * Clase que representa la interfaz de usuario para la gestión de productos
 * Permite crear, editar, eliminar y listar productos mediante la consola
 * 
 * @author Jona Vicesar
 */
public class MenuProductos {

    public Scanner entrada = new Scanner(System.in);

    public ControladorProducto producto = new ControladorProducto();

    public MenuProductos() {
    }

    /**
     * Muestra el menu principal para productos.
     */
    public void mostrar() {
        System.out.println("#MENU PRODUCTOS");
        System.out.println("Seleccione una opcion:");
        System.out.println("1 - Crear un Producto");
        System.out.println("2 - Editar un Producto");
        System.out.println("3 - Eliminar un Producto");
        System.out.println("4 - Lista de Productos");
        System.out.println("5 - Volver al Menu Principal");

        seleccionarOpcion();
    }

    /**
     * Maneja la opcion seleccionada en el menu principal
     */
    private void seleccionarOpcion() {
        String opcion = entrada.next();

        switch (opcion) {
            case "1":
                // Agregar producto
                try {
                    System.out.println("Ingrese los datos del producto:");
                    System.out.print("Nombre del Producto: ");
                    String nombre = entrada.next();

                    System.out.print("Precio unitario del producto: ");
                    double precio = entrada.nextDouble();

                    System.out.print("Stock del Producto: ");
                    int stock = entrada.nextInt();

                    if (producto.agregarProducto(nombre, precio, stock)) {
                        System.out.println("¡Producto agregado exitosamente! *^____^*");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ocurrio un error: " + e.getMessage());
                } finally {
                    mostrar();
                }
                break;

            case "2":
                // Ir al msnu de edición
                this.menuEditarProducto();
                break;

            case "3":
                // Eliminar producto
                try {
                    System.out.print("Ingrese el nombre del producto que desea eliminar: ");
                    String nombreProducto = entrada.next();

                    if (producto.eliminarProducto(nombreProducto)) {
                        System.out.println("¡Producto eliminado exitosamente! ^0^");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ocurrio un error: " + e.getMessage());
                } finally {
                    mostrar();
                }
                break;

            case "4":
                // Listar productos
                listarProductos();
                mostrar();
                break;

            case "5":
                // Volver al menuprincipal
                MenuPrincipal menu = new MenuPrincipal();
                menu.mostrar();
                break;

            default:
                System.out.print("¡Opción invalida, pedazo de alcornoque!");
                mostrar();
                break;
        }
    }

    /**
     * Muestra el menu de edicion de productos
     */
    public void menuEditarProducto() {
        System.out.println("#EDITAR PRODUCTO");
        System.out.println("Seleccione una opción:");
        System.out.println("1 - Cambiar Nombre de Producto");
        System.out.println("2 - Cambiar Precio");
        System.out.println("3 - Agregar o Disminuir Stock");
        System.out.println("4 - Volver al MENÚ PRODUCTOS");

        seleccionarMenuEditar();
    }

    /**
     * Maneja la opcion seleccionada en el menu de edicion
     */
    private void seleccionarMenuEditar() {
        String opcion = entrada.next();

        switch (opcion) {
            case "1":
                // Editar nombre
                try {
                    System.out.print("Ingrese el nombre actual del producto: ");
                    entrada.nextLine();
                    String nombreActual = entrada.nextLine().trim();

                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = entrada.nextLine().trim();

                    if (producto.editarNombre(nombreActual, nuevoNombre)) {
                        System.out.println("¡Nombre actualizado con exito! ^_^");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ocurrio un error: " + e.getMessage());
                } finally {
                    menuEditarProducto();
                }
                break;

            case "2":
                // Editar precio
                try {
                    System.out.print("Ingrese el nombre del producto a editar: ");
                    entrada.nextLine();
                    String nombreProducto = entrada.nextLine().trim();

                    System.out.print("Ingrese el nuevo precio del producto: ");
                    double nuevoPrecio = entrada.nextDouble();
                    entrada.nextLine();

                    if (producto.editarPrecio(nombreProducto, nuevoPrecio)) {
                        System.out.println("¡Precio editado con exito! ^_^");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Ocurrio un error: " + e.getMessage());
                } finally {
                    menuEditarProducto();
                }
                break;

            case "3":
                // Editar stock
                try {
                    System.out.print("Ingrese el nombre del producto que va a editar: ");
                    entrada.nextLine();
                    String nombreProducto = entrada.nextLine().trim();

                    System.out.print("Ingrese el nuevo stock: ");
                    int nuevoStock = entrada.nextInt();
                    entrada.nextLine();

                    if (producto.editarStock(nombreProducto, nuevoStock)) {
                        System.out.println("¡Stock editado con exito! ^_^");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ocurrio un error: " + e.getMessage());
                } finally {
                    menuEditarProducto();
                }
                break;

            case "4":
                mostrar();
                break;

            default:
                System.out.println("No es una opcion valida.");
                menuEditarProducto();
        }
    }

    /**
     * Lista todos los productos disponibles en el sistema, con formato tabular
     */
    public void listarProductos() {
        List<Producto> listaProducto = producto.listaProductos();

        System.out.println("\n### LISTA DE PRODUCTOS ###");

        if (listaProducto.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        // Encabezado de tabla
        System.out.printf("%-8s | %-30s | %-10s | %-8s%n",
                "ID", "NOMBRE", "PRECIO", "STOCK");
        System.out.println("-------------------------------------------------------");

        // Datos de cada producto
        for (Producto p : listaProducto) {
            System.out.printf("%-8d | %-30s | %-10.2f | %-8d%n",
                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getCantidad());
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Total de productos: " + listaProducto.size());
    }

}
