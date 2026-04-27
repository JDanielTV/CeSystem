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
class Posicion {
    
    public List agregarProd;
    public String error;
    
    public Posicion (List agregarProd,String error)
    {
        this.agregarProd = agregarProd;
        this.error = error;
    }

    public List getAgregarProd() {
        return agregarProd;
    }

    public void setAgregarProd(List agregarProd) {
        this.agregarProd = agregarProd;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
