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
public class DDetalleCarrito {
    public int carrito_id;
    public int producto_id;
    public int cantidad;
    
    private BD database;

    public DDetalleCarrito() {
        this.database = new BD();
    }

    public DDetalleCarrito(int carrito_id, int producto_id, int cantidad) {
        this.database = new BD();
        this.carrito_id = carrito_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;        
    }

    public int getCarrito_id() {
        return carrito_id;
    }

    public void setCarrito_id(int carrito_id) {
        this.carrito_id = carrito_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }    
   
    
    public List<DDetalleCarrito> listar() {
        try {            
            database.conectar();
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM detalle_carrito");
            List<DDetalleCarrito> carritoDetalles = new ArrayList<>();
            while (resultSet.next()) {
                carritoDetalles.add( new DDetalleCarrito(
                        resultSet.getInt("carrito_id"), 
                        resultSet.getInt("producto_id"),
                        resultSet.getInt("catidad")
                ));                
            }
            resultSet.close();
            statement.close();
            database.desconectar();
            return carritoDetalles;
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
            String sql = "INSERT INTO detalle_carrito (cliente_id, producto_id, cantidad) VALUES ( ?, ?, ? )";            
                    
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setInt(1,getCarrito_id());
            preparedStatement.setInt(1, getProducto_id());
            preparedStatement.setInt(1, getCantidad());
            
            

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
            String sql = "UPDATE detalle_carrito SET carrito_id, producto_id, cantidad = ? WHERE id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setInt(1, getCarrito_id());                                    
            preparedStatement.setInt(2, getProducto_id());
            preparedStatement.setInt(2, getCantidad());

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
            String sql = "DELETE FROM detalle_carrito WHERE carrito_id = ? AND producto_id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer el valor del parámetro (id) para la eliminación
            preparedStatement.setInt(1, this.carrito_id );
            preparedStatement.setInt(1, this.producto_id );

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
