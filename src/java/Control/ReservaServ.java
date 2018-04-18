/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.MySql.AdministradorSql;
import Modelo.AsignaMod;
import Modelo.AsignaPerMod;
import Modelo.HabitaMod;
import Modelo.ReservaMod;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ReservaServ extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("text/html;charset=UTF-8");

            HttpSession Ses = request.getSession(true);
            String msj;
            String ruta;

            //if (Ses.getAttribute("log") != null) {
            String Accion = request.getParameter("accion");

            List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
            AsignaPerMod acc = null;
            for (AsignaPerMod a : ap) {
                if (a.getTabla().equalsIgnoreCase("Reserva")) {
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
                ruta = "reserva.jsp";
            }

            AsignaMod a = null;
            ReservaMod r = null;

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

            int Codigo;
            String fIngreso;
            String fEgreso;
            String FFin;
            String Cedula;
            String Habita;
            int ni;
            int ad;
            int Num_personas;

            try {
                AdministradorSql Asql = new AdministradorSql();

                switch (Accion) {
                    case "Insertar":
                        if (acc.isNuevo()) {

                            fIngreso = f.format(new Date(request.getParameter("fIngreso")));
                            fEgreso = f.format(new Date(request.getParameter("fEgreso")));

                            Habita = request.getParameter("Habita");
                            Cedula = request.getParameter("cedula");
                            ni = Integer.parseInt(request.getParameter("ninos"));
                            ad = Integer.parseInt(request.getParameter("adultos"));
                            Num_personas = ni + ad;

                            r = new ReservaMod(fIngreso, fEgreso, Cedula, Num_personas);
                            Asql.getReserva().Registrar(r);

                            a = new AsignaMod(Habita, r.getCodigo(), ad, ni);

                            msj = Asql.getAsigna().insertar(a);

                        } else {
                            msj = "No tienes permisos para hacer registros";
                        }

                        break;

                    case "modificar":
                        if (acc.isModificar()) {
                            Codigo = Integer.parseInt(request.getParameter("Codigo"));
                            fIngreso = f.format(new Date(request.getParameter("fIngreso")));
                            fEgreso = f.format(new Date(request.getParameter("fEgreso")));
                            Habita = request.getParameter("Habita");
                            Cedula = request.getParameter("cedula");
                            ni = Integer.parseInt(request.getParameter("ninos"));
                            ad = Integer.parseInt(request.getParameter("adultos"));
                            Num_personas = ni + ad;

                            r = new ReservaMod(Codigo, fIngreso, fEgreso, Cedula, Num_personas);
                            Asql.getReserva().modificar(r);
                            a = new AsignaMod(Habita, r.getCodigo(), ad, ni);

                            msj = Asql.getAsigna().modificar(a);

                        } else {
                            msj = "No tienes permisos para hacer modificaciones";
                        }
                        break;
                    case "eliminar":
                        if (acc.isEliminar()) {
                            Codigo = Integer.parseInt(request.getParameter("Codigo"));
                            msj = Asql.getReserva().eliminar(Codigo);
                            msj = Asql.getAsigna().eliminar(Codigo);
                        } else {
                            msj = "No tienes permisos para eliminar registros";
                        }
                        break;
                    case "obtener":
                        if (acc.isLeer()) {
                            
                            
                        } else {
                            msj = "No tienes permisos para consultar registros";
                        }

                        break;
                    case "Listar":
                        if (acc.isLeer()) {
                            List<ReservaMod> rl = Asql.getReserva().listar();
                            Ses.setAttribute("arrR", rl);
                        } else {
                            msj = "No tienes permisos para consultar registros";
                        }
                        break;

                    default:
                        ruta = "reserva.jsp";
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

            /*try {
                AdministradorSql man = new AdministradorSql();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String Action = request.getParameter("Action");

                if (Action.equals("Nuevo")) {
                    String codigoReg = request.getParameter("codigoReg");
                    String fIngresoReg = f.format(new Date(request.getParameter("fIngresoReg")));
                    String fEgresoReg = f.format(new Date(request.getParameter("fEgresoReg")));
                    String cedulaReg = request.getParameter("cedulaReg");
                    String nombreReg = request.getParameter("nombreReg");
                    int numPerReg = 0;

                    try {
                        numPerReg = Integer.parseInt(request.getParameter("numPerReg"));
                    } catch (NumberFormatException ex) {
                        msj = "Error NumberFormatException: " + ex;
                    }
                    ReservaMod mR = new ReservaMod(codigoReg, fIngresoReg, fEgresoReg, cedulaReg, nombreReg, numPerReg);
                    msj = man.getReserva().insertar(mR);
                } else if (Action.equals("Modificar")) {
                    String codigoReg = request.getParameter("codigoMod");
                    String fIngresoReg = request.getParameter("fIngresoMod");
                    String fEgresoReg = request.getParameter("fEgresoMod");
                    String cedulaReg = request.getParameter("cedulaMod");
                    String nombreReg = request.getParameter("nombreMod");
                    int numPerReg = 0;

                    try {
                        numPerReg = Integer.parseInt(request.getParameter("numPerMod"));
                    } catch (NumberFormatException ex) {
                        msj = "Error NumberFormatException: " + ex;
                    }
                    ReservaMod mR = new ReservaMod(codigoReg, fIngresoReg, fEgresoReg, cedulaReg, nombreReg, numPerReg);
                    msj = man.getReserva().modificar(mR);

                } else if (Action.equals("Eliminar")) {
                    String Id = request.getParameter("Id");
                    msj = man.getReserva().eliminar(Id);
                    msj = "Se ha eliminado la reserva ID: " + Id;
                } else if (Action.equals("Listar")) {
                    List<ReservaMod> r = man.getReserva().listar();
                    HttpSession Ses = request.getSession(true);
                    Ses.setAttribute("arrR", r);
                } else if (Action.equals("Disponibles")) {
                    String fIngresoDis = f.format(new Date(request.getParameter("fIngresoDis")));
                    String fEgresoDis = f.format(new Date(request.getParameter("fEgresoDis")));
                    //List<HabitaMod> hD = man.getHabita()..obtenerCon1(fEgresoDis, fIngresoDis);
                    HttpSession Ses = request.getSession(true);
                    //Ses.setAttribute("hDisp", hD);
                    //msj = "habitaciones disponibles " + hD.size() + " " + fIngresoDis + " " + fEgresoDis;
                } else if (Action.equals("Obtener")) {
                    String Id = request.getParameter("Id");
                    ReservaMod res = man.getReserva().obtener(Id);
                    HttpSession Ses = request.getSession(true);
                    Ses.setAttribute("res", res);
                    msj = "Se ha obtenido la reserva ID: " + Id;
                } else {
                    msj = "Que paso? " + Action;
                }
            } catch (SQLException ex) {
                msj = "Error SQL externo " + ex;
            }
            request.getRequestDispatcher("Reserva.jsp?msj=" + msj).forward(request, response);
        }*/
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
