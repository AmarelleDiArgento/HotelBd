/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import DAO.Interface.AsignaPer;
import Modelo.AsignaPerMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AsignaPerSql implements AsignaPer {

    private final Connection con;

    public AsignaPerSql(Connection con) {

        this.con = con;
    }

    final String Insertar = "Call Hotel.inAsignaPer(?,?,?,?,?,?);";
    final String Modificar = "Call Hotel.moAsignaPer(?,?,?,?,?,?);";
    final String Eliminar = "Call Hotel.elAsignaPer(?);";
    final String Consultar = "Call Hotel.coAsignaPer(?);";
    final String Listar = "call Hotel.liAsignaPer(?);";
    final String ListarSesion = "call Hotel.seAsignaPer(?);";

    @Override
    public String insertar(AsignaPerMod o) {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String modificar(AsignaPerMod o) {

        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setInt(1, o.getRol_id());
            stat.setInt(2, o.getPermisos_id());
            if (o.isLeer()) {
                stat.setInt(3, 1);
            } else {
                stat.setInt(3, 0);
            }
            if (o.isNuevo()) {
                stat.setInt(4, 1);
            } else {
                stat.setInt(4, 0);
            }
            if (o.isModificar()) {
                stat.setInt(5, 1);
            } else {
                stat.setInt(5, 0);
            }
            if (o.isEliminar()) {
                stat.setInt(6, 1);
            } else {
                stat.setInt(6, 0);
            }
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = "Permiso " + o.getPermisos_id() + " modificado exitosamente";
            }
        } catch (SQLException ex) {
            msj = "Error de SQL" + ex;
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    msj = "Error de SQL" + ex;
                }
            }
        }
        return msj;
    }

    @Override
    public String eliminar(Long id) {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    public AsignaPerMod coSession(ResultSet rs) throws SQLException {

        String tabla = rs.getString("tabla");
        int l = rs.getInt("leer");
        boolean leer = l == 1;
        int n = rs.getInt("nuevo");
        boolean nuevo = n == 1;
        int m = rs.getInt("modificar");
        boolean modificar = m == 1;
        int e = rs.getInt("eliminar");
        boolean eliminar = e == 1;
        AsignaPerMod apModel = new AsignaPerMod(tabla, leer, nuevo, modificar, eliminar);
        return apModel;
    }

    @Override
    public AsignaPerMod obtener(Long id) {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AsignaPerMod> listar() {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AsignaPerMod> perSesion(String cedula) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<AsignaPerMod> apModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarSesion);
                stat.setString(1, cedula);
                rs = stat.executeQuery();
                while (rs.next()) {
                    apModel.add(coSession(rs));
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AsignaPerSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AsignaPerSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaPerSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return apModel;
    }

    @Override
    public AsignaPerMod convertir(ResultSet rs) throws SQLException {
        int Rol_id = rs.getInt("Rol_id");
        String rNombre = rs.getString("rNombre");
        int Per_id = rs.getInt("Per_id");
        String tabla = rs.getString("tabla");
        int l = rs.getInt("leer");
        boolean leer = l == 1;
        int n = rs.getInt("nuevo");
        boolean nuevo = n == 1;
        int m = rs.getInt("modificar");
        boolean modificar = m == 1;
        int e = rs.getInt("eliminar");
        boolean eliminar = e == 1;
        AsignaPerMod apModel = new AsignaPerMod(Rol_id, rNombre, Per_id, tabla, leer, nuevo, modificar, eliminar);
        return apModel;
    }

    @Override
    public List<AsignaPerMod> listar(int Rol) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<AsignaPerMod> apModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(Listar);
                stat.setInt(1, Rol);
                rs = stat.executeQuery();
                while (rs.next()) {
                    apModel.add(convertir(rs));
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Error Sql rs: " + ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        System.out.println("Error Sql st : " + ex);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error Sql: " + ex);
        }
        return apModel;
    }

}
