<%-- 
    Document   : Ventas
    Created on : 19/09/2023, 09:27:50 PM
    Author     : Maricruz C T
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> --%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>CeSystem Ventas</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-12">
                <div class="card-body">
                    <form action="Controlador?menu=Ventas&accion=Listar" method="POST">
                        <div class="form-group">
                            <label for="periodo">Seleccionar periodo a listar:</label>
                            <select name="Periodo" id="Periodo">
                                <option value="HOY">Hoy</option>
                                <option value="SEMANAL">Semanal</option>
                                <option value="MENSUAL">Mensual</option>                          
                            </select>
                            <input type="hidden" name="Periodo" value="${Periodo.value}">
                            <input type="submit" name="Periodo" value="Listar" class="btn btn-info" >
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="d-flex">

            <div class="card col-sm-12">
                <table id="Venta" name="Venta" class="table table-hover">
                    <theads>
                        <tr>
                            <th>ID Venta</th>
                            <th>Cliente</th>
                            <th>Vendedor</th>
                            <th>Fecha</th>
                            <th>Hora</th>  
                            <th>Productos</th>
                            <th>Total Venta</th>
                        </tr>
                    </theads>
                    <tbody>                          
                        <c:forEach var="venta" items="${venta}">
                            <tr>                            
                                <td>${venta.getIdventa()}</td>
                                <td>${venta.getIdcliente()}</td>
                                <td>${venta.getIdempleado()}</td>
                                <td>${venta.getFechaventa()}</td>
                                <td>${venta.getHoraventa()}</td>
                                <td>${venta.getCantidaddetalles()}</td>
                                <td>${venta.getMonto()}</td>
                                <td>
<!--                                    <a class="btn btn-warning" href="Controlador?menu=Empleados&accion=Editar&id=${venta.getIdventa()}">Ver</a>-->
                                    <button id = "pagarButton" type="button" class="btn btn-info btn-lg" data-toggle="modal" onclick="mostrarModalVenta();" >Ver</button>                

<!--                                    <a class="btn btn-danger" href="Controlador?menu=Empleados&accion=Eliminar&id=${em.getIdempleado()}">Eliminar</a>-->
                                </td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>                                        
            </div>
        </div>

        <div class="container">
            <div class="modal fade" id="DetallesVenta" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Confirmar venta</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <!--                        <a>Ingresar el ID del cliente</a><br>
                                                    <input type="text" name="idCliente" id="idCliente"><br>-->
                            <a>¿Desea confirmar con la venta?</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <!--                            <form action="Controlador?menu=NuevaVenta" method="POST">   -->
                            <a type="button" class="btn btn-primary" href="Controlador?menu=NuevaVenta&accion=Pagar">Continuar</a>
                            <!--                            </form> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function mostrarModalVenta() {
//                var montoRecibido = document.getElementById('montoRecibido').value;
                //                  if(montoRecibido!==""){
                $('#DetallesVenta').modal({show: true});
                //                }//else{
            }

        </script>



    </body>
</html>
