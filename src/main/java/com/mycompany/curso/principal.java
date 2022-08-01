
package com.mycompany.curso;

import com.mycompany.curso.negocio.AlumnosNegocio;
import com.mycompany.curso.to.AlumnosTO;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class principal {
    public static void main(String[] args) {
        AlumnosTO alumnosto = new AlumnosTO();
        AlumnosNegocio metodos = new AlumnosNegocio();
        Scanner entrada = new Scanner(System.in);
        int a=0;String b;

        a=Integer.parseInt(JOptionPane.showInputDialog("1.-Lista Alumnos \n2.-Guardar Alumno \n3.-Eliminar Alumno\n4.-Filtrado\n\nSeleccione Una Opcion:"));
        
        switch(a){
            
            case 1:
                metodos.obtenerAlumnoNegocio();
                break;
            case 2:
                System.out.println("Guardar Alumno");
                alumnosto.setNombre(JOptionPane.showInputDialog("Ingrese Nombre:"));
                alumnosto.setPrimerApellido(JOptionPane.showInputDialog("Ingrese Primer Apellido:"));
                alumnosto.setSegundoApellido(JOptionPane.showInputDialog("Ingrese Segundo Apellido:"));
                alumnosto.setCurp(JOptionPane.showInputDialog("Ingrese CURP:"));
                alumnosto.setProgramaEducativo(JOptionPane.showInputDialog("Ingrese Programa Educativo:"));
                metodos.guardarAlumnoNegocio(alumnosto);
                metodos.obtenerAlumnoNegocio();
                break;
            case 3:
                alumnosto.setCurp(JOptionPane.showInputDialog("Eliminar Mediante CURP\nIngrese Curp:"));
                metodos.eliminarAlumnoNegocio(alumnosto);
                metodos.obtenerAlumnoNegocio();
                break;
            case 4:
                   metodos.filtroAlumnos(JOptionPane.showInputDialog("Ingrese Programa Educativo:"));
                break;
        }
        
        
        //=====TODO LO DE ABAJO SI FUNCIONA PERO LO DE ARRIBA POR ALGUNA RAZON NO FUNCIONA AUN QUE ES LO MISMO=====
        //======MUESTRA LISTA======
        /*metodos.obtenerAlumnoNegocio();
        //======GUARDA======
        alumnosto.setNombre("Maria");
        alumnosto.setPrimerApellido("Perez");
        alumnosto.setSegundoApellido("Sanes");
        alumnosto.setCurp("ASD");
        alumnosto.setProgramaEducativo("Sistemas");
        
        metodos.guardarAlumnoNegocio(alumnosto);
        //===================
        metodos.obtenerAlumnoNegocio();
        //======ELIMINA======
        alumnosto.setCurp("MAC");
        
        metodos.eliminarAlumnoNegocio(alumnosto);
        //===================
        metodos.obtenerAlumnoNegocio();
        //======FILTRA======
        System.out.println("Ingrese cosa a filtrar: ");
        metodos.filtroAlumnos(entrada.nextLine());*/
        
    }
}