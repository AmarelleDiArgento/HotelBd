/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import Modelo.Reserva_cierreMod;
import java.sql.Connection;
import java.util.List;
import DAO.Interface.Reserva_cierre;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amarelle
 */
public class Reserva_cierreSql implements Reserva_cierre {

    private final Connection con;

    public Reserva_cierreSql(Connection con) {

        this.con = con;
    }
    final String Insertar = "call Hotel.inReserva(?,?,?,?,?,?);";
    final String Modificar = "call Hotel.moReserva(?,?,?,?,?,?);";
    final String Eliminar = "call Hotel.elReserva(?);";
    final String Consultar = "call Hotel.coReserva(?);";
    final String ListarTodos = "call Hotel.liReserva();";
    final String ListarDetalles = "call Hotel.lisReserva();";

    @Override
    public String insertar(Reserva_cierreMod rc) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setInt(1, rc.getCodigo());
            stat.setDate(1, rc.getFecha_cierre());
            stat.setString(1, rc.getTipo_pago());
            stat.setFloat(1, rc.getMonto_total());
            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            } else {
                msj = rc.getCodigo() + " agregado exitosamente";
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
    public String modificar(Reserva_cierreMod rc) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setInt(1, rc.getCodigo());
            stat.setDate(1, rc.getFecha_cierre());
            stat.setString(1, rc.getTipo_pago());
            stat.setFloat(1, rc.getMonto_total());
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = rc.getCodigo() + " modificado exitosamente";
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
            stat.setInt(1, id);
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
    public Reserva_cierreMod convertir(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("id");
        Date fecha_cierre = rs.getDate("fecha_cierre");
        String tipo_pago = rs.getString("tipo_pago");
        float monto_total = rs.getFloat("monto_total");
        Reserva_cierreMod rcModel = new Reserva_cierreMod(codigo, fecha_cierre, tipo_pago, monto_total);
        return rcModel;
    }

    @Override
    public Reserva_cierreMod obtener(Integer id) {
       
        PreparedStatement stat = null;
        ResultSet rs = null;
        Reserva_cierreMod rModel = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                rModel = convertir(rs);
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
        return rModel; 
    }

    @Override
    public List<Reserva_cierreMod> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Reserva_cierreMod> rModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    rModel.add(convertir(rs));
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
        return rModel;
    }
}
