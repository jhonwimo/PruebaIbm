/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.dao;

import com.ibm.model.TblAsesores;
import java.util.List;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateUtil;

/**
 *
 * @author wilberthmoreno
 */
public class asesorDao {

    public JSONObject insertar( JSONObject params ) {
        JSONObject respuesta;
        respuesta = new JSONObject();

        try {
            TblAsesores cliente = new TblAsesores();

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if ( params != null && params.length() > 0 ) {

                if ( params.has( "nombre" ) ) {
                    cliente.setNombre( params.getString( "nombre" ) );
                }
                if ( params.has( "especialidad" ) ) {
                    cliente.setEspecialidad( params.getString( "especialidad" ) );
                }

            }

            session.save( cliente );

            session.getTransaction().commit();
            session.flush();

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
            respuesta.put( "success", false );
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.

        }

        return respuesta;
    }

    public JSONObject listar() {
        // Configure the session factory

        JSONArray data;
        JSONObject lista;

        JSONObject respuesta;
        respuesta = new JSONObject();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            List<TblAsesores> List = session.createQuery( "from TblAsesores" ).list();
            session.flush();
            data = new JSONArray();
            for ( TblAsesores asesor : List ) {
                lista = new JSONObject();
                lista.put( "idasesoresbancarios", asesor.getIdAsesoresBancarios() );
                lista.put( "nombre", asesor.getNombre() );
                lista.put( "especialidad", asesor.getEspecialidad() );
                data.put( lista );
            }

            respuesta.put( "data", data );

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
            respuesta.put( "success", true );
            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.

        }

        return respuesta;
    }

}
