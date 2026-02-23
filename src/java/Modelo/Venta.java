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
public class Venta {
    
    int idventa;
    int idcliente;
    int idempleado;
    String fechaventa;
    String horaventa;
    float monto;
    float cantidaddetalles;
    
    public Venta(){
        
    }
    public Venta(int idventa, int idcliente, int idempleado, String fechaventa, String horaventa, float monto, float cantidaddetalles){
        this.idventa = idventa;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.fechaventa = fechaventa;
        this.horaventa = horaventa;
        this.monto = monto;
        this.cantidaddetalles = cantidaddetalles;
    }

        public int getIdventa() {
        return idventa;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public String getFechaventa() {
        return fechaventa;
    }

    public String getHoraventa() {
        return horaventa;
    }

    public float getMonto() {
        return monto;
    }

    public float getCantidaddetalles() {
        return cantidaddetalles;
    }

    
    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public void setFechaventa(String fechaventa) {
        this.fechaventa = fechaventa;
    }

    public void setHoraventa(String horaventa) {
        this.horaventa = horaventa;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setCantidaddetalles(float cantidaddetalles) {
        this.cantidaddetalles = cantidaddetalles;
    }    
}
