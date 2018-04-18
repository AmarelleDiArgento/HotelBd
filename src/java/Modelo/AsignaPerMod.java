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
public class AsignaPerMod {

    private int Rol_id;
    private String rol;
    private int Permisos_id;
    private String tabla;
    private boolean leer;
    private boolean nuevo;
    private boolean modificar;
    private boolean eliminar;

    public AsignaPerMod(String tabla, boolean leer, boolean nuevo, boolean modificar, boolean eliminar) {
        this.tabla = tabla;
        this.leer = leer;
        this.nuevo = nuevo;
        this.modificar = modificar;
        this.eliminar = eliminar;
    }

    public AsignaPerMod(int Rol_id, String rol, int Permisos_id, String tabla, boolean leer, boolean nuevo, boolean modificar, boolean eliminar) {
        this.Rol_id = Rol_id;
        this.rol = rol;
        this.Permisos_id = Permisos_id;
        this.tabla = tabla;
        this.leer = leer;
        this.nuevo = nuevo;
        this.modificar = modificar;
        this.eliminar = eliminar;
    }

    public AsignaPerMod(int Rol_id, int Permisos_id, boolean leer, boolean nuevo, boolean modificar, boolean eliminar) {
        this.Rol_id = Rol_id;
        this.Permisos_id = Permisos_id;
        this.leer = leer;
        this.nuevo = nuevo;
        this.modificar = modificar;
        this.eliminar = eliminar;
    }

    @Override
    public String toString() {
        return "AsignaPerMod{" + "Rol_id=" + Rol_id + ", rol=" + rol + ", Permisos_id=" + Permisos_id + ", tabla=" + tabla + ", leer=" + leer + ", nuevo=" + nuevo + ", modificar=" + modificar + ", eliminar=" + eliminar + '}';
    }



    /**
     * @return the Rol_id
     */
    public int getRol_id() {
        return Rol_id;
    }


    /**
     * @param Rol_id the Rol_id to set
     */
    public void setRol_id(int Rol_id) {
        this.Rol_id = Rol_id;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the Permisos_id
     */
    public int getPermisos_id() {
        return Permisos_id;
    }

    /**
     * @param Permisos_id the Permisos_id to set
     */
    public void setPermisos_id(int Permisos_id) {
        this.Permisos_id = Permisos_id;
    }

    /**
     * @return the tabla
     */
    public String getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the leer
     */
    public boolean isLeer() {
        return leer;
    }

    /**
     * @param leer the leer to set
     */
    public void setLeer(boolean leer) {
        this.leer = leer;
    }

    /**
     * @return the nuevo
     */
    public boolean isNuevo() {
        return nuevo;
    }

    /**
     * @param nuevo the nuevo to set
     */
    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    /**
     * @return the modificar
     */
    public boolean isModificar() {
        return modificar;
    }

    /**
     * @param modificar the modificar to set
     */
    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    /**
     * @return the eliminar
     */
    public boolean isEliminar() {
        return eliminar;
    }

    /**
     * @param eliminar the eliminar to set
     */
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

}
