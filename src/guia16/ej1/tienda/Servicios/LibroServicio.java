/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Servicios;


import guia16.ej1.tienda.Entidades.Autor;
import guia16.ej1.tienda.Entidades.Editorial;
import guia16.ej1.tienda.Entidades.Libro;
import guia16.ej1.tienda.Persistencia.LibroDAO;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author Ariel
 */
public class LibroServicio {
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    private EditorialServicio editorialServicio;
    private AutorServicio autorServicio;
    private final LibroDAO DAO;
    
    
    
    public LibroServicio() {
        this.DAO = new LibroDAO();
    }

    public void setServicio(EditorialServicio editorialServicio, AutorServicio autorServicio) {
        this.editorialServicio = editorialServicio;
        this.autorServicio = autorServicio;
       
    }
    
    
    
    // este método persiste un registro de tipo Libro en la base de datos
    // a través del método guardar() de la clase DAO.
    
    public Libro crearLibro (){
        
        Libro libro = new Libro();
        try {
            
            System.out.println("Ingrese el Titulo:");
            libro.setTitulo(leer.next());
            System.out.println("Ingrese el año:");
            libro.setAnio(leer.nextInt());
            System.out.println("Ingrese la cantidad de ejemplares:");
            libro.setEjemplares(leer.nextInt());
            System.out.println("Ingrese la cantidad de ejemplares prestados:");
            libro.setEjemplaresPrestados(leer.nextInt());
            System.out.println("Ingrese la cantidad de ejemplares restantes");
            libro.setEjemplaresRestantes(leer.nextInt());
            
            
            System.out.println("Ingrese el nombre del autor que desea asignar:");     
        AutorServicio autorServicio = new AutorServicio();
        Autor autor = autorServicio.buscarAutorPorId(leer.nextInt());
        libro.setAutor(autor);
        
            System.out.println("Ingrese el nombre del editorial que desea asignar:");
        EditorialServicio editorialServicio = new EditorialServicio();
        Editorial editorial = editorialServicio.buscarEditorialPorId(leer.nextInt());
         libro.setEditorial(editorial);
            
            DAO.guardarLibro(libro);
            return libro;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
   
    //Metodo para buscar libro por isbn
    public Libro buscarLibroPorISBN (int isbn){
        
        try {
            return DAO.buscarLibroPorIsbn(isbn);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    //Metodo para buscar libro por titulo
    public Libro buscarLibroPorTitulo(String titulo) {
        try {
            return DAO.buscarLibroPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
  //Metodo para buscar por nombre de autor
  public List<Libro> buscarLibrosPorNombreAutor(String autor) {
        try {
            return DAO.BuscarLibrosXNombreAutor(autor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // Metodo para buscar por editorial 
   
   public List<Libro> buscarLibrosPorEditorial(String editorial) {
       try {
           return DAO.buscarLibrosPorNombreEditorial(editorial);
       } catch (Exception e) {
           System.out.println(e.getMessage());
            return null;
       }
   }
   
   
    public List<Libro> listarLibros() {
        try {
            return DAO.listarTodosLosLibros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   
  public Libro editarLibro() {
    try {
        System.out.println("Ingrese el ISBN del libro que desea modificar");
        int libroISBN = leer.nextInt();
        Libro libro = DAO.buscarLibroPorIsbn(libroISBN); // Buscar el libro por ISBN en el DAO
        
        if (libro != null) { // Verificar si se encontró el libro
            int opcion;
            do {                
                System.out.println("*********MENU DE EDICION DE LIBRO**************");
                System.out.println("1.Editar el titulo");
                System.out.println("2.Editar el año");
                System.out.println("3.Editar el numero de ejemplares");
                System.out.println("4.Editar el numero de ejemplares prestados");
                System.out.println("5.Editar el numero de ejemplares restantes");
                System.out.println("6.Editar el autor (ingresando ID del autor)");
                System.out.println("7.Editar el editorial(ingresando el ID del editorial)");
                System.out.println("8.SALIR");
                
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el titulo");
                        String tituloLibro = leer.next();
                        libro.setTitulo(tituloLibro); // Actualizar el nombre (lo hago para seguir manejandome con el mismo ID)
                        DAO.editarLibro(libro); // Llamar al método editarLibro del DAO para aplicar los cambios
                        break;
                    case 2:
                        System.out.println("Ingrese el año");
                        Integer libroAnio = leer.nextInt();
                        libro.setAnio(libroAnio); // Actualizar el año del libro
                        DAO.editarLibro(libro);
                        break;
                    case 3:
                        System.out.println("Ingrese el numero de ejemplares");
                        Integer libroEjemplares = leer.nextInt(); 
                        libro.setEjemplares(libroEjemplares); // Actualizar la cantidad total de ejemplares
                        DAO.editarLibro(libro);
                        break;
                    case 4:
                        System.out.println("Ingrese el numero de ejemplares prestados");
                        Integer libroEjemplaresPrestados = leer.nextInt();
                        libro.setEjemplaresPrestados(libroEjemplaresPrestados); // Actualizar la cantidad de ejemplares prestados
                        DAO.editarLibro(libro);
                        break;
                    case 5:
                        System.out.println("Ingrese el numero de ejemplares restantes");
                        Integer libroEjemplaresRestantes = leer.nextInt();
                        libro.setEjemplaresRestantes(libroEjemplaresRestantes); // Actualizar la cantidad de ejemplares restantes
                        DAO.editarLibro(libro);
                        break;
                    case 6:
                        System.out.println("Ingrese el ID del autor que desea asignar:");     
                        AutorServicio autorServicio = new AutorServicio();
                        Autor autor = autorServicio.buscarAutorPorId(leer.nextInt());
                        libro.setAutor(autor);
                        DAO.editarLibro(libro);
                        break;
                    case 7:
                        System.out.println("Ingrese el ID del editorial que desea asignar:");
                        EditorialServicio editorialServicio = new EditorialServicio();
                        Editorial editorial = editorialServicio.buscarEditorialPorId(leer.nextInt());
                        libro.setEditorial(editorial);
                        DAO.editarLibro(libro);
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } while (opcion != 8);
            
            return libro;
        } else {
            // El libro no existe
            System.out.println("El libro no existe.");
            return null;
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }

}    
    
    public boolean eliminarLibroPorISBN(int isbn) {
        try {
            
            Libro libro = DAO.buscarLibroPorIsbn(isbn);
            DAO.eliminar(libro);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
  
  
  
}
     
    

   
    
   
    
    
