/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Servicios;

import guia16.ej1.tienda.Entidades.Autor;
import guia16.ej1.tienda.Persistencia.AutorDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ariel
 */
public class AutorServicio {
     private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private LibroServicio libroServicio;
    private EditorialServicio editorialServicio;
    private final AutorDAO DAO;

    public AutorServicio() {
        this.DAO = new AutorDAO();
    }

    public void setServicio(EditorialServicio editorialServicio) {
        this.editorialServicio = editorialServicio;
     
    }
    
    //Metodo para crear un autor
     public Autor crearAutor (){
        
        Autor autor = new Autor();
        try {
            
            System.out.println("Ingrese el nombre del autor:");
            autor.setNombre(leer.next());
            autor.setAlta(Boolean.TRUE);
            DAO.guardar(autor);
            
              return autor;
         
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
      public Autor buscarAutorPorId (int id){
        
        try {
            return DAO.buscarAutorPorId(id);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
       
    }
      
      public Autor buscarAutorPorNombre(String nombre){
          
          try {
              return DAO.buscarAutorPorNombre(nombre);
          } catch (Exception e) {
              System.out.println(e.getMessage());
              return null;
          }
      }
    
      public List<Autor> listarAutores() {
        try {
            return DAO.listarTodosLosAutores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
      
    public void editarAutor() {
    try {
        System.out.println("Ingrese el Id del autor que desea editar:");
        int autorId = leer.nextInt();
        Autor autor = DAO.buscarAutorPorId(autorId); // Buscar el autor por Id en el DAO
        
        if (autor != null) {
            int opcion;
            Boolean alta = true;
            
            do {
                System.out.println("*************MENU DE EDICION DE AUTOR***************");
                System.out.println("1.Editar el nombre del autor");
                System.out.println("2.Editar el alta del autor");
                System.out.println("3.Salir");
                opcion = leer.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre del autor:");
                        String autorNombre = leer.next();
                        autor.setNombre(autorNombre);
                        DAO.editarAutor(autor);
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo estado del autor (true para Alta, false para Baja):");
                        boolean autorEstado = leer.nextBoolean();
                        autor.setAlta(autorEstado);
                        DAO.editarAutor(autor);
                        break;
                    case 3:
                        System.out.println("Saliendo del menú de edición.");
                        break;
                    default:
                        System.out.println("Opcion invalida, intente nuevamente");
                }
            } while (opcion != 3);
        } else {
            System.out.println("El autor no existe.");
        }
    } catch (Exception e) {
        System.out.println("Error: Ingreso inválido. Intente nuevamente.");
   
    }
}

   
}
        

