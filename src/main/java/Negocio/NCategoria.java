/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DCategoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author godof
 */
public class NCategoria {
    public int id;
    public String nombre;

    private DCategoria DCategoria;

    public NCategoria (){
        DCategoria = new DCategoria();
    }

    public NCategoria (int id, String nombre){
        DCategoria = new DCategoria();
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

    public boolean crear(String nombre) {
            DCategoria.setNombre(nombre);
            return DCategoria.crear();
    }

    public boolean editar(String nombre) {
        DCategoria.setId(getId());
        DCategoria.setNombre(nombre);
        return DCategoria.editar();
    }

    public boolean eliminar() {
        DCategoria.setId(getId());
        return DCategoria.eliminar();
    }

    public List<NCategoria> listar() {
        List<DCategoria> DCategorias = DCategoria.listar();
        List<NCategoria> NCategorias = new ArrayList<>();
        for (DCategoria Categoria : DCategorias) {
            NCategorias.add(new NCategoria(Categoria.getId(), Categoria.getNombre()));
        }
        return NCategorias;
    }
}
