/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class NUsuario {
    public int id;
    public String ci;
    public String nombre;
    public String sexo;
    public String email;
    public String password;
    public String tipo;
    
    private DUsuario dUsuario;

    public NUsuario() {
        dUsuario = new DUsuario();
    }

    public NUsuario(int id, String ci, String nombre, String sexo, String email, String password, String tipo) {
        dUsuario = new DUsuario();
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
    
    
    public boolean crear( List<String> parametros ) {
        dUsuario.setCi( parametros.get(0) );
        dUsuario.setNombre( parametros.get(1) );
        dUsuario.setSexo( parametros.get(2) );
        dUsuario.setEmail( parametros.get(3) );
        dUsuario.setPassword( parametros.get(4) );
        dUsuario.setTipo( parametros.get(5) );
        return dUsuario.crear();
    }

    public boolean editar( List<String> parametros ) {
        dUsuario.setId( Integer.parseInt( parametros.get(0) ));
        dUsuario.setCi( parametros.get(1) );
        dUsuario.setNombre( parametros.get(2) );
        dUsuario.setSexo( parametros.get(3) );
        dUsuario.setEmail( parametros.get(4) );
        dUsuario.setPassword( parametros.get(5) );
        dUsuario.setTipo( parametros.get(6) );
        return dUsuario.editar();
    }

    public boolean eliminar( List<String> parametros ) {
        dUsuario.setId( Integer.parseInt( parametros.get(0) ) );
        return dUsuario.eliminar();
    }

    public List<String[]> listar() {
        List<DUsuario> dUsuarios = dUsuario.listar();
         ArrayList<String[]> nUsuarios = new ArrayList<>();        
        for (DUsuario usuario : dUsuarios) {
            nUsuarios.add( new String[]{
                    String.valueOf( usuario.getId() ),
                    usuario.getCi(),
                    usuario.getNombre(),
                    usuario.getSexo(),
                    usuario.getEmail(),
                    usuario.getPassword(),
                    usuario.getTipo()
            });                    
        }
        return nUsuarios;
    }
    
    
    
}
