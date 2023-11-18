/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Conexion.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DDetalle {
    public int producto_id;
    public int nota_venta_id;
    public int cantidad;
    public double precio;
    
    private BD database;

    public DDetalle() {
        this.database = new BD();
    }

    public DDetalle(int producto_id, int nota_venta_id, int cantidad, double precio) {
        this.database = new BD();
        this.producto_id = producto_id;
        this.nota_venta_id = nota_venta_id;
        this.cantidad = cantidad;
        this.precio = precio;        
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getNota_venta_id() {
        return nota_venta_id;
    }

    public void setNota_venta_id(int nota_venta_id) {
        this.nota_venta_id = nota_venta_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public List<DDetalle> listar() throws SQLException{
                   
        database.conectar();
        Statement statement = database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM detalle");
        List<DDetalle> detalles = new ArrayList<>();
        while (resultSet.next()) {
            detalles.add( new DDetalle(
                    resultSet.getInt("producto_id"),
                    resultSet.getInt("nota_venta_id"),
                    resultSet.getInt("cantidad"),
                    resultSet.getDouble("precio")                        
            ));
        }
        resultSet.close();
        statement.close();
        database.desconectar();
        return detalles;
        
    }
    
    public boolean crear() throws SQLException{
        
        database.conectar();         
        
        // Definir la sentencia SQL para la inserción
        String sql = "INSERT INTO detalle (producto_id, nota_venta_id, cantidad, precio) " +
                     "VALUES (?, ?, ?, ?)";            

        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer los valores de los parámetros
        preparedStatement.setInt(1, getProducto_id());
        preparedStatement.setInt(2, getNota_venta_id());
        preparedStatement.setInt(3, getCantidad());
        preparedStatement.setDouble(4, getPrecio());


        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DDetalle.java: "
                    + "Ocurrio un error al insertar un detalle crear()");
            throw new SQLException();
        }

        // Cerrar la conexión y la declaración
        preparedStatement.close();
        database.desconectar();
        return true;
        
    }
    
    public boolean editar() throws SQLException{
        
        database.conectar();        

        // Definir la sentencia SQL para la actualización
        String sql = "UPDATE detalle SET cantidad = ?, precio = ? WHERE producto_id = ? AND nota_venta_id = ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer los valores de los parámetros            
        preparedStatement.setInt(1, getCantidad());
        preparedStatement.setDouble(2, getPrecio());            
        preparedStatement.setInt(3, getProducto_id());
        preparedStatement.setInt(4, getNota_venta_id());

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DDetalle.java: "
                    + "Ocurrio un error al editar un detalle editar()");
            throw new SQLException();
        }

        // Cerrar la conexión y la declaración
        preparedStatement.close();
        database.desconectar();
        return true;
        
    }
    
    public boolean eliminar() throws SQLException{
        
        database.conectar();       

        // Definir la sentencia SQL para la eliminación
        String sql = "DELETE FROM detalle WHERE producto_id = ? AND nota_venta_id = ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer el valor del parámetro (id) para la eliminación
        preparedStatement.setInt(1, getProducto_id());
        preparedStatement.setInt(1, getNota_venta_id());

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DDetalle.java: "
                    + "Ocurrio un error al eliminar un detalle eliminar()");
            throw new SQLException();
        }

        // Cerrar la conexión y la declaración
        preparedStatement.close();
        database.desconectar();
        return true;
        
    }
    
    
}
