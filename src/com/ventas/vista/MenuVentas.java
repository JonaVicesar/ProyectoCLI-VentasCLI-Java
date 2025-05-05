package com.ventas.vista;

import com.ventas.controlador.ControladorVenta;
import com.ventas.modelo.Producto;
import com.ventas.modelo.Venta;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

/**
 * Menú para la gestión de ventas
 * @author Jona Vicesar
 */
public class MenuVentas {
    private final Scanner entrada = new Scanner(System.in);
    private final ControladorVenta controladorVentas = new ControladorVenta();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Muestra el menú de ventas y maneja la selección de opciones
     */
    public void mostrar() {
        boolean salir = false;
        
        while (!salir) {
            System.out.println("MENU VENTAS ");
            System.out.println("1 - Crear Nueva Venta");
            System.out.println("2 - Listar Ventas");
            System.out.println("3 - Ver Detalle de Venta");
            System.out.println("4 - Eliminar Venta");
            System.out.println("5 - Informe de Productos Vendidos");
            System.out.println("6 - Volver al Menu Principal");
            System.out.print("Seleccione una opción: ");
            
            String opcion = entrada.nextLine();
            
            switch (opcion) {
                case "1":
                    crearNuevaVenta();
                    break;
                case "2":
                    listarVentas();
                    break;
                case "3":
                    verDetalleVenta();
                    break;
                case "4":
                    eliminarVenta();
                    break;
                case "5":
                    hacerInformeProductosVendidos();
                    break;
                case "6":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor intente nuevamente ￣へ￣");
                    break;
            }
        }
    }

