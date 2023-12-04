/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Servicios;

import guia16.ej1.tienda.Entidades.Prestamo;
import guia16.ej1.tienda.Persistencia.PrestamoDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ariel
 */
public class PrestamoServicio {
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    private LibroServicio libroServicio;
    private ClienteServicio clienteServicio;
    private final PrestamoDAO DAO;

    public PrestamoServicio() {
        this.DAO = new PrestamoDAO();
    }

    public void setServicio(LibroServicio libroServicio, ClienteServicio clienteServicio) {
        this.libroServicio = libroServicio;
        this.clienteServicio = clienteServicio;
      
    }
    
     public void guardarPrestamo(Prestamo prestamo) {
        DAO.guardarPrestamo(prestamo);
    }

    public void devolverLibro(Prestamo prestamo) {
        // Aquí puedes modificar las propiedades del préstamo para reflejar la devolución
        DAO.devolverLibro(prestamo);
    }

    public List<Prestamo> buscarPrestamosPorCliente(Integer clienteID) {
        return DAO.buscarPrestamosPorCliente(clienteID);
    }
    
     public Prestamo buscarPrestamoPorID (Integer id){
        
        try {
            return DAO.buscarPrestamoPorID(id);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    
}
