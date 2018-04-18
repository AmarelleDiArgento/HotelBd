/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import Modelo.ServicioMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.Interface.Servicio;

/**
 *
 * @author Amarelle
 */
public class ServicioSql implements Servicio {

    private final Connection con;

    public ServicioSql(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Hotel.inServicio(?,?);";
    final String Modificar = "call Hotel.moServicio(?,?,?);";
    final String Eliminar = "call Hotel.elServicio(?);";
    final String Consultar = "call Hotel.coServicio(?);";
    final String ListarTodos = "call Hotel.liServicio();";

    @Override
    public String insertar(ServicioMod s) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, s.getNombre());
            stat.setInt(2, s.getTarifa());

            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            } else {
                msj = s.getId() + " agregado exitosamente";
            }

        } catch (SQLException ex) {
            msj = "Error de SQL " + ex;
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    msj = "Error de SQL " + ex;
                }
            }

        }
        return msj;
    }

    @Override
    public String modificar(ServicioMod c) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setInt(1, c.getId());
            stat.setString(2, c.getNombre());
            stat.setInt(3, c.getTarifa());
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = c.getId() + " modificado exitosamente";
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
    public String eliminar(Integer id) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Eliminar);
            stat.setLong(1, id);
            if (stat.executeUpdate() == 0) {
                msj = "Error al eliminar los datos";
            } else {
                msj = id + " eliminado exitosamente";
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
    public ServicioMod convertir(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        int tarifa = rs.getInt("tarifa");
        ServicioMod sModel = new ServicioMod(id, nombre,tarifa);
        return sModel;

    }

    @Override
    public List<ServicioMod> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ServicioMod> sModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    sModel.add(convertir(rs));
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sModel;
    }

    @Override
    public ServicioMod obtener(Integer id) {

        PreparedStatement stat = null;
        ResultSet rs = null;
        ServicioMod sModel = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                sModel = convertir(rs);
            } else {
                throw new SQLException("Error, registro no encontrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return sModel;
    }

}
