package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    //private static Connection conexion;
    private static Conexion instancia;

    private Conexion() {

    }

    public static Connection conectar() throws SQLException {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/biblioteca";
            String usuarioBD = "root";
            String passwordBD = "";

            conexion = DriverManager.getConnection(servidor, usuarioBD, passwordBD);
            //JOptionPane.showMessageDialog(null, "SE REALIZO LA CONEXION");
        } catch (Exception ex) {
            System.out.println("ERROR:" + ex);
            conexion = null;
        }
        return conexion;
    }

    //Patron sigleton
    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
