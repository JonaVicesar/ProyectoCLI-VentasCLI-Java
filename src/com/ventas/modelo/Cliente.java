package com.ventas.modelo;
/**
 *
 * @author Jona Vicesar
 */
public class Cliente {
    public String nombreCompleto;
    public int edad;
    public int documento;
    public int telefono;
    
    public Cliente(){
        this.nombreCompleto = "No identificado";
    }
    
    public Cliente(String nombreCompleto, int edad, int documento, int telefono){
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.documento = documento;
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
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
