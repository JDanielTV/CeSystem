<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> --%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>CeSystem - Nueva Venta</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="d-flex" >
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=NuevaVenta" method="POST">
                        <div class="form-group">
                            <label>ID Producto / Indicio</label>
                            <input type="text" name="txtID" class="form-control" id="txtID">
                            <!--<input type="text" id="txtID">-->

                        </div>

                        <input type="submit" name="accion" value="Agregar" class="btn btn-info" >
                        <!--                    <input type="submit" name="accion" value="Agregar" class="btn btn-info" onclick="invokeJavaMethod()">-->
                        <input type="submit" name="accion" value="Buscar" class="btn btn-info" >
                        <input type="hidden" name="errorId" value="${errorId}" id="errorId">
                        <input type="hidden" name="numRec" value="${numRec}" id="numRec">

                        <!--                    <input type="submit" name="accion" value="Limpiar" class="btn btn-info" style="text-align:right">-->
                        <div class="form-group">
                            <table type="submit" name="accion" value="AgregarDeTabla" class="table table-hover">
                                <theads>
                                    <tr>
                                        <th>ID Producto</th>
                                        <th>Descripcion</th>
                                    </tr>
                                </theads>
                                <tbody>                          
                                    <c:forEach var="prod" items="${productos}">
                                        <tr onclick="getIndex(this)">                                                       
                                            <td>${prod.getIdproducto()}</td>
                                            <td>${prod.getDescripcion()}</td>
    <!--                                        <td><a class="btn btn-warning" href="Controlador?menu=NuevaVenta&accion=Add&idBusc=${prod.getIdproducto()}">Agregar</a></td>                                            -->
                                            <td><a class="btn btn-warning" href="Controlador?menu=NuevaVenta&accion=AgregarB&idBusc=${prod.getIdproducto()}">Agregar</a></td>                                            

                                        </tr> 
                                    </c:forEach>
                                </tbody>
                            </table> 
                        </div>
                    </form>
                </div>
            </div>
            <div class="card col-sm-8">                
                <table id="NuevaVenta" name="detalleVenta" class="table table-hover">
                    <theads>
                        <tr>
                            <th>ID Producto</th>
                            <th>Descripcion</th>
                            <th>Precio</th>

                            <th>Cantidad</th>

                            <th>Subtotal</th>                            
                        </tr>
                    </theads>
                    <tbody>                          
                        <c:forEach var="detalleProd" items="${detalleProd}">
                            <tr>                            
                                <td>${detalleProd.getIdproducto()}</td>
                                <td>${detalleProd.getDescripcion()}</td>
                                <td>${detalleProd.getPrecioventa()}</td>
