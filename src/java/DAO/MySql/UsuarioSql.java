/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import DAO.Interface.Usuario;
import Modelo.ReservaMod;
import Modelo.UsuarioMod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UsuarioSql implements Usuario {

    private final Connection con;

    public UsuarioSql(Connection con) {

        this.con = con;
    }

    final String Insertar = "call Hotel.inUsuario(?,?,?,?);";
    final String Modificar = "call Hotel.moUsuario(?,?,?,?);";
    final String Eliminar = "call Hotel.elUsuario(?);";
    final String Consultar = "call Hotel.coUsuario(?);";
    final String ListarTodos = "call Hotel.liUsuario();";
    final String Login = "call Hotel.Login(?,?);";

    @Override
    public String insertar(UsuarioMod u) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Insertar);
            stat.setString(1, u.getCedula());
            stat.setString(2, u.getNombre());
            stat.setString(3, u.getPass());
            stat.setInt(4, u.getRol_id());
            if (stat.executeUpdate() == 0) {
                msj = "Error al ingresar los datos";
            } else {
                msj = u.getCedula() + " agregado exitosamente";
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
    public String modificar(UsuarioMod u) {
        String msj = "";
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement(Modificar);
            stat.setString(1, u.getCedula());
            stat.setString(2,u.getNombre());
            stat.setString(3, u.getPass());
            stat.setInt(4, u.getRol_id());
            if (stat.executeUpdate() == 0) {
                msj = "Error al modificar los datos";
            } else {
                msj = u.getCedula() + " modificado exitosamente";
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

    private UsuarioMod conLogin(ResultSet rs) throws SQLException {
        String cedula = rs.getString("cedula");
        String nombre = rs.getString("nombre");
        int Rol_id = rs.getInt("Rol_id");
        String rol = rs.getString("rol");
        UsuarioMod uModel = new UsuarioMod(cedula, nombre, Rol_id, rol);
        return uModel;
    }

    @Override
    public List<UsuarioMod> listar() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<UsuarioMod> uModel = new ArrayList<>();
        try {
            try {
                stat = con.prepareCall(ListarTodos);

                rs = stat.executeQuery();
                while (rs.next()) {
                    uModel.add(conList(rs));
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        System.out.println("Error sql rs: " + ex);
                    }
                }
                if (stat != null) {
                    try {
                        stat.close();
                    } catch (SQLException ex) {
                        System.out.println("Error sql st: " + ex);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex);
        }
        return uModel;
    }

    private UsuarioMod conList(ResultSet rs) throws SQLException {
        String cedula = rs.getString("cedula");
        String nombre = rs.getString("nombre");
        int rol_id = rs.getInt("Rol_id");
        String nRol = rs.getString("rol");
        UsuarioMod uModel = new UsuarioMod(cedula, nombre, rol_id, nRol);
        return uModel;
    }

    @Override
    public UsuarioMod convertir(ResultSet rs) throws SQLException {
        String cedula = rs.getString("cedula");
        String nombre = rs.getString("nombre");
        String pass = rs.getString("pass");
        int rol_id = rs.getInt("Rol_id");
        UsuarioMod uModel = new UsuarioMod(cedula, nombre, pass, rol_id);
        return uModel;
    }

    @Override
    public UsuarioMod login(String cedula, String pass) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        UsuarioMod uModel = null;
        try {
            stat = con.prepareCall(Login);
            stat.setString(1, cedula);
            stat.setString(2, pass);
            rs = stat.executeQuery();
            if (rs.next()) {
                uModel = conLogin(rs);
            } else {
                throw new SQLException("Error, registro no encontrado");
            }
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Error sql rs" + ex);
                }

            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    System.out.println("Error sql st" + ex);
                }

            }
        }
        return uModel;
    }

    @Override
    public UsuarioMod obtener(String id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        UsuarioMod uModel = null;
        try {
            stat = con.prepareCall(Consultar);
            stat.setString(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                uModel = convertir(rs);
            } else {
                throw new SQLException("Error, usuario no encontrado");
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
        return uModel;

    }

}
