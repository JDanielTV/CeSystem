<%-- 
    Document   : Empleados
    Created on : 19/09/2023, 09:26:59 PM
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
    <title>CeSystem Empleados</title>
    </head>
    <body>
        <div class="d-flex">
             <div class="card col-sm-5">
                    <div class="card-body">
                        <form action="Controlador?menu=Empleados" method="POST">
                            <div class="form-group">
                                <label>ID Empleado</label>
                                <input type="text" value="${empleado.getIdempleado()}" name="txtID" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Nombre(es)</label>
                                <input type="text" value="${empleado.getNombre()}" name="txtNombre" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Apellidos</label>
                                <input type="text" value="${empleado.getApellido()}" name="txtApellidos" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Telefono</label>
                                <input type="text" value="${empleado.getTelefono()}" name="txtTel" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Usuario</label>
                                <input type="text" value="${empleado.getUser()}" name="txtUser" class="form-control">
                            </div>
                            <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                        </form>
                    </div>
                </div>
            <div class="card col-sm-7">
                <table class="table table-hover">
                    <theads>
                        <tr>
                            <th>ID Empleado</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Telefono</th>
                            <th>Usuario</th>                            
                        </tr>
                    </theads>
                    <tbody>                          
                        <c:forEach var="em" items="${empleados}">
                        <tr>                            
                            <td>${em.getIdempleado()}</td>
                            <td>${em.getNombre()}</td>
                            <td>${em.getApellido()}</td>
                            <td>${em.getTelefono()}</td>
                            <td>${em.getUser()}</td>
                            <td>
                                <a class="btn btn-warning" href="Controlador?menu=Empleados&accion=Editar&id=${em.getIdempleado()}">Editar</a>
                                <a class="btn btn-danger" href="Controlador?menu=Empleados&accion=Eliminar&id=${em.getIdempleado()}">Eliminar</a>
                            </td>
                        </tr> 
                        </c:forEach>
                    </tbody>
                </table>                                        
            </div>
        </div>
    </body>
</html>
