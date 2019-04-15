/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.dao;

import com.ibm.model.TblCliente;
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
public class clienteDao {

    public JSONObject insertar( JSONObject params ) {
        boolean insert = true;
        JSONObject respuesta;
        respuesta = new JSONObject();
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            TblCliente cliente = new TblCliente();

            if ( params != null && params.length() > 0 ) {

                if ( params.has( "nombre" ) ) {
                    cliente.setNombre( params.getString( "nombre" ) );
                }
                if ( params.has( "direccion" ) ) {
                    cliente.setDireccion( params.getString( "direccion" ) );
                }
                if ( params.has( "telefono" ) ) {
                    cliente.setTelefono( params.getString( "telefono" ) );
                }

                if ( params.has( "cedula" ) ) {
                    cliente.setCedula( params.getInt( "cedula" ) );
                    insert = existecliente( params.getInt( "cedula" ) );
                }

            }
            if ( insert ) {
                session.save( cliente );
                session.getTransaction().commit();
                session.flush();
                respuesta.put( "success", true );
            }

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
            respuesta.put( "success", false );
            System.out.println( "com.ibm.dao.clienteDao.insertar()" );

        }

        return respuesta;
    }

    public JSONObject listar( int cedula ) {

        JSONArray data = null;
        JSONObject lista;

        Transaction tx = null;
        JSONObject respuesta;
        respuesta = new JSONObject();
        Query query;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            if ( cedula > 1 ) {
                query = session.createQuery( "from TblCliente where cedula = :cedula " );
                query.setParameter( "cedula", cedula );
            }
            else {
                query = session.createQuery( "from TblCliente  " );
            }

            List<TblCliente> List = query.list();

            session.flush();
            data = new JSONArray();
            for ( TblCliente cliente : List ) {
                lista = new JSONObject();
                lista.put( "idcliente", cliente.getIdCliente() );
                lista.put( "nombre", cliente.getNombre() );
                lista.put( "direccion", cliente.getDireccion() );
                lista.put( "telefono", cliente.getTelefono() );
                lista.put( "cedula", cliente.getCedula() );
                data.put( lista );
            }

//            respuesta.put( "success", true );
            respuesta.put( "data", data );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
            respuesta.put( "success", true );
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        }
        finally {

        }

        return respuesta;
    }

    public boolean existecliente( int cedula ) {

        boolean insert = true;
        Query query;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            query = session.createQuery( "from TblCliente where cedula = :cedula " );
            query.setParameter( "cedula", cedula );

            List<TblCliente> List = query.list();
            if ( List == null ) {
                insert = false;
            }

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return insert;

    }
}
