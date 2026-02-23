/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author josed
 */
public class ProductoErrorId {

    List<Producto> agregarProd;
    String errorId;
    public ProductoErrorId(){
    }
            
    public ProductoErrorId(List<Producto> agregarProd ,String errorId) {
        this.agregarProd = agregarProd;
        this.errorId = errorId;
    }   
     public List<Producto> getAgregarProd() {
        return agregarProd;
    }

    public void setAgregarProd(List<Producto> agregarProd) {
        this.agregarProd = agregarProd;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

}