<!--                                <td><a class="btn btn-warning" href="Controlador?menu=NuevaVenta&accion=Eliminar&idElim=${detalleProd.getIdproducto()}">-</a></td>-->
                                <td><a class="btn btn-warning" href="Controlador?menu=NuevaVenta&accion=Minus&actCantidad=Minus&idProdM=${detalleProd.getIdproducto()}">-</a>
                                    ${detalleProd.getStock()}
                                    <a class="btn btn-warning" href="Controlador?menu=NuevaVenta&accion=Plus&actCantidad=Plus&idProdP=${detalleProd.getIdproducto()}">+</a></td>
                                <!--<td contenteditable="true">${detalleProd.getStock()}</td>-->
                                <!--<td><a class="btn btn-warning" href="Controlador?menu=NuevaVenta&accion=Eliminar&idElim=${detalleProd.getIdproducto()}">+</a></td>-->
                                <td>${detalleProd.getPreciocompra()}</td>
                                <td><a class="btn btn-warning" href="Controlador?menu=NuevaVenta&accion=Eliminar&idElim=${detalleProd.getIdproducto()}">Eliminar</a></td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>   

            </div>

        </div>
        <div align = "center" >
            <div align = "right">
                <form action="Controlador?menu=NuevaVenta" method="POST">
                    <button type="submit" name="accion" value="Limpiar" class="btn btn-info btn-lg" >Limpiar</button>                    
                </form>
            </div>
        </div>

        <div align = "center" >
            <div align = "right">
                <label style="font-weight: bold; font-size: 1.5em">Total $</label>
                <input type="text" id="totalVenta"  onchange="cambio();" value="${totalVenta}" readonly="readonly"><br>
                <label style="font-weight: bold; font-size: 1.5em">Monto recibido $</label>
                <input type="number" id="montoRecibido" name="montoRecibido" onchange="cambio();"><br>
                <label style="font-weight: bold; font-size: 1.5em">Cambio $</label>
                <input type="text"  id="cambio" name="cambio" readonly="readonly"><br>
                <button id = "pagarButton" type="button" class="btn btn-info btn-lg" data-toggle="modal" disabled="true" onclick="mostrarModal();" >Pagar</button>                
            </div>
        </div>

        <div class="container">
            <div class="modal fade" id="PagarModal" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Confirmar venta</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <!--                        <a>Ingresar el ID del cliente</a><br>
                                                    <input type="text" name="idCliente" id="idCliente"><br>-->
                            <a>¿Desea continuar con la venta?</a><br>
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

        <div class="container">
            <div class="modal fade" id="montoRecibModal" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Campo monto recibido</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <a>Por favor completar el campo Monto Recibido</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="focusMontoRecibido();">Aceptar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="modal fade" id="errorIdModal" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Confirmar venta</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <!--                        <a>Ingresar el ID del cliente</a><br>
                                                    <input type="text" name="idCliente" id="idCliente"><br>-->
                            <a>${errorId}</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="btnAceptarError" onclick="ponleFocus();">Aceptar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="modal fade" id="ventaSinProd" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Sin productos</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <a>No se agregaron productos a la venta</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Aceptar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                <div class="container">
            <div class="modal fade" id="errorResultadosModal" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Error resultados</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <a>No se encontraron resutaldos</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Aceptar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>

            function lanzarError() {
                var errorId = document.getElementById("errorId").value;
                console.log('errorId: ', errorId);
                if (errorId !== "") {
                    $('#errorIdModal').modal({show: true});
                    document.getElementById("btnAceptarError").focus();
                }
            }
            lanzarError();

            function ponleFocus() {
                document.getElementById("txtID").focus();
            }
            ponleFocus();

            function cambio() {
                var totalVenta = document.getElementById('totalVenta').value;
                var montoRecibido = document.getElementById('montoRecibido').value;
                if (totalVenta !== '' && montoRecibido !== '') {
                    var cambio = parseInt(montoRecibido) - parseInt(totalVenta);
                    document.getElementById('cambio').value = cambio;
                    if (cambio >= 0) {
                        document.getElementById("pagarButton").disabled = false;
                    }
                }
            }

            function mostrarModal() {
                var montoRecibido = document.getElementById('montoRecibido').value;
                //              const tabla = document.getElementById("NuevaVenta");
                //                const rows = tabla.getElementsByTagName("tr");
                //                if (rows.length <= 1) {
                //                      $('#ventaSinProd').modal({ show:true });               
                //            }else{
                if (montoRecibido !== "") {
                    $('#PagarModal').modal({show: true});
                }//else{
                //                        focusMontoRecibido();
                //                      $('#montoRecibModal').modal({ show:true });                    
                //                }
                //          }
            }

            function focusMontoRecibido() {
                const montoRecibidoTF = document.getElementById("montoRecibido");
                montoRecibidoTF.focus();
                input.setSelectionRange(input.value.length, input.value.length);
            }
            
            function mostrarModalErrorRes() {
                var numRec = document.getElementById('numRec').value;
                if (numRec !== "") {
                    $('#errorResultadosModal').modal({show: true});
                }
            }
            mostrarModalErrorRes();
            
            
        </script>
    </script>
</body>
</html>
