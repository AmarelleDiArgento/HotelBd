/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import Modelo.HabitaMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.Interface.Habita;

/**
 *
 * @author Amarelle
 */
public class HabitaSql implements Habita {

    private final Connection con;

    public HabitaSql(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Hotel.inHabita(?,?);";
    final String Modificar = "call Hotel.moHabita(?,?);";
    final String Eliminar = "call Hotel.elHabita(?);";
    final String Consultar = "call Hotel.coHabita(?);";
    final String ListarTodos = "call Hotel.liHabita();";
    final String Disponibles = "call Hotel.disponible(?,?);";

    @Override
    public String insertar(HabitaMod h) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, h.getNumero());
            stat.setFloat(2, h.getTarifa());

            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            } else {
                msj = h.getNumero() + " agregado exitosamente";
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
    public String modificar(HabitaMod h) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setString(1, h.getNumero());
            stat.setFloat(2, h.getTarifa());
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = h.getNumero() + " modificado exitosamente";
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
    public HabitaMod convertir(ResultSet rs) throws SQLException {
        String numero = rs.getString("numero");
        float tarifa = rs.getFloat("tarifa");
        HabitaMod hModel = new HabitaMod(numero, tarifa);
        return hModel;

    }

    @Override
    public List<HabitaMod> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<HabitaMod> hModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    hModel.add(convertir(rs));
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hModel;

    }

    @Override
    public HabitaMod obtener(String id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        HabitaMod hModel = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setString(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                hModel = convertir(rs);
            } else {
                throw new SQLException("Error, registro no encontrado");
            }

        } catch (SQLException ex) {
            Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hModel;
    }

    @Override
    public List<HabitaMod> disponible(String ing, String egr) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<HabitaMod> hModel = new ArrayList<>();
        try {
            stat = con.prepareCall(Disponibles);
            stat.setString(1, ing);
            stat.setString(2, egr);

            rs = stat.executeQuery();
            while (rs.next()) {
                hModel.add(convertir(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HabitaSql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hModel;
    }
}
