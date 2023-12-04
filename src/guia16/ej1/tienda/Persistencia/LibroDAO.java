/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Persistencia;


import guia16.ej1.tienda.Entidades.Libro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ariel
 */
public class LibroDAO {
    
     private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("Guia16-Ej1-TiendaPU");
    private EntityManager em = EMF.createEntityManager();
    
      // Este método nos permite conectar con la base de datos
    // se verifica si la conexión está realizada, en caso que
    // no esté realizada, se realiza.
    public void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }
    
     // Este método nos permite desconectarnos de la base de datos
    // Se verifica si existe una conexión, y de ser el caso, se
    // cierra la conexión y se desconecta el programa con la base de datos.
    public void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }

    // este método nos permite persistir un objeto Persona en la base de datos.
    // Toma como parámetro el objeto de tipo Direccion que se quiere persistir
    public void guardarLibro(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de tipo Persona de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.remove(libro);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editarLibro(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
        desconectar();
    }
    
    //  9) Búsqueda de un libro por ISBN.
     public Libro buscarLibroPorIsbn(int isbn) throws Exception {
        conectar();
        Libro libro = em.find(Libro.class, isbn); // "em.find no funciona con String"
         desconectar();
        return libro;
       
    }
    
    
      //   10) Búsqueda de un libro por Título. 
       public Libro buscarLibroPorTitulo(String titulo) throws Exception {
        conectar();
        Libro libro = null;
        try {
            libro = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class)
                    .setParameter("titulo", "%" + titulo + "%").getSingleResult();
        } finally {
            desconectar();
        }
        return libro;
    }
       
       //  11) Búsqueda de un libro/s por nombre de Autor.
       
        public List<Libro> BuscarLibrosXNombreAutor(String autor) throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre = :autor")
         .setParameter("autor", autor).getResultList();
        desconectar();
        return libros;
    }
       
        //  12) Búsqueda de un libro/s por nombre de Editorial.
        
        public List<Libro> buscarLibrosPorNombreEditorial(String editorial) throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre = :editorial")
            .setParameter("editorial", editorial).getResultList();
            desconectar();
        return libros;
      
}

        
             
         public List<Libro> listarTodosLosLibros() {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l")
                .getResultList();
        desconectar();
        return libros;
    }
    

}    
        
        
   
        
       
 
    
    
    
    

