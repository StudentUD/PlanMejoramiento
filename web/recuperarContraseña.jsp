<%-- 
    Document   : recuperarContraseña
    Created on : 24/06/2020, 04:18:55 PM
    Author     : User
--%>


<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="Model.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="css/styledocumento.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav>
            <div class="container">
                <br>
                <h1>Productos</h1>
                <a href="menuAdministrador.jsp" style="color: white" class="btn btn-success">Volver</a>
                <br>
                <br>
                <table  class="table table-sm table-bordered table-hover ">
                    <thead class="thead-dark">
                        <tr>
                            <th class="text-center">Nombre Producto</th>
                            <th class="text-center">Precio Prducto</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                        <%                Conexion con = new Conexion();
                            PreparedStatement pst = null;
                            ResultSet rs = null;
                            try {
                                String consulta = "SELECT * FROM usuario;";
                                pst = con.getConexion().prepareStatement(consulta);
                                rs = pst.executeQuery();

                                while (rs.next()) {
                        %>
                            <td class="text-center"><a class="btn btn-info" href="actualizarUsuario.jsp?nomProd=<%=rs.getString(1)%>">Actualizar</a>
                            </td>
                        </tr>
                        <%}%>
                    </thead>
                </table>
            </div>
        </nav>
    </body>
</html>
<%
    } catch (SQLException e) {
        System.out.println("Error" + e);
    } finally {
        try {
            if (con.getConexion() != null) {
                con.getConexion().close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
    }


%>