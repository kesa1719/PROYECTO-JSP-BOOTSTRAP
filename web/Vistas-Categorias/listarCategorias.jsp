<%-- 
    Document   : listarCategorias
    Created on : 06-14-2020, 02:38:06 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import ="Model.Categoria" %> <!-- Importar el modelo-->
<!-- el id debe ser el mismo que se le colocó de nombre a la sesión en el controlador -->
<jsp:useBean id="lista" scope= "session" class="java.util.List"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control de inventario</title>
        <%@include file = "../WEB-INF/Vistas-Parciales/css-js.jspf" %>
    </head>
    <body>
       <%@include file = "../WEB-INF/Vistas-Parciales/encabezado.jspf" %>
       
       <div style="width: 600px;">
           <a href="<%= request.getContextPath() %>/categorias?opcion=crear" class="btn btn-success btn-sm glyphicon glyphicon-pencil" role="button"> Nueva Categoria</a>
           <h3>Listado de Categorias Registradas</h3>
           <table class="table table-striped">
               <tr>
                   <th>ID</th><th>NOMBRE</th><th>ESTADO</th><th>ACCION</th>
               </tr>
               <%
                   for(int i =0 ; i < lista.size(); i++){
                       Categoria categoria = new Categoria();
                       categoria = (Categoria)lista.get(i); //(Categoria) es para el casting
                %>  
                <tr>
                    <td><%= categoria.getId_categoria()%></td>
                    <td><%= categoria.getNom_categoria() %></td>
                    <td><%= categoria.getEstado_categoria() %></td>
                    <td>
                        <a href="#" class="btn btn-info btn-sm glyphicon glyphicon-edit" role="button"> Editar</a>
                        <a href="#" class="btn btn-danger btn-sm glyphicon glyphicon-remove" role="button"> Eliminar</a>
                    </td>
                </tr>
                 <%
                    }
                 %>  
           </table>    
           
       </div>
           <%@include file = "../WEB-INF/Vistas-Parciales/pie.jspf" %>
    </body>
</html>
