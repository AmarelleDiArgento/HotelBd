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
public class AsignaMod {
    private String num_hab;
    private int codigo_res;
    private int num_ad;
    private int num_ni;

    public AsignaMod(String num_hab, int codigo_res, int num_ad, int num_ni) {
        this.num_hab = num_hab;
        this.codigo_res = codigo_res;
        this.num_ad = num_ad;
        this.num_ni = num_ni;
    }

    /**
     * @return the num_hab
     */
    public String getNum_hab() {
        return num_hab;
    }

    /**
     * @param num_hab the num_hab to set
     */
    public void setNum_hab(String num_hab) {
        this.num_hab = num_hab;
    }

    /**
     * @return the codigo_res
     */
    public int getCodigo_res() {
        return codigo_res;
    }

    /**
     * @param codigo_res the codigo_res to set
     */
    public void setCodigo_res(int codigo_res) {
        this.codigo_res = codigo_res;
    }

    /**
     * @return the num_ad
     */
    public int getNum_ad() {
        return num_ad;
    }

    /**
     * @param num_ad the num_ad to set
     */
    public void setNum_ad(int num_ad) {
        this.num_ad = num_ad;
    }

    /**
     * @return the num_ni
     */
    public int getNum_ni() {
        return num_ni;
    }

    /**
     * @param num_ni the num_ni to set
     */
    public void setNum_ni(int num_ni) {
        this.num_ni = num_ni;
    }
}
