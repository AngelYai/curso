
package com.mycompany.curso.dao;

import com.mycompany.curso.conexion.Conexion;
import com.mycompany.curso.to.AlumnosTO;
import com.mycompany.curso.to.ResultadoTO;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AlumnosDAO {
    
    Conexion conex = new Conexion();
    
    //regresar lista
    public ArrayList obtenerAlumnos(){
        ArrayList temp = new ArrayList();
        
        try {
            Statement st = conex.obtenerConexion().createStatement();
            String sql = "SELECT * FROM alumnos";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {                
                
                AlumnosTO alumnTO = new AlumnosTO();
                
                alumnTO.setId(rs.getInt("id"));
                alumnTO.setNombre(rs.getString("nombre"));
                alumnTO.setPrimerApellido(rs.getString("primerapellido"));
                alumnTO.setSegundoApellido(rs.getString("segundoapellido"));
                alumnTO.setCurp(rs.getString("curp"));
                alumnTO.setProgramaEducativo(rs.getString("programaeducativo"));
                
                temp.add(alumnTO);

            }
            st.close();
            
        } catch (SQLException ex) {
            System.out.println("Hubo un problema al obtener la tabla alumnos");
        }
        return temp;
    }
    
    
    public ResultadoTO guardarAlumno(AlumnosTO alumnos){
        String sql = "INSERT INTO alumnos (nombre,primerapellido,segundoapellido,curp,programaeducativo) VALUES (?,?,?,?,?)";
        ResultadoTO res = new ResultadoTO();
        
        try {
            PreparedStatement ps = conex.obtenerConexion().prepareStatement(sql);
            ps.setString(1, alumnos.getNombre());
            ps.setString(2, alumnos.getPrimerApellido());
            ps.setString(3, alumnos.getSegundoApellido());
            ps.setString(4, alumnos.getCurp());
            ps.setString(5, alumnos.getProgramaEducativo());
            ps.execute();
            ps.close();
            
            res.setExito(true);
            res.setMensaje("Guardado con exito");
            
        } catch (SQLIntegrityConstraintViolationException ex){
            res.setExito(false);
            res.setMensaje("Oh, lo lamento pero existen campos en blanco");
        } catch (MysqlDataTruncation ex){
            res.setExito(false);
            res.setMensaje("Un campo esta exediendo el limite de caracteres");
        } catch (SQLException ex){
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            res.setExito(false);
            res.setMensaje("Error drastico contacte con el administrador... No se guardo");
        }
        return res;
    }
    
    public ResultadoTO eliminarAlumno(AlumnosTO alumnos){
        ResultadoTO res = new ResultadoTO();
        
        String sql = "DELETE FROM alumnos WHERE alumnos.curp = ?";
        
        try {
            PreparedStatement ps = conex.obtenerConexion().prepareStatement(sql);
            ps.setString(1, alumnos.getCurp());
            ps.execute();
            res.setExito(true);
            res.setMensaje("Borrado con exito");
        } catch (SQLException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public ResultadoTO buscarCurpAlumno(String curp, String mensaje){
        ResultadoTO res = new ResultadoTO();
        res.setExito(false);
        res.setMensaje(mensaje);
        
        try {
            Statement st = conex.obtenerConexion().createStatement();
            String sql = "SELECT * FROM alumnos WHERE curp LIKE '"+curp+"'";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                res.setExito(true);
            }
            st.close();
            
        } catch (SQLException ex) {
            System.out.println("Hubo un problema al buscar el curp");
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
}
