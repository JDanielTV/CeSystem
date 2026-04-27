<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   --%>
        <%--        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> --%>
        <%--       <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">   --%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> --%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>CeSystem - Productos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <!--<form action="Controlador?menu=Productos" method="POST" enctype="multipart/form-data">-->
                    <form action="Controlador?menu=Productos" method="POST">
                        <input type="hidden" value="${banderaNuevo}" id="banderaNuevo">                        
                        <input type="hidden" value="${banderaEditar}" id="banderaEditar">
                        <input type="hidden" value="${banderaActualizado}" id="banderaActualizado">

                        <div class="form-group">
                            <label>ID Producto</label>                            
                            <input type="hidden" value="${nuevoIDProd}" id="nuevoIDProd">
                            <input type="text" value="${producto.getIdproducto()}" name="txtID" class="form-control" size="10" id="txtID" onchange="nuevoProd();">
                        </div>
                        
                        <div class="form-group">
                            <label>Descripcion</label>
                            <input type="text" value="${producto.getDescripcion()}" name="txtDescripcion" class="form-control" size="5" id="txtDescripcion" >
                        </div>
                        
                        <div class="form-group" id="DivDescripcionCorta" style="display: none">
                            <label>Descripcion Corta</label>
                            <input type="text" value="${producto.getDescripcionc()}" name="txtDescripcionCorta" class="form-control" size="5" id="txtDescripcionCorta" maxlength="50">                        </div>

                        <div class="form-group" id="DivMarca" style="display: none">
                            <label>Marca</label>
                            <input type="text" value="${producto.getMarca()}" name="txtMarca" class="form-control" size="5" id="txtMarca" >
                        </div>

                        <div class="form-group">
                            <input type="hidden" value="${proovedores}" id="proovedores">
                            <input type="hidden" value="${producto.getProovedor()}" id="proovedorSel" onchange="selectProv();">
                            <%--<label for="proovedoresDD" >Proovedor</label><br>  %--%>
                            <label>Proovedor</label><br>
                            <select id="proovedoresDD" name="proovedoresDD" >
                                <option value="">Cargando opciones...</option>
                            </select>
                        </div>

                        <div class="form-group" id="DivPrecioCompra" style="display: none">
                            <label>Precio Compra</label>
                            <input type="number" step="0.01"  pattern="[0-9]*" value="${producto.getPreciocompra()}" name="txtPcompra" class="form-control" size="5" id="txtPcompra" >
                        </div>

                        <div class="form-group">
                            <label>Precio venta</label>
                            <input type="number" step="0.01" value="${producto.getPrecioventa()}" name="txtPventa" class="form-control" >
                        </div>
                        
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="number" step="0.01" value="${producto.getStock()}" name="txtStock" class="form-control" onchange="selectProv();" >
                        </div>
                        
                        <div class="form-group" id="DivPresentacion" style="display: none">
                            <input type="hidden" value="${producto.getPresentacion()}" id="presentacion">
                            <label>Presentacion</label><br>
                            <select name="presentacionDD" id="presentacionDD" >
                                <option value="">Seleccione una opción</option>
                                <option value="GRANEL">GRANEL</option>
                                <option value="PIEZA">PIEZA</option>
                            </select>

