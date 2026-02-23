/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import static com.itextpdf.text.Rectangle.NO_BORDER;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author josed
 */

//tutotial https://www.youtube.com/watch?v=iusNJYPfxcA&t=135s
public class CrearDocumento {

    Document documento;
    FileOutputStream fileOutputStream;
    
    Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12);
    Font fuenteParrafo = FontFactory.getFont(FontFactory.HELVETICA,10);
    
    Producto prodVenta = new Producto();

        
    /**
     *
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public void crearDocumento(String folioVenta) throws DocumentException, FileNotFoundException{
        documento = new Document(PageSize.A7, 20, 20, 20, 20);
        String ruta = System.getProperty("user.home");
        String directorio = "/Desktop";
        String mesAnio = "/112025";
        fileOutputStream = new FileOutputStream(ruta+directorio+mesAnio+"/"+folioVenta+".pdf");
        
        PdfWriter.getInstance(documento,fileOutputStream);
                
    }
    public void abrirDocumento(){
        documento.open();
    }

    public void agregarTitulo(String tituloText) throws DocumentException{
        PdfPTable tabla = new PdfPTable(1);        
        PdfPCell celda = new PdfPCell(new Phrase(tituloText,fuenteTitulo));
        celda.setColspan(5);
        celda.setBorderColor(BaseColor.WHITE);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
        documento.add(tabla);
    }
    public void agregarParrafo(String parrafoText) throws DocumentException{
        Paragraph parrafo = new Paragraph(); 
        parrafo.add(new Phrase(parrafoText, fuenteParrafo)); 
        parrafo.setAlignment(Element.ALIGN_CENTER);
        documento.add(parrafo) ; 
    }

    public void agregarSaltosDeLinea() throws DocumentException {
        Paragraph saltosdelinea = new Paragraph(); 
        saltosdelinea.add(new Phrase(Chunk.NEWLINE)); 
        saltosdelinea.add(new Phrase(Chunk.NEWLINE)); 
        documento.add(saltosdelinea) ;
    }
    
    public void agregarTablaVenta(List detalleProd, List detalleVentaRet) throws DocumentException{
        PdfPTable tabla = new PdfPTable(3);
        tabla.setWidthPercentage(100);
        Font fontNormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        
        String totalVenta   = String.valueOf(detalleVentaRet.get(5));
        String cambio       = String.valueOf(detalleVentaRet.get(6));
        String montoRecibido = String.valueOf(detalleVentaRet.get(7));

        tabla.getDefaultCell().setPhrase(new Phrase("Cant",fontNormal));

        
        tabla.addCell(new Phrase("Cant",fontNormal));
        tabla.addCell(new Phrase("Precio",fontNormal));
        tabla.addCell(new Phrase("Importe",fontNormal));
        tabla.completeRow();
        
        for (int i = 0; i < detalleProd.size(); i++) {
            
            prodVenta = (Producto) detalleProd.get(i);
            String cantidad = Float.toString(prodVenta.getStock());
            String descripcion = prodVenta.getDescripcion();
            String precio = Float.toString(prodVenta.getPrecioventa());
            String subtotal = Float.toString(prodVenta.getPreciocompra());

            tabla.addCell(new Phrase(cantidad,fontNormal));
            PdfPCell descCell = new PdfPCell(new Phrase(descripcion,fontNormal));
            descCell.setColspan(2);
            tabla.addCell(descCell);
            tabla.completeRow();

            tabla.addCell("");
            tabla.addCell(new Phrase(precio,fontNormal));
            tabla.addCell(new Phrase(subtotal,fontNormal)); 
            tabla.completeRow();
            
            
        }
        
        tabla.addCell("");
        tabla.addCell(new Phrase("TOTAL: ",fontNormal));
        tabla.addCell(new Phrase(totalVenta,fontNormal)); 
        tabla.completeRow();
        tabla.addCell("");
        tabla.addCell(new Phrase("EFECTIVO: ",fontNormal));
        tabla.addCell(new Phrase(montoRecibido,fontNormal)); 
        tabla.completeRow();
        tabla.addCell("");
        tabla.addCell(new Phrase("CAMBIO: ",fontNormal));
        tabla.addCell(new Phrase(cambio,fontNormal)); 
        tabla.completeRow();


        
//        tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        //tabla.getDefaultCell().setBorderWidth(0f);
 //       tabla.SetBorder(Border.NO_BORDER);
        //tabla.setWidthPercentage(100f);


        documento.add(tabla);
//        cerrarDocumento();
        
    }
    
    public void agregarTablaDatos(List detalleVentaRet) throws DocumentException{
        PdfPTable tablaDatos = new PdfPTable(2);
        tablaDatos.setWidthPercentage(100);
        Font fontNormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
/*            Producto prod = new Producto();
        
        int i = 0;
        while (i < detalleVentaRet.size()) { 
            prod = (Producto) detalleVentaRet.get(i);
               String folioVenta   = String.valueOf(prod.);
//        String folioVenta   = String.valueOf(detalleVentaRet.get(NO_BORDER)
               */
        String folioVenta   = String.valueOf(detalleVentaRet.get(0));
        String idEmpleado   = String.valueOf(detalleVentaRet.get(2));
        String fechaVenta   = String.valueOf(detalleVentaRet.get(3));
        String horaVenta    = String.valueOf(detalleVentaRet.get(4));
        
        tablaDatos.getDefaultCell().setPhrase(new Phrase("Cant",fontNormal));

        tablaDatos.addCell(new Phrase("Folio: "+folioVenta,fontNormal));
        tablaDatos.addCell(new Phrase("Le atendio: "+idEmpleado,fontNormal));
        tablaDatos.completeRow();
        
        tablaDatos.addCell(new Phrase("Fecha: "+fechaVenta,fontNormal));
        tablaDatos.addCell(new Phrase("Hora: "+horaVenta,fontNormal));
        tablaDatos.completeRow();

