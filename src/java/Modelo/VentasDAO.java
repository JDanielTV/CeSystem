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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josed
 */
public class VentasDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    Connection conP;
    PreparedStatement psP;
    ResultSet rsP;

    int r;
    int ConRegistros;

    String sql;

    public List listar(String Periodo) {
        List<Venta> VentasList = new ArrayList();
        LocalDateTime ahora = LocalDateTime.now();
        int año = ahora.getYear();
        int mes = ahora.getMonthValue();
        int dia = ahora.getDayOfMonth();

        LocalDate hoy = LocalDate.now();
        System.out.println("hoyDt: " + hoy);

// Método 1: Ajustar al lunes (si la semana empieza en lunes)
        LocalDate inicioSemanaLunest = hoy.with(DayOfWeek.MONDAY);
        System.out.println("Inicio de semana (Lunes): " + inicioSemanaLunest);

        switch (Periodo) {
            case "HOY":
                sql = "select * from ventas where FechaVenta = '" + hoy + "'";
                break;
            case "SEMANAL":
                LocalDate inicioSemanaLunes = hoy.with(DayOfWeek.MONDAY);               
                sql = "select * from ventas where FechaVenta >= '" + inicioSemanaLunes + "' AND FechaVenta <= '"+ hoy +"'";
                break;
            case "MENSUAL":
                LocalDate inicioMes = hoy.with(TemporalAdjusters.firstDayOfMonth());
                sql = "select * from ventas where FechaVenta >= '" + inicioMes + "' AND FechaVenta <= '"+ hoy +"'";
                break;
        }

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Venta venta = new Venta();
                venta.setIdventa(Integer.parseInt(rs.getString(1)));
                venta.setIdcliente(Integer.parseInt(rs.getString(2)));
                venta.setIdempleado(Integer.parseInt(rs.getString(3)));
                venta.setFechaventa(rs.getString(4));
                venta.setHoraventa(rs.getString(5));
                venta.setMonto(parseFloat(rs.getString(6)));
                venta.setCantidaddetalles(Integer.parseInt(rs.getString(7)));
                VentasList.add(venta);
            }
        } catch (SQLException e) {
        }

        return VentasList;

    }
    public float obtenerMontoperiodo(List<Venta> VentasList){
        float totalPeriodo = 0;
        for (int i = 0; i < VentasList.size(); i++) {
            Venta subtotales = VentasList.get(i);
            totalPeriodo = (float) (totalPeriodo + subtotales.getMonto());
        }
        return totalPeriodo;
        
    }
    public List mostrar(String idVenta){
        List<Producto> listaDet = new ArrayList();
        String sqlP;
        String descripcion = null;
        
//        SELECT * FROM table_name WHERE ID LIKE '3%' ORDER BY ID ASC;
        sql = "select * from detalle_ventas where IdDetalleVentas LIKE '" + idVenta + "%' ORDER BY IdDetalleVentas ASC";
        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String idProd = rs.getString(3);
                
                
                Producto prod = new Producto();
                sqlP = "select * from producto where IdProducto = '" + idProd + "'";

                conP = cn.Conexion();
                psP = conP.prepareStatement(sqlP);
                rsP = psP.executeQuery();
                while (rsP.next()) {
                    descripcion = rsP.getString("Descripcion");                     
                }
                float cantidad = parseFloat(rs.getString(4));                
                float subtotal = parseFloat(rs.getString(5));
                
                prod.setIdproducto(idProd);
                prod.setDescripcion(descripcion);
                prod.setStock(cantidad);
                prod.setPrecioventa(subtotal/cantidad);
                prod.setPreciocompra(subtotal);
                prod.setIdVenta(idVenta);
                
                listaDet.add(prod);
            }
        } catch (SQLException e) {
        }



        return listaDet;
    }
}
