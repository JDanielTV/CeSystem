/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author josed
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class TicketPrinter {

//    public static void main(String[] args) {
    public String ticketPrinter(){
        // Obtener la lista de impresoras
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        if (printServices.length > 0) {
            // Seleccionar la primera impresora (puedes personalizar la selección)
            PrintService printService = printServices[0];

            // Crear el objeto PrintJob
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            try {
                printerJob.setPrintService(printService);
            } catch (PrinterException e) {
                System.err.println("Error al configurar la impresora: " + e.getMessage());
 //               return;
            }

            // Definir el contenido a imprimir (Printable)
            Printable printable = new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                    }

                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                    // Aquí puedes dibujar el contenido del ticket
                    g2d.drawString("Contenido del ticket...", 10, 20);
                    g2d.drawString("Otro texto...", 10, 40);

                    return PAGE_EXISTS;
                }
            };

            // Establecer el Printable y imprimir
            printerJob.setPrintable(printable);
            try {
                printerJob.print();
            } catch (PrinterException e) {
                System.err.println("Error al imprimir: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontraron impresoras conectadas.");
        }
        return null;
    }
}