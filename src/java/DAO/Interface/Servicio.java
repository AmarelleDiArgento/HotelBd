/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

import DAO.DAO;
import Modelo.ServicioMod;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Amarelle
 */
public interface Servicio extends DAO<ResultSet, ServicioMod, Integer> {
    
}