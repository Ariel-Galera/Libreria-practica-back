/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Servicios;

import guia16.ej1.tienda.Entidades.Editorial;
import guia16.ej1.tienda.Persistencia.EditorialDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ariel
 */
public class EditorialServicio {
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private LibroServicio libroServicio;
    private AutorServicio autorServicio;
    private final EditorialDAO DAO;
    
    public EditorialServicio() {
        this.DAO = new EditorialDAO();
    }

    public void setServicio(LibroServicio libroServicio, AutorServicio autorServicio) {
        this.libroServicio = libroServicio;
        this.autorServicio = autorServicio;
      
    }
    
    
    //Metodo para crear un autor
     public Editorial crearEditorial (){
        
        Editorial editorial = new Editorial();
        try {
            
            System.out.println("Ingrese el nombre del editorial:");
            editorial.setNombre(leer.next());
            editorial.setAlta(Boolean.TRUE);
            DAO.guardar(editorial);
            
           
            return editorial;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
        
     public Editorial buscarEditorialPorId (int id){
        
        try {
            return DAO.buscarEditorialPorId(id);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
       
    }
    
      public Editorial buscaEditorialPorNombre(String nombre){
          
          try {
              return DAO.buscarEditorialPorNombre(nombre);
          } catch (Exception e) {
              System.out.println(e.getMessage());
              return null;
          }
      } 
      
        public List<Editorial> listarEditoriales() {
        try {
            return DAO.listarTodosLosEditoriales();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
        
      public void editarEditorial() {
    try {
        System.out.println("Ingrese el Id del editorial que desea editar:");
        int autorId = leer.nextInt();
        Editorial editorial = DAO.buscarEditorialPorId(autorId);
        
        if (editorial != null) {
            int opcion;
            Boolean alta = true;
            
            do {
                System.out.println("*************MENU DE EDICION DE EDITORIAL***************");
                System.out.println("1.Editar el nombre del editorial");
                System.out.println("2.Editar el alta del editorial");
                System.out.println("3.Salir");
                opcion = leer.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre del editorial:");
                        String editorialNombre = leer.next();
                        editorial.setNombre(editorialNombre);
                        DAO.editarEditorial(editorial);
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo estado del editorial (true para Alta, false para Baja):");
                        boolean editorialEstado = leer.nextBoolean();
                        editorial.setAlta(editorialEstado);
                        DAO.editarEditorial(editorial);
                        break;
                    case 3:
                        System.out.println("Saliendo del menú de edición.");
                        break;
                    default:
                        System.out.println("Opcion invalida, intente nuevamente");
                }
            } while (opcion != 3);
        } else {
            System.out.println("El editorial no existe.");
        }
    } catch (Exception e) {
        System.out.println("Error: Ingreso inválido. Intente nuevamente.");
   
    }
}

       
      
 }     
    
    
    
    

