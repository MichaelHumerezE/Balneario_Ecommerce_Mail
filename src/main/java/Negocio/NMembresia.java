/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;



import Datos.DMembresia;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author user
 */
public class NMembresia {
    public int id;
    public String nombre;
    public double precio;
    public String imagen;
    public  int periodo;
    
    private DMembresia dMembresia;
    
    public NMembresia(){
        dMembresia = new DMembresia();
    }
    
    public NMembresia( int id, String nombre, double precio, String imagen, int periodo ){
        dMembresia = new DMembresia();
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
    
    
    public boolean crear( List<String> parametros ) {
        dMembresia.setNombre( parametros.get(0) );
        dMembresia.setPrecio( Double.parseDouble( parametros.get(1) ));
        dMembresia.setImagen( parametros.get(2) );
        dMembresia.setPeriodo( Integer.parseInt( parametros.get(3) ) );
        return dMembresia.crear();
    }

    public boolean editar( List<String> parametros ) {
        dMembresia.setId( Integer.parseInt( parametros.get(0) ) );
        dMembresia.setNombre( parametros.get(1) );
        dMembresia.setPrecio( Double.parseDouble( parametros.get(2) ));
        dMembresia.setImagen( parametros.get(3) );
        dMembresia.setPeriodo( Integer.parseInt( parametros.get(4) ) );
        return dMembresia.editar();
    }

    public boolean eliminar( List<String> parametros ) {
        dMembresia.setId( Integer.parseInt( parametros.get(0) ) );
        return dMembresia.eliminar();
    }

    public ArrayList<String[]> listar() {
        List<DMembresia> dMembresias = dMembresia.listar();
        ArrayList<String[]> nMembresias = new ArrayList<>();        
        for (DMembresia membresia : dMembresias) {            
            nMembresias.add( new String[]{ 
                    String.valueOf(membresia.getId()),
                    membresia.getNombre(),
                    String.valueOf(membresia.getPrecio()),
                    membresia.getImagen(),
                    String.valueOf(membresia.getPeriodo())}
            );
        }
        return nMembresias;
    }
    
}
