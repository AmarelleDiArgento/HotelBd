/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import Modelo.ReservaMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.Interface.Reserva;
import java.sql.Date;

/**
 *
 * @author Amarelle
 */
public class ReservaSql implements Reserva {

    private final Connection con;

    public ReservaSql(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Hotel.inReserva(?,?,?,?);";
    final String Modificar = "call Hotel.moReserva(?,?,?,?,?);";
    final String Eliminar = "call Hotel.elReserva(?);";
    final String Consultar = "call Hotel.coReserva(?);";
    final String ListarTodos = "call Hotel.liReserva();";
    final String ListarDetalles = "call Hotel.lisReserva();";

    @Override
    public String insertar(ReservaMod r) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, r.getFecha_ingreso());
            stat.setString(2, r.getFecha_egreso());
            stat.setString(3, r.getCedula());
            stat.setInt(4, r.getNum_personas());
            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            } else {
                msj = r.getCodigo() + " agregado exitosamente";
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
    public String modificar(ReservaMod r) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setInt(1, r.getCodigo());
            stat.setString(2, r.getFecha_ingreso());
            stat.setString(3, r.getFecha_egreso());
            stat.setString(4, r.getCedula());
            stat.setInt(6, r.getNum_personas());
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = r.getCodigo() + " modificado exitosamente";
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
    public ReservaMod convertir(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("codigo");
        String fecha_ingreso = rs.getString("fecha_ingreso");
        String fecha_egreso = rs.getString("fecha_egreso");
        String cedula = rs.getString("cedula");
        int num_personas = rs.getInt("num_personas");
        ReservaMod rModel = new ReservaMod(codigo, fecha_ingreso, fecha_egreso, cedula, num_personas);
        return rModel;
    }

    public ReservaMod conDetalles(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("codigo");
        String numero = rs.getString("numero");
        String fecha_ingreso = rs.getString("fecha_ingreso");
        String fecha_egreso = rs.getString("fecha_egreso");
        String cedula = rs.getString("cedula");
        String nombre = rs.getString("nombre");
        int num_personas = rs.getInt("num_personas");
        ReservaMod rModel = new ReservaMod(codigo, numero, fecha_ingreso, fecha_egreso, cedula, nombre, num_personas);
        return rModel;
    }

    @Override
    public List<ReservaMod> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ReservaMod> rModel = new ArrayList<>();
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
                        System.out.println("Error de SQL rs: " + ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        System.out.println("Error de SQL st: " + ex);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL " + ex);
        }
        return rModel;
    }

    @Override
    public ReservaMod obtener(Integer id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        ReservaMod rModel = null;
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
            System.out.println("Error de SQL " + ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error de SQL rs: " + ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    System.out.println("Error de SQL: " + ex);
                }

            }
        }
        return rModel;
    }

    @Override
    public List<ReservaMod> listarDetalles() {

        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ReservaMod> rModel = new ArrayList<>();
        try {
            stat = con.prepareCall(ListarDetalles);

            rs = stat.executeQuery();
            while (rs.next()) {
                rModel.add(conDetalles(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error Sql: " + ex);
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
                    System.out.println("Error Sql st: " + ex);
                }
            }
        }

        return rModel;

    }

    @Override
    public void Registrar(ReservaMod r) {

        String msj = "";
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, r.getFecha_ingreso());
            stat.setString(2, r.getFecha_egreso());
            stat.setString(3, r.getCedula());
            stat.setInt(4, r.getNum_personas());
            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            }

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                r.setCodigo(rs.getInt(1));
            } else {
                msj = "Error al ingresar los datos";
            Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, msj);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
            Logger.getLogger(ServicioSql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
