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
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Productos" method="POST">
                        <div class="form-group">
                            <label>ID Producto</label>
                            <input type="text" value="${producto.getIdproducto()}" name="txtID" class="form-control" size="10" id="txtID">
                        </div>
                        <div class="form-group">
                            <label>Descripcion</label>
                            <input type="text" value="${producto.getDescripcion()}" name="txtDescripcion" class="form-control" size="5">
                        </div>
                        <div class="form-group">
                            <input type="hidden" value="${proovedores}" id="proovedores">
                            <input type="hidden" value="${producto.getProovedor()}" id="proovedorSel" onchange="selectProv();">
                            <%--<label for="proovedoresDD" >Proovedor</label><br>  %--%>
                            <label>Proovedor</label><br>
                            <select id="proovedoresDD">
                                <option value="">Cargando opciones...</option>
                            </select>
                        </div>
                        <%--                            <div class="form-group">
                                                        <label>Precio compra</label>
                                                        <input type="text" value="${producto.getPreciocompra()}" name="txtPcompra" class="form-control">
                                                    </div>
                        --%>
                        <div class="form-group">
                            <label>Precio venta</label>
                            <input type="text" value="${producto.getPrecioventa()}" name="txtPventa" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="text" value="${producto.getStock()}" name="txtStock" class="form-control" onchange="selectProv();">
                        </div>
                        <%--                        <div class="form-group">
                                                        <label>Stock piso</label>
                                                        <input type="text" value="${producto.getStockpiso()}" name="txtStockpiso" class="form-control">
                                                    </div>
                        --%>
                        <input type="submit" id="Agregar" name="Agregar" value="Agregar" class="btn btn-info" disabled="true">
                        <input type="submit" id="Actualizar" name="Actualizar" value="Actualizar" class="btn btn-success">
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
        <script>
            document.getElementById("Agregar").disabled = false;
            function mostrarContenido(contenido) {
                const elemento = document.getElementById('contenido-archivo');
                elemento.innerHTML = contenido;
            }

            function desabilitarAgregar() {
                document.getElementById("Agregar").disabled = true;
            }

            async function cargarDesplegable() {
                const selectElement = document.getElementById('proovedoresDD');
                let proovedoreslist = document.getElementById('proovedores').value;

                let temp1 = proovedoreslist.replace("[", "");
                let temp2 = temp1.replace("]", "");
                let temp3 = temp2.replace(" ", "");
                let proovedoresArr = temp3.split(',');
                console.log('proovedoresArr  ', proovedoresArr);
                try {
                    selectElement.innerHTML = '<option value="">Seleccione una opción</option>';
                    //var prueba = proovedoreslist2

                    for (let i = 0; i < proovedoresArr.length; i++) {
                        const opcion = document.createElement('option');            
                        opcion.value = proovedoresArr[i]; // Valor interno
                        opcion.textContent = proovedoresArr[i];         // Texto visible
                        selectElement.appendChild(opcion);
                    }
                } catch (error) {
                    console.error("Error al cargar los datos:", error);
                    selectElement.innerHTML = '<option>Error al cargar datos</option>';
                }
                const proovedorSel = document.getElementById('proovedorSel').value;
                if (proovedorSel){
                    selectProv();
                }
            }
            
            selectProv();
            function selectProv(){      
                const proovedorSel = document.getElementById('proovedorSel').value;
                const select = document.getElementById("proovedoresDD");

                var proovedorDDv = document.getElementById("proovedoresDD");
                proovedorDDv.value = " "+proovedorSel;
                    
                select.dispatchEvent(new Event('change'));
                
                const txtIDConst = document.getElementById('txtID').value;
                if(txtIDConst){
                    document.getElementById('txtID').disabled=true;
                }            
            }

            // Ejecutar la función al cargar la página
            document.addEventListener('DOMContentLoaded', cargarDesplegable);

        </script>

    </body>
</html>
