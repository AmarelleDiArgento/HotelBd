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
public class ReservaMod {

    private int codigo;
    private String Habitacion;
    private String fecha_ingreso;
    private String fecha_egreso;
    private String cedula;
    private String nombre;
    private int num_personas;

    public ReservaMod(String fecha_ingreso, String fecha_egreso, String cedula, int num_personas) {
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.cedula = cedula;
        this.num_personas = num_personas;
    }

    public ReservaMod(int codigo, String fecha_ingreso, String fecha_egreso, String cedula, int num_personas) {
        this.codigo = codigo;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.cedula = cedula;
        this.num_personas = num_personas;
    }

    public ReservaMod(String Habitacion, String fecha_ingreso, String fecha_egreso, String cedula, int num_personas) {
        this.Habitacion = Habitacion;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.cedula = cedula;
        this.num_personas = num_personas;
    }

    public ReservaMod(int codigo, String Habitacion, String fecha_ingreso, String fecha_egreso, String cedula, int num_personas) {
        this.codigo = codigo;
        this.Habitacion = Habitacion;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.cedula = cedula;
        this.num_personas = num_personas;
    }

    public ReservaMod(int codigo, String Habitacion, String fecha_ingreso, String fecha_egreso, String cedula, String nombre, int num_personas) {
        this.codigo = codigo;
        this.Habitacion = Habitacion;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.cedula = cedula;
        this.nombre = nombre;
        this.num_personas = num_personas;
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
     * @return the Habitacion
     */
    public String getHabitacion() {
        return Habitacion;
    }

    /**
     * @param Habitacion the Habitacion to set
     */
    public void setHabitacion(String Habitacion) {
        this.Habitacion = Habitacion;
    }

    /**
     * @return the fecha_ingreso
     */
    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the fecha_egreso
     */
    public String getFecha_egreso() {
        return fecha_egreso;
    }

    /**
     * @param fecha_egreso the fecha_egreso to set
     */
    public void setFecha_egreso(String fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
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
     * @return the num_personas
     */
    public int getNum_personas() {
        return num_personas;
    }

    /**
     * @param num_personas the num_personas to set
     */
    public void setNum_personas(int num_personas) {
        this.num_personas = num_personas;
    }

}
