/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.Conexion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.logging.Logger;


import log.Log;
import org.apache.log4j.Logger;

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
    
    
    
//    private static final Logger LOG = Log.getLogger(ProductoDAO.class);
    
    boolean banderaIdnuevo = false;   

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
        String sql = "insert into producto(IdProducto, Descripcion, DescripcionCorta, Marca, Proovedor, PrecioCompra, PrecioVenta, Stock, Presentacion, StockMinimo) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getIdproducto());
            ps.setString(2, prod.getDescripcion());
            ps.setString(3, prod.getDescripcionc());
            ps.setString(4, prod.getMarca());
            ps.setString(5, prod.getProovedor());
            String precioc = String.valueOf(prod.getPreciocompra());
            ps.setString(6, precioc);
            String preciov = String.valueOf(prod.getPrecioventa());
            ps.setString(7, preciov);
            String stock = String.valueOf(prod.getStock());
            ps.setString(8, stock);
            //String stockpiso = String.valueOf(prod.getStockpiso());
            //ps.setString(7, stockpiso);
            ps.setString(9, prod.getPresentacion());
            ps.setString(10, prod.getStockMin());
            String nombreFoto = prod.getIdproducto()+".jpg";
//            ps.setString(11, nombreFoto);
//            ps.setBlob(12, prod.getImagen());
            
            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Inserción exitosa!");
            }
            
            if(banderaIdnuevo){
                long nuevoIdDispo = Long.valueOf(prod.getIdproducto());
                nuevoIdDispo += 1;
                String nuevoIdDispoAct =String.valueOf(nuevoIdDispo);
                String sqlIdNuevo = "update id_local_producto set IdProd_disp='"+nuevoIdDispoAct+"' where Id='SYSTEM'";
                ps = con.prepareStatement(sqlIdNuevo);
    //            ps.setString(1, String.valueOf(nuevoIdDispo));
                ps.executeUpdate();
            }
        } catch (Exception e) {
             System.err.println("Error al insertar en la base de datos:");
            System.err.println("Mensaje: " + e.getMessage());
            e.printStackTrace(); // Muestra la traza completa        
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
                prod.setDescripcionc(rs.getString(3));
                prod.setMarca(rs.getString(4));
                prod.setProovedor(rs.getString(5));
                float preciocompra = parseFloat(rs.getString(6));
                prod.setPreciocompra(preciocompra);
                float precioventa = parseFloat(rs.getString(7));
                prod.setPrecioventa(precioventa);
                float stock = parseFloat(rs.getString(8));
                prod.setStock(stock);
                prod.setPresentacion(rs.getString(10));               
                prod.setStockMin(rs.getString(11));


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
        String fileName = "C:\\Users\\PC\\Documents\\NetBeansProjects\\Cesystem\\PruebaCargaMasiva.csv";

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
                //prodM.setStockMin(partes[10]);
                prodM.setStockMin("3");
                listaProdM.add(prodM);
                }

            }
        } catch (IOException e) {
        }
        return listaProdM;
    }

    
//    private static final Logger logger = Logger.getLogger(MiClase.class.getName());
    /**
     *
     * @param prodMas
     * @return
     */
    public int agregarMas(List<Producto> prodMas) throws IOException {
//        String sql = "insert into producto(IdProducto, Descripcion, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso)values(?,?,?,?,?,?,?)";
//        String sql = "insert into producto (IdProducto, Descripcion, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso) values ";
        //
        String outDir = "C:\\Users\\PC\\Documents\\NetBeansProjects\\Cesystem\\";
        String outFileName = "ProductoDAO.log"; 
        File finalOutFilePost = new File(outDir + outFileName);
        String lineaLog = null;

        
//        createlog.createLog("ProductoDAO.agregarMas");
        String sql = "insert into producto (IdProducto, Descripcion, DescripcionCorta, Marca, Proovedor, PrecioCompra, PrecioVenta, Stock, StockPiso, Presentacion, StockMinimo) values ";
        String linea = null;        
        ConRegistros = 0;

        for (Producto prodMa : prodMas) {
            String sqlComplete = null;
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
            String StockM = prodMa.getStockMin();
//                if (linea == null) {
            linea = "('" + ID + "','" + Descripcion + "','" + DescripcionC + "','" + Marca + "','" + Provedor + "','" + PrecioC 
                            + "','" + PrecioV + "','" + Stock + "','" + StockP + "','" + Presentacion + "','" + StockM + "')";
  //              } else {
    //                linea = ",('" + ID + "','" + Descripcion + "','" + DescripcionC + "','" + Marca + "','" + Provedor + "','" + PrecioC 
      //                      + "','" + PrecioV + "','" + Stock + "','" + StockP + "','" + Presentacion + "','" + StockM + "')";
        //        }
            sqlComplete = sql + linea;            
              
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sqlComplete);
                ps.executeUpdate();
//                createlog.writelog("Se completo la linea: "+sqlComplete);
                lineaLog = "Se completo la linea: "+sqlComplete;

                ConRegistros++;
            } catch (Exception e) {
//                createlog.writelog("Error en la linea: "+sqlComplete);   
                lineaLog = "Error en la linea: "+sqlComplete+" "+ e;                
            }
            try (FileWriter fwOut = new FileWriter(finalOutFilePost, true);
                BufferedWriter bwOut = new BufferedWriter(fwOut)) {
                bwOut.append(lineaLog).append("\n");
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }                           


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
                float precioVenta = Float.parseFloat(rs.getString("PrecioVenta")); 
                prod.setPrecioventa(precioVenta);
                String dirNombreImagen = "img/ProductImages/"+rs.getString(1)+".jpg";
                prod.setNombreImagen(dirNombreImagen);
