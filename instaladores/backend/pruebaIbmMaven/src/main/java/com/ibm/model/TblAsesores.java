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
@Table( name = "tbl_asesores" )

public class TblAsesores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = IDENTITY )
    private Integer idAsesor;

    private String nombre;

    private String especialidad;

    public TblAsesores() {
    }

    public TblAsesores( Integer idAsesoresBancarios ) {
        this.idAsesor = idAsesoresBancarios;
    }

    public TblAsesores( Integer idAsesoresBancarios, String strNombre, String strEspecialidad ) {
        this.idAsesor = idAsesoresBancarios;
        this.nombre = strNombre;
        this.especialidad = strEspecialidad;
    }

    public Integer getIdAsesoresBancarios() {
        return idAsesor;
    }

    public void setIdAsesoresBancarios( Integer idAsesoresBancarios ) {
        this.idAsesor = idAsesoresBancarios;
    }

    public Integer getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor( Integer idAsesor ) {
        this.idAsesor = idAsesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad( String especialidad ) {
        this.especialidad = especialidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( idAsesor != null ? idAsesor.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof TblAsesores ) ) {
            return false;
        }
        TblAsesores other = ( TblAsesores ) object;
        if ( ( this.idAsesor == null && other.idAsesor != null ) || ( this.idAsesor != null && !this.idAsesor.equals( other.idAsesor ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ibm.model.TblAsesoresBancarios[ idAsesoresBancarios=" + idAsesor + " ]";
    }

}
