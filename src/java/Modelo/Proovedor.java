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
public class Proovedor {
    String idproovedor;
    String nombre;
    String direccion;
    String telefono;
            
    public Proovedor(){
    }
    
    public Proovedor(String idproovedor, String nombre, String direccion, String telefono){
        this.idproovedor = idproovedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getIdproovedor() {
        return idproovedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdproovedor(String idproovedor) {
        this.idproovedor = idproovedor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
