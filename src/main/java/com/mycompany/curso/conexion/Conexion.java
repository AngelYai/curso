/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.curso.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author AndyIsael
 */
public class Conexion {
    
    public static final String URL = "jdbc:mysql://localhost:3306/curso?zeroDateTimeBehavior=CONVERT_TO_NULL";
    public static final String USUARIO = "root";
    public static final String CLAVE = "";
    
    public Connection obtenerConexion(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(URL, USUARIO, CLAVE);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("No funciona o no carga el Driver");
        } catch (SQLException ex) {
            System.out.println("Hubo un problema en la conexion a la BD.");
        }
        return con;
        
    }
    
}
