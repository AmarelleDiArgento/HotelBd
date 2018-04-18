/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import DAO.DAO;
import Modelo.AsignaPerMod;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface AsignaPer extends DAO<ResultSet, AsignaPerMod, Long> {

    List<AsignaPerMod> perSesion(String cedula);
    
    public List<AsignaPerMod> listar(int Rol) ;
        
}
