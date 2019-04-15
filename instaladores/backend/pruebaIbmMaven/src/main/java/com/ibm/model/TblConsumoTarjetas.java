/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.model;

import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

/**
 *
 * @author wilberthmoreno
 */
public class TblConsumoTarjetas {

    @Id

    @GeneratedValue( strategy = IDENTITY )
    private Integer idclientetarjeta;

    private Integer cedula;
    private Integer numerotarjeta;

    private Integer idconsumo;
    private String fecha;
    private String descripcion;
    private double monto;

    public Integer getIdclientetarjeta() {
        return idclientetarjeta;
    }

    public void setIdclientetarjeta( Integer idclientetarjeta ) {
        this.idclientetarjeta = idclientetarjeta;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula( Integer cedula ) {
        this.cedula = cedula;
    }

    public Integer getNumerotarjeta() {
        return numerotarjeta;
    }

    public void setNumerotarjeta( Integer numerotarjeta ) {
        this.numerotarjeta = numerotarjeta;
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

}
