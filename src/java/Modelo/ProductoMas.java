package Modelo;

public class ProductoMas {
    String idproducto;
    String descripcion;
    String proovedor;
    float preciocompra;
    float precioventa;
    float stock;
    float stockpiso;
    
    public ProductoMas(){
    }
    
    public ProductoMas(String idproducto, String descripcion, String proovedor, float preciocompra, float precioventa, float stock, float stockpiso){
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.proovedor = proovedor;
        this.preciocompra = preciocompra;
        this.precioventa = precioventa;
        this.stock = stock;
        this.stockpiso = stockpiso;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public String getDescripcion() {
        return descripcion;
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

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
}
