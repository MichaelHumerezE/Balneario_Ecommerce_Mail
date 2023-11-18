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
    
    public List<DCarrito> listar() {
        try {            
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
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }   
    
    public boolean crear() {
        try {
            database.conectar();          
            boolean resultado = true;
            // Definir la sentencia SQL para la inserción
            String sql = "INSERT INTO carrito (cliente_id) VALUES ( ? )";            
                    
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setInt(1, getCliente_id());
            
            

            // Ejecutar la sentencia de inserción
            int filasInsertadas = preparedStatement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Carrito insertado con éxito.");
            } else {
                System.out.println("No se pudo insertar el carrito.");
                resultado = false;
            }

            // Cerrar la conexión y la declaración
            preparedStatement.close();
            database.desconectar();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    public boolean editar() {
        try {
            database.conectar();
            boolean resultado = true;

            // Definir la sentencia SQL para la actualización
            String sql = "UPDATE carrito SET cliente_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setInt(1, getCliente_id());                                    
            preparedStatement.setInt(2, getId());

            // Ejecutar la sentencia de actualización
            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Carrito editado con éxito.");
            } else {
                System.out.println("No se pudo editar el carrito.");
                resultado = false;
            }

            // Cerrar la conexión y la declaración
            preparedStatement.close();
            database.desconectar();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
   
    public boolean eliminar() {
        try {
            database.conectar();
            boolean resultado = true;

            // Definir la sentencia SQL para la eliminación
            String sql = "DELETE FROM carrito WHERE id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer el valor del parámetro (id) para la eliminación
            preparedStatement.setInt(1, this.id);

            // Ejecutar la sentencia de eliminación
            int filasEliminadas = preparedStatement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Carrito eliminado con éxito.");
            } else {
                System.out.println("No se pudo eliminar el carrito.");
                resultado = false;
            }

            // Cerrar la conexión y la declaración
            preparedStatement.close();
            database.desconectar();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
    
    
}