//                prod.setImagen(rs.getBinaryStream("Imagen"));
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
                    float stockAva = parseFloat(rs.getString("Stock"));
                    if (stockAva > 0 && stockAva >= cantidad) {
                        int cantidadInt = (int) cantidad;
                        String presentacion = rs.getString("Presentacion");
                        String descripcion = rs.getString("Descripcion");
                        if ((cantidad != cantidadInt && presentacion.equals("PIEZA"))) { // O Math.ceil(num)
                            errorId = "El producto " + IdProducto + " - " + descripcion + " solo venta por pieza, no a granel.";
                        } else {

                            prod.setIdproducto(rs.getString("IdProducto"));
                            prod.setDescripcion(descripcion);
                            float precioventa = parseFloat(rs.getString("PrecioVenta"));
                            prod.setPrecioventa(precioventa);
                            prod.setStock(cantidad);

                            float cantidadDisponible;
                            prod.setStockpiso(parseFloat(rs.getString("IdProducto")));

                            float subtotal = precioventa * cantidad;
                            prod.setPreciocompra(subtotal);
                            agregarProd.add(prod);

                            prod.setPresentacion(presentacion);
                            errorId = "";
                        }
                    } else {
                        errorId = "El producto " + IdProducto + " - " + rs.getString("Descripcion") + " no tiene stock disponible.";
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
    
    public Producto nuevoIDProd(String idp){
        
        Producto prod = new Producto();

        if(idp.isEmpty()){
            String nuevoIDProd = null;
            String sql = null;

            sql = "select * from id_local_producto WHERE Id = 'SYSTEM'";

            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    prod.setIdproducto(rs.getString(2));
                    banderaIdnuevo = true;

    //                nuevoIDProd = rs.getString(2);
                }
            } catch (Exception e) {
            }
        }else{
            prod = listarId(idp);
            if(prod.getIdproducto() == null){
                prod.setIdproducto(idp);
            }            
        }

        return prod;
    }
    
    public List listarFaltantes(String ordenF, String proovedorF) {
                
        String sql = "SELECT producto.IdProducto, producto.Descripcion, faltantes.FechaApartir, producto.Proovedor, producto.Stock FROM faltantes INNER JOIN producto ON producto.IdProducto = faltantes.IdProducto";     
        if(proovedorF != null){
            if(proovedorF.isEmpty()){
            }else{
                proovedorF = proovedorF.replace(" ", "");
                sql += " AND producto.Proovedor = '"+proovedorF+"'";           
            }
        }        

        if(ordenF != null){
            switch (ordenF) {
                case "MASRECIENTE":
                    sql += " ORDER BY faltantes.FechaApartir DESC";
                break;
                case "MASANTIGUO": 
                    sql += " ORDER BY faltantes.FechaApartir ASC";
                break;
                case "PROOVEDOR":
                    sql += " ORDER BY producto.Proovedor DESC";
                break;
                default:
            }
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
                prod.setProovedor(rs.getString(4));
                String stockS = rs.getString(5);
                prod.setStock(Float.parseFloat(stockS));
                prod.setFechaApartir(rs.getString(3));
                listaProd.add(prod);
            }
        } catch (Exception e) {
        }
        return listaProd;
    }

        public boolean CargarImagen(String nombreImg, String IdProd) {
                
        boolean Respuesta = false;
        Path origen = Paths.get("C:/Users/PC/Desktop/FotosProductos/"+nombreImg);
        //Path destino = Paths.get("C:/CeSystem/ProductImages/"+IdProd+".jpg");
        //Path destino = Paths.get("img/ProductImages/"+IdProd+".jpg");
        Path destino = Paths.get("C:/Users/PC/Documents/NetBeansProjects/Cesystem/web/img/ProductImages/"+IdProd+".jpg");       

        try {
            // Mover el archivo, reemplazando si ya existe en el destino
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Imagen movida con éxito.");
            Respuesta = true;
        } catch (IOException e) {
            System.err.println("Error al mover la imagen: " + e.getMessage());
        }
        return Respuesta;
    }


}
