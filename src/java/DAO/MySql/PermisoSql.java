/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import DAO.Interface.Permiso;
import Modelo.PermisoMod;
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
public class PermisoSql implements Permiso {

    private final Connection con;

    public PermisoSql(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Hotel.inPermiso(?,?,?);";
    final String Modificar = "call Hotel.moPermiso(?,?,?,?);";
    final String Eliminar = "call Hotel.elPermiso(?);";
    final String Consultar = "call Hotel.coPermiso(?);";
    final String ListarTodos = "call Hotel.liPermiso();";
    final String Menu = "call Hotel.perMenu(?)";

    @Override
    public String insertar(PermisoMod p) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, p.getModulo());
            stat.setString(2, p.getTabla());
            stat.setString(3, p.getUrl());
            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            } else {
                msj = p.getTabla() + " agregado exitosamente";
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
    public String modificar(PermisoMod p) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setInt(1, p.getId());
            stat.setString(2, p.getModulo());
            stat.setString(3, p.getTabla());
            stat.setString(4, p.getUrl());
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = p.getTabla() + " modificado exitosamente";
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
    public PermisoMod convertir(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String modulo = rs.getString("modulo");
        String tabla = rs.getString("tabla");
        String url = rs.getString("url");
        PermisoMod pModel = new PermisoMod(id, modulo, tabla, url);
        return pModel;
    }

    private PermisoMod conMenu(ResultSet rs) throws SQLException {
        String modulo = rs.getString("modulo");
        String tabla = rs.getString("tabla");
        String url = rs.getString("url");
        PermisoMod pModel = new PermisoMod(modulo, tabla, url);
        return pModel;

    }

    @Override //Menuuuu!!!
    public List<PermisoMod> menu(String cedula) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<PermisoMod> pModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(Menu);
                stat.setString(1, cedula);

                rs = stat.executeQuery();
                while (rs.next()) {
                    pModel.add(conMenu(rs));
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReservaSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReservaSql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pModel;

    }

    @Override
    public PermisoMod obtener(Integer id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        PermisoMod pModel = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                pModel = convertir(rs);
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
        return pModel;
    }

    @Override
    public List<PermisoMod> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<PermisoMod> pModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    pModel.add(convertir(rs));
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
                        System.out.println("Error Sql st: " + ex);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error Sql: " + ex);
        }
        return pModel;
    }
}
