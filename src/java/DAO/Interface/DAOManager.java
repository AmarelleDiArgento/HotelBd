/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interface;

/**
 *
 * @author Amarelle
 */
public interface DAOManager  {
    Asigna getAsigna();
    Consumo getConsumo();
    Habita getHabita();
    Reserva getReserva();
    Reserva_cierre getReserva_cierre();
    Servicio getServicio();
    
    Usuario getUsuario();
    Rol getRol();
    AsignaPer getAsignaPer();
    Permiso getPermiso();
    String SHA(String  t );
    
}