package Controlador;

import Modelo.LibrosCRUD;

public class Operaciones {
    
     private static Operaciones instancia;
     
     LibrosCRUD libro = LibrosCRUD.getInstance();
     
     public void AgregarLibro(String autor, String titulo, String anoPublicacion, String numEdicion, String resumen){
         
           libro.Agregar(autor, titulo, anoPublicacion, numEdicion, resumen);
     }
     
     public void ConsultarPorAutor(String autor){
         
         libro.ConsultarPorAutor(autor);
         
     }
     
      public void ConsultarPorTitulo(String titulo){
          
          libro.ConsultarPorTitulo(titulo);
         
     }
      
       public void ActualizarLibro(String titulo, String resumen){
          
          libro.ActualizarLibro(titulo, resumen);
         
     }
       
       public void EliminarLibro(String titulo){
           
            libro.EliminarLibro(titulo);
       }
       
       public void GenerarReporte(){
           
           libro.GenerarReporte();
       }
     
     
     
    
    //Patron Sigleton
    public static Operaciones getInstance(){
        if(instancia == null){
            instancia = new Operaciones();
        }
        return instancia;
    }
    
}
