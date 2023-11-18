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
import java.util.Date;
import java.util.List;
/**
 *
 * @author user
 */
public class DMembresia {
    
    public int id;
    public String nombre;
    public double precio;
    public String imagen;    
    public  int periodo;
    
    private BD database;
    
    
    public DMembresia(){
       this.database = new BD();  
    }    

    public DMembresia(int id, String nombre, double precio, String imagen, int periodo) {
        this.database = new BD();
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.periodo = periodo;        
    }
    
    
    
    // Getter y Setter para el miembro 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para el miembro 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para el miembro 'precio'
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Getter y Setter para el miembro 'imagen'
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    
    
    public List<DMembresia> listar() {
        try {            
            database.conectar();
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM membresia");
            List<DMembresia> membresias = new ArrayList<>();
            while (resultSet.next()) {
                membresias.add( new DMembresia(
                        resultSet.getInt("id"), 
                        resultSet.getString("nombre"), 
                        resultSet.getDouble("precio"), 
                        resultSet.getString("imagen"), 
                        resultSet.getInt("periodo"))                        
                );                
            }
            resultSet.close();
            statement.close();
            database.desconectar();
            return membresias;
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
            String sql = "INSERT INTO membresia (nombre, precio, imagen, periodo) " +
                         "VALUES (?, ?, ?, ?, ?)";            
                    
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, getNombre());
            preparedStatement.setDouble(2, getPrecio());
            preparedStatement.setString(3, getImagen());
            preparedStatement.setInt(4, getPeriodo());
            

            // Ejecutar la sentencia de inserción
            int filasInsertadas = preparedStatement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Membresía insertada con éxito.");
            } else {
                System.out.println("No se pudo insertar la membresía.");
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
            String sql = "UPDATE membresia SET nombre = ?, precio = ?, imagen = ?, fechaIni = ?, fechaFin = ? WHERE id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, getNombre());
            preparedStatement.setDouble(2, getPrecio());
            preparedStatement.setString(3, getImagen());
            preparedStatement.setInt(4, getPeriodo());                        
            preparedStatement.setInt(6, getId());

            // Ejecutar la sentencia de actualización
            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Membresía actualizada con éxito.");
            } else {
                System.out.println("No se pudo actualizar la membresía.");
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
            String sql = "DELETE FROM membresia WHERE id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer el valor del parámetro (id) para la eliminación
            preparedStatement.setInt(1, this.id);

            // Ejecutar la sentencia de eliminación
            int filasEliminadas = preparedStatement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Membresía eliminada con éxito.");
            } else {
                System.out.println("No se pudo eliminar la membresía.");
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
