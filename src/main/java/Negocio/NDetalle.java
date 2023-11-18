/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DDetalle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class NDetalle {
    public int producto_id;
    public int nota_venta_id;
    public int cantidad;
    public double precio;
    
    private DDetalle dDetalle;

    public NDetalle() {
        this.dDetalle = new DDetalle();
    }

    public NDetalle(int producto_id, int nota_venta_id, int cantidad, double precio) {
        this.dDetalle = new DDetalle();
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
    
    public boolean crear( List<String> parametros ) {
        dDetalle.setProducto_id( Integer.valueOf( parametros.get(0) ));
        dDetalle.setNota_venta_id( Integer.valueOf( parametros.get(1) ) );
        dDetalle.setCantidad( Integer.valueOf( parametros.get(2) ) );
        dDetalle.setPrecio( Double.valueOf( parametros.get(3)) );        
        return dDetalle.crear();
    }

    public boolean editar( List<String> parametros ) {  
        dDetalle.setProducto_id( Integer.valueOf( parametros.get(0) ));
        dDetalle.setNota_venta_id( Integer.valueOf( parametros.get(1) ));
        dDetalle.setCantidad( Integer.valueOf( parametros.get(2) ));
        dDetalle.setPrecio( Double.valueOf( parametros.get(3)) );        
        return dDetalle.editar();
    }

    public boolean eliminar( List<String> parametros ) {
        dDetalle.setProducto_id( Integer.valueOf( parametros.get(0) ));
        dDetalle.setNota_venta_id( Integer.valueOf( parametros.get(1) ));
        return dDetalle.eliminar();
    }

    public List<String[]> listar() {
        List<DDetalle> dDetalles = dDetalle.listar();
        ArrayList<String[]> nDetalles = new ArrayList<>();        
        for (DDetalle detalle : dDetalles) {
            nDetalles.add( new String[]{
                    String.valueOf( detalle.getProducto_id() ),
                    String.valueOf( detalle.getNota_venta_id() ),
                    String.valueOf( detalle.getCantidad() ),
                    String.valueOf( detalle.getPrecio() )
            });
                    
        }
        return nDetalles;
    }
}
