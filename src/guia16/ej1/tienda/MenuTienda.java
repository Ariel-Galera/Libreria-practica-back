/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda;



import guia16.ej1.tienda.Entidades.Autor;
import guia16.ej1.tienda.Entidades.Cliente;
import guia16.ej1.tienda.Entidades.Editorial;
import guia16.ej1.tienda.Entidades.Libro;
import guia16.ej1.tienda.Entidades.Prestamo;
import static guia16.ej1.tienda.Entidades.Prestamo_.fechaDevolucion;
import guia16.ej1.tienda.Servicios.AutorServicio;
import guia16.ej1.tienda.Servicios.ClienteServicio;
import guia16.ej1.tienda.Servicios.EditorialServicio;
import guia16.ej1.tienda.Servicios.LibroServicio;
import guia16.ej1.tienda.Servicios.PrestamoServicio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;




/**
 *
 * @author Ariel
 */
public class MenuTienda {
    
  
    
    private final LibroServicio libroServicio = new LibroServicio();
    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();
    private final PrestamoServicio prestamoServicio = new PrestamoServicio();
    private final ClienteServicio clienteServicio = new ClienteServicio();
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public MenuTienda (){
        libroServicio.setServicio(editorialServicio, autorServicio);
        autorServicio.setServicio(editorialServicio);
        editorialServicio.setServicio(libroServicio, autorServicio);
        prestamoServicio.setServicio(libroServicio, clienteServicio);
        clienteServicio.setServicio(prestamoServicio);
    } 
    
   
      Libro libro; 
      Autor autor;
      Cliente cliente;
      Editorial editorial;
     List <Prestamo>listaPrestamos = new ArrayList<>();
     List <Libro> listaLibros = new ArrayList<>();
     List<Autor>listaAutores = new ArrayList<>();
     List<Editorial>listaEditoriales = new ArrayList<>();
    
