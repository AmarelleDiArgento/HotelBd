/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.MySql.AdministradorSql;
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
public class UsuarioServ extends HttpServlet {

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

            if (Ses.getAttribute("msj") != null) {
                msj = (String) Ses.getAttribute("msj");
            } else {
                msj = "";
            }
            if (Ses.getAttribute("jsp") != null) {
                ruta = (String) Ses.getAttribute("jsp");
            } else {
                ruta = "usuario.jsp";
            }
            UsuarioMod u = null;

            String Cedula;
            String Nombre;
            String Pass;
            int Rol = 3;

            try {
                AdministradorSql Asql = new AdministradorSql();

                switch (Accion) {
                    case "Registrar":
                        Cedula = request.getParameter("Cedula");
                        Nombre = request.getParameter("Nombre");

                        if (request.getParameter("Password") != null) {
                            Pass = request.getParameter("Password");
                        } else {
                            Pass = "1234";
                        }

                        if (request.getParameter("Rol") != null) {
                            Rol = Integer.parseInt(request.getParameter("Rol"));
                        } else {
                            Rol = 3;
                        }
                        u = new UsuarioMod(Cedula, Nombre, Pass, Rol);
                        msj = Asql.getUsuario().insertar(u);
                        break;

                    case "Modificar":
                        Cedula = request.getParameter("Cedula");
                        Nombre = request.getParameter("Nombre");
                        Pass = request.getParameter("Password");
                        Rol = Integer.parseInt(request.getParameter("Rol"));
                        u = new UsuarioMod(Cedula, Nombre, Pass, Rol);
                        msj = Asql.getUsuario().modificar(u);
                        break;
                    case "Eliminar":
                        Cedula = request.getParameter("Id");
                        msj = Asql.getUsuario().eliminar(Cedula);
                        break;
                    case "Obtener":
                        Cedula = request.getParameter("Cedula");
                        u = Asql.getUsuario().obtener(Cedula);
                        Ses.setAttribute("Usu", u);
                        msj = "Se ha obtenido el usuario con cedula: " + u.getCedula();

                        break;
                    case "Listar":
                        List<UsuarioMod> ul = Asql.getUsuario().listar();
                        Ses.setAttribute("arrU", ul);
                        break;

                    case "Ingresar":
                        String cedlog = request.getParameter("cedula");
                        String paslog = request.getParameter("password");
                        UsuarioMod Us = Asql.getUsuario().login(cedlog, paslog);
                        if (Us != null) {
                            Ses.setAttribute("log", Us);
                            ruta = "asignapers.do?accion=session";
                        } else {
                            msj = "Usuario o contraseña invalidos";
                            ruta = "index.jsp";
                        }
                        break;

                    default:
                        msj = "No se que paso o_oU";
                        ruta = "usuario.jsp";
                }

            } catch (SQLException ex) {
                msj = "Error " + ex;
                ruta = "main.jsp";

            } catch (Exception ex) {
                msj = "Error " + ex;
                ruta = "main.jsp";
            }
        //} else {
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
