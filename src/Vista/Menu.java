package Vista;

import Controlador.Operaciones;
import java.util.Scanner;

public class Menu {

    public static Scanner teclado = new Scanner(System.in);

    static Operaciones operacion = Operaciones.getInstance();

    public static void main(String[] args) {

        String saveAutor, saveTitulo, saveAnoPublicacion, saveNumEdicion, saveResumen;

        System.out.println("Digite la operacion a realizar:");
        System.out.println("1.- Agregar un libro nuevo");
        System.out.println("2.- Buscar libro");
        System.out.println("3.- Actualizar resumen de un libro");
        System.out.println("4.- Eliminar libro");
        System.out.println("5.- Generar Reporte");
        System.out.print("Respuesta:");
        int respueta = teclado.nextInt();
        System.out.println("-----------------------------");

        switch (respueta) {
            case 1:

                System.out.println("--DATOS PARA AGREGAR UN NUEVO LIBRO--");
                System.out.print("* NOMBRE DEL AUTOR:");
                saveAutor = teclado.next();
                System.out.print("* NOMBRE DEL TITULO:");
                saveTitulo = teclado.next();
                System.out.print("* AÑO DE PUBLICACION:");
                saveAnoPublicacion = teclado.next();
                System.out.print("* NUMERO DE EDICION:");
                saveNumEdicion = teclado.next();
                System.out.print("* DESCRIBE UN RESUMEN:");
                saveResumen = teclado.next();

                operacion.AgregarLibro(saveAutor, saveTitulo, saveAnoPublicacion, saveNumEdicion, saveResumen);
                break;

            case 2:

                System.out.println("--DESEAS BUSCAR EL LIBRO POR AUTOR O TITULO? --");
                System.out.println("1.- BUSQUEDA POR AUTOR");
                System.out.println("2.- BUSQUEDA POR TITULO");
                System.out.print("Respuesta:");
                int resp = teclado.nextInt();

                if (resp == 1) {
                    System.out.print("* NOMBRE DEL AUTOR:");
                    saveAutor = teclado.next();

                    operacion.ConsultarPorAutor(saveAutor);
                } else {
                    System.out.print("* NOMBRE DEL TITULO:");
                    saveTitulo = teclado.next();

                    operacion.ConsultarPorTitulo(saveTitulo);
                }
                break;

            case 3:

                System.out.println("-- ESCRIBE EL TITULO QUE DESEAS EDITAR SU RESUMEN --");
                saveTitulo = teclado.next();
                System.out.println("-- ESCRIBE NUEVO RESUMEN DE ESTE TITULO --");
                saveResumen = teclado.next();

                operacion.ActualizarLibro(saveTitulo, saveResumen);
                break;

            case 4:

                System.out.println("-- ESCRIBE EL LIBRO QUE DESEAS ELIMINAR --");
                saveTitulo = teclado.next();
                
                operacion.EliminarLibro(saveTitulo);
                break;
                
            case 5:
                
                operacion.GenerarReporte();
                break;
                
            default:
                System.out.println("ERROR, ELIJA OTRA OPCION.");
        }

    }

}
