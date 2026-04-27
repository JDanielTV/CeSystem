<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> --%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
<!--        <div class="d-flex">
<!--            <div class="card col-sm-3">-->
            <div class="d-flex">
                <div class="card-body">
                    <form action="Controlador?menu=ProductosMasivo" method="POST">
                        <div class="form-group">
                            <h1>Carga Masiva</h1>
                        </div>
                            <label>Asegurese que el archivo exista en el siguiente directorio.</label>
                            <br>
                            <label>C:\Users\josed\Documents\NetBeansProjects\Cesystem\PruebaCargaMasiva.csv</label>
                            <br>
                            <input type="submit" name="accion" value="Cargar" class="btn btn-info">
                            <option value="${CanRegistros}" <c:if test="${CanRegistros > 0}">>
                                <script>
                                    window.open("BuscarProductos.jsp");                                    
                                </script>   
                            </c:if></option>                          
                    </form>
                </div>
            </div>
            <!--<div class="card col-sm-9">-->
            <label>Se muestra el contenido del archivo:</label>
            
            <div class="d-flex">
                
                <table class="table table-hover">
                    <theads>
                        <tr>
                            <th>ID Producto</th>
                            <th>Descripcion</th>
                            <th>Proovedor</th>
                            <th>Precio compra</th>
                            <th>Precio venta</th>
                            <th>Stock</th>
                            <th>Stock piso</th>
                        </tr>
                    </theads>
                    <tbody>
                        <c:forEach var="prod" items="${productosM}">
                        <tr>                            
                            <td>${prod.getIdproducto()}</td>
                            <td>${prod.getDescripcion()}</td>
                            <td>${prod.getProovedor()}</td>
                            <td>${prod.getPreciocompra()}</td>
                            <td>${prod.getPrecioventa()}</td>
                            <td>${prod.getStock()}</td>
                            <td>${prod.getStockpiso()}</td>
                        </tr>                         
                        </c:forEach>
                    </tbody>
                </table>  
            </div>
<!--        </div> -->
    </body>
</html>
