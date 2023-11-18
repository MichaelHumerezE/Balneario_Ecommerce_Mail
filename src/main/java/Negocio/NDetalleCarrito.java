/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DDetalleCarrito;
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
public class NDetalleCarrito {
   public int carrito_id;
    public int producto_id;
    public int cantidad;
    
    private DDetalleCarrito dDetalleCarrito;

    public NDetalleCarrito() {
        this.dDetalleCarrito = new DDetalleCarrito();
    }

    public NDetalleCarrito(int carrito_id, int producto_id, int cantidad) {
        this.dDetalleCarrito = new DDetalleCarrito();
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
    
    public boolean crear( List<String> parametros ) {
        dDetalleCarrito.setCarrito_id( Integer.valueOf( parametros.get(0) ));
        dDetalleCarrito.setProducto_id( Integer.valueOf( parametros.get(1) ));
        dDetalleCarrito.setCantidad( Integer.valueOf( parametros.get(2) ));
        return dDetalleCarrito.crear();
    }
    
    public boolean editar( List<String> parametros ) {
        dDetalleCarrito.setCarrito_id( Integer.valueOf( parametros.get(0) ));
        dDetalleCarrito.setProducto_id( Integer.valueOf( parametros.get(1) ));
        dDetalleCarrito.setCantidad( Integer.valueOf( parametros.get(2) ));
        return dDetalleCarrito.editar();
    }
   
    public boolean eliminar( List<String> parametros ) {
        dDetalleCarrito.setCarrito_id( Integer.valueOf( parametros.get(0) ));
        dDetalleCarrito.setProducto_id( Integer.valueOf( parametros.get(1) ));
        return dDetalleCarrito.eliminar();
    }

    public List<String[]> listar() {
        List<DDetalleCarrito> dDetalleCarritos = dDetalleCarrito.listar();
        ArrayList<String[]> nDetalleCarritos = new ArrayList<>();        
        for (DDetalleCarrito detalleCarrito : dDetalleCarritos ) {
            nDetalleCarritos.add( new String[]{
                    String.valueOf( detalleCarrito.getCarrito_id()),
                    String.valueOf( detalleCarrito.getProducto_id()),
                    String.valueOf( detalleCarrito.getCantidad() ),                   
            });
                    
        }
        return nDetalleCarritos;
    }
}
