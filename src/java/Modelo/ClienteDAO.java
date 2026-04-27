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
public class ClienteDAO {
    
        Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listarDetalles(){
        
        List<Cliente> listaClientes = new ArrayList<>();
       
        String sql = "select * from cliente";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getString(1));
                cliente.setNombres(rs.getString(2));
                cliente.setApellidos(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
//                int puntosInt = Integer.parseInt(rs.getString(6));
  //              cliente.setPuntos(puntosInt);
                
                listaClientes.add(cliente);
            }
        } catch (Exception e) {
        }

        return listaClientes;
    }

    
}
