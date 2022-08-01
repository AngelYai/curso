
package com.mycompany.curso.negocio;

import com.mycompany.curso.dao.AlumnosDAO;
import com.mycompany.curso.to.AlumnosTO;
import com.mycompany.curso.to.ResultadoTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class AlumnosNegocio {

    ArrayList<AlumnosTO> listaAlumnos = new ArrayList<>();
    
    public AlumnosNegocio() {
    
    }
    
    public void obtenerAlumnoNegocio(){
        AlumnosDAO alumDao = new AlumnosDAO();
        
        listaAlumnos = alumDao.obtenerAlumnos();
        
        listaAlumnos.stream().forEach(t -> System.out.println(t));
        
    }
    
    public void filtroAlumnos(String dato){
         AlumnosDAO alumDao = new AlumnosDAO();
         
         listaAlumnos.stream().filter(p -> p.getProgramaEducativo().equalsIgnoreCase(dato)).forEach(t -> System.out.println(t));
         
    }
    
    public void guardarAlumnoNegocio(AlumnosTO alumnTo){
        AlumnosDAO alumnDao = new AlumnosDAO();
        ResultadoTO res;
        
        if(alumnTo.getNombre() == null || alumnTo.getNombre().equals("") ||
                alumnTo.getPrimerApellido() == null || alumnTo.getPrimerApellido().equals("") ||
                alumnTo.getSegundoApellido() == null || alumnTo.getSegundoApellido().equals("") ||
                alumnTo.getCurp() == null || alumnTo.getCurp().equals("") ||
                alumnTo.getProgramaEducativo() == null || alumnTo.getProgramaEducativo().equals(""))
        {
            System.out.println("Por favor, rellena todos los campos");
            return;
        }
        
        res = alumnDao.buscarCurpAlumno(alumnTo.getCurp(), "No se pudo guardar. El CURP ya existe");
        
        
        if(res.isExito()){
            System.out.println(res.getMensaje());
        } else{
            res = alumnDao.guardarAlumno(alumnTo);
            System.out.println(res.getMensaje());
        }
        
    }
    
    public void eliminarAlumnoNegocio(AlumnosTO alumnTo){
        AlumnosDAO alumnDao = new AlumnosDAO();
        ResultadoTO res = alumnDao.buscarCurpAlumno(alumnTo.getCurp(), "No se puede borrar. No existe el CURP");
        
        
        if(res.isExito()){
            alumnDao.eliminarAlumno(alumnTo);
            System.out.println("Se elimino correctamente");
        } else{
            System.out.println(res.getMensaje());
        }
    }
    
}
