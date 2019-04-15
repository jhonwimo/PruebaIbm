/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.datos;

import com.ibm.model.TblAsesores;
import com.ibm.model.TblCliente;
import com.ibm.model.TblTarjetas;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author wilberthmoreno
 */
public class Ini {

    private static Ini instance = null;

    /**
     * Método constructor de la clase TblRolControl.
     */
    protected Ini() {

    }

    /**
     * Método que devuelve la instancia de la clase RestaurarPassControl, si no
     * se ha instanciado previamente, este método la instancia automáticamente.
     *
     * @return RestaurarPassControl
     */
    public static Ini getInstance() {

        if ( instance == null ) {
            instance = new Ini();
            arranque();

        }

        return instance;
    }

    private static void arranque() {

        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            TblCliente cliente = new TblCliente();

            cliente.setNombre( "jhon" );
            cliente.setDireccion( "cali valle" );
            cliente.setTelefono( "312827478" );
            cliente.setCedula( 14675472 );

            TblAsesores asesor = new TblAsesores();
            asesor.setNombre( "florindo" );
            asesor.setEspecialidad( "venta" );

            TblTarjetas tarjeta = new TblTarjetas();
            tarjeta.setNumerotarjeta( Long.parseLong( "3333333333333333" ) );
            tarjeta.setCcv( 1234 );
            tarjeta.setCedula( 14675472 );
            tarjeta.setTipotarjeta( "visa" );
            session.save( cliente );
            session.save( tarjeta );
            session.save( asesor );
            session.flush();
            session.getTransaction().commit();
            session.flush();

        }
        catch ( Exception ex ) {
            ex.printStackTrace();

            System.out.println( "com.ibm.dao.clienteDao.insertar()" );

        }

    }

}
