/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.Conexion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author josed
 */
public class ProductoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    int ConRegistros;

    float cantidad;
    boolean flagExiste;

//Variables NuevaVenta
    //variables de metodo detalleProd
    Producto prod = new Producto();
    List<Producto> agregarProd = new ArrayList<>();
    String idProdList;
    String errorIdProd;

    //Variables del metodo calcularTotal
    float totalVenta;
//Cierra variables NuevaVenta

    //Operaciones CRUD
    public List listar() {
        String sql = "select * from producto";
        List<Producto> listaProd = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdproducto(rs.getString(1));
                prod.setDescripcion(rs.getString(2));
                prod.setProovedor(rs.getString(5));
                prod.setPreciocompra(rs.getFloat(6));
                prod.setPrecioventa(rs.getFloat(7));
                prod.setStock(rs.getFloat(8));
                prod.setStockpiso(rs.getFloat(9));
                listaProd.add(prod);
            }
        } catch (Exception e) {
        }
        return listaProd;
    }

    public int agregar(Producto prod) {
        String sql = "insert into producto(IdProducto, Descripcion, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso)values(?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getIdproducto());
            ps.setString(2, prod.getDescripcion());
            ps.setString(3, prod.getProovedor());
            String precioc = String.valueOf(prod.getPreciocompra());
            ps.setString(4, precioc);
            String preciov = String.valueOf(prod.getPrecioventa());
            ps.setString(5, preciov);
            String stock = String.valueOf(prod.getStock());
            ps.setString(6, stock);
            String stockpiso = String.valueOf(prod.getStockpiso());
            ps.setString(7, stockpiso);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public Producto listarId(String id) {
        Producto prod = new Producto();
        String sql = "select * from producto where IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                prod.setIdproducto(id);
                prod.setDescripcion(rs.getString(2));
                prod.setProovedor(rs.getString(5));
                float preciocompra = parseFloat(rs.getString(6));
                prod.setPreciocompra(preciocompra);
                float precioventa = parseFloat(rs.getString(7));
                prod.setPrecioventa(precioventa);
                float stock = parseFloat(rs.getString(8));
                prod.setStock(stock);
//                float stockp = parseFloat(rs.getString(7));
  //              prod.setStockpiso(stockp);
            }

        } catch (Exception e) {
        }
        return prod;
    }

    public int actualizar(Producto pro) {
        String sql = "update producto set Proovedor=?, PrecioCompra=?, PrecioVenta=?, Stock=? where IdProducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getProovedor());
            ps.setFloat(2, pro.getPreciocompra());
            ps.setFloat(3, pro.getPrecioventa());
            ps.setFloat(4, pro.getStock());
            ps.setString(5, pro.getIdproducto());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public void eliminar(String id) {
        String sql = "delete from producto where IdProducto = " + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

//Carga Masiva            
    public List listarMas() {
        String fileName = "C:\\Users\\josed\\Documents\\NetBeansProjects\\Cesystem\\PruebaCargaMasiva.csv";

        BufferedReader lector;
        String linea;
        String partes[] = null;
        List<Producto> listaProdM = new ArrayList<>();
        

        try {
            lector = new BufferedReader(new FileReader(fileName));
            while ((linea = lector.readLine()) != null) {
                partes = linea.split(",");
                Producto prodM = new Producto();
                
                if(!partes[0].equals(null)){
                prodM.setIdproducto(partes[0]);               
                prodM.setDescripcion(partes[1]);
                prodM.setDescripcionc(partes[2]);
                prodM.setMarca(partes[3]);
                prodM.setProovedor(partes[4]);
                float preciocompra = parseFloat(partes[5]);
                prodM.setPreciocompra(preciocompra);
                float precioventa = parseFloat(partes[6]);
                prodM.setPrecioventa(precioventa);
                float stock = parseFloat(partes[7]);
                prodM.setStock(stock);
//                float stockp = parseFloat(partes[8]);
  //              prodM.setStockpiso(stockp);
                prodM.setPresentacion(partes[9]);
                listaProdM.add(prodM);
                }

            }
        } catch (IOException e) {
        }
        return listaProdM;
    }

    /**
     *
     * @param prodMas
     * @return
     */
    public int agregarMas(List<Producto> prodMas) {
//        String sql = "insert into producto(IdProducto, Descripcion, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso)values(?,?,?,?,?,?,?)";
//        String sql = "insert into producto (IdProducto, Descripcion, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso) values ";
        String sql = "insert into producto (IdProducto, Descripcion, DescripcionCorta, Marca, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso, Presentacion) values ";
        String linea = null;
        try {
            ConRegistros = 0;
            for (Producto prodMa : prodMas) {
                String ID = prodMa.getIdproducto();
                String Descripcion = prodMa.getDescripcion();
                String DescripcionC = prodMa.getDescripcionc();
                String Marca = prodMa.getMarca();
                String Provedor = prodMa.getProovedor();
                String PrecioC = Float.toString(prodMa.getPreciocompra());
                String PrecioV = Float.toString(prodMa.getPrecioventa());
                String Stock = Float.toString(prodMa.getStock());
                String StockP = Float.toString(prodMa.getStockpiso());
                String Presentacion = prodMa.getPresentacion();
                if (linea == null) {
                    linea = "('" + ID + "','" + Descripcion + "','" + DescripcionC + "','" + Marca + "','" + Provedor + "','" + PrecioC + "','" + PrecioV + "','" + Stock + "','" + StockP + "','" + Presentacion + "')";
                } else {
                    linea = ",('" + ID + "','" + Descripcion + "','" + DescripcionC + "','" + Marca + "','" + Provedor + "','" + PrecioC + "','" + PrecioV + "','" + Stock + "','" + StockP + "','" + Presentacion + "')";
                }
                sql += linea;
                ConRegistros++;
            }
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {

        }
        return ConRegistros;
    }

    public List buscar(String indicio) {
        String sql = null;
        if (!"".equals(indicio)) {
            sql = "select * from producto where Descripcion like '%" + indicio + "%'";
        }

        List<Producto> listaProd = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdproducto(rs.getString(1));
                prod.setDescripcion(rs.getString(2));
                listaProd.add(prod);
            }
        } catch (Exception e) {
        }
        return listaProd;
    }

    public ProductoErrorId detalleProd(String IdProducto, String actCantidad) {
        String errorId = null;
        flagExiste = false;
        int flagCantidad = -1;
        flagCantidad = IdProducto.indexOf("*");
        if (flagCantidad >= 0) {
            String cantidadStr = IdProducto.substring(0, flagCantidad);
            if (cantidadStr.isEmpty()) {
                cantidad = 1;
            } else {
                cantidad = Float.parseFloat(cantidadStr);
            }
            int numCaracteres = IdProducto.length();
            IdProducto = IdProducto.substring(flagCantidad + 1, numCaracteres);
        } else {
            cantidad = 1;
        }

        for (int i = 0; i < agregarProd.size(); i++) {
            idProdList = agregarProd.get(i).getIdproducto();
            if (idProdList.equals(IdProducto)) {
                flagExiste = true;
                float cantidadList = agregarProd.get(i).getStock();
                String presentacion = agregarProd.get(i).getPresentacion();
                float nueCantidad = 0;
                int cantidadInt = (int) cantidad;

                if ((cantidad != cantidadInt && presentacion.equals("PIEZA"))) { // O Math.ceil(num)
                    errorId = "El producto " + IdProducto + " - " + agregarProd.get(i).getDescripcion() + " solo venta por pieza, no a granel.";
                } else {
                    switch (actCantidad) {
                        case "":
//                        cantidadList = agregarProd.get(i).getStock();
                            nueCantidad = cantidad + cantidadList;
                            break;
                        case "Minus":
                            //                      cantidadList = agregarProd.get(i).getStock();
                            nueCantidad = cantidadList - 1;
                            break;
                        case "Plus":
                            //                    cantidadList = agregarProd.get(i).getStock();
                            nueCantidad = cantidadList + 1;
                            break;

                    }
                    if (nueCantidad <= 0) {
                        eliminarProd(IdProducto);
                        break;
                    }
                    if (nueCantidad > agregarProd.get(i).getStockpiso()) {
                        errorId = "El producto " + IdProducto + " - " + agregarProd.get(i).getDescripcion() + " no tiene stock disponible.";
//                    flagExiste = true;
                        break;
                    }
                    if (nueCantidad < agregarProd.get(i).getStockpiso()) {
                        agregarProd.get(i).setStock(nueCantidad);
                        float precio = agregarProd.get(i).getPrecioventa();
                        float nueSubtotal = precio * nueCantidad;
                        agregarProd.get(i).setPreciocompra(nueSubtotal);
                        //                  flagExiste = true;
                    }
                }

            }
        }

        if (flagExiste == false) {

            String sql = "select * from producto where IdProducto = '" + IdProducto + "'";

            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                errorId = "El ID " + IdProducto + " no se encuentra en la BD.";
                while (rs.next()) {
                    Producto prod = new Producto();
                    float stockAva = parseFloat(rs.getString(6));
                    if (stockAva > 0 && stockAva >= cantidad) {
                        int cantidadInt = (int) cantidad;
                        String presentacion = rs.getString(10);
                        String descripcion = rs.getString(2);
                        if ((cantidad != cantidadInt && presentacion.equals("PIEZA"))) { // O Math.ceil(num)
                            errorId = "El producto " + IdProducto + " - " + descripcion + " solo venta por pieza, no a granel.";
                        } else {

                            prod.setIdproducto(rs.getString(1));
                            prod.setDescripcion(descripcion);
                            float precioventa = parseFloat(rs.getString(7));
                            prod.setPrecioventa(precioventa);
                            prod.setStock(cantidad);

                            float cantidadDisponible;
                            prod.setStockpiso(parseFloat(rs.getString(8)));

                            float subtotal = precioventa * cantidad;
                            prod.setPreciocompra(subtotal);
                            agregarProd.add(prod);

                            prod.setPresentacion(presentacion);
                            errorId = "";
                        }
                    } else {
                        errorId = "El producto " + IdProducto + " - " + rs.getString(2) + " no tiene stock disponible.";
                    }
                }
            } catch (SQLException e) {
            }
        }
//        ProductoErrorId proderrId = new ProductoErrorId();
        //proderrId.setAgregarProd(agregarProd);
        //proderrId.setErrorId(errorId);
//        return agregarProd;

        return new ProductoErrorId(agregarProd, errorId);
    }

    public List eliminarProd(String IdProducto) {

        for (int i = 0; i < agregarProd.size(); i++) {
            Producto prodTemp = agregarProd.get(i);
            if (prodTemp.getIdproducto().equals(IdProducto)) {
                int posEliminar = i;
                agregarProd.remove(posEliminar);
            }
        }
        return agregarProd;
    }

    public float calcularTotal(List listaVenta) {
        totalVenta = 0;
        for (int i = 0; i < agregarProd.size(); i++) {
            Producto subtotales = agregarProd.get(i);
            totalVenta = (float) (totalVenta + subtotales.getPreciocompra());
        }
        return totalVenta;
    }

    public boolean limpiarVariables() {
        agregarProd.clear();
        idProdList = null;
        errorIdProd = null;
        totalVenta = 0;
        return true;
    }

}
