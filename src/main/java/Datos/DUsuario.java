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
public class DUsuario {
    
    public int id;
    public String ci;
    public String nombre;
    public String sexo;
    public String email;
    public String password;
    public String tipo;
    
    private BD database;
    
    
    public DUsuario(){
       this.database = new BD(); 
    }    

    public DUsuario(int id, String ci, String nombre, String sexo, String email, String password, String tipo) {
        this.database = new BD();
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.sexo = sexo;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }    
    
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }  
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }    
    
    public List<DUsuario> listar() throws SQLException{
                   
        database.conectar();
        Statement statement = database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM usuario");
        List<DUsuario> usuarios = new ArrayList<>();
        while (resultSet.next()) {
            usuarios.add( new DUsuario(
                    resultSet.getInt("id"),
                    resultSet.getString("ci"),
                    resultSet.getString("nombre"),
                    resultSet.getString("sexo"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("tipo")
            ));
        }
        resultSet.close();
        statement.close();
        database.desconectar();
        return usuarios;
        
    }
    
    public boolean crear() throws SQLException{
        
        database.conectar();       
        // Definir la sentencia SQL para la inserción
        String sql = "INSERT INTO usuario (ci, nombre, sexo, email, password, tipo) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";            

        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer los valores de los parámetros
        preparedStatement.setString(1, getCi());
        preparedStatement.setString(2, getNombre());
        preparedStatement.setString(3, getSexo());
        preparedStatement.setString(4, getEmail());
        preparedStatement.setString(5, getPassword());
        preparedStatement.setString(6, getTipo());

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DUsuario.java: "
                    + "Ocurrio un error al insertar un usuario crear()");
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
        String sql = "UPDATE usuario SET ci = ?, nombre = ?, sexo = ?, email = ?, password = ?, tipo = ? WHERE id = ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer los valores de los parámetros
        preparedStatement.setString(1, getCi());
        preparedStatement.setString(2, getNombre());
        preparedStatement.setString(3, getSexo());
        preparedStatement.setString(4, getEmail());
        preparedStatement.setString(5, getPassword());
        preparedStatement.setString(6, getTipo());
        preparedStatement.setInt(7, getId());

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DUsuario.java: "
                    + "Ocurrio un error al editar un usuario editar()");
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
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

        // Establecer el valor del parámetro (id) para la eliminación
        preparedStatement.setInt(1, getId());

        if ( preparedStatement.executeUpdate() == 0 ){
            preparedStatement.close();
            database.desconectar();
            System.err.println("Class DUsuario.java: "
                    + "Ocurrio un error al eliminar un usuario eliminar()");
            throw new SQLException();
        }

        // Cerrar la conexión y la declaración
        preparedStatement.close();
        database.desconectar();
        return true;
        
    }
            
            
}
