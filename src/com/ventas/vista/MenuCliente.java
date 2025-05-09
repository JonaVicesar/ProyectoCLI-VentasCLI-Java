package com.ventas.vista;

import com.ventas.controlador.ControladorCliente;
import com.ventas.modelo.Cliente;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Vista del sistema encargada de mostrar el menú de gestión de clientes
 * e interactuar con el usuario mediante consola.
 * Se conecta con el controlador para realizar operaciones.
 * Forma parte de la capa de presentación (Vista) del patrón MVC.
 * 
 * @author Jona Vicesar
 */
public class MenuCliente {

    // Scanner para entrada por consola
    public Scanner entrada = new Scanner(System.in);

    // Controlador que maneja la lógica de negocio de los clientes
    public static final ControladorCliente controladorClientes = new ControladorCliente();

    /**
     * Muestra el menú principal para gestionar clientes.
     */
    public void mostrar() {
        System.out.println("####MENU CLIENTES#### ^_^");
        System.out.println("Seleccione una opcion");
        System.out.println("1 - Agregar Cliente");
        System.out.println("2 - Eliminar Cliente");
        System.out.println("3 - Editar Cliente");
        System.out.println("4 - Lista de Clientes");
        System.out.println("5 - Volver al Menu Principal");

        seleccionarOpcion();
    }

    /**
     * Maneja la opción ingresada por el usuario en el menú principal.
     */
    private void seleccionarOpcion() {
        String opcion = entrada.next();

        switch (opcion) {
            case "1":
                // Agregar Cliente
                try {
                    System.out.println("--Ingrese los datos requeridos para agregar un nuevo cliente (*^-^*)\n");

                    System.out.println("Ingrese su Nombre (no puede contener números ni simbolos)");
                    String nombre = entrada.next();
                    entrada.nextLine();

                    System.out.println("Ingrese su edad (debe ser mayor a 13)");
                    int edad = entrada.nextInt();
                    entrada.nextLine();

                    System.out.println("Ingrese su numero de documento (no se puede editar luego)");
                    int documento = entrada.nextInt();
                    entrada.nextLine();

                    System.out.println("Ingrese su numero de telefono");
                    String telefono = entrada.next();
                    entrada.nextLine();

                    System.out.println("Seleccione su metodo de pago (Tarjeta o Efectivo): ");
                    String metodo = entrada.next().toUpperCase();
                    entrada.nextLine();

                    // Cliente con tarjeta
                    if (metodo.equalsIgnoreCase("tarjeta")) {
                        System.out.println("Ingrese su numero de tarjeta: ");
                        String tarjeta = entrada.next();

                        if (controladorClientes.agregarCliente(nombre, edad, documento, telefono, metodo, tarjeta)) {
                            System.out.println("Cliente agregado exitosamente! (*^_^*)");
                        }
                    }

                    // Cliente con efectivo
                    if (metodo.equalsIgnoreCase("efectivo")) {
                        if (controladorClientes.agregarCliente(nombre, edad, documento, telefono, metodo)) {
                            System.out.println("Cliente agregado exitosamente! (*^_^*)");
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ocurrio un error: " + e.getMessage());
                } finally {
                    mostrar();
                }
                break;

            case "2":
                // Eliminar Cliente
                try {
                    System.out.println("Ingrese el documento del cliente que desea eliminar:");
                    int documentoCliente = entrada.nextInt();

                    System.out.println("¿Esta seguro que desea eliminar el cliente? (Y = Sí | N = No)");
                    String confirmacion = entrada.next();

                    if (confirmacion.equalsIgnoreCase("y")) {
                        if (controladorClientes.eliminarCliente(documentoCliente)) {
                            System.out.println("Usuario eliminado exitosamente!");
                        }
                    } else {
                        System.out.println("Cancelado. Volviendo al menu...");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese solo numeros validos.");
                    entrada.nextLine(); // limpiar el scanner
                } finally {
                    mostrar();
                }
                break;

            case "3":
                // Editar Cliente
                menuEditar();
                break;

            case "4":
                // Listar Clientes
                listarClientes();
                break;

            case "5":
                // Volver al menú principal
                MenuPrincipal menu = new MenuPrincipal();
                menu.mostrar();
                break;

            default:
                System.out.println("Opcion incorrecta. Intente nuevamente.");
                mostrar();
        }
    }

    /**
     * Muestra el submenú de edición de clientes.
     */
    public void menuEditar() {
        System.out.println("#MENU EDICION CLIENTES");
        System.out.println("Seleccione una opcion ^_^:");
        System.out.println("1 - Editar Nombre");
        System.out.println("2 - Editar Telelono");
        System.out.println("3 - Volver al Menu Principal");

        seleccionarOpcionEditar();
    }

    /**
     * Maneja la opción de edición seleccionada por el usuario.
     */
    private void seleccionarOpcionEditar() {
        String opcion = entrada.next();
        switch (opcion) {
            case "1":
                // Editar nombre
                System.out.println("Ingrese el numero de documento del cliente:");
                int documento = entrada.nextInt();
                System.out.println("Ingrese el nuevo nombre:");
                String nuevoNombre = entrada.next();

                if (controladorClientes.editarNombre(nuevoNombre, documento)) {
                    System.out.println("¡Nombre editado exitosamente! ヾ(≧▽≦*)o");
                } else {
                    System.out.println("Ocurrio un error al editar el nombre.");
                }
                menuEditar();
                break;

            case "2":
                // Editar teléfono
                System.out.println("Ingrese el documento del cliente:");
                int doc = entrada.nextInt();
                entrada.nextLine();
                System.out.println("Ingrese el nuevo telefono (ej: 0981123456):");
                String nuevoTelefono = entrada.nextLine().trim();

                if (controladorClientes.editarTelefono(nuevoTelefono, doc)) {
                    System.out.println("¡Telefono actualizado con exito! ヽ(^◇^*)/");
                } else {
                    System.out.println("Error al actualizar el telefono.");
                }
                menuEditar();
                break;

            case "3":
                // Volver al menú principal
                MenuPrincipal menu = new MenuPrincipal();
                menu.mostrar();
                break;

            default:
                System.out.println("Opcion invalida.");
                menuEditar();
                break;
        }
    }

    /**
     * Muestra la lista de clientes registrados en forma de tabla.
     * Si no hay clientes registrados, informa al usuario.
     */
    private void listarClientes() {
        List<Cliente> clientes = controladorClientes.listaClientes();
        System.out.println("\n### LISTA DE CLIENTES ### (‾◡◝)");

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            mostrar();
            return;
        }

        // Encabezado de tabla
        System.out.printf("%-30s %-10s %-15s %-15s %-15s %-20s%n",
                "NOMBRE", "EDAD", "DOCUMENTO", "TELEFONO", "METODO", "TARJETA");
        System.out.println("----------------------------------------------------------------------------------------------");

        // Datos de cada cliente
        for (Cliente c : clientes) {
            System.out.printf("%-30s %-10d %-15d %-15s %-15s %-20s%n",
                    c.getNombreCompleto(),
                    c.getEdad(),
                    c.getDocumento(),
                    c.getTelefono(),
                    c.getMetodoPago(),
                    c.getTarjeta() != null ? "****" + c.getTarjeta().substring(Math.max(0, c.getTarjeta().length() - 4)) : "N/A");
        }

        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Total de clientes: " + clientes.size());

        mostrar();
    }
}
