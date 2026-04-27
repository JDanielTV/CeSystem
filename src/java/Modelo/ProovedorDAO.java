/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josed
 */
public class ProovedorDAO {

    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar(){
        List<String> lista = new ArrayList<>();
       
        String sql = "select * from proovedores";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String isProov = rs.getString(1);
                lista.add(isProov);
            }
        } catch (Exception e) {
        }

        return lista;
    }

    public List listarDetalles(){
        
        List<Proovedor> listaProov = new ArrayList<>();
       
        String sql = "select * from proovedores";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proovedor proov = new Proovedor();
                proov.setIdproovedor(rs.getString(1));
                proov.setNombre(rs.getString(2));
                proov.setDireccion(rs.getString(3));
                proov.setTelefono(rs.getString(4));
                listaProov.add(proov);
            }
        } catch (Exception e) {
        }

        return listaProov;
    }

    public Proovedor proovedorEditar(String idProovedor){
       
        String sql = "select * from proovedores where IdProovedor='"+idProovedor+"'";
        Proovedor proov = new Proovedor();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                proov.setIdproovedor(rs.getString(1));
                proov.setNombre(rs.getString(2));
                proov.setDireccion(rs.getString(3));
                proov.setTelefono(rs.getString(4));
            }
        } catch (Exception e) {
        }

        return proov;

    }
    
    
    public boolean agregarProovedor(Proovedor proov){
    boolean respuesta = false;
    
        String sql = "insert into proovedores (IdProovedor, Nombre, Direccion, Telefono) values ";
       
        try {
            String ID = proov.getIdproovedor();
            String nombre = proov.getNombre();
            String direccion = proov.getDireccion();
            String telefono = proov.getTelefono();
            sql += "('" + ID + "','" + nombre + "','" + direccion + "','" + telefono + "')";
                
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            respuesta = true;
        } catch (Exception e) {

        }    
        return respuesta;
    }
    public boolean eliminarProovedor(String proovId){
        boolean respuesta = false;
    
        String sql = "delete from proovedores where IdProovedor = '" + proovId+"'";
        
        try {               
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            respuesta = true;
        } catch (Exception e) {

        }    
        return respuesta;
    }
}
