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
@Table( name = "tbl_cliente" )

public class TblCliente implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id

    @GeneratedValue( strategy = IDENTITY )
    private Integer idCliente;
    private Integer cedula;
    private String nombre;

    private String direccion;
    private String telefono;

    public TblCliente() {
    }

    public TblCliente( Integer idCliente ) {
        this.idCliente = idCliente;
    }

    public TblCliente( Integer idCliente, String strNombre, String intTelefono ) {
        this.idCliente = idCliente;
        this.nombre = strNombre;
//        this.direccion = strDireccion;
        this.telefono = intTelefono;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente( Integer idCliente ) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion( String direccion ) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula( Integer cedula ) {
        this.cedula = cedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idCliente != null ? idCliente.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof TblCliente ) ) {
            return false;
        }
        TblCliente other = ( TblCliente ) object;
        if ( ( this.idCliente == null && other.idCliente != null ) || ( this.idCliente != null && !this.idCliente.equals( other.idCliente ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.TblCliente[ idCliente=" + idCliente + " ]";
    }

}
