package com.ventas.vista;

import com.ventas.controlador.ControladorCliente;
import com.ventas.modelo.Cliente;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jona Vicesar
 */
public class MenuCliente {

    public Scanner entrada = new Scanner(System.in);
    public static final ControladorCliente controladorClientes = new ControladorCliente();

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

    
    private void seleccionarOpcion() {
        String opcion = entrada.next();

        switch (opcion) {
            case "1":
                try {
                    System.out.println("--Ingrese los requeridos para agregar un nuevo cliente (*^-^*)");
                    System.out.println(" ");
                    System.out.println("Ingrese su Nombre(El nombre no puede contener numeros ni caracteres especiales))");
                    String nombre = entrada.next();
                    entrada.nextLine();
                    System.out.println("Ingrese su edad(debe ser mayor  a 13");
                    int edad = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingrese su numero de documento");
                    System.out.println("RECUERDE QUE UNA VEZ CREADO EL NUMERO DE DOCUMENTO NO SE PUEDE EDITAR (ㆆ_ㆆ)");
                    int documento = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingrese su numero de telefono");
                    String telefono = entrada.next();
                    entrada.nextLine();
                    System.out.println("Seleccione su metodo de pago(Tarjeta o Efectivo): ");
                    String metodo = entrada.next().toUpperCase();
                    entrada.nextLine();

                    if (metodo.equalsIgnoreCase("tarjeta")) {
                        System.out.println("Ingrese su numero de tarjeta: ");
                        String tarjeta = entrada.next();

                        if (controladorClientes.agregarCliente(nombre, edad, documento, telefono, metodo, tarjeta)) {
                            System.out.println("Cliente agregado exitosamente!(*^_^*)");
                        }
                    }

                    if (metodo.equalsIgnoreCase("efectivo")) {
                        if (controladorClientes.agregarCliente(nombre, edad, documento, telefono, metodo)) {
                            System.out.println("Cliente agregado exitosamente!(*^_^*)");

                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ocurrio un error: " + e.getMessage());
                } finally {
                    mostrar();
                }
                break;

            case "2":
                try {
                    System.out.println("Ingrese el documento del cliente que desea eliminar: ");

                    int documentoCliente = entrada.nextInt();

                    System.out.println("ESTA SEGURO QUE QUIERE ELIMINAR EL CLIENTE? (INGRESE (Y) SI SI (N) SI NO");
                    String confirmacion = entrada.next();

                    if (confirmacion.equalsIgnoreCase("y")) {
                        if (controladorClientes.eliminarCliente(documentoCliente)) {
                            System.out.println("Usuario elimnado exitosamente!");
                        }
                    }

                    if (confirmacion.equalsIgnoreCase("n")) {
                        System.out.println("Muchaas gracias. Volviendo al Menu Cliente! ^_^");
                    }
                }catch(IllegalArgumentException e){
                    System.out.println("Ocurrio un error: " + e.getMessage());
                    mostrar();
                    
                            
                }catch(InputMismatchException e){
                    System.out.println("Ingrese NUMEROS no otros caracteres");
                    mostrar();
                    
                }

                mostrar();
                break;

            case "3":
                menuEditar();
                break;

            case "4":
                listarClientes();
                mostrar();
                break;

            case "5":
                MenuPrincipal menu = new MenuPrincipal();
                menu.mostrar();
                break;

            default :
                System.out.println("Opcion Incorrecta. Vuelva a Intentar");
                mostrar();
        }
    }

    public void menuEditar() {
        System.out.println("#MENU CLIENTES");
        System.out.println("Seleccione una opcion ^_^: ");
        System.out.println("1 - Editar Nombre");
        System.out.println("2 - Editar Telefono");
        System.out.println("3 - Volver al Menu Principal");

        seleccionarOpcionEditar();
    }

    private void seleccionarOpcionEditar() {
        String opcion = entrada.next();
        switch (opcion) {
            case "1":
                System.out.println("Ingrese el numero de documento del Cliente que va a editar  ");
                int documento = entrada.nextInt();
                System.out.println("Ingrese el Nuevo Nombre");
                String nuevoNombre = entrada.next();

                if (controladorClientes.editarNombre(nuevoNombre, documento)) {
                    System.out.println("Nombre editado exitosamente! ヾ(≧▽≦*)o");

                } else {
                    System.out.println("Ocurrio un error al editar el nombre X﹏X");
                }
                menuEditar();

                break;

            case "2":
                System.out.println("Ingrese el documento del cliente:");
                int doc = entrada.nextInt();
                entrada.nextLine();
                System.out.println("Ingrese el nuevo telefono (ej:0981123456):");
                String nuevoTelefono = entrada.nextLine().trim();

                if (controladorClientes.editarTelefono(nuevoTelefono, doc)) {
                    System.out.println("Telefono actualizado con exito! ヽ(^◇^*)/");
                } else {

                    System.out.println("Error al actualizar el teléfono (╥﹏╥)");
                }
                menuEditar();
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
        List<Cliente> clientes = controladorClientes.listaClientes();
        System.out.println("\n### LISTA DE CLIENTES ### (‾◡◝)");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        // Encabezado de la tabla
        System.out.printf("%-30s %-10s %-15s %-15s%n", "NOMBRE", "EDAD", "DOCUMENTO", "TELEFONO");
        System.out.println("------------------------------------------------------------------------------");

        // Datos de los clientes
        for (Cliente c : clientes) {
            System.out.printf("%-30s %-10d %-15d %-15s %-15s %-20s%n",
                    c.getNombreCompleto(),
                    c.getEdad(),
                    c.getDocumento(),
                    c.getTelefono(),
                    c.getMetodoPago(),
                    c.getTarjeta() != null ? "****" + c.getTarjeta().substring(12) : "N/A");

        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Total de clientes: " + clientes.size());
        mostrar();
    }
}
