/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.BD;
import Datos.DCarrito;
import Datos.DMembresia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class NCarrito {
    public int id;
    public int cliente_id;
    
    private DCarrito dCarrito;

    public NCarrito() {
        this.dCarrito = new DCarrito();
    }

    public NCarrito(int id, int cliente_id) {
        this.dCarrito = new DCarrito();
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
    
    public boolean crear( List<String> parametros ) throws SQLException{
        dCarrito.setCliente_id( Integer.valueOf( parametros.get(0) ));        
        return dCarrito.crear();
    }

    public boolean editar( List<String> parametros ) throws SQLException{
        dCarrito.setId( Integer.valueOf( parametros.get(0) ));
        dCarrito.setCliente_id( Integer.valueOf( parametros.get(0) ));
        return dCarrito.editar();
    }

    public boolean eliminar( List<String> parametros ) throws SQLException{
        dCarrito.setId( Integer.valueOf( parametros.get(0) ));
        return dCarrito.eliminar();
    }

    public List<String[]> listar() throws SQLException{
        List<DCarrito> dCarritos = dCarrito.listar();
        ArrayList<String[]> nCarritos = new ArrayList<>();        
        for (DCarrito carrito : dCarritos) {            
            nCarritos.add( new String[]{ 
                    String.valueOf( carrito.getId() ),
                    String.valueOf( carrito.getCliente_id() )
            });
        }
        return nCarritos;
    }
}
