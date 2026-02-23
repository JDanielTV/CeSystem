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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josed
 */
public class ProductoMasDAO {
    
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    Producto prod=new Producto();

    //Operaciones CRUD
    public List listar(){
        String sql = "select * from producto";
        List<Producto>listaProd=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Producto prod = new Producto();
                prod.setIdproducto(rs.getString(1));
                prod.setDescripcion(rs.getString(2));
                prod.setProovedor(rs.getString(3));
                prod.setPreciocompra(rs.getFloat(4));
                prod.setPrecioventa(rs.getFloat(5));
                prod.setStock(rs.getFloat(6));
                prod.setStockpiso(rs.getFloat(7));
                listaProd.add(prod);
            }
        }catch (Exception e){            
        }        
        return listaProd;
    }   
    
    public int agregar(Producto prod){
        String sql = "insert into producto(IdProducto, Descripcion, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso)values(?,?,?,?,?,?,?)";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);            
            ps.setString(1,prod.getIdproducto());            
            ps.setString(2,prod.getDescripcion());
            ps.setString(3,prod.getProovedor());
            String precioc = String.valueOf(prod.getPreciocompra());
            ps.setString(4,precioc);
            String preciov = String.valueOf(prod.getPrecioventa());
            ps.setString(5,preciov);
            String stock = String.valueOf(prod.getStock());
            ps.setString(6,stock);            
            String stockpiso = String.valueOf(prod.getStockpiso());
            ps.setString(7,stockpiso);            
            ps.executeUpdate();
        }catch (Exception e){            
        }
        return r;
    }
    
    public Producto listarId(String id){
        Producto prod= new Producto();
        String sql="select * from producto where IdProducto="+id;
        try{            
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){    
                prod.setIdproducto(id);
                prod.setDescripcion(rs.getString(2));
                prod.setProovedor(rs.getString(3));
                float preciocompra = parseFloat(rs.getString(4));
                prod.setPreciocompra(preciocompra);
                float precioventa = parseFloat(rs.getString(5));
                prod.setPrecioventa(precioventa);
                float stock = parseFloat(rs.getString(6));
                prod.setStock(stock);
                float stockp = parseFloat(rs.getString(7));
                prod.setStockpiso(stockp);                
            }
                    
        }catch (Exception e){            
        }
        return prod;
    }
            
    public int actualizar(Producto pro){
        String sql = "update producto set Proovedor=?, PrecioCompra=?, PrecioVenta=?, Stock=? where IdProducto=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1,pro.getProovedor());
            ps.setFloat(2,pro.getPreciocompra());
            ps.setFloat(3,pro.getPrecioventa());
            ps.setFloat(4,pro.getStock());
            ps.setString(5,pro.getIdproducto());            
            ps.executeUpdate();
        }catch (Exception e){            
        }
        return r;        
    }
    
    public void eliminar(String id){
        String sql="delete from producto where IdProducto = "+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){            
        }
        
    }


}
