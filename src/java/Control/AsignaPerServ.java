/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.MySql.AdministradorSql;
import Modelo.AsignaPerMod;
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
public class AsignaPerServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    @Resource(name = "jdbc/poolHotel")
    private DataSource pool;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession Ses = request.getSession(true);
        String msj = "";
        String ruta = "";
        String Dato = "";
        String Accion = request.getParameter("accion");
        try {
            AdministradorSql Asql = new AdministradorSql();

            switch (Accion) {
                case "Insertar":

                    break;
                case "modificar":
                    boolean L,
                     N,
                     M,
                     E;
                    List<String> msjs = null;
                    List<AsignaPerMod> li = (List<AsignaPerMod>) Ses.getAttribute("Aprr");
                    int Rol = li.get(1).getRol_id();
                    int P = 1;
                    for (AsignaPerMod ap : li) {

                        String[] cap = request.getParameterValues(ap.getTabla());
                        L = false;
                        N = false;
                        M = false;
                        E = false;
                        for (String a : cap) {
                            switch (a) {
                                case "1":
                                    L = true;
                                    break;
                                case "2":
                                    N = true;
                                    break;
                                case "3":
                                    M = true;
                                    break;
                                case "4":
                                    E = true;
                                    break;

                            }
                        }
                        AsignaPerMod aps = new AsignaPerMod(Rol, P, L, N, M, E);
                        //Dato = aps.toString();
                        msj = msj + Asql.getAsignaPer().modificar(aps);
                        //Ses.setAttribute("conf", msjs);
                        P++;
                    }
                    msj = "?msj=" + msj;
                    ruta = "rol.jsp";
                    break;
                case "eliminar":

                    break;
                    
                case "Obtener":
                    int rol = Integer.parseInt(request.getParameter("rol"));
                    List<AsignaPerMod> apli = Asql.getAsignaPer().listar(rol);
                    Ses.setAttribute("Aprr", apli);
                    ruta = "rol.jsp";
                    msj = "?msj=Cargado";
                    break;
                case "session":

                    UsuarioMod u = (UsuarioMod) Ses.getAttribute("log");
                    List<AsignaPerMod> apl = Asql.getAsignaPer().perSesion(u.getCedula());
                    Ses.setAttribute("ApSes", apl);
                    ruta = "menu.jsp";

                    break;
                default:
            }
        } catch (SQLException ex) {
            msj = "?msj=Servlet Error sql: " + ex + " " + Dato;
            ruta = "main.jsp";
        } catch (Exception ex) {
            msj = "?msj=Servlet Error : " + ex + " " + Dato;
            ruta = "main.jsp";
        }

        request.getRequestDispatcher(ruta + msj).forward(request, response);

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
