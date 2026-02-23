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
public class NuevaVenta {
    int IdVentas, IdCliente, IdEmpleado,IdProducto,Cantidad;
    String Folio,FechaVenta;
    Float Monto,Subtotal;
    
    NuevaVenta() {
    }
    
    public NuevaVenta(int IdVentas,int IdCliente,int IdEmpleado,int IdProducto,int Cantidad, String Folio,String FechaVenta,Float Monto,Float Subtotal){
        this.IdVentas = IdVentas;
        this.IdCliente=IdCliente;
        this.IdEmpleado=IdEmpleado;
        this.IdProducto=IdProducto;
        this.Cantidad=Cantidad;
        this.Folio=Folio;
        this.FechaVenta=FechaVenta;
        this.Monto=Monto;
        this.Subtotal=Subtotal;                
    }

    public void setIdVentas(int IdVentas) {
        this.IdVentas = IdVentas;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public void setFechaVenta(String FechaVenta) {
        this.FechaVenta = FechaVenta;
    }

    public void setMonto(Float Monto) {
        this.Monto = Monto;
    }

    public void setSubtotal(Float Subtotal) {
        this.Subtotal = Subtotal;
    }

    public int getIdVentas() {
        return IdVentas;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public String getFolio() {
        return Folio;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public Float getMonto() {
        return Monto;
    }

    public Float getSubtotal() {
        return Subtotal;
    }
    
}
