/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia16.ej1.tienda.Servicios;

import guia16.ej1.tienda.Entidades.Cliente;
import guia16.ej1.tienda.Persistencia.ClienteDAO;
import java.util.Scanner;

/**
 *
 * @author Ariel
 */
public class ClienteServicio {
    
     private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
     private PrestamoServicio prestamoServicio;
     private final ClienteDAO DAO;

    public ClienteServicio() {
        this.DAO = new ClienteDAO();
    }

    public void setServicio(PrestamoServicio prestamoServicio) {
        this.prestamoServicio = prestamoServicio;
         
    }
     
    
     public Cliente crearCliente (){
        
        Cliente cliente = new Cliente();
        try {
            
            System.out.println("Ingrese el documento del cliente:");
            cliente.setDocumento(leer.nextLong());
            System.out.println("Ingrese el nombre del cliente");
            cliente.setNombre(leer.next());
            System.out.println("Ingrese el apellido del cliente");
            cliente.setApellido(leer.next());
            System.out.println("Ingrese el telefono del cliente");
            cliente.setTelefono(leer.nextInt());
             DAO.guardarCliente(cliente);
            
              return cliente;
                   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
          
           
         public Cliente buscarClientePorID (Integer id){
        
        try {
            return DAO.buscarClientePorID(id);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    } 
     
    
   
    
}
