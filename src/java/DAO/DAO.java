/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Amarelle
 
 */
public interface DAO<R, O, I> {

    String insertar(O o);

    String modificar(O o);

    String eliminar(I id);

    O convertir(R rs) throws SQLException;

    O obtener(I id);
    
    List<O> listar();

}
