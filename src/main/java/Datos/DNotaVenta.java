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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DNotaVenta {
    public int id;
    public String nit;
    public LocalDateTime fecha_hora;
    public double monto_total;
    public String nombre_cliente;
    public int cliente_id; 
    public int empleado_id;
    public int membresia_id;
    
    private BD database;

    public DNotaVenta() {
        this.database = new BD();
    }    

    public DNotaVenta(int id, String nit, LocalDateTime fecha_hora, double monto_total, 
            String nombre_cliente, int cliente_id, int empleado_id, int membresia_id) {
        this.database = new BD();
        this.id = id;
        this.nit = nit;
        this.fecha_hora = fecha_hora;
        this.monto_total = monto_total;
        this.nombre_cliente = nombre_cliente;
        this.cliente_id = cliente_id;
        this.empleado_id = empleado_id;
        this.membresia_id = membresia_id;        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }    

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public int getMembresia_id() {
        return membresia_id;
    }

    public void setMembresia_id(int membresia_id) {
        this.membresia_id = membresia_id;
    }    
    
    
    public List<DNotaVenta> listar() {
        try {            
            database.conectar();
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM nota_venta");
            List<DNotaVenta> nota_ventas = new ArrayList<>();
            while (resultSet.next()) {
                nota_ventas.add( new DNotaVenta(
                        resultSet.getInt("id"),
                        resultSet.getString("nit"),
                        resultSet.getTimestamp("fecha_hora").toLocalDateTime(),
                        resultSet.getDouble("monto_total"),
                        resultSet.getString("nombre_cliente"),
                        resultSet.getInt("cliente_id"),
                        resultSet.getInt("empleado_id"),
                        resultSet.getInt("membresia_id")
                ));
            }
            resultSet.close();
            statement.close();
            database.desconectar();
            return nota_ventas;
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
            String sql = "INSERT INTO nota_venta (nit, fecha_hora, monto_total, nombre_cliente, cliente_id, empleado_id, miembro_id) " +
                         "VALUES (?, ?, ?, ?, ?)";            
                    
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, getNit());
            preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf( getFecha_hora() ));
            preparedStatement.setDouble(3, getMonto_total());
            preparedStatement.setString(4, getNombre_cliente());
            preparedStatement.setInt(5, getCliente_id());
            preparedStatement.setInt(6, getEmpleado_id());
            preparedStatement.setInt(7, getMembresia_id());

            // Ejecutar la sentencia de inserción
            int filasInsertadas = preparedStatement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Nota de venta insertada con éxito.");
            } else {
                System.out.println("No se pudo insertar la nota de venta.");
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
            String sql = "UPDATE nota_venta SET nit = ?, fecha_hora = ?, monto_total = ?, "
                    + "nombre_cliente = ?, cliente_id = ?, empleado_id = ?, membresia_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, getNit());
            preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf( getFecha_hora() ));
            preparedStatement.setDouble(3, getMonto_total());
            preparedStatement.setString(4, getNombre_cliente());
            preparedStatement.setInt(5, getCliente_id());
            preparedStatement.setInt(6, getEmpleado_id());
            preparedStatement.setInt(7, getMembresia_id());
            preparedStatement.setInt(6, getId());

            // Ejecutar la sentencia de actualización
            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Nota de venta actualizada con éxito.");
            } else {
                System.out.println("No se pudo actualizar la nota de venta.");
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
    
    public boolean eliminar() { // Excepciones
        try {
            database.conectar();
            boolean resultado = true;

            // Definir la sentencia SQL para la eliminación
            String sql = "DELETE FROM nota_venta WHERE id = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql);

            // Establecer el valor del parámetro (id) para la eliminación
            preparedStatement.setInt(1, getId());

            // Ejecutar la sentencia de eliminación
            int filasEliminadas = preparedStatement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Nota de venta eliminada con éxito.");
            } else {
                System.out.println("No se pudo eliminar la nota de venta.");
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
    //int id, String nit, String fecha_hora, String monto_total, String nombre_cliente, int cliente_id
    
}