    public void mostrarMenu(){
        int opcion;
       
        do {   
            System.out.println("******** MENU LIBRERIA *********");
            System.out.println("1.Agregar/Crear un libro");
            System.out.println("2.Agregar/Crear un autor");
            System.out.println("3.Agregar/Crear un editorial");
            System.out.println("4.Editar un libro (Busqueda por isbn)");
            System.out.println("5.Editar un autor (Busqueda por id)");
            System.out.println("6.Editar un editorial (Busqueda por id)");
            System.out.println("7.Ver la lista de todos los libros de la tienda");
            System.out.println("8.Ver la lista de todos los autores");
            System.out.println("9.Ver la lista de editoriales");
            System.out.println("10.Buscar un libro por isbn");
            System.out.println("11.Buscar libro por titulo");
            System.out.println("12.Buscar libro por nombre de autor");
            System.out.println("13.Buscar libro por editorial");
            System.out.println("14.Eliminar un libro (ISBN)");
            System.out.println("15.Agregar/Crear un cliente");
            System.out.println("16.Buscar un cliente por ID");
            System.out.println("17.Registrar prestamo de un libro");
            System.out.println("18.Registrar la devolucion de un libro");
            System.out.println("19.Busqueda de todos los prestamos de un cliente");
             System.out.println("20.Salir");
            System.out.println("*******************************");
            
           
                
           
            
              opcion = leer.nextInt();
             leer.hasNextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                     libro = libroServicio.crearLibro();
                     listaLibros.add(libro);
                     break;
                case 2:
                    autor = autorServicio.crearAutor();
                    listaAutores.add(autor);
                 break;
                 
                case 3: 
                    editorial = editorialServicio.crearEditorial();
                    listaEditoriales.add(editorial);
                break;
                case 4:
                    System.out.println("Editar libro:");
                    libroServicio.editarLibro();
                     System.out.println("************************************************************");
                   
                   
                 break;
                    
                case 5:
                    System.out.println("Editar autor:");
                    autorServicio.editarAutor();
                    System.out.println("************************************************************");
                  
                    
                break;
                
                case 6:
                    System.out.println("Editar editorial:");
                    editorialServicio.editarEditorial();
                    System.out.println("*************************************************************");
                    
                break;
                
                case 7: 
                     //Mostrar todos los libros de la lista libros
                System.out.println("Lista de todos los libros de la base de datos de la Libreria");
                System.out.println("-------------------------------------------------------------");
                libroServicio.listarLibros().forEach((a) -> System.out.println(a.toString()));
                System.out.println("************************************************************");
                System.out.println("");
                 break;
                 
                case 8:
                System.out.println("Lista de todos los autores");
                System.out.println("-----------------------------------------------------");
                autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
                System.out.println("*******************************************************");
                System.out.println("");
                break;
                
                case 9:
                System.out.println("Lista de todos los editoriales");    
                System.out.println("------------------------------------------------");
                editorialServicio.listarEditoriales().forEach((a) -> System.out.println(a.toString()));
                System.out.println("");
                 break;
                 
                case 10:
                     //Buscar y ver un elemento libro de la base de datos. 
                System.out.println("Ingrese el isbn del libro que desea buscar");
                int libroISBN = leer.nextInt();
                System.out.println("El libro que se busco por isbn es:");
                System.out.println(libroServicio.buscarLibroPorISBN(libroISBN));
                System.out.println("************************************************************");
                    break;
                    
                case 11:
                System.out.println("Ingrese el nombre del libro que desea buscar");
                String libroNombre = leer.next();
                Libro libroEncontrado = libroServicio.buscarLibroPorTitulo(libroNombre);
                if (libroEncontrado != null) {
                    System.out.println("Libro encontrado: " + libroEncontrado);
                } else {
                    System.out.println("Libro no encontrado.");
                }
                System.out.println("********************************************************");
               
                    break;
                    
                case 12:    
                System.out.println("Ingrese el nombre del autor del libro que desea buscar:");
                String libroAutor = leer.next();
                System.out.println("Libro buscado por autor:");
                List<Libro> librosEncontradosxAutor = libroServicio.buscarLibrosPorNombreAutor(libroAutor);
                if (librosEncontradosxAutor != null && !librosEncontradosxAutor.isEmpty()) {
                for (Libro libro : librosEncontradosxAutor) {
                    System.out.println(libro.getTitulo());
                }
                } else {
                System.out.println("No se encontraron libros para el autor especificado.");
                }
                System.out.println("*********************************************************");
                    break;
                case 13:
                System.out.println("Ingrese el nombre del editorial del libro que desea buscar:");
                String libroEditorial = leer.next();
                System.out.println("Libro buscado por editorial:");  
                List<Libro>librosEncontradosxEditorial = libroServicio.buscarLibrosPorEditorial(libroEditorial);
                if (librosEncontradosxEditorial !=null && !librosEncontradosxEditorial.isEmpty()) {
                    for (Libro libro : librosEncontradosxEditorial) {
                        System.out.println(libro.getTitulo());
                    }
                }else{
                    System.out.println("No se encontraron libros con ese editorial.");
                }
                    System.out.println("***********************************************************");
                    
                     break;
                case 14:
                     System.out.println("Ingrese el ISBN del libro que desea eliminar:");
                    int libroEliminar = leer.nextInt();
                    libroServicio.eliminarLibroPorISBN(libroEliminar);
                    System.out.println("Libro eliminado");
                    break;
                    
                case 15:
                     cliente = clienteServicio.crearCliente();
                  
                    break;
                    
                case 16:
                     System.out.println("Ingrese el id del cliente que desea buscar");
                Integer clienteID = leer.nextInt();
                System.out.println("El cliente que se busco por id es:");
                System.out.println(clienteServicio.buscarClientePorID(clienteID));
                System.out.println("************************************************************");
                    
                    
                    break;
                    
                case 17:
                          System.out.println("Registrar préstamo de un libro:");
                        Prestamo prestamo = new Prestamo();

                        System.out.println("Ingrese el isbn del libro a prestar:");
                        int libroPrestamo = leer.nextInt();
                        prestamo.setLibro(libroServicio.buscarLibroPorISBN(libroPrestamo));

                        System.out.println("Ingrese el ID del cliente que solicita el préstamo:");
                        Integer idClientePrestamo = leer.nextInt();
                        prestamo.setCliente(clienteServicio.buscarClientePorID(idClientePrestamo));

                        prestamo.setFechaPrestamo(new Date()); // Usar la fecha actual

                        // Solicitar y asignar la fecha de devolución
                        System.out.println("Ingrese la fecha de devolución (en formato dd/MM/yyyy):");
                        String fechaDevolucionStr = leer.next();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date fechaDevolucion = dateFormat.parse(fechaDevolucionStr);
                            prestamo.setFechaDevolucion(fechaDevolucion);
                        } catch (ParseException e) {
                            System.out.println("Error al parsear la fecha de devolución. Verifique el formato (dd/MM/yyyy).");
                            break; // Salimos del caso 17 si hay un error con la fecha
                        }

                        // Registramos el préstamo
                        prestamoServicio.guardarPrestamo(prestamo);
                        System.out.println("Préstamo registrado con éxito.");  
           
                    break;
                    
                    
                    
                case 18:
                      System.out.println("Devolución de un libro:");
                System.out.println("Ingrese el ID del cliente:");
                int clienteIDPrestamoDevolucion = leer.nextInt();
                List<Prestamo> prestamosClienteD = prestamoServicio.buscarPrestamosPorCliente(clienteIDPrestamoDevolucion);

                if (prestamosClienteD.isEmpty()) {
                    System.out.println("El cliente no tiene préstamos registrados.");
                } else {
                    System.out.println("Préstamos del cliente:");
                    for (Prestamo p : prestamosClienteD) {
                        System.out.println(p.toString());
                    }

                    System.out.println("Ingrese el ID del préstamo que desea devolver:");
                    int idPrestamoDevolver = leer.nextInt();

                    // Buscar el préstamo seleccionado
                    Prestamo prestamoDevolver = prestamoServicio.buscarPrestamoPorID(idPrestamoDevolver);

                    if (prestamoDevolver != null) {
                        // Asignar la fecha de devolución actual
                        prestamoDevolver.setFechaDevolucion(new Date());

                        // Realizar la devolución del libro
                        prestamoServicio.devolverLibro(prestamoDevolver);

                        System.out.println("Devolución realizada con éxito.");
                    } else {
                        System.out.println("El préstamo con el ID proporcionado no fue encontrado.");
                    }
                }
                    break;
                    
                   case 19:
                     System.out.println("Buscar todos los préstamos de un cliente:");
                    System.out.println("Ingrese el ID del cliente:");
                    int clienteIDPrestamo = leer.nextInt();
                    List<Prestamo> prestamosCliente = prestamoServicio.buscarPrestamosPorCliente(clienteIDPrestamo);
                    if (prestamosCliente.isEmpty()) {
                        System.out.println("El cliente no tiene préstamos registrados.");
                    } else {
                    System.out.println("Préstamos del cliente:");
                    for (Prestamo p : prestamosCliente) {
                    System.out.println(p.toString());
                }
            }
                    
                    
                    break;
                    
                default:
                    System.out.println("Saliendo del programa...");
            }
             
             
        } while (opcion != 20);
    }      
                  
                    
               
        
        
        
        
    
    
    
    /*public void ejecucion(){
        
        /*Boolean altaAutor1 = true;
        String nombreAutor1 = "Carlos";
        
          Boolean altaAutor2 = true;
        String nombreAutor2 = "Merlo";
        
        
        Boolean altaEditorial1 = true;
        String nombreEditorial1= "Capeluz";
        
        
         Boolean altaEditorial2 = true;
        String nombreEditorial2= "Planeta";
        
        
     String tituloLibro1 = "Jurassic Park";
     Integer anioLibro1 = 1990;
     Integer ejemplaresLibro1 = 20;
     Integer ejemplaresPrestadosLibro1 = 10;
     Integer ejemplaresRestantesLibro1 = 10;
     
     
     
     
     String tituloLibro2 = "El Resplandor";
     Integer anioLibro2 = 1980;
     Integer ejemplaresLibro2 = 40;
     Integer ejemplaresPrestadosLibro2 = 20;
     Integer ejemplaresRestantesLibro2 = 20;*/
        
        //Autor a1 = autorServicio.crearAutor(nombreAutor1, altaAutor1);
        //Autor a2 = autorServicio.crearAutor(nombreAutor2, altaAutor2);
        
        //Editorial e1 = editorialServicio.crearEditorial(altaEditorial1, nombreEditorial1);
        //Editorial e2 = editorialServicio.crearEditorial(altaEditorial2, nombreEditorial2);
        
        
        
       
        
        //Libro l1 = libroServicio.crearLibro(tituloLibro1, anioLibro1, ejemplaresLibro1, ejemplaresPrestadosLibro1, ejemplaresRestantesLibro1, a1, e1);
        //Libro l2 = libroServicio.crearLibro(tituloLibro2, anioLibro2, ejemplaresLibro2, ejemplaresPrestadosLibro2, ejemplaresRestantesLibro2, a2, e2);
        
        //List <Libro> listaLibros = new ArrayList<>();
        //listaLibros.add(l1);
        //listaLibros.add(l2);
        
         
        //System.out.println("Los libros que tienen el titulo pedido son:");
        //System.out.println(libroServicio.buscarLibroxTitulo("El Resplandor"));
      
       
         
 }       
       
        
     
           
        
           
        
        
        
      
        
      
        
        
         
    
    
      
    
    
    
    
    

