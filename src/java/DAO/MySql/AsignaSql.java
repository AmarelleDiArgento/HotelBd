/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import Modelo.AsignaMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import DAO.Interface.Asigna;
import java.sql.ResultSet;

/**
 *
 * @author Amarelle
 */
public class AsignaSql implements Asigna {

    private final Connection con;

    public AsignaSql(Connection con) {

        this.con = con;
    }
    final String Insertar = "call Hotel.inReserva(?,?,?,?,?,?);";
    final String Modificar = "call Hotel.moReserva(?,?,?,?,?,?);";
    final String Eliminar = "call Hotel.elReserva(?);";
    final String Consultar = "call Hotel.coReserva(?);";
    final String ListarTodos = "call Hotel.liReserva();";
    final String ListarDetalles = "call Hotel.lisReserva();";

    @Override
    public String insertar(AsignaMod o) {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String modificar(AsignaMod o) {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
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
    public AsignaMod convertir(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AsignaMod obtener(Integer id) {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AsignaMod> listar() {
        throw new UnsupportedOperationException("No lo he hecho T-T "); //To change body of generated methods, choose Tools | Templates.
    }

}
