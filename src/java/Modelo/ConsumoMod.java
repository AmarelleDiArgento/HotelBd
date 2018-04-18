/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Amarelle
 */
public class ConsumoMod {
    private int id_cons;
    private String numero_hab;
    private String codigo_res;
    private String id_servicio;
    private String servicio;
    private double tarifa;
    private String fecha_hora;

    public ConsumoMod(int id_cons, String numero_hab, String codigo_res, String id_servicio, String fecha_hora) {
        this.id_cons = id_cons;
        this.numero_hab = numero_hab;
        this.codigo_res = codigo_res;
        this.id_servicio = id_servicio;
        this.fecha_hora = fecha_hora;
    }
    public ConsumoMod(String numero_hab, String codigo_res, String id_servicio, String fecha_hora) {
        this.numero_hab = numero_hab;
        this.codigo_res = codigo_res;
        this.id_servicio = id_servicio;
        this.fecha_hora = fecha_hora;
    }

    public ConsumoMod(int id_cons, String codigo_res, String numero_hab, String servicio, double tarifa, String fecha_hora) {
        this.id_cons = id_cons;
        this.numero_hab = numero_hab;
        this.codigo_res = codigo_res;
        this.servicio = servicio;
        this.tarifa = tarifa;
        this.fecha_hora = fecha_hora;
    }




    /**
     * @return the id_cons
     */
    public int getId_cons() {
        return id_cons;
    }

    /**
     * @param id_cons the id_cons to set
     */
    public void setId_cons(int id_cons) {
        this.id_cons = id_cons;
    }

    /**
     * @return the numero_hab
     */
    public String getNumero_hab() {
        return numero_hab;
    }

    /**
     * @param numero_hab the numero_hab to set
     */
    public void setNumero_hab(String numero_hab) {
        this.numero_hab = numero_hab;
    }

    /**
     * @return the codigo_res
     */
    public String getCodigo_res() {
        return codigo_res;
    }

    /**
     * @param codigo_res the codigo_res to set
     */
    public void setCodigo_res(String codigo_res) {
        this.codigo_res = codigo_res;
    }

    /**
     * @return the id_servicio
     */
    public String getId_servicio() {
        return id_servicio;
    }

    /**
     * @param id_servicio the id_servicio to set
     */
    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    /**
     * @return the servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the tarifa
     */
    public double getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return the fecha_hora
     */
    public String getFecha_hora() {
        return fecha_hora;
    }

    /**
     * @param fecha_hora the fecha_hora to set
     */
    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    

}