<!--                            <input type="text" value="${producto.getPresentacion()}" name="txtPresentacion" class="form-control" size="5" id="txtPresentacion">   -->
                        </div>

                        <div class="form-group" id="DivStockMin" style="display: none">
                            <label>Alerta</label>
                            <input type="number" min="1" value="${producto.getStockMin()}" name="txtStockMin" class="form-control" size="5" id="txtStockMin" >
                        </div>
                            
                        <div class="form-group" id="DivImagen">
                            <label>Imagen</label><br>
                            <input type="file" name="imagen" accept=".jpg, .jpeg, .png, .pdf">
                        </div>

                        <%--                        <div class="form-group">
                                                        <label>Stock piso</label>
                                                        <input type="text" value="${producto.getStockpiso()}" name="txtStockpiso" class="form-control">
                                                    </div>
                        --%>
                        <%--          <input type="submit" id="Agregar" name="Agregar" value="Agregar" class="btn btn-info" disabled="true">   --%>

                        <input type="submit" id="Nuevo" name="accion" value="Nuevo" class="btn btn-info">
                        <input type="submit" id="Limpiar" name="accion" value="Limpiar" class="btn btn-success">
                        <input type="submit" id="Agregar" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" id="Actualizar" name="accion" value="Actualizar" class="btn btn-success"> 

                    </form>
                </div>
            </div>
            <div class="card col-sm-8">
                <table class="table table-hover">
                    <theads>
                        <tr>
                            <th>ID Producto</th>
                            <th>Descripcion</th>
                            <th>Proovedor</th>
                                <%--                            <th>Precio compra</th>  --%>
                            <th>Precio venta</th>
                            <th>Stock</th>
                                <%--                            <th>Stock piso</th>   --%>
                        </tr>
                    </theads>
                    <tbody>
                        <c:forEach var="prod" items="${productos}">
                            <tr>                            
                                <td>${prod.getIdproducto()}</td>
                                <td>${prod.getDescripcion()}</td>
                                <td>${prod.getProovedor()}</td>
                                <%--                            <td>${prod.getPreciocompra()}</td>   --%>
                                <td>${prod.getPrecioventa()}</td>
                                <td>${prod.getStock()}</td>
                                <%--                            <td>${prod.getStockpiso()}</td>   --%>
                                <td>
                                    <%--                                <a class="btn btn-warning" href="Controlador?menu=Productos&accion=Editar&id=${prod.getIdproducto()}" id="editarR" onclick = "desabilitarAgregar()">Editar</a>   --%>

                                    <a class="btn btn-warning" href="Controlador?menu=Productos&accion=Editar&id=${prod.getIdproducto()}" id="editarR" onclick="selectProv();">Editar</a>  
                                    <a class="btn btn-danger" href="Controlador?menu=Productos&accion=Eliminar&id=${prod.getIdproducto()}" id="eliminarR">Eliminar</a>
                                </td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>                                        
            </div>
        </div>
                        
        <div class="container">
            <div class="modal fade" id="ActualizadoModal" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Confirmar venta</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <a>Producto ${producto.getIdproducto()} actualizado.</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>                            
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
                
                document.getElementById("Agregar").disabled = true;
                document.getElementById("Actualizar").disabled = true;
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
                const txtIDConst = document.getElementById('txtID').value;
                const proovedorSel = document.getElementById('proovedorSel').value;
                console.log("proovedorSel ",proovedorSel);
                
                if (proovedorSel){         
                    const proovedorSel = document.getElementById('proovedorSel').value;
                    const select = document.getElementById("proovedoresDD");
                    var proovedorDDv = document.getElementById("proovedoresDD");     
                    proovedorDDv.value = proovedorSel;
                    select.dispatchEvent(new Event('change'));
                    document.getElementById('DivStockMin').style.display = 'block';

//                    if (txtIDConst){
  //                      document.getElementById('txtID').disabled = true;
    //                    document.getElementById("Actualizar").disabled = false;
      //              }
                }
            }
            cargarDesplegable();
            
            function ProductoNuevo(){
                let banderaNuevoLet = document.getElementById('banderaNuevo').value;
                console.log("banderaNuevoLet: ",banderaNuevoLet);
                if(banderaNuevoLet == "1"){
                    document.getElementById('DivDescripcionCorta').style.display = 'block';
                    document.getElementById('DivPrecioCompra').style.display = 'block';
                    document.getElementById('DivMarca').style.display = 'block';
                    document.getElementById('DivPresentacion').style.display = 'block';
                    document.getElementById('DivStockMin').style.display = 'block';
//                    document.getElementById('DivImagen').style.display = 'block';

                    document.getElementById('Actualizar').disabled = true;
                    document.getElementById('Agregar').disabled = false;
                    document.getElementById('Nuevo').disabled = true;

                    document.getElementById('txtID').disabled = true;
                    
                    
//                    document.getElementById('txtDescripcion').required = true;
  //                  document.getElementById('txtDescripcionCorta').required = true;
    //                document.getElementById('txtMarca').required = true;
      //              document.getElementById('proovedoresDD').required = true;
        //            document.getElementById('presentacionDD').required = true;
          //          document.getElementById('txtStockMin').required = true;
                }
            }
            ProductoNuevo();
            function EditarProducto(){
                let banderaEditarLet = document.getElementById('banderaEditar').value;
                console.log("banderaEditarLet ",banderaEditarLet);
                if(banderaEditarLet == "1"){
                    document.getElementById('DivDescripcionCorta').style.display = 'block';
                    document.getElementById('DivPrecioCompra').style.display = 'block';

                    document.getElementById('Actualizar').disabled = false;
                    document.getElementById('Agregar').disabled = true;
                    document.getElementById('Nuevo').disabled = true;

                    document.getElementById('txtID').disabled = true;
                }
            }
            EditarProducto();
            function ProductoActualizado(){
                let banderaActualizadoLet = document.getElementById('banderaActualizado').value;
                console.log("banderaActualizadoLet ",banderaActualizadoLet);
                if(banderaActualizadoLet == "1"){
                    $('#ActualizadoModal').modal({show: true});                }
            }
            ProductoActualizado();
            
            function MostrarModal(){
                console.log("MostrarModal ");

                    $('#ActualizadoModal').modal({show: true});                
            }

        </script>

    </body>
</html>
