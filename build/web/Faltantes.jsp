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
            <div class="card col-sm-5">
                <div class="card-body">
                    <form action="Controlador?menu=Faltantes" method="POST">
                        
                        <div class="form-group">
                            <table id="FaltantesT" name="FaltantesT" class="table table-hover" style="border: none; border-collapse: collapse;">
                                <tbody>                          
                                    <tr>                            
                                        <td><label>Ordenar por:</label></td>
                                        <!--<td></td>-->
                                        <td><label>Filtrar por Proovedor: </label></td>
                                        <td></td>
                                        </tr> 
                                    <tr>
                                        <td>
                                            <select name="OrdenarSel" id="OrdenarSel">
                                                <option value="">Seleccione una opción</option>
                                                <option value="MASRECIENTE">Mas Recientes</option>
                                                <option value="MASANTIGUO">Mas Antigüo</option>
                                                <!--<option value="PROOVEDOR">Proovedor</option>                        -->  
                                            </select>
                                        </td>
<!--                                        <td><input type="submit" name="Ordenar" value="Ordenar" class="btn btn-info" ></td>-->
                                        <td>
                                            <input type="hidden" value="${proovedores}" id="proovedores">
                                            <select id="proovedoresDD" name="proovedoresDD">
                                                <option value="">Cargando opciones...</option>
                                            </select>
                                        </td>
                                        <!--<td><input type="submit" name="Filtrar" value="Filtrar" class="btn btn-info" ></td>-->
                                        <td><input type="submit" name="Filtrar" value="Actualizar" class="btn btn-info" ></td>
                                    </tr> 
                                </tbody>
                            </table>  
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="d-flex">

            <div class="card col-sm-12">
                <input type="hidden" value="${proovedorF}" id="proovedorF">
                <input type="hidden" value="${ordenF}" id="ordenF">

                <table id="Venta" name="Venta" class="table table-hover">
                    <theads>
                        <tr>
                            <th>ID Producto</th>
                            <th>Descripcion</th>
                            <th>Proovedor</th>
                            <th>Stock</th>
                            <th>Apartir</th>  
                        </tr>
                    </theads>
                    <tbody>                          
                        <c:forEach var="faltantes" items="${listaFaltantes}">
                            <tr>                            
                                <td>${faltantes.getIdproducto()}</td>
                                <td>${faltantes.getDescripcion()}</td>
                                <td>${faltantes.getProovedor()}</td>
                                <td>${faltantes.getStock()}</td>
                                <td>${faltantes.getFechaApartir()}</td>
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

            function cargarDesplegable() {
                console.log("en  cargarDesplegable");
                const selectElement = document.getElementById('proovedoresDD');
                let proovedoreslist = document.getElementById('proovedores').value;
                let temp1 = proovedoreslist.replace("[", "");
                let temp2 = temp1.replace("]", "");
                let temp3 = temp2.replaceAll(" ", "");
                let proovedoresArr = temp3.split(',');

                try {
                    selectElement.innerHTML = '<option value="">Seleccione una opción</option>';                      

                    for (let i = 0; i < proovedoresArr.length; i++) {
                        const opcion = document.createElement('option');
                        opcion.value = proovedoresArr[i]; // Valor interno
                        opcion.textContent = proovedoresArr[i]; // Texto visible
                        selectElement.appendChild(opcion);
                    }
                } catch (error) {
                    console.error("Error al cargar los datos:", error);
                }
                
                const ordenF = document.getElementById('ordenF').value;
                const proovedorF = document.getElementById('proovedorF').value;

                if (proovedorF){         
                    const select = document.getElementById("proovedoresDD");
                    var proovedorDDv = document.getElementById("proovedoresDD");     
                    proovedorDDv.value = proovedorF;
                    select.dispatchEvent(new Event('change'));
                }
                if (ordenF){         
                    const select = document.getElementById("OrdenarSel");
                    var proovedorDDv = document.getElementById("OrdenarSel");     
                    proovedorDDv.value = ordenF;
                    select.dispatchEvent(new Event('change'));
                }

            }
            cargarDesplegable();            

        </script>



    </body>
</html>
