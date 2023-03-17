package Modelo;

public class Libros {
    
    private static Libros instancia;
    
    private int id;
    private String autor;
    private String anoPublicacion;
    private String Titulo;
    private String numEdicion;
    private String resumen;
    
    public Libros(){
        
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(String anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getNumEdicion() {
        return numEdicion;
    }

    public void setNumEdicion(String numEdicion) {
        this.numEdicion = numEdicion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    
    //Patron Sigleton
    public static Libros getInstance(){
        if(instancia == null){
            instancia = new Libros();
        }
        return instancia;
    }
}
