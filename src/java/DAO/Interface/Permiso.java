/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import DAO.DAO;
import Modelo.PermisoMod;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface Permiso extends DAO<ResultSet, PermisoMod,  Integer> {

    List<PermisoMod> menu(String cedula);
    
}