    /**
     * Crea una nueva venta
     */
    private void crearNuevaVenta() {
        try {
            System.out.println("\nCREAR NUEVA VENTA");
            
            // Limpiar el carrito por si acaso
            controladorVentas.limpiarCarrito();
            
            System.out.print("Ingrese el documento del cliente: ");
            int documento = Integer.parseInt(entrada.nextLine());

            boolean agregarProductos = true;
            while (agregarProductos) {
                System.out.print("Ingrese el nombre del producto: ");
                String nombreProducto = entrada.nextLine();

                System.out.print("Ingrese la cantidad: ");
                int cantidad = Integer.parseInt(entrada.nextLine());

                try {
                    controladorVentas.agregarProductoAlCarrito(nombreProducto, cantidad);
                    System.out.println("Producto agregado al carrito ^_^");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    continue;
                }

                System.out.print("¿Desea agregar otro producto? (S/N): ");
                agregarProductos = entrada.nextLine().equalsIgnoreCase("S");
            }

            if (!controladorVentas.getCarritoProductos().isEmpty()) {
                System.out.println("\nResumen de la compra:");
                mostrarCarrito();
                
                System.out.print("¿Confirmar venta? (S/N): ");
                if (entrada.nextLine().equalsIgnoreCase("S")) {
                    int idVenta = controladorVentas.crearVenta(documento, LocalDate.now());
                    System.out.println("Venta creada exitosamente. ID de Venta: " + idVenta);
                    imprimirTicket(controladorVentas.obtenerVentaPorId(idVenta));
                } else {
                    System.out.println("Venta cancelada.");
                    controladorVentas.limpiarCarrito();
                }
            } else {
                System.out.println("No hay productos en el carrito. Venta cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Muestra el contenido del carrito
     */
    private void mostrarCarrito() {
        System.out.println("\nCARRITO DE COMPRAS");
        Map<Producto, Integer> carrito = controladorVentas.getCarritoProductos();
        
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        
        double total = 0;
        System.out.printf("%-20s %-10s %-10s %-10s%n", "PRODUCTO", "CANTIDAD", "PRECIO", "SUBTOTAL");
        System.out.println("----------------------------------------------------");
        
        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            double subtotal = producto.getPrecio() * cantidad;
            total += subtotal;
            
            System.out.printf("%-20s %-10d $%-9.2f $%-9.2f%n", 
                    producto.getNombre(), 
                    cantidad, 
                    producto.getPrecio(), 
                    subtotal);
        }
        
        System.out.println("----------------------------------------------------");
        System.out.printf("TOTAL: $%.2f%n", total);
    }

    /**
     * Lista todas las ventas
     */
    private void listarVentas() {
        System.out.println("\n==== LISTA DE VENTAS ====");
        
        if (controladorVentas.listarVentas().isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        
        System.out.printf("%-5s %-15s %-15s %-10s%n", "ID", "CLIENTE", "FECHA", "TOTAL");
        System.out.println("---------------------------------------------------");
        
        for (Venta venta : controladorVentas.listarVentas()) {
            System.out.printf("%-5d %-15s %-15s $%-9.2f%n", 
                    venta.getIdVenta(), 
                    venta.getCliente().getNombreCompleto(), 
                    venta.getFecha().format(dateFormatter), 
                    venta.getTotal());
        }
    }

    /**
     * Muestra los detalles de una venta específica
     */
    private void verDetalleVenta() {
        System.out.println("\nDETALLE DE VENTA");
        System.out.print("Ingrese el ID de la venta: ");
        
        try {
            int idVenta = Integer.parseInt(entrada.nextLine());
            Venta venta = controladorVentas.obtenerVentaPorId(idVenta);
            
            if (venta != null) {
                imprimirTicket(venta);
            } else {
                System.out.println("No se encontró la venta con ID " + idVenta);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID de venta inválido.");
        }
    }

    /**
     * Elimina una venta
     */
    private void eliminarVenta() {
        System.out.println("\nELIMINAR VENTA");
        System.out.print("Ingrese el ID de la venta a eliminar: ");
        
        try {
            int idVenta = Integer.parseInt(entrada.nextLine());
            Venta venta = controladorVentas.obtenerVentaPorId(idVenta);
            
            if (venta != null) {
                System.out.println("Detalles de la venta a eliminar:");
                imprimirTicket(venta);
                
                System.out.print("¿Está seguro que desea eliminar esta venta? (S/N): ");
                if (entrada.nextLine().equalsIgnoreCase("S")) {
                    if (controladorVentas.eliminarVenta(idVenta)) {
                        System.out.println("Venta eliminada exitosamente.");
                    } else {
                        System.out.println("No se pudo eliminar la venta.");
                    }
                } else {
                    System.out.println("Operación cancelada.");
                }
            } else {
                System.out.println("No se encontró la venta con ID " + idVenta);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID de venta inválido.");
        }
    }

    /**
     * Genera un informe de productos vendidos entre dos fechas
     */
    private void hacerInformeProductosVendidos() {
        System.out.println("\nINFORME DE PRODUCTOS VENDIDOS");
        
        try {
            System.out.print("Ingrese la fecha desde (DD/MM/AAAA): ");
            LocalDate fechaDesde = LocalDate.parse(entrada.nextLine(), dateFormatter);
            
            System.out.print("Ingrese la fecha hasta (DD/MM/AAAA): ");
            LocalDate fechaHasta = LocalDate.parse(entrada.nextLine(), dateFormatter);
            
            if (fechaHasta.isBefore(fechaDesde)) {
                System.out.println("Error: La fecha hasta no puede ser anterior a la fecha desde.");
                return;
            }
            
            Map<Producto, Integer> informe = controladorVentas.generarInformeProductosVendidos(fechaDesde, fechaHasta);
            
            if (informe.isEmpty()) {
                System.out.println("No hay productos vendidos en el rango de fechas especificado.");
                return;
            }
            
            System.out.println("\nInforme de productos vendidos desde " + 
                    fechaDesde.format(dateFormatter) + " hasta " + 
                    fechaHasta.format(dateFormatter));
            
            System.out.printf("%-20s %-10s %-10s %-10s%n", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL");
            System.out.println("----------------------------------------------------");
            
            double granTotal = 0;
            for (Map.Entry<Producto, Integer> entry : informe.entrySet()) {
                Producto producto = entry.getKey();
                int cantidad = entry.getValue();
                double subtotal = producto.getPrecio() * cantidad;
                granTotal += subtotal;
                
                System.out.printf("%-20s %-10d $%-9.2f $%-9.2f%n", 
                        producto.getNombre(), 
                        cantidad, 
                        producto.getPrecio(), 
                        subtotal);
            }
            
            System.out.println("----------------------------------------------------");
            System.out.printf("TOTAL VENTAS: $%.2f%n", granTotal);
            
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use DD/MM/AAAA.");
        }
    }

    /**
     * Imprime un ticket de venta
     * @param venta Venta a imprimir
     */
    private void imprimirTicket(Venta venta) {
        System.out.println("\n============ TICKET DE VENTA ============");
        System.out.println("ID Venta: " + venta.getIdVenta());
        System.out.println("Cliente: " + venta.getCliente().getNombreCompleto());
        System.out.println("Documento: " + venta.getCliente().getDocumento());
        System.out.println("Fecha: " + venta.getFecha().format(dateFormatter));
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %-10s %-10s %-10s%n", "PRODUCTO", "CANTIDAD", "PRECIO", "SUBTOTAL");
        System.out.println("----------------------------------------");
        
        venta.getListaCompras().forEach((producto, cantidad) -> {
            double subtotal = producto.getPrecio() * cantidad;
            System.out.printf("%-20s %-10d $%-9.2f $%-9.2f%n", 
                    producto.getNombre(), 
                    cantidad, 
                    producto.getPrecio(), 
                    subtotal);
        });
        
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL: $%.2f%n", venta.getTotal());
        System.out.println("========================================");
    }
}