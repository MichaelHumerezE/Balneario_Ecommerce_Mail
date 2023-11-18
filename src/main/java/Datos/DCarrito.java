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
public class DCarrito {
    
    public int id;
    public int cliente_id;
    
    private BD database;

    public DCarrito() {
        this.database = new BD();
    }

    public DCarrito(int id, int cliente_id) {
        this.database = new BD();
        this.id = id;
        this.cliente_id = cliente_id;       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }
    
    public List<DCarrito> listar() throws SQLException{
                    
        database.conectar();
        Statement statement = database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM carrito");
        List<DCarrito> carritos = new ArrayList<>();
        while (resultSet.next()) {
            carritos.add( new DCarrito(
                    resultSet.getInt("id"), 
                    resultSet.getInt("cliente_id")
            ));                
        }
        resultSet.close();
        statement.close();
        database.desconectar();
        return carritos;
        
    }   
    
    public boolean crear() throws SQLException{
        
        database.conectar();       
       
        // Definir la sentencia SQL para la inserción
        String sql = "INSERT INTO carrito (cliente_id) VALUES ( ? )";            

        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer los valores de los parámetros
        preparedStatement.setInt(1, getCliente_id());

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DCarrito.java: "
                    + "Ocurrio un error al insertar un carrito crear()");
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
        String sql = "UPDATE carrito SET cliente_id = ? WHERE id = ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer los valores de los parámetros
        preparedStatement.setInt(1, getCliente_id());                                    
        preparedStatement.setInt(2, getId());

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DCarrito.java: "
                    + "Ocurrio un error al editar un carrito editar()");
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
        String sql = "DELETE FROM carrito WHERE id = ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer el valor del parámetro (id) para la eliminación
        preparedStatement.setInt(1, this.id);

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DCarrito.java: "
                    + "Ocurrio un error al eliminar un carrito eliminar()");
            throw new SQLException();
        }

        // Cerrar la conexión y la declaración
        preparedStatement.close();
        database.desconectar();
        return true;
        
    }
    
    
}
