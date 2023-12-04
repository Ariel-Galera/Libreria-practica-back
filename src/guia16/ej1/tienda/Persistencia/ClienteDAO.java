/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Persistencia;

import guia16.ej1.tienda.Entidades.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ariel
 */
public class ClienteDAO {
    
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
    public void guardarCliente(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de tipo Persona de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminarCliente(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editarCliente(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        desconectar();
    }
    
     public Cliente buscarClientePorID(Integer id) throws Exception {
        conectar();
        Cliente cliente = em.find(Cliente.class, id); // "em.find no funciona con String"
         desconectar();
        return cliente;
       
    }
    
    
    
    
}
