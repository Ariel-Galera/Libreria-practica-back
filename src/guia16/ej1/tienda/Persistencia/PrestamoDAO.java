/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Persistencia;

import guia16.ej1.tienda.Entidades.Prestamo;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ariel
 */
public class PrestamoDAO {
    
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
    public void guardarCliente(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de tipo Persona de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminarCliente(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.remove(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editarCliente(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.merge(prestamo);
        em.getTransaction().commit();
        desconectar();
    }
    
     // Método para registrar el préstamo de un libro
    public void guardarPrestamo(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    // Método para devolver un libro
   
    public void devolverLibro(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        prestamo.setFechaDevolucion(new Date()); // Asignar la fecha de devolución actual
        em.merge(prestamo);
        em.getTransaction().commit();
        desconectar();
    }
    
    
    
    
    // Método para buscar todos los préstamos de un cliente por su ID
    public List<Prestamo> buscarPrestamosPorCliente(Integer clienteID) {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.id = :clienteID", Prestamo.class)
                .setParameter("clienteID", clienteID)
                .getResultList();
        desconectar();
        return prestamos;
    }
    
     public Prestamo buscarPrestamoPorID(Integer id) throws Exception {
        conectar();
        Prestamo prestamo = em.find(Prestamo.class, id); // "em.find no funciona con String"
         desconectar();
        return prestamo;
       
    }
    
}
