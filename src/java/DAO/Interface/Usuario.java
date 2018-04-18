/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import DAO.DAO;
import Modelo.UsuarioMod;
import java.sql.ResultSet;

/**
 *
 * @author Amarelle
 */
public interface Usuario extends DAO<ResultSet, UsuarioMod, String> {

    UsuarioMod login(String cedula, String pass);
}
