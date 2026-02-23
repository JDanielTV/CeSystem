/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.Conexion;
import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author josed
 */
public class NuevaVentaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    NuevaVenta NVen = new NuevaVenta();

    //ObtenerIDVenta
    int ultimaVenta;
    String idVenta;
    String idSucursal;

    //agregarVenta
    boolean ventaExitosa;
    Producto prodVenta = new Producto();

    public String obtenerIDVenta() {
        String sql = "select * from ventas order by IdVenta DESC LIMIT 1";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ultimaVentaS = rs.getString(1);
                ultimaVenta = Integer.parseInt(ultimaVentaS);
            }
        } catch (Exception e) {
        }
        if (ultimaVenta == 0) {
            sql = "select * from configuracion where IdConfiguracion EQ SISTEMA";
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    idSucursal = rs.getString(2);
                }
            } catch (Exception e) {
            }
            if (idSucursal.length() == 1) {
                idSucursal = "0" + idSucursal;
            }
            idVenta = "1" + idSucursal + "000001";
        } else {
            idVenta = String.valueOf(ultimaVenta + 1);
        }
        return idVenta;
    }

    public List agregarVenta(String idVenta, String idCliente, int idEmpleado, float totalVenta, List detallesVenta) {
        int ConRegistros=0;

        LocalDateTime ahora = LocalDateTime.now();
        int año = ahora.getYear();
        int mes = ahora.getMonthValue();
        int dia = ahora.getDayOfMonth();
        int hora = ahora.getHour();
        int minutos = ahora.getMinute();
        int segundos = ahora.getSecond();

        String fechaVenta = String.valueOf(año) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia);
        String horaVenta = String.valueOf(hora) + ":" + String.valueOf(minutos) + ":" + String.valueOf(segundos);

//    -------------------------------------------------
//    *IdVenta    IdCliente   IdEmpleado    *FechaVenta    *HoraVenta   *Monto   CantidadDetalles
//   101000001      2010001       301001    2024-03-28     14:51:09      70                  1
        String añoL = String.valueOf(año);
        char[] añoChar = añoL.toCharArray();

        String año2digitos = String.valueOf(añoChar[2]) + String.valueOf(añoChar[3]);
        String sqlSelProd;
        String sqlSelEstibar;

        String sqlGeneral = "insert into ventas (IdVenta, IdCliente, IdEmpleado, FechaVenta, HoraVenta, Monto, CantidadDetalles) values ";
        String sqlDetalle = "insert into detalle_ventas (IdDetalleVentas, IdVenta, IdProducto, Cantidad, Subtotal) values ";
        String sqlEstib = "insert into por_estibar (IdProducto, Cantidad) values ";
        String sqlStock = "update into detalle_ventas (IdDetalleVentas, IdVenta, IdProducto, Cantidad, Subtotal) values ";
        String linea = null;
        String liniaEstib = null;
        List<String> actualizarProd = new ArrayList<>();
        
        con = cn.Conexion();
        try {
            ConRegistros = 0;
            for (int i = 0; i < detallesVenta.size(); i++) {

                prodVenta = (Producto) detallesVenta.get(i);
                String IdProd = prodVenta.getIdproducto();
                String cantidad = Float.toString(prodVenta.getStock());
                String subtotal = Float.toString(prodVenta.getPreciocompra());

                String IdDetalleVentas = idVenta + "." + (i + 1);
                
                float stock = 0;
                float cantidadEstib = 0;
                float cantidadEstibNew;
                //recorrer productos y restar
                sqlSelProd = "select * from producto where IdProducto  = " + IdProd;
                try {

                    ps = con.prepareStatement(sqlSelProd);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        String stockT = rs.getString(6);
                        stock = Float.parseFloat(stockT);
                    }
                } catch (SQLException | NumberFormatException e2) {
                }
                float newStock = stock - (Float.parseFloat(cantidad));
                String actProd= "update producto set Stock="+newStock+" where IdProducto="+IdProd;
                actualizarProd.add(actProd);
                //recorrer productos y restar

                if (linea == null) {
                    linea = "('" + IdDetalleVentas + "','" + idVenta + "','" + IdProd + "','" + cantidad + "','" + subtotal + "')";                    
                } else {
                    linea += ",('" + IdDetalleVentas + "','" + idVenta + "','" + IdProd + "','" + cantidad + "','" + subtotal + "')";
                }
                sqlSelEstibar = "select * from por_estibar where IdProducto  = " + IdProd;
                
                try {

                    ps = con.prepareStatement(sqlSelEstibar);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        String cantidadEstibS = rs.getString("Cantidad");
                        cantidadEstib = Float.parseFloat(cantidadEstibS);
                    }                   
                } catch (SQLException | NumberFormatException e2) {
                }

                if(cantidadEstib>0){
                    cantidadEstibNew = cantidadEstib+Float.parseFloat(cantidad);
                    if(liniaEstib == null){
                        liniaEstib = "('"+IdProd+"','"+cantidadEstibNew+"')";
                    }else{
                        liniaEstib += ",('"+IdProd+"','"+cantidadEstibNew+"')";                            
                    }
                }else{
                    cantidadEstibNew = Float.parseFloat(cantidad);
                    if(liniaEstib == null){
                        liniaEstib = "('"+IdProd+"','"+cantidadEstibNew+"')";
                    }else{
                        liniaEstib += ",('"+IdProd+"','"+cantidadEstibNew+"')";                            
                    }
                }                       
                
                ConRegistros++;
            }

            sqlDetalle += linea;
            idCliente = "200013";
            sqlGeneral += "('" + idVenta + "','" + idCliente + "','" + idEmpleado + "','" + fechaVenta + "','" + horaVenta + "','" + totalVenta + "','" + ConRegistros + "')";
            sqlEstib += liniaEstib;

            ps = con.prepareStatement(sqlGeneral);
            ps.executeUpdate();
            ps = con.prepareStatement(sqlDetalle);
            ps.executeUpdate();
            for(int i = 0; i<actualizarProd.size(); i++){
                ps = con.prepareStatement(actualizarProd.get(i));
                ps.executeUpdate();
            }
            ps = con.prepareStatement(sqlEstib);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error en la escritura");
            System.out.println(e.getMessage()); 
        }
        List<String> detallesVentaRetorno = new ArrayList();
        detallesVentaRetorno.add(idVenta);
        detallesVentaRetorno.add(idCliente);
        detallesVentaRetorno.add(String.valueOf(idEmpleado));
        detallesVentaRetorno.add(fechaVenta);
        detallesVentaRetorno.add(horaVenta);
        detallesVentaRetorno.add(String.valueOf(totalVenta));
        detallesVentaRetorno.add(String.valueOf(ConRegistros));

        return detallesVentaRetorno;

    }

}
