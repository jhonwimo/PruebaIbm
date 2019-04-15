/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wilberthmoreno
 */
@Entity
@Table( name = "tbl_tarjetas" )
public class TblTarjetas implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id

    @GeneratedValue( strategy = IDENTITY )
    private Integer idtarjeta;

    private Long numerotarjeta;
    private Integer ccv;
    private String tipotarjeta;
    private Integer cedula;

    public Integer getIdtarjeta() {
        return idtarjeta;
    }

    public void setIdtarjeta( Integer idtarjeta ) {
        this.idtarjeta = idtarjeta;
    }

    public Long getNumerotarjeta() {
        return numerotarjeta;
    }

    public void setNumerotarjeta( Long numerotarjeta ) {
        this.numerotarjeta = numerotarjeta;
    }

    public Integer getCcv() {
        return ccv;
    }

    public void setCcv( Integer ccv ) {
        this.ccv = ccv;
    }

    public String getTipotarjeta() {
        return tipotarjeta;
    }

    public void setTipotarjeta( String tipotarjeta ) {
        this.tipotarjeta = tipotarjeta;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula( Integer cedula ) {
        this.cedula = cedula;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof TblTarjetas ) ) {
            return false;
        }
        TblTarjetas other = ( TblTarjetas ) object;
        if ( ( this.idtarjeta == null && other.idtarjeta != null ) || ( this.idtarjeta != null && !this.idtarjeta.equals( other.idtarjeta ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.TblCliente[ idCliente=" + idtarjeta + " ]";
    }

}
