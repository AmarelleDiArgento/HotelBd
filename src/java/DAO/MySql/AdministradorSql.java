/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import DAO.Interface.DAOManager;
import java.sql.Connection;
import java.sql.SQLException;
import DAO.Interface.Usuario;
import DAO.Interface.Servicio;
import DAO.Interface.Asigna;
import DAO.Interface.Consumo;
import DAO.Interface.Habita;
import DAO.Interface.Reserva;
import DAO.Interface.Reserva_cierre;
import DAO.Interface.AsignaPer;
import DAO.Interface.Permiso;
import DAO.Interface.Rol;
import java.sql.DriverManager;
import org.apache.commons.codec.digest.DigestUtils;
import javax.sql.DataSource;

/**
 *
 * @author Amarelle
 */
public class AdministradorSql implements DAOManager {

    Connection con = null;

    /*public AdministradorSql(DataSource con) throws SQLException{
        
        this.con = con.getConnection();
    }*/
    public AdministradorSql() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel?zeroDateTimeBehavior=convertToNull", "root", "Arkangel");
        } catch (ClassNotFoundException ex) {
            System.out.print("Class for name " + ex);
        }
    }

    private Asigna aDAO = null;
    private Consumo cDAO = null;
    private Habita hDAO = null;
    private Reserva reDAO = null;
    private Reserva_cierre rcDAO = null;
    private Servicio sDAO = null;
    private Usuario uDAO = null;
    private Rol roDAO = null;
    private AsignaPer apDAO = null;
    private Permiso pDAO = null;

    @Override
    public Asigna getAsigna() {
        if (aDAO == null) {
            aDAO = new AsignaSql(con);
        }
        return aDAO;
    }

    @Override
    public Consumo getConsumo() {
        if (cDAO == null) {
            cDAO = new ConsumoSql(con);
        }
        return cDAO;
    }

    @Override
    public Habita getHabita() {
        if (hDAO == null) {
            hDAO = new HabitaSql(con);
        }
        return hDAO;
    }

    @Override
    public Reserva getReserva() {
        if (reDAO == null) {
            reDAO = new ReservaSql(con);
        }
        return reDAO;
    }

    @Override
    public Reserva_cierre getReserva_cierre() {
        if (rcDAO == null) {
            rcDAO = new Reserva_cierreSql(con);
        }
        return rcDAO;
    }

    @Override
    public Servicio getServicio() {
        if (sDAO == null) {
            sDAO = new ServicioSql(con) {
            };
        }
        return sDAO;
    }

    @Override
    public Usuario getUsuario() {
        if (uDAO == null) {
            uDAO = new UsuarioSql(con);
        }
        return uDAO;
    }

    @Override
    public Rol getRol() {
        if (roDAO == null) {
            roDAO = new RolSql(con);
        }
        return roDAO;
    }

    @Override
    public AsignaPer getAsignaPer() {
        if (apDAO == null) {
            apDAO = new AsignaPerSql(con);
        }
        return apDAO;
    }

    @Override
    public Permiso getPermiso() {
        if (pDAO == null) {
            pDAO = new PermisoSql(con);
        }
        return pDAO;
    }

    @Override
    public String SHA(String t) {

        String encript = DigestUtils.shaHex(t);
        return encript;

    }

}
