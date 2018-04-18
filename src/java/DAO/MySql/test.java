/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.MySql;

import Modelo.AsignaPerMod;
import Modelo.HabitaMod;
import Modelo.PermisoMod;
import Modelo.UsuarioMod;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String msj = "";
        try {
            /*
            List<ReservaMod> Us = Asql.getReserva().listarDetalles();
            msj = "Bienvenido: ";
            for (ReservaMod s : Us) {
            System.out.println(s.toString());
            }
             */

            AdministradorSql Asql = new AdministradorSql();

            /*String cedula = "1013579";
            String nombre = "Diana Ardila";
            String pass = "7110eda4d09e062aa5e4a390b0a572ac0d2c0220";
            int rol = 1;*/
            
            
            HabitaMod u = Asql.getHabita().obtener("H001");
            msj= u.getNumero();
            /*Integer id = 1;
            List<AsignaPerMod> AsPer = Asql.getAsignaPer().perSesion("1070949");
            for(AsignaPerMod ap: AsPer){
                System.out.println(ap.toString());
            }*/
            
        } catch (SQLException ex) {
            msj = "error sql " + ex;
        } catch (Exception ex) {
            msj = "error " + ex;
        }
        System.out.println(msj);

    }
}
