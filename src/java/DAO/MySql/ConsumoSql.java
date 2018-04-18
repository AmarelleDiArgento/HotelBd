/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import Modelo.ConsumoMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import DAO.Interface.Consumo;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Amarelle
 */
public class ConsumoSql implements Consumo {

    private final Connection con;

    public ConsumoSql(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Hotel.inConsumo(?,?,?,?);";
    final String Modificar = "call Hotel.moConsumo(?,?,?,?);";
    final String Eliminar = "call Hotel.elConsumo(?);";
    final String Consultar = "call Hotel.coConsumo(?);";
    final String ListarTodos = "call Hotel.liConsumo();";
    final String ListarDetalles = "call Hotel.liDetConsumo();";

    @Override
    public String insertar(ConsumoMod c) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, c.getNumero_hab());
            stat.setString(2, c.getCodigo_res());
            stat.setString(3, c.getId_servicio());
            stat.setString(4, c.getFecha_hora());
            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            } else {
                msj = c.getId_cons() + " agregado exitosamente";
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
    public String modificar(ConsumoMod c) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setString(1, c.getNumero_hab());
            stat.setString(2, c.getCodigo_res());
            stat.setString(3, c.getId_servicio());
            stat.setString(4, c.getFecha_hora());
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = c.getCodigo_res() + " modificado exitosamente";
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
    public String eliminar(String id) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Eliminar);
            stat.setString(1, id);
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
    public ConsumoMod convertir(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    public ConsumoMod conDetalles(ResultSet rs) throws SQLException {
        int id_cons = rs.getInt("id_cons");
        String codigo_res = rs.getString("codigo_res");
        String numero_hab = rs.getString("numero_hab");
        String nombre = rs.getString("nombre");
        double tarifa = rs.getDouble("tarifa");
        String fecha_hora = rs.getString("fecha_hora");
        ConsumoMod cModel = new ConsumoMod(id_cons, codigo_res, numero_hab, nombre, tarifa, fecha_hora);
        return cModel;
    }

    @Override
    public ConsumoMod obtener(String id) {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ConsumoMod> listar() {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ConsumoMod> listarDetalles() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<ConsumoMod> rModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarDetalles);

                rs = stat.executeQuery();
                while (rs.next()) {
                    rModel.add(conDetalles(rs));
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
        return rModel;
    }

}
