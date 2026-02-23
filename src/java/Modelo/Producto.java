package Modelo;

public class Producto {
    String idproducto;
    String descripcion;
    String descripcionc;
    String marca;
    String proovedor;
    float preciocompra;
    float precioventa;
    float stock;
    float stockpiso;
    String presentacion;
    
    public Producto(){
    }
    
    public Producto(String idproducto, String descripcion, String descripcionc, String marca, String proovedor, float preciocompra, float precioventa, float stock, float stockpiso, String presentacion){
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.descripcionc = descripcionc;
        this.marca = marca;
        this.proovedor = proovedor;
        this.preciocompra = preciocompra;
        this.precioventa = precioventa;
        this.stock = stock;
        this.stockpiso = stockpiso;
        this.presentacion = presentacion;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcionc() {
        return descripcionc;
    }

    public String getMarca() {
        return marca;
    }

    public String getProovedor() {
        return proovedor;
    }

    public float getPreciocompra() {
        return preciocompra;
    }

    public float getPrecioventa() {
        return precioventa;
    }

    public float getStock() {
        return stock;
    }

    public float getStockpiso() {
        return stockpiso;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDescripcionc(String descripcionc) {
        this.descripcionc = descripcionc;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setProovedor(String proovedor) {
        this.proovedor = proovedor;
    }

    public void setPreciocompra(float preciocompra) {
        this.preciocompra = preciocompra;
    }

    public void setPrecioventa(float precioventa) {
        this.precioventa = precioventa;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public void setStockpiso(float stockpiso) {
        this.stockpiso = stockpiso;
    }
    
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

}
