package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.NuevaVentaDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.CrearDocumento;
import Modelo.ProductoErrorId;
import Modelo.Proovedor;
import Modelo.ProovedorDAO;
import Modelo.Venta;
import Modelo.VentasDAO;
import Modelo.crearTicket;
import com.itextpdf.text.DocumentException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    int ide;

    Producto prod = new Producto();
    ProductoDAO proddao = new ProductoDAO();
    String idp;

    String indicio, IdProducto;

    List listarMas;
    //Variables agregar:
    List detalleProd;
    List proderrId;

    //Variables NuevaVenta:
    String idBusc;
    String idElim;
    float totalVenta;
    int idEmpleado;
    String idVenta;
    NuevaVentaDAO nvdao = new NuevaVentaDAO();
    String idCliente;

    String actCantidad;
    boolean variablesLimpias;
    boolean draftFlag = false;

    String txtID;

    //// imprimir
//    PrinterMatrix printer = new PrinterMatrix();
    //Ventas
    Venta ven = new Venta();
    VentasDAO vendao = new VentasDAO();
    
    Proovedor proov = new Proovedor();
    ProovedorDAO proovdao = new ProovedorDAO();
    
    // Productos
    List listaProductos;
    List listaProovedores;
    String nuevoIdDispo;

    
    Cliente cliente = new Cliente();
    ClienteDAO clientedao = new ClienteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        String eKC = request.getParameter("eKC");

        ProductoErrorId proderrId = new ProductoErrorId();
        String errorId = null;

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
            request.getRequestDispatcher("Inicio.jsp").forward(request, response);
        }
        if (menu.equals("Inicio")) {
            request.getRequestDispatcher("Inicio.jsp").forward(request, response);
        }

        if (menu.equals("NuevaVenta")) {
            if (accion.equals("Buscar") || accion.equals("Agregar") ) {
                txtID = request.getParameter("txtID");
                if (txtID.matches("[A-Za-z]+")) { // Verifica si son solo letras
                    accion = "Buscar";
                } else {
                    accion = "Agregar";
                }
            }
            if (accion.equals("AgregarB")){
                accion = "Agregar";
                txtID = null;
            }

            switch (accion) {
                case "Nueva":
                    if (draftFlag) {
                        request.setAttribute("detalleProd", detalleProd);
                        request.setAttribute("totalVenta", totalVenta);
                        request.setAttribute("errorId", errorId);
                        request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);

                    } else {
                        variablesLimpias = proddao.limpiarVariables();
                        detalleProd = null;
                        totalVenta = 0;
                        errorId = null;
                        request.setAttribute("detalleProd", detalleProd);
                        request.setAttribute("totalVenta", totalVenta);
                        request.setAttribute("errorId", errorId);

                        request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    }
                    break;
                case "Limpiar":
                    variablesLimpias = proddao.limpiarVariables();
                    detalleProd = null;
                    totalVenta = 0;
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);

                    break;
//////////texto
                case "Buscar":
//                    indicio = request.getParameter("txtID");
                    indicio = txtID;
                    List lista = proddao.buscar(indicio);
                    int numRec = lista.size();
                    request.setAttribute("productos", lista);
                    request.setAttribute("detalleProd", detalleProd);
                    request.setAttribute("totalVenta", totalVenta);
                    request.setAttribute("numRec", numRec);
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    break;
/////////////numerico
                case "Agregar":
//                    IdProducto = request.getParameter("txtID");
                    IdProducto = txtID;
                    if (IdProducto == null) {
                        IdProducto = request.getParameter("idBusc");
                    }
//                    ProductoErrorId proderrId = new ProductoErrorId();

                    proderrId = (ProductoErrorId) proddao.detalleProd(IdProducto, "");
                    detalleProd = (List) proderrId.getAgregarProd();
                    errorId = (String) proderrId.getErrorId();
                    totalVenta = proddao.calcularTotal(detalleProd);

                    request.setAttribute("detalleProd", detalleProd);
                    request.setAttribute("totalVenta", totalVenta);
                    request.setAttribute("errorId", errorId);
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    draftFlag = true;
                    break;
                case "Add":

                    proderrId = (ProductoErrorId) proddao.detalleProd(IdProducto, "");
                    detalleProd = (List) proderrId.getAgregarProd();
                    errorId = (String) proderrId.getErrorId();

                    idBusc = request.getParameter("idBusc");
                    String error2 = "";
