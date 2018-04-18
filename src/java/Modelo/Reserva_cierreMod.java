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
public class Reserva_cierreMod {
    private int codigo;
    private Date fecha_cierre;
    private String tipo_pago;
    private float monto_total;

    public Reserva_cierreMod(int codigo, Date fecha_cierre, String tipo_pago, float monto_total) {
        this.codigo = codigo;
        this.fecha_cierre = fecha_cierre;
        this.tipo_pago = tipo_pago;
        this.monto_total = monto_total;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the fecha_cierre
     */
    public Date getFecha_cierre() {
        return fecha_cierre;
    }

    /**
     * @param fecha_cierre the fecha_cierre to set
     */
    public void setFecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    /**
     * @return the tipo_pago
     */
    public String getTipo_pago() {
        return tipo_pago;
    }

    /**
     * @param tipo_pago the tipo_pago to set
     */
    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    /**
     * @return the monto_total
     */
    public float getMonto_total() {
        return monto_total;
    }

    /**
     * @param monto_total the monto_total to set
     */
    public void setMonto_total(float monto_total) {
        this.monto_total = monto_total;
    }


}



