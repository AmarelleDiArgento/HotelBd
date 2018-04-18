/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.MySql.AdministradorSql;
import Modelo.AsignaPerMod;
import Modelo.PermisoMod;
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
public class PermisoServ extends HttpServlet {

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
            if (a.getTabla().equalsIgnoreCase("Permiso")) {
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
            ruta = "permiso.jsp";
        }
        Integer Id;
        String Modulo;
        String Tabla;
        String Url;

        PermisoMod p = null;

        try {
            AdministradorSql Asql = new AdministradorSql();

            switch (Accion) {
                case "Registrar":
                    Modulo = request.getParameter("Modulo");
                    Tabla = request.getParameter("Tabla");
                    Url = request.getParameter("Url");
                    p = new PermisoMod(Modulo, Tabla, Url);
                    msj = Asql.getPermiso().insertar(p);
                    break;

                case "Obtener":
                    Id = Integer.parseInt(request.getParameter("Id"));
                    p = Asql.getPermiso().obtener(Id);
                    Ses.setAttribute("per", p);
                    msj = "Se ha obtenido la permiso: " + p.getTabla();

                    break;
                case "Eliminar":

                    Id = Integer.parseInt(request.getParameter("Id"));
                    msj = Asql.getPermiso().eliminar(Id);
                    break;

                case "Modificar":
                    Id = Integer.parseInt(request.getParameter("Id"));
                    Modulo = request.getParameter("Modulo");
                    Tabla = request.getParameter("Tabla");
                    Url = request.getParameter("Url");
                    p = new PermisoMod(Id, Modulo, Tabla, Url);
                    msj = Asql.getPermiso().modificar(p);
                    break;

                case "Listar":

                    List<PermisoMod> pl = Asql.getPermiso().listar();
                    Ses.setAttribute("arrP", pl);
                    ruta = "permiso.jsp";
                    break;

                case "menu":

                    List<PermisoMod> menu = Asql.getPermiso().menu(uSes.getCedula());
                    Ses.setAttribute("Menu", menu);
                    msj = "Bienvenido " + uSes.getNombre();
                    ruta = "main.jsp";

                    break;
                default:

            }
        } catch (SQLException ex) {
            msj = "MySql Error: " + ex;

        } catch (Exception ex) {
            msj = "Error: " + ex;
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