//                    detalleProd = proddao.detalleProd(idBusc);

                    request.setAttribute("detalleProd", detalleProd);
                    totalVenta = proddao.calcularTotal(detalleProd);
                    request.setAttribute("totalVenta", totalVenta);
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    idElim = request.getParameter("idElim");
                    detalleProd = proddao.eliminarProd(idElim);
                    request.setAttribute("detalleProd", detalleProd);
                    totalVenta = proddao.calcularTotal(detalleProd);
                    request.setAttribute("totalVenta", totalVenta);
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    break;
                case "Minus":
                    IdProducto = request.getParameter("idProdM");
                    actCantidad = request.getParameter("actCantidad");
                    proderrId = (ProductoErrorId) proddao.detalleProd(IdProducto, actCantidad);
                    request.setAttribute("detalleProd", detalleProd);
                    totalVenta = proddao.calcularTotal(detalleProd);
                    request.setAttribute("totalVenta", totalVenta);
                    errorId = (String) proderrId.getErrorId();
                    request.setAttribute("errorId", errorId);
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    break;

                case "Plus":
                    IdProducto = request.getParameter("idProdP");
                    actCantidad = request.getParameter("actCantidad");
                    proderrId = (ProductoErrorId) proddao.detalleProd(IdProducto, actCantidad);
                    request.setAttribute("detalleProd", detalleProd);
                    totalVenta = proddao.calcularTotal(detalleProd);
                    request.setAttribute("totalVenta", totalVenta);
                    errorId = (String) proderrId.getErrorId();
                    request.setAttribute("errorId", errorId);
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    break;

                case "Pagar":
//                    request.getRequestDispatcher("ConfirmarVenta.jsp").forward(request, response);

                    idVenta = nvdao.obtenerIDVenta();
//                    String idEmpleadoS =request.getParameter("idEmpleado");
                    //                  idEmpleado = Integer.parseInt(idEmpleadoS);
                    //                  idEmpleado = edao.obtenerIdEmp();
                    idCliente = request.getParameter("idCliente");
                    idEmpleado = EmpleadoDAO.idEmpleadoAct;

                    List detallesVentaRetorno = nvdao.agregarVenta(idVenta, idCliente, idEmpleado, totalVenta, detalleProd);
                    String montoRecibido = request.getParameter("montoRecibido");
                    String cambio = request.getParameter("cambio");
                    detallesVentaRetorno.add(montoRecibido);
                    detallesVentaRetorno.add(cambio);

//                    crearTicket.main(detallesVentaRetorno, detalleProd);

                    variablesLimpias = proddao.limpiarVariables();
                    detalleProd = null;
                    totalVenta = 0;
                    errorId = null;
                    draftFlag = false;

//                    TicketPrinter ticket = new TicketPrinter();
                    //                  String printer = ticket.ticketPrinter();