//            i++;
  //      }
        
//        tablaDatos.getDefaultCell().setBorder(Rectangle.NO_BORDER);
  //      tablaDatos.setWidthPercentage(0f);
          tablaDatos.getDefaultCell().setBorder(0);

        documento.add(tablaDatos);
        
    }
    
    public void agregarTablaTotales(List detalleVentaRet) throws DocumentException{
        PdfPTable tablaDatos = new PdfPTable(2);
        tablaDatos.setWidthPercentage(100);
        Font fontNormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        
        String folioVenta   = String.valueOf(detalleVentaRet.get(0));
        String idCliente    = String.valueOf(detalleVentaRet.get(1));
        String idEmpleado   = String.valueOf(detalleVentaRet.get(2));
        String fechaVenta   = String.valueOf(detalleVentaRet.get(3));
        String horaVenta    = String.valueOf(detalleVentaRet.get(4));
        String totalVenta   = String.valueOf(detalleVentaRet.get(5));
        String ConRegistros = String.valueOf(detalleVentaRet.get(6));
        
        tablaDatos.getDefaultCell().setPhrase(new Phrase("Cant",fontNormal));

        tablaDatos.addCell(new Phrase("Folio: "+folioVenta,fontNormal));
        tablaDatos.addCell(new Phrase("Le atendio: "+idEmpleado,fontNormal));
        tablaDatos.completeRow();
        
        tablaDatos.addCell(new Phrase("Fecha: "+fechaVenta,fontNormal));
        tablaDatos.addCell(new Phrase("Hora: "+horaVenta,fontNormal));
        tablaDatos.completeRow();
        
        tablaDatos.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        documento.add(tablaDatos);
        
    }

    public void cerrarDocumento(){
         documento.close();
    }  
/*    public void crarTicketVenta(String folioVenta) throws DocumentException, FileNotFoundException{
        CrearDocumento crearTicket = new CrearDocumento();
        crearTicket.crearDocumento(folioVenta);
        crearTicket.abrirDocumento();
        crearTicket.agregarTitulo("Jarcieria Limpio Hogar");
        crearTicket.agregarSaltosDeLinea();
        crearTicket.agregarParrafo("Ignacio Zaragoza N14, San Francisco Coapan");
        crearTicket.agregarSaltosDeLinea();
        crearTicket.agregarParrafo("San Pedro Cholula Puebla");
        crearTicket.agregarSaltosDeLinea();
        crearTicket.agregarParrafo("WhatsApp 222 426 0578");
        crearTicket.agregarSaltosDeLinea();
        crearTicket.agregarTablaVenta();
        crearTicket.cerrarDocumento();

    }*/
}
