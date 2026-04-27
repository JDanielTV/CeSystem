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
                    <form action="Controlador?menu=Proovedores" method="POST">                       
                        <input type="hidden" value="${respAgregarProv}" id="respAgregarProv">
                        <input type="hidden" value="${respEliminarProv}" id="respEliminarProv">
                        <input type="hidden" value="${proovEliminado}" id="proovEliminado">
                        <input type="hidden" value="${banderaEditarProov}" id="banderaEditarProov">

                        <div class="form-group">
                            <label>ID Proovedor</label>                            
                            <input type="hidden" value="${nuevoIDProovedor}" id="nuevoIDProd">
                            <input type="text" value="${proovedor.getIdproovedor()}" name="txtID" class="form-control" size="10" id="txtID" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Nombre Completo</label>
                            <input type="text" value="${proovedor.getNombre()}" name="txtNombre" class="form-control" size="5" id="txtNombre" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Direccion</label>
                            <input type="text" value="${proovedor.getDireccion()}" name="txtDireccion" class="form-control" size="5" id="txtDireccion" required>
                        </div>

                        <div class="form-group">
                            <label>Telefono</label>
                            <input type="text" value="${proovedor.getTelefono()}" name="txtTelefono" class="form-control" size="5" id="txtTelefono" required>
                        </div>


<!--                        <input type="submit" id="Nuevo" name="accion" value="Nuevo" class="btn btn-info">-->
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
                            <th>ID Proovedor</th>
                            <th>Nombre Completo</th>
                            <th>Direccion</th>
                            <th>Telefono</th>
                        </tr>
                    </theads>
                    <tbody>
                        <c:forEach var="proov" items="${listaProovDet}">
                            <tr>                            
                                <td>${proov.getIdproovedor()}</td>
                                <td>${proov.getNombre()}</td>
                                <td>${proov.getDireccion()}</td>                                
                                <td>${proov.getTelefono()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Proovedores&accion=Editar&id=${proov.getIdproovedor()}" id="editarR">Editar</a>  
                                    <a class="btn btn-danger" href="Controlador?menu=Proovedores&accion=Eliminar&id=${proov.getIdproovedor()}" id="eliminarR">Eliminar</a>
                                </td>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>                                        
            </div>
        </div>
                        
        <div class="container">
            <div class="modal fade" id="AgregarModal" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Confirmar venta</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <a>El proovedor ${proov.getIdproovedor()} se agrego correctamente.</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="modal fade" id="EliminarModal" role="dialog" aria-hidden="false">
                <div class="modal-dialog modal-dialog-centered">                    
                    <div class="modal-content">
                        <div class="modal-header">
                            <a>Confirmar venta</a>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>        
                        <div class="modal-body">
                            <a>El proovedor ${proovEliminado} se elimino correctamente.</a><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>

        function ProductoActualizado(){
            let respAgregarProv = document.getElementById('respAgregarProv').value;
            if(respAgregarProv == "true"){
                $('#AgregarModal').modal({show: true});
            }           
            
            let respEliminarProv = document.getElementById('respEliminarProv').value;
            if(respEliminarProv == "true"){
                $('#EliminarModal').modal({show: true});
            }

            let banderaEditarProov = document.getElementById('banderaEditarProov').value;
            if(banderaEditarProov == "true"){
                document.getElementById('txtID').disabled = true;
            }
            
            

        }
        ProductoActualizado();
            
        </script>

    </body>
</html>
