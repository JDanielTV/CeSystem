/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author josed
 */
public class crearTicket {
    
    public static void main(List detalleVentaRet, List detalleProd) throws DocumentException, FileNotFoundException{
        
        String folioVenta   = String.valueOf(detalleVentaRet.get(0));
                
        CrearDocumento crearTicket = new CrearDocumento();
        crearTicket.crearDocumento(folioVenta);
        crearTicket.abrirDocumento();
        crearTicket.agregarTitulo("Jarcieria Limpio Hogar");
        crearTicket.agregarParrafo("Ignacio Zaragoza N14");
        crearTicket.agregarParrafo("San Francisco Coapan");
        crearTicket.agregarParrafo("San Pedro Cholula Puebla");
        crearTicket.agregarParrafo("WhatsApp 222 426 0578");

        crearTicket.agregarParrafo("************ V E N T A ************");
        crearTicket.agregarSaltosDeLinea();                                        
        crearTicket.agregarTablaDatos(detalleVentaRet);
        crearTicket.agregarTablaVenta(detalleProd, detalleVentaRet);
        
        crearTicket.cerrarDocumento();
    }

}
