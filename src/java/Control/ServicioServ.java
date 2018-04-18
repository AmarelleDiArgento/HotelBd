/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.MySql.AdministradorSql;
import Modelo.AsignaPerMod;
import Modelo.ServicioMod;
import Modelo.UsuarioMod;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ServicioServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Resource(name = "poolHotel")
    private DataSource pool;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession Ses = request.getSession(true);

        String Accion = request.getParameter("accion");
        UsuarioMod uSes = (UsuarioMod) Ses.getAttribute("log");
        List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
        AsignaPerMod acc = null;
        for (AsignaPerMod a : ap) {
            if (a.getTabla().equalsIgnoreCase("Servicio")) {
                acc = a;
            }
        }
        String msj;
        String ruta;
        if (Ses.getAttribute("msj") != null) {
            msj = (String) Ses.getAttribute("msj");
        } else {
            msj = "";
        }
        if (Ses.getAttribute("jsp") != null) {
            ruta = (String) Ses.getAttribute("jsp");
        } else {
            ruta = "servicio.jsp";
        }
        ServicioMod s = null;

        String Nombre;
        int Id, Tarifa;

        try {
            AdministradorSql Asql = new AdministradorSql();

            switch (Accion) {
                case "Registrar":
                    if (acc.isNuevo()) {
                        Nombre = request.getParameter("Nombre");
                        Tarifa = Integer.parseInt(request.getParameter("Tarifa"));
                        s = new ServicioMod(Nombre, Tarifa);
                        msj = Asql.getServicio().insertar(s);

                    } else {
                        msj = "No tienes permisos para hacer registros";
                    }
                    break;
                case "Modificar":
                    if (acc.isModificar()) {

                        Id = Integer.parseInt(request.getParameter("Id"));
                        Nombre = request.getParameter("Nombre");
                        Tarifa = Integer.parseInt(request.getParameter("Tarifa"));
                        s = new ServicioMod(Id, Nombre, Tarifa);
                        msj = Asql.getServicio().modificar(s);
                    } else {
                        msj = "No tienes permisos para hacer modificaciones";
                    }
                    break;

                case "Eliminar":
                    if (acc.isEliminar()) {

                        Id = Integer.parseInt(request.getParameter("Id"));
                        msj = Asql.getServicio().eliminar(Id);
                    } else {
                        msj = "No tienes permisos para eliminar registros";
                    }
                    break;
                case "Obtener":
                    if (acc.isLeer()) {
                        Id = Integer.parseInt(request.getParameter("Id"));
                        s = Asql.getServicio().obtener(Id);
                        Ses.setAttribute("Ser", s);
                        msj = "Se ha obtenido el servicio con id: " + s.getId();
                    } else {
                        msj = "No tienes permisos para consultar registros";
                    }
                    break;
                case "Listar":
                    if (acc.isLeer()) {
                        List<ServicioMod> sl = Asql.getServicio().listar();
                        Ses.setAttribute("arrS", sl);
                    } else {
                        msj = "No tienes permisos para consultar registros";
                    }
                    break;
                default:
                    msj = "No se que paso o_oU";
                    ruta = "servicio.jsp";
            }

        } catch (SQLException ex) {
            msj = "Error " + ex;
            ruta = "main.jsp";

        } catch (Exception ex) {
            msj = "Error " + ex;
            ruta = "main.jsp";
        }

        Ses.setAttribute("msj", msj);
        request.getRequestDispatcher(ruta).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
