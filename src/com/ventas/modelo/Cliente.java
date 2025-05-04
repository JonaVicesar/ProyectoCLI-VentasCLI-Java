package com.ventas.modelo;

/**
 *
 * @author Jona Vicesar
 */
public class Cliente {

    private String nombreCompleto;
    private String metodoPago;
    private String tarjeta;
    private int edad;
    private int documento;
    private String telefono;

    public Cliente() {
        this.nombreCompleto = "No identificado";
    }

    /**
     *
     * @param nombreCompleto
     * @param edad
     * @param documento
     * @param telefono
     */
    public Cliente(String nombreCompleto, int edad, int documento, String telefono, String metodoPago) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.documento = documento;
        this.telefono = telefono;
        this.metodoPago = metodoPago;
    }
    
    /**
     * 
     * @param nombreCompleto
     * @param edad
     * @param documento
     * @param telefono
     * @param metodoPago
     * @param tarjeta 
     */
    public Cliente(String nombreCompleto, int edad, int documento, String telefono, String metodoPago, String tarjeta) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.documento = documento;
        this.telefono = telefono;
        this.metodoPago = metodoPago;
        this.tarjeta = tarjeta;

    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     *
     * @return
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     *
     * @param nombreCompleto
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     *
     * @return
     */
    public int getEdad() {
        return edad;
    }

    /**
     *
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     *
     * @return
     */
    public int getDocumento() {
        return documento;
    }

    /**
     *
     * @param documento
     */
    public void setDocumento(int documento) {
        this.documento = documento;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente" + "\n nombre = " + nombreCompleto
                + "\n edad = " + edad
                + "\n documento = " + documento
                + "\n telefono = " + telefono;
    }

}
