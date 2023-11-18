/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.BD;
import Datos.DNotaVenta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class NNotaVenta {
    public int id;
    public String nit;
    public LocalDateTime fecha_hora;
    public double monto_total;
    public String nombre_cliente;
    public int cliente_id; 
    public int empleado_id;
    public int membresia_id;
    
    private DNotaVenta dNotaVenta;

    public NNotaVenta() {
        this.dNotaVenta = new DNotaVenta();
    }    
    
    public NNotaVenta(int id, String nit, LocalDateTime fecha_hora, double monto_total, 
            String nombre_cliente, int cliente_id, int empleado_id, int membresia_id) {
        this.dNotaVenta = new DNotaVenta(); 
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
    
    public boolean crear( List<String> parametros ) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        dNotaVenta.setNit( parametros.get(0) );
        dNotaVenta.setFecha_hora( fechaHoraActual );
        dNotaVenta.setMonto_total( Double.valueOf( parametros.get(1) ));
        dNotaVenta.setNombre_cliente( parametros.get(2) );
        dNotaVenta.setCliente_id( Integer.valueOf( parametros.get(3) ));
        dNotaVenta.setEmpleado_id( Integer.valueOf( parametros.get(4) ) );
        dNotaVenta.setMembresia_id( Integer.valueOf( parametros.get(5) ) );
        return dNotaVenta.crear();
    }

    public boolean editar( List<String> parametros ) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        dNotaVenta.setId( Integer.valueOf( parametros.get(0) ));
        dNotaVenta.setNit( parametros.get(1) );
        dNotaVenta.setFecha_hora( fechaHoraActual );
        dNotaVenta.setMonto_total( Double.valueOf( parametros.get(2) ));
        dNotaVenta.setNombre_cliente( parametros.get(3) );
        dNotaVenta.setCliente_id( Integer.valueOf( parametros.get(4) ));
        dNotaVenta.setEmpleado_id( Integer.valueOf( parametros.get(5) ));
        dNotaVenta.setMembresia_id( Integer.valueOf( parametros.get(6) ));        
        return dNotaVenta.editar();
    }

    public boolean eliminar( List<String> parametros ) {
        dNotaVenta.setId( Integer.valueOf( parametros.get(0) ));
        return dNotaVenta.eliminar();
    }

    public List<String[]> listar() {
        List<DNotaVenta> dNotaVentas = dNotaVenta.listar();
        ArrayList<String[]> nNotaVentas = new ArrayList<>();        
        for (DNotaVenta notaVenta : dNotaVentas) {
            nNotaVentas.add( new String[]{
                    String.valueOf( notaVenta.getId() ),
                    notaVenta.getNit(),
                    String.valueOf( notaVenta.getFecha_hora() ),
                    String.valueOf( notaVenta.getMonto_total() ),
                    notaVenta.getNombre_cliente(),
                    String.valueOf( notaVenta.getCliente_id() ),
                    String.valueOf( notaVenta.getEmpleado_id() ),
                    String.valueOf( notaVenta.getMembresia_id() )
            });
                    
        }
        return nNotaVentas;
    }
    
}