//                    CrearTicket ct = new CrearTicket();
                    //                  String equis = ct.crearTicket();
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);

                    break;

            }
        }

        if (menu.equals("Productos")) {
            switch (accion) {
                case "Listar":
                    listaProductos = proddao.listar();
                    listaProovedores = proovdao.listar();
//                    String nuevoIDProd = proddao.nuevoIDProd();

                    request.setAttribute("productos", listaProductos);
                    request.setAttribute("proovedores", listaProovedores);
  //                  request.setAttribute("nuevoIDProd", nuevoIDProd);

                    break;

                case "Agregar":
//                    String IdProd = request.getParameter("txtID");
                    String Desc = request.getParameter("txtDescripcion");
                    String DescCorta = request.getParameter("txtDescripcionCorta");
                    String Marca = request.getParameter("txtMarca");
                    String Proov = request.getParameter("proovedoresDD");
                    float Pcom = parseFloat(request.getParameter("txtPcompra"));
                    float Pven = parseFloat(request.getParameter("txtPventa"));
                    float Stock = parseFloat(request.getParameter("txtStock"));
//                    float Stockp = parseFloat(request.getParameter("txtStockpiso"));
                    String Prese = request.getParameter("presentacionDD");
                    String StockMin = request.getParameter("txtStockMin");
                    String ImageNombre = request.getParameter("imagen");
                    
//                    Part part = request.getPart("imagen");
  //                  InputStream inputStream = part.getInputStream();
                    
                    prod.setIdproducto(nuevoIdDispo);
                    prod.setDescripcion(Desc);
                    prod.setDescripcionc(DescCorta);
                    prod.setMarca(Marca);
                    prod.setProovedor(Proov);
                    prod.setPreciocompra(Pcom);
                    prod.setPrecioventa(Pven);
                    prod.setStock(Stock);
                    prod.setPresentacion(Prese);
                    prod.setStockMin(StockMin); 
//                    prod.setImagen(inputStream);
//                    prod.setStockpiso(Stockp);
                    
                    boolean RespuestaCargar;
                    if(ImageNombre != null){
                        RespuestaCargar = proddao.CargarImagen(ImageNombre,nuevoIdDispo);
                        if(RespuestaCargar){
                            prod.setNombreImagen(nuevoIdDispo);
                        }
                    }
                    proddao.agregar(prod);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                     break;

                case "Editar":
                    idp = request.getParameter("id");
                    Producto p = proddao.listarId(idp);
                    request.setAttribute("producto", p);
                    request.setAttribute("banderaEditar", "1");
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
//                    String AProov = request.getParameter("txtProovedor");
                    String AProov = request.getParameter("proovedoresDD");
                    String Descr = request.getParameter("txtDescripcion");
                    String DescrC = request.getParameter("txtDescripcionCorta");
                    String APven = request.getParameter("txtPventa");
                    String APcomp = request.getParameter("txtPcompra");
                    String AStock = request.getParameter("txtStock");
                    
                    if(!AProov.equals(null)){
                        prod.setProovedor(AProov);
                    }
                    
                    if(!Descr.equals(null)){
                        prod.setDescripcion(Descr);
                    }
                    
                    if(!DescrC.equals(null)){
                        prod.setDescripcionc(DescrC);
                    }
                    
                    if(!APven.equals(null)){
                        prod.setPrecioventa(parseFloat(APven));
                    }
                    
                    if(!APcomp.equals(null)){
                        prod.setPreciocompra(parseFloat(APcomp));
                    }

                    if(!AStock.equals(null)){
                        prod.setStock(parseFloat(AStock));
                    }
                    
                    if(!idp.equals(null)){
                        prod.setIdproducto(idp);
                    }
                    
                    ImageNombre = request.getParameter("imagen");

                    if(ImageNombre != null){
                        RespuestaCargar = proddao.CargarImagen(ImageNombre,idp);
                        if(RespuestaCargar){
                            prod.setNombreImagen(ImageNombre);
                        }
                    }

                                        
                    proddao.actualizar(prod);
                    
                    request.setAttribute("banderaActualizado", "1");
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idp = request.getParameter("id");
                    proddao.eliminar(idp);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);
                    break;

                case "Nuevo":
//                    String nuevoIDProd = proddao.nuevoIDProd();
  //                  request.setAttribute("txtID", nuevoIDProd);
    //                request.getRequestDispatcher("Productos.jsp").forward(request, response);       
                    idp = null;
                    idp = request.getParameter("txtID");                    
                    Producto nuevoIDProd = proddao.nuevoIDProd(idp);
                                            
                    request.setAttribute("producto", nuevoIDProd);
                    nuevoIdDispo = nuevoIDProd.getIdproducto();
                    request.setAttribute("banderaNuevo", "1");
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request, response);

                    break;
                case "Limpiar":
                    request.setAttribute("productos", listaProductos);
                    request.setAttribute("proovedores", listaProovedores);
                    break;

                default:
                //throw new AssertionError();                    
            }
            if (accion.equals("CargaMasiva")) {
                request.getRequestDispatcher("ProductosMasivo.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("Productos.jsp").forward(request, response);
            }
        }
        if (menu.equals("ProductosMasivo")) {
            switch (accion) {
                case "ListarMas":
                    listarMas = proddao.listarMas();
                    request.setAttribute("productosM", listarMas);

                    break;
                case "Cargar":
                    int CanRegistros = proddao.agregarMas(listarMas);
                    request.setAttribute("CanRegistros", CanRegistros);
                    break;

            }
            request.getRequestDispatcher("ProductosMasivo.jsp").forward(request, response);

        }
        if (menu.equals("Empleados")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    int IdEmp = Integer.parseInt(request.getParameter("txtID"));
                    String Nom = request.getParameter("txtNombre");
                    String Ape = request.getParameter("txtApellidos");
                    String Tel = request.getParameter("txtTel");
                    String Usu = request.getParameter("txtUser");
                    em.setIdempleado(IdEmp);
                    em.setNombre(Nom);
                    em.setApellido(Ape);
                    em.setTelefono(Tel);
                    em.setUser(Usu);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String ANom = request.getParameter("txtNombre");
                    String AApe = request.getParameter("txtApellidos");
                    String ATel = request.getParameter("txtTel");
                    String AUsu = request.getParameter("txtUser");

                    em.setNombre(ANom);
                    em.setApellido(AApe);
                    em.setTelefono(ATel);
                    em.setUser(AUsu);
                    em.setIdempleado(ide);
                    edao.actualizar(em);

                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.eliminar(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);

                    break;
                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("Empleados.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            switch(accion){
                case "ListarDet":
                    List listaClientesDet = clientedao.listarDetalles();
                    request.setAttribute("listaClientesDet", listaClientesDet);
                    request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                    break;
            }
        }
        
        if (menu.equals("Ventas")) {
            String Periodo = request.getParameter("Periodo");
            if(Periodo == null){
                Periodo = "HOY";
            }
            List lista = vendao.listar(Periodo);
            float totalPeriodo = vendao.obtenerMontoperiodo(lista);


            switch (accion) {
                case "Inicio":
                    request.setAttribute("venta", lista);
                    request.setAttribute("totalPeriodo", totalPeriodo);
                    request.setAttribute("PeriodoS", Periodo);
                    
                    request.getRequestDispatcher("Ventas.jsp").forward(request, response);
//                    List lista = vendao.listar(Periodo);

                    break;
                case "Listar":
//                    String Periodo = request.getParameter("Periodo");

                    request.setAttribute("venta", lista);
                    request.setAttribute("totalPeriodo", totalPeriodo);
                    request.setAttribute("PeriodoS", Periodo);
                    //request.setAttribute("errorId", errorId);
                    request.getRequestDispatcher("Ventas.jsp").forward(request, response);

                    break;
                case "MostrarVenta":
                    String idVenta = request.getParameter("id");
                    String banderaMostrar = "0";
                    String totalV = request.getParameter("totalV");
                    
                    
                    List listaDet = vendao.mostrar(idVenta);
                    request.setAttribute("listaDet", listaDet);
                    if(listaDet.size() > 0){
                        banderaMostrar = "1";
                    }
                    request.setAttribute("listaDet", listaDet);
                    request.setAttribute("banderaMostrar", banderaMostrar);
                    request.setAttribute("totalV", totalV);
                    request.setAttribute("venta", lista);
                    request.setAttribute("PeriodoS", Periodo);
                    request.setAttribute("totalPeriodo", totalPeriodo);
                    request.getRequestDispatcher("Ventas.jsp").forward(request, response);

                    break;

                default:
                    throw new AssertionError();
            }
        }
        if (menu.equals("Faltantes")) {
            String ordenF;
            String proovedorF;
            
            ordenF = request.getParameter("OrdenarSel");
            proovedorF = request.getParameter("proovedoresDD");
            
            List listaFaltantes = proddao.listarFaltantes(ordenF, proovedorF);
            if(proovedorF != null){
                if(proovedorF.isEmpty()){
                }else{
                    request.setAttribute("proovedorF", proovedorF);
                }
            }        
            if(ordenF != null){
                if(ordenF.isEmpty()){
                }else{
                    request.setAttribute("ordenF", ordenF);
                }
            }        

            request.setAttribute("listaFaltantes", listaFaltantes);
            listaProovedores = proovdao.listar();
            request.setAttribute("proovedores", listaProovedores);
            request.getRequestDispatcher("Faltantes.jsp").forward(request, response);
        }
        
        if (menu.equals("Proovedores")) {
            switch(accion){
                case "ListarDet":
                    List listaProovDet = proovdao.listarDetalles();
                    request.setAttribute("listaProovDet", listaProovDet);
                    request.getRequestDispatcher("Proovedores.jsp").forward(request, response);
                    break;
                case "Limpiar":
                    listaProovDet = proovdao.listarDetalles();
                    request.setAttribute("listaProovDet", listaProovDet);
                    request.getRequestDispatcher("Proovedores.jsp").forward(request, response);
                    break;

                case "Editar":
                    Proovedor proovedor = new Proovedor();
                    String idProovedor = request.getParameter("id");
                    proovedor = proovdao.proovedorEditar(idProovedor);
                    request.setAttribute("proovedor", proovedor);
                    listaProovDet = proovdao.listarDetalles();
                    request.setAttribute("listaProovDet", listaProovDet);
                    boolean banderaEditarProov = true;
                    request.setAttribute("banderaEditarProov", banderaEditarProov);
                    request.getRequestDispatcher("Proovedores.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    
                    String proovId = request.getParameter("id");
                    
                    boolean respEliminarProv = proovdao.eliminarProovedor(proovId);
                    request.setAttribute("respEliminarProv", respEliminarProv);

                    listaProovDet = proovdao.listarDetalles();
                    request.setAttribute("listaProovDet", listaProovDet);
                    request.setAttribute("proovEliminado", proovId);
                    request.getRequestDispatcher("Proovedores.jsp").forward(request, response);
                    
                    break;
                case "Agregar":
                    Proovedor proov = new Proovedor(); 
                    proov.setIdproovedor(request.getParameter("txtID"));
                    proov.setNombre(request.getParameter("txtNombre"));
                    proov.setDireccion(request.getParameter("txtDireccion"));
                    proov.setTelefono(request.getParameter("txtTelefono"));
                    boolean respAgregarProv = false;
                    respAgregarProv = proovdao.agregarProovedor(proov);
                    
                    request.setAttribute("respAgregarProv", respAgregarProv);

                    listaProovDet = proovdao.listarDetalles();
                    request.setAttribute("listaProovDet", listaProovDet);

                    request.getRequestDispatcher("Proovedores.jsp").forward(request, response);
                    
                    break;

                default:
                    throw new AssertionError();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DocumentException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
