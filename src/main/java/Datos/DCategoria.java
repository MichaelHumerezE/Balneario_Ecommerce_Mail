/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.BD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author godof
 */
public class DCategoria {
    public int id;
    public String nombre;

    private BD database;

    public DCategoria(){
        this.database = new BD();
    }

    public DCategoria(int id, String nombre){
        this.database = new BD();
        this.id = id;
        this.nombre = nombre;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public List<DCategoria> listar() {
        try {
            database.conectar();
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categoria");
            List<DCategoria> categorias = new ArrayList<>();
            while (resultSet.next()) {
                categorias.add(new DCategoria(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                ));
            }
            resultSet.close();
            statement.close();
            database.desconectar();
            return categorias;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public boolean crear() {
        try {
            database.conectar();
            Statement statement = database.getConnection().createStatement();
            statement.execute("INSERT INTO categoria (nombre) VALUES ('" + nombre + "')");
            statement.close();
            database.desconectar();
            System.out.println("Categoria Creada.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public boolean editar() {
        try {
            database.conectar();
            Statement statement = database.getConnection().createStatement();
            statement.executeUpdate("UPDATE categoria SET nombre = '" + nombre + "' WHERE id = " + id);
            statement.close();
            database.desconectar();
            System.out.println("Categoria Editada.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public boolean eliminar() {
        try {
            database.conectar();
            Statement statement = database.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM categoria WHERE id = " + id);
            statement.close();
            database.desconectar();
            System.out.println("Categoria Eliminada.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
}
