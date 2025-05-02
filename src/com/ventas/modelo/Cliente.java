package com.ventas.modelo;
/**
 *
 * @author Jona Vicesar
 */
public class Cliente {
    public String nombreCompleto;
    public int edad;
    public int documento;
    public String telefono;
    
    public Cliente(){
        this.nombreCompleto = "No identificado";
    }
    
    /**
     * 
     * @param nombreCompleto
     * @param edad
     * @param documento
     * @param telefono 
     */
    public Cliente(String nombreCompleto, int edad, int documento, String telefono){
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.documento = documento;
        this.telefono = telefono;
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
        return "Cliente"+  "\n nombre = ss" + nombreCompleto + 
                           "\n edad = " + edad + 
                           "\n documento = " + documento + 
                           "\n telefono = " + telefono;
    }
   
}
