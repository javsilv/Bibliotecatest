package Modelo;

import Modelo.Conexion;
import Modelo.Libros;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LibrosCRUD {

    private static LibrosCRUD instancia;

    Libros book = Libros.getInstance();

    public void Agregar(String autor, String titulo, String anoPublicacion, String numEdicion, String resumen) {

        book.setAutor(autor);
        book.setTitulo(titulo);
        book.setAnoPublicacion(anoPublicacion);
        book.setNumEdicion(numEdicion);
        book.setResumen(resumen);

        try {

            Conexion conectar = Conexion.getInstance();
            Statement s = null;
            s = conectar.conectar().createStatement();

            s.execute("insert into libros values('" + book.getAutor() + "','" + book.getTitulo() + "','" + book.getAnoPublicacion() + "','" + book.getNumEdicion() + "','" + book.getResumen() + "');");

            System.out.println("SE HA INSERTADO CORRECTAMENTE EL LIBRO :) ");

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EliminarLibro(String titulo) {

        book.setTitulo(titulo);

        try {

            Conexion conectar = Conexion.getInstance();

            Statement s = null;
            ResultSet resul;
            s = conectar.conectar().createStatement();

            resul = s.executeQuery("select * from libros where titulo = '" + book.getTitulo() + "';");

            if (resul.next() == true) {
                s.execute("delete from libros where titulo = '" + book.getTitulo() + "';");
                System.out.println("-- ¡SE HA ELIMINADO EL LIBRO CORRECTAMENTE! --");

            } else {
                System.out.println("NO EXISTE EL LIBRO: " + book.getTitulo() + " EN NUESTRA BD.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ConsultarPorAutor(String autor) {

        book.setAutor(autor);

        try {

            Conexion conectar = Conexion.getInstance();

            Statement s = null;
            ResultSet resul;
            s = conectar.conectar().createStatement();

            resul = s.executeQuery("select * from libros where autor = '" + book.getAutor() + "';");

            if (resul.next() == true) {

                resul = s.executeQuery("select * from libros where autor = '" + book.getAutor() + "';");
                System.out.println("AUTOR  | TITULO  | AÑO PUBLICACION  | NO. EDICION  |  RESUMEN");

                while (resul.next()) {
                    System.out.println(resul.getString("autor") + " | " + resul.getString("titulo") + " | " + resul.getString("anopublicacion") + " | " + resul.getString("numedicion") + " | " + resul.getString("resumen"));
                }
                System.out.println("------------------------------------------------------------------");

            } else {
                System.out.println("NO SE ENCONTRO LIBROS CON ESTE NOMBRE DE AUTOR");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ConsultarPorTitulo(String titulo) {

        book.setTitulo(titulo);

        try {

            Conexion conectar = Conexion.getInstance();

            Statement s = null;
            ResultSet resul;
            s = conectar.conectar().createStatement();

            resul = s.executeQuery("select * from libros where titulo = '" + book.getTitulo() + "';");

            if (resul.next() == true) {

                resul = s.executeQuery("select * from libros where titulo = '" + book.getTitulo() + "';");
                System.out.println("AUTOR  | TITULO  | AÑO PUBLICACION  | NO. EDICION  |  RESUMEN");

                while (resul.next()) {
                    System.out.println(resul.getString("autor") + " | " + resul.getString("titulo") + " | " + resul.getString("anopublicacion") + " | " + resul.getString("numedicion") + " | " + resul.getString("resumen"));
                }
                System.out.println("------------------------------------------------------------------");

            } else {
                System.out.println("NO SE ENCONTRO LIBROS CON ESTE TITULO");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ActualizarLibro(String titulo, String resumen) {

        book.setTitulo(titulo);
        book.setResumen(resumen);

        try {

            Conexion conectar = Conexion.getInstance();
            Statement s = null;
            ResultSet resul;
            s = conectar.conectar().createStatement();

            resul = s.executeQuery("select * from libros where titulo = '" + book.getTitulo() + "';");

            if (resul.next() == true) {

                s.execute("update libros set resumen='" + book.getResumen() + "' where titulo='" + book.getTitulo() + "';");

                System.out.println("-- ¡ACTUALIZACION DE RESUMEN CORRECTAMENTE! --");

            } else {
                System.out.println("NO EXISTE EL LIBRO: " + book.getTitulo() + " EN NUESTRA BD.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void GenerarReporte(){
        
         Workbook book = new XSSFWorkbook();

        Sheet sheet = book.createSheet("Libros");

        try {
            InputStream is = new FileInputStream("src/img/logo.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Libros");

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"Autor", "Titulo", "Año Publicacion", "No. Edicion", "Resumen"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

            Conexion conectar = Conexion.getInstance();
            Connection conexion = conectar.conectar();
            ResultSet rs;
      
            int numFilaDatos = 5;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            PreparedStatement ps = conexion.prepareStatement("SELECT autor, titulo, anopublicacion, numedicion, resumen FROM libros");
            rs = ps.executeQuery();
            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }

                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            sheet.setZoom(150);
            String fileName = "libros";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            System.out.println("¡Reporte generado exitosamente!");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibrosCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LibrosCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//Patron Sigleton
    public static LibrosCRUD getInstance() {
        if (instancia == null) {
            instancia = new LibrosCRUD();
        }
        return instancia;
    }

}
