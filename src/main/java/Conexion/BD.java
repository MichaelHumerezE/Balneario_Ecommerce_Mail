/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

/**
 *
 * @author godof
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    private Connection connection;

    public BD() {
        this.connection = null;
    }

    public void conectar() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void desconectar() {
        try {
            connection.close();
            System.out.println("Desconectado.");
        } catch (SQLException e) {
            System.out.println("Excepcion al deconectar DbConn: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
