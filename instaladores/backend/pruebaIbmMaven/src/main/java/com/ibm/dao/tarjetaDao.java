/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.dao;

import com.ibm.model.TblCliente;
import com.ibm.model.TblTarjetas;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateUtil;

/**
 *
 * @author wilberthmoreno
 */
public class tarjetaDao {

    public JSONObject insertar( JSONObject params ) {
        boolean inserta = true;
        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            TblTarjetas tarjeta = new TblTarjetas();

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if ( params != null && params.length() > 0 ) {

                if ( params.has( "numerotarjeta" ) ) {
                    tarjeta.setNumerotarjeta( params.getLong( "numerotarjeta" ) );
                }
                if ( params.has( "ccv" ) ) {
                    tarjeta.setCcv( params.getInt( "ccv" ) );
                }
                if ( params.has( "tipotarjeta" ) ) {
                    tarjeta.setTipotarjeta( params.getString( "tipotarjeta" ) );
                }
                if ( params.has( "cedula" ) ) {
                    tarjeta.setCedula( params.getInt( "cedula" ) );
                    inserta = existeNumeroTarjeta( params.getInt( "numerotarjeta" ) );
                }

            }

            if ( inserta ) {
                session.save( tarjeta );
                session.getTransaction().commit();
                session.flush();

            }
            else {
                respuesta.put( "msmError", "este numero de tarjeta ya existe" );
            }

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
            respuesta.put( "success", false );
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.

        }

        return respuesta;
    }

    public JSONObject listar( String cedula ) {
        // Configure the session factory

        JSONArray data = null;
        JSONObject lista;

        Transaction tx = null;
        JSONObject respuesta;
        respuesta = new JSONObject();
        Query query;
        StringBuilder idtar;
        idtar = new StringBuilder();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            // List<TblTarjetas> List = session.createQuery( "from TblTarjetas" ).list();
            if ( !cedula.isEmpty() ) {
                query = session.createQuery( " from TblTarjetas where cedula = :cedula " );
                query.setParameter( "cedula", Integer.parseInt( cedula ) );
                List<TblTarjetas> List = query.list();
                session.flush();
                data = new JSONArray();
                for ( TblTarjetas tarjeta : List ) {
                    lista = new JSONObject();
                    lista.put( "idtarjeta", tarjeta.getIdtarjeta() );

                    String nunid = tarjeta.getNumerotarjeta().toString();
                    idtar.append( nunid.subSequence( 0, 4 ) );
                    idtar.append( "  " );
                    idtar.append( nunid.subSequence( 4, 8 ) );
                    idtar.append( "  " );
                    idtar.append( nunid.subSequence( 8, 12 ) );
                    idtar.append( "  " );
                    idtar.append( nunid.subSequence( 12, 16 ) );

                    lista.put( "numerotarjeta", idtar.toString() );
                    lista.put( "ccv", tarjeta.getCcv() );
                    lista.put( "tipotarjeta", tarjeta.getTipotarjeta() );
                    lista.put( "cedula", tarjeta.getCedula() );
                    data.put( lista );
                }
            }

            respuesta.put( "data", data );

        }
        catch ( Exception ex ) {
            respuesta.put( "msmError", ex.getMessage() );
            respuesta.put( "success", true );
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        }

        return respuesta;
    }

    public boolean existeNumeroTarjeta( int numeroTar ) {

        boolean existe = true;
        Query query;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            query = session.createQuery( "from TblTarjetas where numerotarjeta = :numerotarjeta " );
            query.setParameter( "numerotarjeta", numeroTar );

            List<TblCliente> List = query.list();
            if ( List == null ) {
                existe = false;
            }

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
        finally {

        }

        return existe;
    }
}
