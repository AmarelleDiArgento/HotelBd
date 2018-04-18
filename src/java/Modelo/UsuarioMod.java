/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Amarelle
 */
public class UsuarioMod {

    private String cedula;
    private String nombre;
    private String pass;
    private int Rol_id;
    private String nRol;

    public UsuarioMod(String cedula, String nombre, int Rol_id, String nRol) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.Rol_id = Rol_id;
        this.nRol = nRol;
    }

    public UsuarioMod(String cedula, String nombre, String pass, int Rol_id) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.pass = pass;
        this.Rol_id = Rol_id;
    }

    @Override
    public String toString() {
        return "UsuarioMod{" + "cedula=" + cedula + ", nombre=" + nombre + ", pass=" + pass + ", Rol_id=" + Rol_id + '}';
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
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
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
     * @return the nRol
     */
    public String getnRol() {
        return nRol;
    }

    /**
     * @param nRol the nRol to set
     */
    public void setnRol(String nRol) {
        this.nRol = nRol;
    }


}
