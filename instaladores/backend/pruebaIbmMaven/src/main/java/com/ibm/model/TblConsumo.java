/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.model;

/**
 *
 * @author wilberthmoreno
 */
public class TblConsumo {

    private Integer idconsumo;
    private String fecha;
    private String descripcion;
    private double monto;
    private Integer idtarjeta;

    public Integer getIdconsumo() {
        return idconsumo;
    }

    public void setIdconsumo( Integer idconsumo ) {
        this.idconsumo = idconsumo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha( String fecha ) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto( double monto ) {
        this.monto = monto;
    }

    public Integer getIdtarjeta() {
        return idtarjeta;
    }

    public void setIdtarjeta( Integer idtarjeta ) {
        this.idtarjeta = idtarjeta;
    }

}
