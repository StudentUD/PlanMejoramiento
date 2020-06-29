package Controller.AlquilerDao;

import Model.Conexion;
import Modelo.Carrito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class AlquilerDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement pst;
    ResultSet rs;
    int res = 0;

    public int generarAlquiler(Alquiler alquiler) {

        int codigo_alquiler;
        String sql = "INSERT INTO alquiler (precio_alquiler,fecha_entrega,fecha_devolucion,numero_documento) VALUES (?,?,?,?)";
        try {
            con = cn.getConexion();
            pst = con.prepareStatement(sql);
            pst.setInt(1, alquiler.getPrecio_alquiler());
            pst.setString(2, alquiler.getFecha_entrega());
            pst.setString(3, alquiler.getFecha_devolucion());
            pst.setString(4, alquiler.getNumero_documento());
            res = pst.executeUpdate();

            sql = "SELECT @@IDENTITY AS codigo_alquiler";
            rs = pst.executeQuery(sql);
            rs.next();
            codigo_alquiler = rs.getInt("codigo_alquiler");
            rs.close();

            for (Carrito c : alquiler.getProductos()) {
                sql = "INSERT INTO alquiler_mayor VALUES (?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, c.getId_producto());
                pst.setInt(2, codigo_alquiler);
                res = pst.executeUpdate();

            }
        } catch (SQLException e) {
        }

        return res;
    }

}
