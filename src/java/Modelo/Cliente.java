/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author josed
 */
public class Cliente {
    
    String IdCliente;
    String Nombres;
    String Apellidos;
    String Direccion;
    String Telefono;
    int Puntos;
    
            
    public Cliente(){
    }
    
    public Cliente(String IdCliente, String Nombres, String Apellidos, String Direccion, String Telefono, int Puntos){
        this.IdCliente = IdCliente;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Puntos = Puntos;
    }

    public void setIdCliente(String IdCliente) {
        this.IdCliente = IdCliente;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setPuntos(int Puntos) {
        this.Puntos = Puntos;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public String getNombres() {
        return Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public int getPuntos() {
        return Puntos;
    }

}
