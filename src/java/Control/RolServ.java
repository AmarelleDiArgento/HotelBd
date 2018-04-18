/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.MySql.AdministradorSql;
import Modelo.AsignaPerMod;
import Modelo.RolMod;
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
public class RolServ extends HttpServlet {

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
        String msj;
        String ruta;

        //if (Ses.getAttribute("log") != null) {
        String Accion = request.getParameter("accion");

        List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
        AsignaPerMod acc = null;
        for (AsignaPerMod a : ap) {
            if (a.getTabla().equalsIgnoreCase("Rol")) {
                acc = a;
            }
        }
        if (Ses.getAttribute("msj") != null) {
            msj = (String) Ses.getAttribute("msj");
        } else {
            msj = "";
        }
        if (Ses.getAttribute("jsp") != null) {
            ruta = (String) Ses.getAttribute("jsp");
        } else {
            ruta = "rol.jsp";
        }
        RolMod r = null;
        int Id;
        String Nombre;
        try {
            AdministradorSql Asql = new AdministradorSql();

            switch (Accion) {
                case "Insertar":
                    if (acc.isNuevo()) {
                        Nombre = request.getParameter("Nombre");
                        r = new RolMod(Nombre);
                        msj = Asql.getRol().insertar(r);

                    } else {
                        msj = "No tienes permisos para hacer registros";
                    }

                    break;

                case "modificar":
                    if (acc.isModificar()) {

                        Id = Integer.parseInt(request.getParameter("Id"));
                        Nombre = request.getParameter("Nombre");
                        r = new RolMod(Id, Nombre);
                        msj = Asql.getRol().modificar(r);
                    } else {
                        msj = "No tienes permisos para hacer modificaciones";
                    }
                    break;
                case "eliminar":
                    if (acc.isEliminar()) {
                        Id = Integer.parseInt(request.getParameter("Id"));
                        msj = Asql.getRol().eliminar(Id);
                    } else {
                        msj = "No tienes permisos para eliminar registros";
                    }
                    break;
                case "obtener":
                    if (acc.isLeer()) {
                        Id = Integer.parseInt(request.getParameter("Id"));
                        r = Asql.getRol().obtener(Id);
                        Ses.setAttribute("Rol", r);
                        msj = "Se ha obtenido el rol con id: " + r.getId();
                    } else {
                        msj = "No tienes permisos para consultar registros";
                    }

                    break;
                case "Listar":
                    if (acc.isLeer()) {
                        List<RolMod> rl = Asql.getRol().listar();
                        Ses.setAttribute("arrRol", rl);
                    } else {
                        msj = "No tienes permisos para consultar registros";
                    }
                    break;

                default:
                    ruta = "rol.jsp";
            }
        } catch (SQLException ex) {
            msj = "MySql Error: " + ex;

        } catch (Exception ex) {
            msj = "Error: " + ex;
        }
        //}else{
        //    ruta = "index.jsp";
        //    msj = "No has iniciado sesión";
        //}
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
