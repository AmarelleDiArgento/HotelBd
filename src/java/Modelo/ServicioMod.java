/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Amarelle
 */
public class ServicioMod {

    private int id;
    private String nombre;
    private int tarifa;

    public ServicioMod(String nombre, int tarifa) {
        this.nombre = nombre;
        this.tarifa = tarifa;
    }

    public ServicioMod(int id, String nombre, int tarifa) {
        this.id = id;
        this.nombre = nombre;
        this.tarifa = tarifa;
    
    
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tarifa
     */
    public int getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }
    
}
